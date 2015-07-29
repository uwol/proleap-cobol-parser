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

package org.cobol85.runner;

import java.io.File;
import java.io.IOException;

import org.cobol85.preprocessor.Cobol85Preprocessor.Cobol85Format;

public interface Cobol85ParseTestRunner {

	void parseDirectory(File inputDirectory, final Cobol85Format[] formats) throws IOException;

	void parseFile(File inputFile, Cobol85Format[] formats) throws IOException;

	void parseString(String inputString, File libDirectory, Cobol85Format[] formats);
}
