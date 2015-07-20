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

package org.cobol85.applicationcontext;

import org.cobol85.preprocessor.Cobol85Preprocessor;

public class Cobol85GrammarContext {

	protected static Cobol85GrammarContext instance;

	public static Cobol85GrammarContext getInstance() {
		if (instance == null) {
			instance = new Cobol85GrammarContext();
		}

		return instance;
	}

	protected Cobol85Preprocessor cobol85Preprocessor;

	public Cobol85Preprocessor getCobol85Preprocessor() {
		return cobol85Preprocessor;
	}

	public void reset() {
		instance = null;
	}

	public void setCobol85Preprocessor(final Cobol85Preprocessor cobol85Preprocessor) {
		this.cobol85Preprocessor = cobol85Preprocessor;
	}
}
