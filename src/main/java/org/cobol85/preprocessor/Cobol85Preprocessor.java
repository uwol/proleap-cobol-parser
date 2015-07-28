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

package org.cobol85.preprocessor;

import java.io.File;
import java.io.IOException;

public interface Cobol85Preprocessor {

	public enum Cobol85Format {

		/**
		 * Fixed format, standard ANSI / IBM reference. Each line exactly 80
		 * chars.<br />
		 * 1-6 : sequence area<br />
		 * 7: indicator field<br />
		 * 8-12: area A<br />
		 * 13-72: area B<br />
		 * 73-80: comments<br />
		 */
		FIXED(".{6}[ABCdD\\-/* ].{5}.{60}.{8}"),

		/**
		 * HP Tandem format.<br />
		 * 1: indicator field<br />
		 * 2-5: area A<br />
		 * 6-132: area B<br />
		 */
		TANDEM("[ABCdD\\-/* ].+"),

		/**
		 * Variable format.<br />
		 * 1-6 : sequence area<br />
		 * 7: indicator field<br />
		 * 8-12: area A<br />
		 * 13-*: area B<br />
		 */
		VARIABLE("[0-9a-zA-Z]{6}[ABCdD\\-/* ].*");

		public final String regex;

		Cobol85Format(final String regex) {
			this.regex = regex;
		}
	}

	boolean hasLineFormat(String line, Cobol85Format format);

	String process(File inputFile, File libDirectory) throws IOException;

	String process(String input, File libDirectory);
}