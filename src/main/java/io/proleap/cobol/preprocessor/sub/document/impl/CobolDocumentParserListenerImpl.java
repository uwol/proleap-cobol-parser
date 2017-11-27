/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.document.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85PreprocessorBaseListener;
import io.proleap.cobol.Cobol85PreprocessorParser;
import io.proleap.cobol.Cobol85PreprocessorParser.CobolWordContext;
import io.proleap.cobol.Cobol85PreprocessorParser.CopySourceContext;
import io.proleap.cobol.Cobol85PreprocessorParser.LiteralContext;
import io.proleap.cobol.Cobol85PreprocessorParser.ReplaceClauseContext;
import io.proleap.cobol.Cobol85PreprocessorParser.ReplacingPhraseContext;
import io.proleap.cobol.preprocessor.CobolPreprocessor;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolDialect;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import io.proleap.cobol.preprocessor.impl.CobolPreprocessorImpl;
import io.proleap.cobol.preprocessor.sub.CobolLine;
import io.proleap.cobol.preprocessor.sub.util.StringUtils;
import io.proleap.cobol.preprocessor.sub.util.TokenUtils;

/**
 * ANTLR visitor, which preprocesses a given COBOL program by executing COPY and
 * REPLACE statements.
 */
public class CobolDocumentParserListenerImpl extends Cobol85PreprocessorBaseListener {

	private final static Logger LOG = LogManager.getLogger(CobolDocumentParserListenerImpl.class);

	private final Stack<CobolDocumentContext> contexts = new Stack<CobolDocumentContext>();

	private final List<File> copyBooks;

	private final CobolDialect dialect;

	private final CobolSourceFormatEnum format;

	private final BufferedTokenStream tokens;

	public CobolDocumentParserListenerImpl(final List<File> copyBooks, final CobolSourceFormatEnum format,
			final CobolDialect dialect, final BufferedTokenStream tokens) {
		this.copyBooks = copyBooks;
		this.dialect = dialect;
		this.tokens = tokens;
		this.format = format;

		contexts.push(new CobolDocumentContext());
	}

	protected String buildLines(final String text, final String linePrefix) {
		final StringBuffer sb = new StringBuffer(text.length());
		final Scanner scanner = new Scanner(text);
		boolean firstLine = true;

		while (scanner.hasNextLine()) {
			if (!firstLine) {
				sb.append(CobolPreprocessor.NEWLINE);
			}

			final String line = scanner.nextLine();
			final String trimmedLine = line.trim();
			final String prefixedLine = linePrefix + CobolPreprocessor.WS + trimmedLine;
			final String suffixedLine = prefixedLine.replaceAll("(?i)(end-exec)",
					"$1 " + CobolPreprocessor.EXEC_END_TAG);

			sb.append(suffixedLine);
			firstLine = false;
		}

		scanner.close();
		return sb.toString();
	}

	public CobolDocumentContext context() {
		return contexts.peek();
	}

	@Override
	public void enterCompilerOptions(final Cobol85PreprocessorParser.CompilerOptionsContext ctx) {
		// push a new context for COMPILER OPTIONS terminals
		push();
	}

	@Override
	public void enterCopyStatement(final Cobol85PreprocessorParser.CopyStatementContext ctx) {
		// push a new context for COPY terminals
		push();
	}

	@Override
	public void enterEjectStatement(final Cobol85PreprocessorParser.EjectStatementContext ctx) {
		push();
	}

	@Override
	public void enterExecCicsStatement(final Cobol85PreprocessorParser.ExecCicsStatementContext ctx) {
		// push a new context for SQL terminals
		push();
	}

	@Override
	public void enterExecSqlImsStatement(final Cobol85PreprocessorParser.ExecSqlImsStatementContext ctx) {
		// push a new context for SQL IMS terminals
		push();
	}

	@Override
	public void enterExecSqlStatement(final Cobol85PreprocessorParser.ExecSqlStatementContext ctx) {
		// push a new context for SQL terminals
		push();
	}

	@Override
	public void enterReplaceArea(final Cobol85PreprocessorParser.ReplaceAreaContext ctx) {
		push();
	}

