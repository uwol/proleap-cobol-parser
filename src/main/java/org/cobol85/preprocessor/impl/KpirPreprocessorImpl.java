/*
Copyright (C) 2015 u.wol@wwu.de

This file is part of cobol85grammar.

cobol85grammar is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

cobol85grammar is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with cobol85grammar. If not, see <http://www.gnu.org/licenses/>.
 */

package org.cobol85.preprocessor.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cobol85.preprocessor.KpirPreprocessor;

public class KpirPreprocessorImpl implements KpirPreprocessor {

	private final static Logger LOG = LogManager.getLogger(KpirPreprocessorImpl.class);

	protected final static String NEWLINE = "\n";

	@Override
	public String process(final File inputFile) throws IOException {
		LOG.info("Preprocessing file {}.", inputFile.getName());

		final InputStream inputStream = new FileInputStream(inputFile);
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		final BufferedReader bufferedInputStreamReader = new BufferedReader(inputStreamReader);
		final StringBuffer outputBuffer = new StringBuffer();

		String line = null;

		while ((line = bufferedInputStreamReader.readLine()) != null) {
			outputBuffer.append(line + NEWLINE);
		}

		bufferedInputStreamReader.close();

		final String result = process(outputBuffer.toString());
		return result;
	}

	@Override
	public String process(final String input) {
		final String kpirProcessed = input.replace("KPIR  ", "");
		return kpirProcessed;
	}

}
