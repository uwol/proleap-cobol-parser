/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data;

import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat3Context;
import io.proleap.cobol.Cobol85Parser.DataDivisionBodyContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;

public interface DataDivision extends CobolDivision {

	DataDescriptionEntry addDataDescriptionEntry(DataDescriptionEntryFormat1Context ctx);

	DataDescriptionEntry addDataDescriptionEntry(DataDescriptionEntryFormat2Context ctx);

	DataDescriptionEntry addDataDescriptionEntry(DataDescriptionEntryFormat3Context ctx);

	DataDivisionBody addDataDivisionBody(DataDivisionBodyContext ctx);

	DataDescriptionEntry getDataDescriptionEntry(String name);

	DataDivisionBody getDataDivisionBody();

	void setDataDivisionBody(DataDivisionBody dataDivisionBody);

}
