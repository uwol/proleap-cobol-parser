/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.resolver.impl;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.AlphabetNameContext;
import io.proleap.cobol.Cobol85Parser.AssignmentNameContext;
import io.proleap.cobol.Cobol85Parser.ClassNameContext;
import io.proleap.cobol.Cobol85Parser.CobolWordContext;
import io.proleap.cobol.Cobol85Parser.ComputerNameContext;
import io.proleap.cobol.Cobol85Parser.ConditionNameContext;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat3Context;
import io.proleap.cobol.Cobol85Parser.DataNameContext;
import io.proleap.cobol.Cobol85Parser.FileControlEntryContext;
import io.proleap.cobol.Cobol85Parser.FileDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.FileNameContext;
import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.Cobol85Parser.IndexNameContext;
import io.proleap.cobol.Cobol85Parser.LocalNameContext;
import io.proleap.cobol.Cobol85Parser.ObjectComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.ParagraphContext;
import io.proleap.cobol.Cobol85Parser.ParagraphNameContext;
import io.proleap.cobol.Cobol85Parser.ProcedureNameContext;
import io.proleap.cobol.Cobol85Parser.ProgramIdParagraphContext;
import io.proleap.cobol.Cobol85Parser.ProgramNameContext;
import io.proleap.cobol.Cobol85Parser.QualifiedDataNameContext;
import io.proleap.cobol.Cobol85Parser.ReportNameContext;
import io.proleap.cobol.Cobol85Parser.SelectClauseContext;
import io.proleap.cobol.Cobol85Parser.SourceComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.SystemNameContext;
import io.proleap.cobol.parser.resolver.NameResolver;

public class NameResolverImpl implements NameResolver {

	private final static Logger LOG = LogManager.getLogger(NameResolverImpl.class);

	public String determineName(final AlphabetNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final AssignmentNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final ClassNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final CobolWordContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final ComputerNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final ConditionNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final DataDescriptionEntryFormat1Context ctx) {
		final String result = ctx != null ? determineName(ctx.dataName()) : null;
		return result;
	}

	public String determineName(final DataDescriptionEntryFormat2Context ctx) {
		final String result = ctx != null ? determineName(ctx.dataName()) : null;
		return result;
	}

	public String determineName(final DataDescriptionEntryFormat3Context ctx) {
		final String result = ctx != null ? determineName(ctx.conditionName()) : null;
		return result;
	}

	public String determineName(final DataNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final FileControlEntryContext ctx) {
		final String result = ctx != null ? determineName(ctx.selectClause()) : null;
		return result;
	}

	public String determineName(final FileDescriptionEntryContext ctx) {
		final String result = ctx != null ? determineName(ctx.fileName()) : null;
		return result;
	}

	public String determineName(final FileNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final IdentifierContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final IndexNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final LocalNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final ObjectComputerParagraphContext ctx) {
		final String result = ctx != null ? determineName(ctx.computerName()) : null;
		return result;
	}

	public String determineName(final ParagraphContext ctx) {
		final String result = determineName(ctx.paragraphName());
		return result;
	}

	public String determineName(final ParagraphNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	@Override
	public String determineName(final ParseTree ctx) {
		final String result;

		if (ctx instanceof AlphabetNameContext) {
			result = determineName((AlphabetNameContext) ctx);
		} else if (ctx instanceof AssignmentNameContext) {
			result = determineName((AssignmentNameContext) ctx);
		} else if (ctx instanceof ClassNameContext) {
			result = determineName((ClassNameContext) ctx);
		} else if (ctx instanceof CobolWordContext) {
			result = determineName((CobolWordContext) ctx);
		} else if (ctx instanceof ComputerNameContext) {
			result = determineName((ComputerNameContext) ctx);
		} else if (ctx instanceof ConditionNameContext) {
			result = determineName((ConditionNameContext) ctx);
		} else if (ctx instanceof DataDescriptionEntryFormat1Context) {
			result = determineName((DataDescriptionEntryFormat1Context) ctx);
		} else if (ctx instanceof DataDescriptionEntryFormat2Context) {
			result = determineName((DataDescriptionEntryFormat2Context) ctx);
		} else if (ctx instanceof DataDescriptionEntryFormat3Context) {
			result = determineName((DataDescriptionEntryFormat3Context) ctx);
		} else if (ctx instanceof DataNameContext) {
			result = determineName((DataNameContext) ctx);
		} else if (ctx instanceof FileControlEntryContext) {
			result = determineName((FileControlEntryContext) ctx);
		} else if (ctx instanceof FileDescriptionEntryContext) {
			result = determineName((FileDescriptionEntryContext) ctx);
		} else if (ctx instanceof FileNameContext) {
			result = determineName((FileNameContext) ctx);
		} else if (ctx instanceof IdentifierContext) {
			result = determineName((IdentifierContext) ctx);
		} else if (ctx instanceof IndexNameContext) {
			result = determineName((IndexNameContext) ctx);
		} else if (ctx instanceof LocalNameContext) {
			result = determineName((LocalNameContext) ctx);
		} else if (ctx instanceof ObjectComputerParagraphContext) {
			result = determineName((ObjectComputerParagraphContext) ctx);
		} else if (ctx instanceof ParagraphContext) {
			result = determineName((ParagraphContext) ctx);
		} else if (ctx instanceof ParagraphNameContext) {
			result = determineName((ParagraphNameContext) ctx);
		} else if (ctx instanceof ProcedureNameContext) {
			result = determineName((ProcedureNameContext) ctx);
		} else if (ctx instanceof ProgramIdParagraphContext) {
			result = determineName((ProgramIdParagraphContext) ctx);
		} else if (ctx instanceof ProgramNameContext) {
			result = determineName((ProgramNameContext) ctx);
		} else if (ctx instanceof ReportNameContext) {
			result = determineName((ReportNameContext) ctx);
		} else if (ctx instanceof QualifiedDataNameContext) {
			result = determineName((QualifiedDataNameContext) ctx);
		} else if (ctx instanceof SelectClauseContext) {
			result = determineName((SelectClauseContext) ctx);
		} else if (ctx instanceof SourceComputerParagraphContext) {
			result = determineName((SourceComputerParagraphContext) ctx);
		} else if (ctx instanceof SystemNameContext) {
			result = determineName((SystemNameContext) ctx);
		} else {
			result = null;
		}

		return result;
	}

	public String determineName(final ProcedureNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final ProgramIdParagraphContext ctx) {
		final String result = determineName(ctx.programName());
		return result;
	}

	public String determineName(final ProgramNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final QualifiedDataNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final ReportNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final SelectClauseContext ctx) {
		final String result = ctx != null ? determineName(ctx.fileName()) : null;
		return result;
	}

	public String determineName(final SourceComputerParagraphContext ctx) {
		final String result = ctx != null ? determineName(ctx.computerName()) : null;
		return result;
	}

	public String determineName(final SystemNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}
}
