/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.asg.params.CobolParserParams;
import io.proleap.cobol.preprocessor.CobolPreprocessor;
import io.proleap.cobol.preprocessor.sub.CobolLine;
import io.proleap.cobol.preprocessor.sub.document.CobolDocumentParser;
import io.proleap.cobol.preprocessor.sub.document.impl.CobolDocumentParserImpl;
import io.proleap.cobol.preprocessor.sub.line.reader.CobolLineReader;
import io.proleap.cobol.preprocessor.sub.line.reader.impl.CobolLineReaderImpl;
import io.proleap.cobol.preprocessor.sub.line.rewriter.CobolCommentEntriesMarker;
import io.proleap.cobol.preprocessor.sub.line.rewriter.CobolInlineCommentEntriesNormalizer;
import io.proleap.cobol.preprocessor.sub.line.rewriter.CobolLineIndicatorProcessor;
import io.proleap.cobol.preprocessor.sub.line.rewriter.impl.CobolCommentEntriesMarkerImpl;
import io.proleap.cobol.preprocessor.sub.line.rewriter.impl.CobolInlineCommentEntriesNormalizerImpl;
import io.proleap.cobol.preprocessor.sub.line.rewriter.impl.CobolLineIndicatorProcessorImpl;
import io.proleap.cobol.preprocessor.sub.line.writer.CobolLineWriter;
import io.proleap.cobol.preprocessor.sub.line.writer.impl.CobolLineWriterImpl;

public class CobolPreprocessorImpl implements CobolPreprocessor {

	private final static Logger LOG = LoggerFactory.getLogger(CobolPreprocessorImpl.class);

	protected CobolCommentEntriesMarker createCommentEntriesMarker() {
		return new CobolCommentEntriesMarkerImpl();
	}

	protected CobolDocumentParser createDocumentParser() {
		return new CobolDocumentParserImpl();
	}

	protected CobolInlineCommentEntriesNormalizer createInlineCommentEntriesNormalizer() {
		return new CobolInlineCommentEntriesNormalizerImpl();
	}

	protected CobolLineIndicatorProcessor createLineIndicatorProcessor() {
		return new CobolLineIndicatorProcessorImpl();
	}

	protected CobolLineReader createLineReader() {
		return new CobolLineReaderImpl();
	}

	protected CobolLineWriter createLineWriter() {
		return new CobolLineWriterImpl();
	}

	protected String parseDocument(final List<CobolLine> lines, final CobolParserParams params) {
		final String code = createLineWriter().serialize(lines);
		final String result = createDocumentParser().processLines(code, params);
		return result;
	}

	@Override
	public String process(final File cobolFile, final CobolParserParams params) throws IOException {
		final Charset charset = params.getCharset();

		LOG.info("Preprocessing file {} with line format {} and charset {}.", cobolFile.getName(), params.getFormat(),
				charset);

		final String cobolFileContent = Files.readString(cobolFile.toPath(), charset);
		final String result = process(cobolFileContent, params);
		return result;
	}

	@Override
	public String process(final String cobolCode, final CobolParserParams params) {
		final List<CobolLine> lines = readLines(cobolCode, params);
		final List<CobolLine> rewrittenLines = rewriteLines(lines);
		final String result = parseDocument(rewrittenLines, params);
		return result;
	}

	protected List<CobolLine> readLines(final String cobolCode, final CobolParserParams params) {
		final List<CobolLine> lines = createLineReader().processLines(cobolCode, params);
		return lines;
	}

	/**
	 * Normalizes lines of given COBOL source code, so that comment entries can be
	 * parsed and lines have a unified line format.
	 */
	protected List<CobolLine> rewriteLines(final List<CobolLine> lines) {
		final List<CobolLine> lineIndicatorProcessedLines = createLineIndicatorProcessor().processLines(lines);
		final List<CobolLine> normalizedInlineCommentEntriesLines = createInlineCommentEntriesNormalizer()
				.processLines(lineIndicatorProcessedLines);
		final List<CobolLine> result = createCommentEntriesMarker().processLines(normalizedInlineCommentEntriesLines);
		return result;
	}
}
