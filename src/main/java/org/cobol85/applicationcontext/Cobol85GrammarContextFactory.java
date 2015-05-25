/*
Copyright (C) 2015 u.wol@wwu.de

This file is part of cobol85grammar.

cobol85grammar is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

cobol85grammar is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with cobol85grammar. If not, see <http://www.gnu.org/licenses/>.
 */

package org.cobol85.applicationcontext;

import org.cobol85.preprocessor.impl.Cobol85PreprocessorImpl;

public class Cobol85GrammarContextFactory {

	public static void configureDefaultApplicationContext() {

		// reset application context
		Cobol85GrammarContext.getInstance().reset();

		Cobol85GrammarContext.getInstance().setCobol85Preprocessor(
				new Cobol85PreprocessorImpl());
	}
}