	@Override
	public void enterReplaceByStatement(final Cobol85PreprocessorParser.ReplaceByStatementContext ctx) {
		push();
	}

	@Override
	public void enterReplaceOffStatement(final Cobol85PreprocessorParser.ReplaceOffStatementContext ctx) {
		push();
	}

	@Override
	public void enterSkipStatement(final Cobol85PreprocessorParser.SkipStatementContext ctx) {
		push();
	}

	@Override
	public void enterTitleStatement(final Cobol85PreprocessorParser.TitleStatementContext ctx) {
		push();
	}

	@Override
	public void exitCompilerOptions(final Cobol85PreprocessorParser.CompilerOptionsContext ctx) {
		// throw away COMPILER OPTIONS terminals
		pop();
	}

	@Override
	public void exitCopyStatement(final Cobol85PreprocessorParser.CopyStatementContext ctx) {
		// throw away COPY terminals
		pop();

		// a new context for the copy book content
		push();

		/*
		 * replacement phrase
		 */
		for (final ReplacingPhraseContext replacingPhrase : ctx.replacingPhrase()) {
			context().storeReplaceablesAndReplacements(replacingPhrase.replaceClause());
		}

		/*
		 * copy the copy book
		 */
		final CopySourceContext copySource = ctx.copySource();

		if (copyBooks == null || copyBooks.isEmpty()) {
			LOG.warn("Could not identify copy book {} due to missing copy books.", copySource.getText());
		} else {
			final String fileContent = getCopyBookContent(copySource, copyBooks, dialect, format);

			if (fileContent != null) {
				context().write(fileContent + CobolPreprocessor.NEWLINE);
				context().replaceReplaceablesByReplacements(tokens);
			}
		}

		final String content = context().read();
		pop();

		context().write(content);
	}

	@Override
	public void exitEjectStatement(final Cobol85PreprocessorParser.EjectStatementContext ctx) {
		// throw away eject statement
		pop();
	}

	@Override
	public void exitExecCicsStatement(final Cobol85PreprocessorParser.ExecCicsStatementContext ctx) {
		// throw away EXEC CICS terminals
		pop();

		// a new context for the CICS statement
		push();

		/*
		 * text
		 */
		final String text = TokenUtils.getTextIncludingHiddenTokens(ctx, tokens);
		final String linePrefix = CobolLine.blankSequenceArea(format) + CobolPreprocessor.EXEC_CICS_TAG;
		final String lines = buildLines(text, linePrefix);

		context().write(lines);

		final String content = context().read();
		pop();

		context().write(content);
	}

	@Override
	public void exitExecSqlImsStatement(final Cobol85PreprocessorParser.ExecSqlImsStatementContext ctx) {
		// throw away EXEC SQLIMS terminals
		pop();

		// a new context for the SQLIMS statement
		push();

		/*
		 * text
		 */
		final String text = TokenUtils.getTextIncludingHiddenTokens(ctx, tokens);
		final String linePrefix = CobolLine.blankSequenceArea(format) + CobolPreprocessor.EXEC_SQLIMS_TAG;
		final String lines = buildLines(text, linePrefix);

		context().write(lines);

		final String content = context().read();
		pop();

		context().write(content);
	}

	@Override
	public void exitExecSqlStatement(final Cobol85PreprocessorParser.ExecSqlStatementContext ctx) {
		// throw away EXEC SQL terminals
		pop();

		// a new context for the SQL statement
		push();

		/*
		 * text
		 */
		final String text = TokenUtils.getTextIncludingHiddenTokens(ctx, tokens);
		final String linePrefix = CobolLine.blankSequenceArea(format) + CobolPreprocessor.EXEC_SQL_TAG;
		final String lines = buildLines(text, linePrefix);

		context().write(lines);

		final String content = context().read();
		pop();

		context().write(content);
	}

	@Override
	public void exitReplaceArea(final Cobol85PreprocessorParser.ReplaceAreaContext ctx) {
		/*
		 * replacement phrase
		 */
		final List<ReplaceClauseContext> replaceClauses = ctx.replaceByStatement().replaceClause();
		context().storeReplaceablesAndReplacements(replaceClauses);

		context().replaceReplaceablesByReplacements(tokens);
		final String content = context().read();

		pop();
		context().write(content);
	}

