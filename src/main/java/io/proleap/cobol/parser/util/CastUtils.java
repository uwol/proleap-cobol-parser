/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.util;

import java.util.Collection;

import io.proleap.cobol.parser.metamodel.ModelElement;
import io.proleap.cobol.parser.metamodel.Paragraph;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry;

public class CastUtils {

	public static DataDescriptionEntry castDataDescriptionEntry(final Collection<ModelElement> elements) {
		DataDescriptionEntry result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final DataDescriptionEntry dataDescriptionEntry = castDataDescriptionEntry(element);

				if (dataDescriptionEntry != null) {
					result = dataDescriptionEntry;
					break;
				}
			}
		}

		return result;
	}

	public static DataDescriptionEntry castDataDescriptionEntry(final ModelElement element) {
		return element != null && element instanceof DataDescriptionEntry ? (DataDescriptionEntry) element : null;
	}

	public static Paragraph castParagraph(final Collection<ModelElement> elements) {
		Paragraph result = null;

		if (elements != null) {
			for (final ModelElement element : elements) {
				final Paragraph paragraph = castParagraph(element);

				if (paragraph != null) {
					result = paragraph;
					break;
				}
			}
		}

		return result;
	}

	public static Paragraph castParagraph(final ModelElement element) {
		return element != null && element instanceof Paragraph ? (Paragraph) element : null;
	}
}
