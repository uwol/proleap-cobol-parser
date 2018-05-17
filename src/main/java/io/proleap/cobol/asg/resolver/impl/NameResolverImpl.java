/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.resolver.impl;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.CobolParser.AlphabetNameContext;
import io.proleap.cobol.CobolParser.AssignmentNameContext;
import io.proleap.cobol.CobolParser.CdNameContext;
import io.proleap.cobol.CobolParser.ClassNameContext;
import io.proleap.cobol.CobolParser.CobolWordContext;
import io.proleap.cobol.CobolParser.ComputerNameContext;
import io.proleap.cobol.CobolParser.ConditionNameContext;
import io.proleap.cobol.CobolParser.DataDescNameContext;
import io.proleap.cobol.CobolParser.DataDescriptionEntryFormat1Context;
import io.proleap.cobol.CobolParser.DataDescriptionEntryFormat2Context;
import io.proleap.cobol.CobolParser.DataDescriptionEntryFormat3Context;
import io.proleap.cobol.CobolParser.DataNameContext;
import io.proleap.cobol.CobolParser.FileControlEntryContext;
import io.proleap.cobol.CobolParser.FileDescriptionEntryContext;
import io.proleap.cobol.CobolParser.FileNameContext;
import io.proleap.cobol.CobolParser.FunctionCallContext;
import io.proleap.cobol.CobolParser.FunctionNameContext;
import io.proleap.cobol.CobolParser.IdentifierContext;
import io.proleap.cobol.CobolParser.InDataContext;
import io.proleap.cobol.CobolParser.InSectionContext;
import io.proleap.cobol.CobolParser.InTableContext;
import io.proleap.cobol.CobolParser.IndexNameContext;
import io.proleap.cobol.CobolParser.LibraryNameContext;
import io.proleap.cobol.CobolParser.LocalNameContext;
import io.proleap.cobol.CobolParser.ObjectComputerParagraphContext;
import io.proleap.cobol.CobolParser.ParagraphContext;
import io.proleap.cobol.CobolParser.ParagraphNameContext;
import io.proleap.cobol.CobolParser.ProcedureNameContext;
import io.proleap.cobol.CobolParser.ProcedureSectionContext;
import io.proleap.cobol.CobolParser.ProcedureSectionHeaderContext;
import io.proleap.cobol.CobolParser.ProgramIdParagraphContext;
import io.proleap.cobol.CobolParser.ProgramNameContext;
import io.proleap.cobol.CobolParser.QualifiedDataNameContext;
import io.proleap.cobol.CobolParser.QualifiedDataNameFormat1Context;
import io.proleap.cobol.CobolParser.QualifiedDataNameFormat2Context;
import io.proleap.cobol.CobolParser.QualifiedDataNameFormat3Context;
import io.proleap.cobol.CobolParser.QualifiedInDataContext;
import io.proleap.cobol.CobolParser.RecordNameContext;
import io.proleap.cobol.CobolParser.ReportDescriptionContext;
import io.proleap.cobol.CobolParser.ReportDescriptionEntryContext;
import io.proleap.cobol.CobolParser.ReportGroupDescriptionEntryFormat1Context;
import io.proleap.cobol.CobolParser.ReportGroupDescriptionEntryFormat2Context;
import io.proleap.cobol.CobolParser.ReportGroupDescriptionEntryFormat3Context;
import io.proleap.cobol.CobolParser.ReportNameContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionEntryContext;
import io.proleap.cobol.CobolParser.ScreenNameContext;
import io.proleap.cobol.CobolParser.SectionNameContext;
import io.proleap.cobol.CobolParser.SelectClauseContext;
import io.proleap.cobol.CobolParser.SourceComputerParagraphContext;
import io.proleap.cobol.CobolParser.SpecialRegisterContext;
import io.proleap.cobol.CobolParser.SystemNameContext;
import io.proleap.cobol.CobolParser.TableCallContext;
import io.proleap.cobol.asg.resolver.NameResolver;

public class NameResolverImpl implements NameResolver {