	@Override
	public void exitReplaceByStatement(final Cobol85PreprocessorParser.ReplaceByStatementContext ctx) {
		// throw away terminals
		pop();
	}

	@Override
	public void exitReplaceOffStatement(final Cobol85PreprocessorParser.ReplaceOffStatementContext ctx) {
		// throw away REPLACE OFF terminals
		pop();
	}

	@Override
	public void exitSkipStatement(final Cobol85PreprocessorParser.SkipStatementContext ctx) {
		// throw away skip statement
		pop();
	}

	@Override
	public void exitTitleStatement(final Cobol85PreprocessorParser.TitleStatementContext ctx) {
		// throw away title statement
		pop();
	}

	protected String getCopyBookContent(final CopySourceContext copySource, final List<File> copyBooks,
			final CobolDialect dialect, final CobolSourceFormatEnum format) {
		final File copyBook = identifyCopyBook(copySource, copyBooks);
		String result;

		if (copyBook == null) {
			LOG.warn("Copy book {} not found in copy books {}.", copySource.getText(), copyBooks);
			result = null;
		} else {
			try {
				result = new CobolPreprocessorImpl().process(copyBook, copyBooks, format, dialect);
			} catch (final IOException e) {
				result = null;
				LOG.warn(e.getMessage());
			}
		}

		return result;
	}

	/**
	 * Identifies a copy book by its name and directory.
	 */
	protected File identifyCopyBook(final CopySourceContext copySource, final List<File> copyBooks) {
		final File result;

		if (copySource.cobolWord() != null) {
			result = identifyCopyBookByCobolWord(copyBooks, copySource.cobolWord());
		} else if (copySource.literal() != null) {
			result = identifyCopyBookByLiteral(copyBooks, copySource.literal());
		} else {
			LOG.warn("unknown copy source type {}", copySource);
			result = null;
		}

		return result;
	}

	protected File identifyCopyBookByCobolWord(final List<File> copyBooks, final CobolWordContext ctx) {
		final String copyBookIdentifier = ctx.getText();
		File result = null;

		for (final File copyBook : copyBooks) {
			final String baseName = FilenameUtils.getBaseName(copyBook.getName());
			final boolean matchingBaseName = copyBookIdentifier.toLowerCase().equals(baseName.toLowerCase());

			if (matchingBaseName) {
				result = copyBook;
			}
		}

		return result;
	}

	protected File identifyCopyBookByLiteral(final List<File> copyBooks, final LiteralContext ctx) {
		final String copyBookIdentifier = ctx.getText();
		final String copyBookIdentifierCleaned = StringUtils.trimQuotes(copyBookIdentifier);
		final String copyBookIdentifierPathString = normalizeCopyBookPath(Paths.get(copyBookIdentifierCleaned));
		File result = null;

		for (final File file : copyBooks) {
			final String filePathString = normalizeCopyBookPath(file.toPath());
			final boolean matching = filePathString.endsWith(copyBookIdentifierPathString);

			if (matching) {
				result = file;
				break;
			}
		}

		return result;
	}

	protected String normalizeCopyBookPath(final Path copBookPath) {
		final Path normalizedPath = copBookPath.normalize();
		final String result = normalizedPath.toString().toLowerCase();
		return result;
	}

	/**
	 * Pops the current preprocessing context from the stack.
	 */
	protected CobolDocumentContext pop() {
		return contexts.pop();
	}

	/**
	 * Pushes a new preprocessing context onto the stack.
	 */
	protected CobolDocumentContext push() {
		return contexts.push(new CobolDocumentContext());
	}

	@Override
	public void visitTerminal(final TerminalNode node) {
		final int tokPos = node.getSourceInterval().a;
		context().write(TokenUtils.getHiddenTokensToLeft(tokPos, tokens));

		if (!TokenUtils.isEOF(node)) {
			final String text = node.getText();
			context().write(text);
		}
	}
}
