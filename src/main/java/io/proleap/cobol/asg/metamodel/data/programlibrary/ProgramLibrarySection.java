/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.programlibrary;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.LibraryDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.LibraryDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.LibraryDescriptionEntryFormat2Context;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface ProgramLibrarySection extends CobolDivisionElement {

	LibraryDescriptionEntryExport addLibraryDescriptionEntryExport(LibraryDescriptionEntryFormat1Context ctx);

	LibraryDescriptionEntryImport addLibraryDescriptionEntryImport(LibraryDescriptionEntryFormat2Context ctx);

	LibraryDescriptionEntry createLibraryDescriptionEntry(LibraryDescriptionEntryContext ctx);

	List<LibraryDescriptionEntry> getLibraryDescriptionEntries();

	LibraryDescriptionEntry getLibraryDescriptionEntry(String name);

}
