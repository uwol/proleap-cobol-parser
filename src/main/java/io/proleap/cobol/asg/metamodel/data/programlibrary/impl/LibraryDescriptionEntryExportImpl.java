/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.programlibrary.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.LibraryAttributeClauseFormat1Context;
import io.proleap.cobol.Cobol85Parser.LibraryDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.LibraryEntryProcedureClauseFormat1Context;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ExportAttribute;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ExportEntryProcedure;
import io.proleap.cobol.asg.metamodel.data.programlibrary.LibraryDescriptionEntryExport;

public class LibraryDescriptionEntryExportImpl extends LibraryDescriptionEntryImpl
		implements LibraryDescriptionEntryExport {

	private final static Logger LOG = LogManager.getLogger(LibraryDescriptionEntryExportImpl.class);

	protected final LibraryDescriptionEntryFormat1Context ctx;

	protected ExportAttribute exportAttribute;

	protected ExportEntryProcedure exportEntryProcedure;

	public LibraryDescriptionEntryExportImpl(final String name, final ProgramUnit programUnit,
			final LibraryDescriptionEntryFormat1Context ctx) {
		super(name, programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ExportAttribute addExportAttribute(final LibraryAttributeClauseFormat1Context ctx) {
		ExportAttribute result = (ExportAttribute) getASGElement(ctx);

		if (result == null) {
			result = new ExportAttributeImpl(programUnit, ctx);

			/*
			 * sharing
			 */
			if (ctx.SHARING() != null) {
				final ExportAttribute.Sharing sharing;

				if (ctx.DONTCARE() != null) {
					sharing = ExportAttribute.Sharing.DONT_CARE;
				} else if (ctx.PRIVATE() != null) {
					sharing = ExportAttribute.Sharing.PRIVATE;
				} else if (ctx.SHAREDBYRUNUNIT() != null) {
					sharing = ExportAttribute.Sharing.SHARED_BY_RUN_UNIT;
				} else if (ctx.SHAREDBYALL() != null) {
					sharing = ExportAttribute.Sharing.SHARED_BY_ALL;
				} else {
					LOG.warn("unknown sharing at {}", ctx);
					sharing = null;
				}

				result.setSharing(sharing);
			}

			exportAttribute = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ExportEntryProcedure addExportEntryProcedure(final LibraryEntryProcedureClauseFormat1Context ctx) {
		ExportEntryProcedure result = (ExportEntryProcedure) getASGElement(ctx);

		if (result == null) {
			result = new ExportEntryProcedureImpl(programUnit, ctx);

			/*
			 * program name
			 */
			final Call programCall = createCall(ctx.programName());
			result.setProgramCall(programCall);

			/*
			 * for
			 */
			if (ctx.libraryEntryProcedureForClause() != null) {
				result.addForClause(ctx.libraryEntryProcedureForClause());
			}

			exportEntryProcedure = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ExportAttribute getExportAttribute() {
		return exportAttribute;
	}

	@Override
	public ExportEntryProcedure getExportEntryProcedure() {
		return exportEntryProcedure;
	}

	@Override
	public LibraryDescriptionEntryType getLibraryDescriptionEntryType() {
		return LibraryDescriptionEntryType.EXPORT;
	}
}