	public String determineName(final AlphabetNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final AssignmentNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final CdNameContext ctx) {
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

	public String determineName(final DataDescNameContext ctx) {
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

	public String determineName(final FunctionCallContext ctx) {
		final String result = ctx != null ? determineName(ctx.functionName()) : null;
		return result;
	}

	public String determineName(final FunctionNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final IdentifierContext ctx) {
		final String result;

		if (ctx == null) {
			result = null;
		} else if (ctx.qualifiedDataName() != null) {
			result = determineName(ctx.qualifiedDataName());
		} else {
			result = ctx.getText();
		}

		return result;
	}

	public String determineName(final InDataContext ctx) {
		final String result = ctx != null ? determineName(ctx.dataName()) : null;
		return result;
	}

	public String determineName(final IndexNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final InSectionContext ctx) {
		final String result = ctx != null ? determineName(ctx.sectionName()) : null;
		return result;
	}

	public String determineName(final InTableContext ctx) {
		final String result = ctx != null ? determineName(ctx.tableCall()) : null;
		return result;
	}

	public String determineName(final LibraryNameContext ctx) {
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
		final String result = ctx != null ? determineName(ctx.paragraphName()) : null;
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
		} else if (ctx instanceof CdNameContext) {
			result = determineName((CdNameContext) ctx);
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
		} else if (ctx instanceof DataDescNameContext) {
			result = determineName((DataDescNameContext) ctx);
		} else if (ctx instanceof DataNameContext) {
			result = determineName((DataNameContext) ctx);
		} else if (ctx instanceof FileControlEntryContext) {
			result = determineName((FileControlEntryContext) ctx);
		} else if (ctx instanceof FileDescriptionEntryContext) {
			result = determineName((FileDescriptionEntryContext) ctx);
		} else if (ctx instanceof FileNameContext) {
			result = determineName((FileNameContext) ctx);
		} else if (ctx instanceof FunctionCallContext) {
			result = determineName((FunctionCallContext) ctx);
		} else if (ctx instanceof FunctionNameContext) {
			result = determineName((FunctionNameContext) ctx);
		} else if (ctx instanceof IdentifierContext) {
			result = determineName((IdentifierContext) ctx);
		} else if (ctx instanceof IndexNameContext) {
			result = determineName((IndexNameContext) ctx);
		} else if (ctx instanceof InDataContext) {
			result = determineName((InDataContext) ctx);
		} else if (ctx instanceof InSectionContext) {
			result = determineName((InSectionContext) ctx);
		} else if (ctx instanceof InTableContext) {
			result = determineName((InTableContext) ctx);
		} else if (ctx instanceof LibraryNameContext) {
			result = determineName((LibraryNameContext) ctx);
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
		} else if (ctx instanceof ProcedureSectionContext) {
			result = determineName((ProcedureSectionContext) ctx);
		} else if (ctx instanceof ProcedureSectionHeaderContext) {
			result = determineName((ProcedureSectionHeaderContext) ctx);
		} else if (ctx instanceof ProgramIdParagraphContext) {
			result = determineName((ProgramIdParagraphContext) ctx);
		} else if (ctx instanceof ProgramNameContext) {
			result = determineName((ProgramNameContext) ctx);
		} else if (ctx instanceof QualifiedDataNameContext) {
			result = determineName((QualifiedDataNameContext) ctx);
		} else if (ctx instanceof QualifiedDataNameFormat1Context) {
			result = determineName((QualifiedDataNameFormat1Context) ctx);
		} else if (ctx instanceof QualifiedDataNameFormat2Context) {
			result = determineName((QualifiedDataNameFormat2Context) ctx);
		} else if (ctx instanceof QualifiedDataNameFormat3Context) {
			result = determineName((QualifiedDataNameFormat3Context) ctx);
		} else if (ctx instanceof QualifiedInDataContext) {
			result = determineName((QualifiedInDataContext) ctx);
		} else if (ctx instanceof RecordNameContext) {
			result = determineName((RecordNameContext) ctx);
		} else if (ctx instanceof ReportDescriptionContext) {
			result = determineName((ReportDescriptionContext) ctx);
		} else if (ctx instanceof ReportDescriptionEntryContext) {
			result = determineName((ReportDescriptionEntryContext) ctx);
		} else if (ctx instanceof ReportGroupDescriptionEntryFormat1Context) {
			result = determineName((ReportGroupDescriptionEntryFormat1Context) ctx);
		} else if (ctx instanceof ReportGroupDescriptionEntryFormat2Context) {
			result = determineName((ReportGroupDescriptionEntryFormat2Context) ctx);
		} else if (ctx instanceof ReportGroupDescriptionEntryFormat3Context) {
			result = determineName((ReportGroupDescriptionEntryFormat3Context) ctx);
		} else if (ctx instanceof ReportNameContext) {
			result = determineName((ReportNameContext) ctx);
		} else if (ctx instanceof SectionNameContext) {
			result = determineName((SectionNameContext) ctx);
		} else if (ctx instanceof SelectClauseContext) {
			result = determineName((SelectClauseContext) ctx);
		} else if (ctx instanceof ScreenNameContext) {
			result = determineName((ScreenNameContext) ctx);
		} else if (ctx instanceof ScreenDescriptionEntryContext) {
			result = determineName((ScreenDescriptionEntryContext) ctx);
		} else if (ctx instanceof SourceComputerParagraphContext) {
			result = determineName((SourceComputerParagraphContext) ctx);
		} else if (ctx instanceof SpecialRegisterContext) {
			result = determineName((SpecialRegisterContext) ctx);
		} else if (ctx instanceof SystemNameContext) {
			result = determineName((SystemNameContext) ctx);
		} else if (ctx instanceof TableCallContext) {
			result = determineName((TableCallContext) ctx);
		} else {
			result = null;
		}

		return result;
	}

	public String determineName(final ProcedureNameContext ctx) {
		final String result = ctx != null ? determineName(ctx.paragraphName()) : null;
		return result;
	}

	public String determineName(final ProcedureSectionContext ctx) {
		final String result = ctx != null ? determineName(ctx.procedureSectionHeader()) : null;
		return result;
	}

	public String determineName(final ProcedureSectionHeaderContext ctx) {
		final String result = ctx != null ? determineName(ctx.sectionName()) : null;
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
		final String result;

		if (ctx == null) {
			result = null;
		} else if (ctx.qualifiedDataNameFormat1() != null) {
			result = determineName(ctx.qualifiedDataNameFormat1());
		} else if (ctx.qualifiedDataNameFormat2() != null) {
			result = determineName(ctx.qualifiedDataNameFormat2());
		} else if (ctx.qualifiedDataNameFormat3() != null) {
			result = determineName(ctx.qualifiedDataNameFormat3());
		} else if (ctx.qualifiedDataNameFormat4() != null) {
			result = determineName(ctx.qualifiedDataNameFormat4());
		} else {
			result = null;
		}

		return result;
	}

	public String determineName(final QualifiedDataNameFormat1Context ctx) {
		final String result;

		if (ctx == null) {
			result = null;
		} else if (ctx.dataName() != null) {
			result = determineName(ctx.dataName());
		} else if (ctx.conditionName() != null) {
			result = determineName(ctx.conditionName());
		} else {
			result = null;
		}

		return result;
	}

	public String determineName(final QualifiedDataNameFormat2Context ctx) {
		final String result = ctx != null ? determineName(ctx.paragraphName()) : null;
		return result;
	}

	public String determineName(final QualifiedDataNameFormat3Context ctx) {
		final String result = ctx != null ? determineName(ctx.textName()) : null;
		return result;
	}

	public String determineName(final QualifiedInDataContext ctx) {
		final String result;

		if (ctx == null) {
			result = null;
		} else if (ctx.inData() != null) {
			result = determineName(ctx.inData());
		} else if (ctx.inTable() != null) {
			result = determineName(ctx.inTable());
		} else {
			result = null;
		}

		return result;
	}

	public String determineName(final RecordNameContext ctx) {
		final String result = ctx != null ? determineName(ctx.qualifiedDataName()) : null;
		return result;
	}

	public String determineName(final ReportDescriptionContext ctx) {
		final String result = ctx != null ? determineName(ctx.reportDescriptionEntry()) : null;
		return result;
	}

	public String determineName(final ReportDescriptionEntryContext ctx) {
		final String result = ctx != null ? determineName(ctx.reportName()) : null;
		return result;
	}

	public String determineName(final ReportGroupDescriptionEntryFormat1Context ctx) {
		final String result = ctx != null ? determineName(ctx.dataName()) : null;
		return result;
	}

	public String determineName(final ReportGroupDescriptionEntryFormat2Context ctx) {
		final String result = ctx != null ? determineName(ctx.dataName()) : null;
		return result;
	}

	public String determineName(final ReportGroupDescriptionEntryFormat3Context ctx) {
		final String result = ctx != null ? determineName(ctx.dataName()) : null;
		return result;
	}

	public String determineName(final ReportNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final ScreenDescriptionEntryContext ctx) {
		final String result = ctx != null ? determineName(ctx.screenName()) : null;
		return result;
	}

	public String determineName(final ScreenNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final SectionNameContext ctx) {
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

	public String determineName(final SpecialRegisterContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final SystemNameContext ctx) {
		final String result = ctx != null ? ctx.getText() : null;
		return result;
	}

	public String determineName(final TableCallContext ctx) {
		final String result = ctx != null ? ctx.qualifiedDataName().getText() : null;
		return result;
	}
}
