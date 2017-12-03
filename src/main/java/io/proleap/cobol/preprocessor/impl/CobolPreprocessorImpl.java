/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;

import io.proleap.cobol.asg.params.CobolParserParams;
import io.proleap.cobol.asg.params.impl.CobolParserParamsImpl;
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

	private final static Logger LOG = LogManager.getLogger(CobolPreprocessorImpl.class);

	protected CobolCommentEntriesMarker createCommentEntriesMarker() {
		return new CobolCommentEntriesMarkerImpl();
	}

	protected CobolParserParams createDefaultParams() {
		final CobolParserParams result = new CobolParserParamsImpl();
		return result;
	}

	protected CobolParserParams createDefaultParams(final File cobolFile) {
		final CobolParserParams result = createDefaultParams();

		final File copyBooksDirectory = cobolFile.getParentFile();
		result.setCopyBookDirectories(Lists.newArrayList(copyBooksDirectory));

		return result;
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

	protected String parseDocument(final List<CobolLine> lines, final CobolSourceFormatEnum format,
			final CobolParserParams params) {
		final String code = createLineWriter().serialize(lines);
		final String result = createDocumentParser().processLines(code, format, params);
		return result;
	}

	@Override
	public String process(final File cobolFile, final CobolSourceFormatEnum format) throws IOException {
		return process(cobolFile, format, createDefaultParams(cobolFile));
	}

	@Override
	public String process(final File cobolFile, final CobolSourceFormatEnum format, final CobolParserParams params)
			throws IOException {
		final Charset charset = params.getCharset();

		LOG.info("Preprocessing file {} with line format {} and charset {}.", cobolFile.getName(), format, charset);

		final InputStream inputStream = new FileInputStream(cobolFile);
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
		final BufferedReader bufferedInputStreamReader = new BufferedReader(inputStreamReader);
		final StringBuffer outputBuffer = new StringBuffer();

		String line = null;

		while ((line = bufferedInputStreamReader.readLine()) != null) {
			outputBuffer.append(line + NEWLINE);
		}

		bufferedInputStreamReader.close();

		final String result = process(outputBuffer.toString(), format, params);
		return result;
	}

	@Override
	public String process(final String cobolSourceCode, final CobolSourceFormatEnum format) {
		return process(cobolSourceCode, format, createDefaultParams());
	}

	@Override
	public String process(final String cobolCode, final CobolSourceFormatEnum format, final CobolParserParams params) {
		final List<CobolLine> lines = readLines(cobolCode, format, params);
		final List<CobolLine> rewrittenLines = rewriteLines(lines);
		final String result = parseDocument(rewrittenLines, format, params);
		return result;
	}

	protected List<CobolLine> readLines(final String cobolCode, final CobolSourceFormatEnum format,
			final CobolParserParams params) {
		final List<CobolLine> lines = createLineReader().processLines(cobolCode, format, params);
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
