/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import static io.proleap.cobol.parser.util.CastUtils.castDataDescriptionEntry;
import static io.proleap.cobol.parser.util.CastUtils.castParagraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.AuthorParagraphContext;
import io.proleap.cobol.Cobol85Parser.ConfigurationSectionContext;
import io.proleap.cobol.Cobol85Parser.ConfigurationSectionParagraphContext;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat1Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat2Context;
import io.proleap.cobol.Cobol85Parser.DataDescriptionEntryFormat3Context;
import io.proleap.cobol.Cobol85Parser.DateCompiledParagraphContext;
import io.proleap.cobol.Cobol85Parser.DateWrittenParagraphContext;
import io.proleap.cobol.Cobol85Parser.DisplayStatementContext;
import io.proleap.cobol.Cobol85Parser.EnvironmentDivisionBodyContext;
import io.proleap.cobol.Cobol85Parser.EnvironmentDivisionContext;
import io.proleap.cobol.Cobol85Parser.IdentificationDivisionBodyContext;
import io.proleap.cobol.Cobol85Parser.IdentificationDivisionContext;
import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.Cobol85Parser.InstallationParagraphContext;
import io.proleap.cobol.Cobol85Parser.LiteralContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementSendingAreaContext;
import io.proleap.cobol.Cobol85Parser.ObjectComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.ParagraphContext;
import io.proleap.cobol.Cobol85Parser.ParagraphNameContext;
import io.proleap.cobol.Cobol85Parser.PerformProcedureStatementContext;
import io.proleap.cobol.Cobol85Parser.PerformStatementContext;
import io.proleap.cobol.Cobol85Parser.ProcedureDivisionContext;
import io.proleap.cobol.Cobol85Parser.ProcedureNameContext;
import io.proleap.cobol.Cobol85Parser.ProgramIdParagraphContext;
import io.proleap.cobol.Cobol85Parser.RemarksParagraphContext;
import io.proleap.cobol.Cobol85Parser.SecurityParagraphContext;
import io.proleap.cobol.Cobol85Parser.SourceComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.StopStatementContext;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.ASGElement;
import io.proleap.cobol.parser.metamodel.AuthorParagraph;
import io.proleap.cobol.parser.metamodel.CobolScope;
import io.proleap.cobol.parser.metamodel.CobolScopedElement;
import io.proleap.cobol.parser.metamodel.ConfigurationSection;
import io.proleap.cobol.parser.metamodel.ConfigurationSectionParagraph;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.DateCompiledParagraph;
import io.proleap.cobol.parser.metamodel.DateWrittenParagraph;
import io.proleap.cobol.parser.metamodel.Declaration;
import io.proleap.cobol.parser.metamodel.DisplayStatement;
import io.proleap.cobol.parser.metamodel.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.EnvironmentDivisionBody;
import io.proleap.cobol.parser.metamodel.IdentificationDivision;
import io.proleap.cobol.parser.metamodel.IdentificationDivisionBody;
import io.proleap.cobol.parser.metamodel.InstallationParagraph;
import io.proleap.cobol.parser.metamodel.Literal;
import io.proleap.cobol.parser.metamodel.ModelElement;
import io.proleap.cobol.parser.metamodel.MoveToStatement;
import io.proleap.cobol.parser.metamodel.NamedElement;
import io.proleap.cobol.parser.metamodel.ObjectComputerParagraph;
import io.proleap.cobol.parser.metamodel.Paragraph;
import io.proleap.cobol.parser.metamodel.ParagraphName;
import io.proleap.cobol.parser.metamodel.PerformProcedureStatement;
import io.proleap.cobol.parser.metamodel.PerformStatement;
import io.proleap.cobol.parser.metamodel.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.ProgramIdParagraph;
import io.proleap.cobol.parser.metamodel.RemarksParagraph;
import io.proleap.cobol.parser.metamodel.SecurityParagraph;
import io.proleap.cobol.parser.metamodel.SourceComputerParagraph;
import io.proleap.cobol.parser.metamodel.StopStatement;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.parser.metamodel.call.ProcedureCall;
import io.proleap.cobol.parser.metamodel.call.impl.DataDescriptionEntryCallImpl;
import io.proleap.cobol.parser.metamodel.call.impl.ProcedureCallImpl;
import io.proleap.cobol.parser.metamodel.call.impl.UndefinedCallImpl;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry1;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry2;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry3;
import io.proleap.cobol.parser.metamodel.data.impl.DataDescriptionEntry1Impl;
import io.proleap.cobol.parser.metamodel.data.impl.DataDescriptionEntry2Impl;
import io.proleap.cobol.parser.metamodel.data.impl.DataDescriptionEntry3Impl;
import io.proleap.cobol.parser.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.CallValueStmtImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.LiteralValueStmtImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.ValueStmtDelegateImpl;

public abstract class CobolScopeImpl extends CobolScopedElementImpl implements CobolScope {

	private final static Logger LOG = LogManager.getLogger(CobolScopeImpl.class);

	protected Map<String, DataDescriptionEntry> dataDescriptionEntriesByName = new HashMap<String, DataDescriptionEntry>();

	protected List<Paragraph> paragraphs = new ArrayList<Paragraph>();

	protected Map<String, Paragraph> paragraphsByName = new HashMap<String, Paragraph>();

	protected final List<CobolScopedElement> scopedElements = new ArrayList<CobolScopedElement>();

	protected final Map<String, List<CobolScopedElement>> scopedElementsByName = new LinkedHashMap<String, List<CobolScopedElement>>();

	public CobolScopeImpl(final CopyBook copyBook, final CobolScope superScope, final ParseTree ctx) {
		super(copyBook, superScope, ctx);
	}

	@Override
	public AuthorParagraph addAuthorParagraph(final AuthorParagraphContext ctx) {
		AuthorParagraph result = (AuthorParagraph) getASGElement(ctx);

		if (result == null) {
			result = new AuthorParagraphImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public Call addCall(final IdentifierContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final List<ModelElement> referencedProgramElements = getElements(name);
			final DataDescriptionEntry dataDescriptionEntry = castDataDescriptionEntry(referencedProgramElements);

			/*
			 * create call model element
			 */
			if (dataDescriptionEntry != null) {
				final DataDescriptionEntryCall dataDescriptionEntryCall = new DataDescriptionEntryCallImpl(name,
						dataDescriptionEntry, copyBook, this, ctx);

				associateDataDescriptionEntryCallWithDataDescriptionEntry(dataDescriptionEntryCall,
						dataDescriptionEntry);

				result = dataDescriptionEntryCall;
			} else {
				LOG.warn("call to unknown element {}", name);
				result = new UndefinedCallImpl(name, copyBook, this, ctx);
			}
		}

		return result;
	}

	@Override
	public Call addCall(final ProcedureNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);

			/*
			 * determine referenced element, i. e. variable or procedure
			 */
			final List<ModelElement> referencedProgramElements = getElements(name);
			final Paragraph paragraph = castParagraph(referencedProgramElements);

			if (paragraph != null) {
				final ProcedureCall procedureCall = new ProcedureCallImpl(name, paragraph, copyBook, this, ctx);

				associateProcedureCallWithParagraph(procedureCall, paragraph);

				result = procedureCall;
			} else {
				result = new UndefinedCallImpl(name, copyBook, this, ctx);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected List<Call> addCallsThrough(final Call firstCall, final Call lastCall,
			final PerformProcedureStatementContext ctx) {
		final List<Call> result = new ArrayList<Call>();

		final String firstCallName = firstCall.getName();
		final String lastCallName = lastCall.getName();

		boolean inThrough = false;

		for (final Paragraph paragraph : paragraphs) {
			final String paragraphName = paragraph.getName();

			if (paragraphName.equals(lastCallName)) {
				break;
			} else if (paragraphName.equals(firstCallName)) {
				inThrough = true;
			} else if (inThrough) {
				final ProcedureCall procedureCall = new ProcedureCallImpl(paragraphName, paragraph, copyBook, this,
						ctx);
				result.add(procedureCall);

				associateProcedureCallWithParagraph(procedureCall, paragraph);
			}
		}

		return result;
	}

	@Override
	public ConfigurationSection addConfigurationSection(final ConfigurationSectionContext ctx) {
		ConfigurationSection result = (ConfigurationSection) getASGElement(ctx);

		if (result == null) {
			result = new ConfigurationSectionImpl(copyBook, this, ctx);

			for (final ConfigurationSectionParagraphContext configurationSectionParagraphContext : ctx
					.configurationSectionParagraph()) {
				final ConfigurationSectionParagraph configurationSectionParagraph;

				if (configurationSectionParagraphContext.sourceComputerParagraph() != null) {
					configurationSectionParagraph = addSourceComputerParagraph(
							configurationSectionParagraphContext.sourceComputerParagraph());
				} else if (configurationSectionParagraphContext.objectComputerParagraph() != null) {
					configurationSectionParagraph = addObjectComputerParagraph(
							configurationSectionParagraphContext.objectComputerParagraph());
				} else {
					LOG.warn("unknown configuration section paragraph {}", configurationSectionParagraphContext);
					configurationSectionParagraph = null;
				}

				result.addConfigurationSectionParagraph(configurationSectionParagraph);
			}

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public DataDescriptionEntry addDataDescriptionEntry(final DataDescriptionEntryFormat1Context ctx) {
		DataDescriptionEntry1 result = (DataDescriptionEntry1) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new DataDescriptionEntry1Impl(name, copyBook, this, ctx);

			storeScopedElement(result);
			dataDescriptionEntriesByName.put(name, result);
		}

		return result;
	}

	@Override
	public DataDescriptionEntry addDataDescriptionEntry(final DataDescriptionEntryFormat2Context ctx) {
		DataDescriptionEntry2 result = (DataDescriptionEntry2) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new DataDescriptionEntry2Impl(name, copyBook, this, ctx);

			storeScopedElement(result);
			dataDescriptionEntriesByName.put(name, result);
		}

		return result;
	}

	@Override
	public DataDescriptionEntry addDataDescriptionEntry(final DataDescriptionEntryFormat3Context ctx) {
		DataDescriptionEntry3 result = (DataDescriptionEntry3) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new DataDescriptionEntry3Impl(name, copyBook, this, ctx);

			storeScopedElement(result);
			dataDescriptionEntriesByName.put(name, result);
		}

		return result;
	}

	@Override
	public DateCompiledParagraph addDateCompiledParagraph(final DateCompiledParagraphContext ctx) {
		DateCompiledParagraph result = (DateCompiledParagraph) getASGElement(ctx);

		if (result == null) {
			result = new DateCompiledParagraphImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public DateWrittenParagraph addDateWrittenParagraph(final DateWrittenParagraphContext ctx) {
		DateWrittenParagraph result = (DateWrittenParagraph) getASGElement(ctx);

		if (result == null) {
			result = new DateWrittenParagraphImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public DisplayStatement addDisplayStatement(final DisplayStatementContext ctx) {
		DisplayStatement result = (DisplayStatement) getASGElement(ctx);

		if (result == null) {
			result = new DisplayStatementImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public EnvironmentDivision addEnvironmentDivision(final EnvironmentDivisionContext ctx) {
		EnvironmentDivision result = (EnvironmentDivision) getASGElement(ctx);

		if (result == null) {
			result = new EnvironmentDivisionImpl(copyBook, this, ctx);

			for (final EnvironmentDivisionBodyContext environmentDivisionBodyContext : ctx.environmentDivisionBody()) {
				final EnvironmentDivisionBody environmentDivisionBody;

				if (environmentDivisionBodyContext.configurationSection() != null) {
					environmentDivisionBody = addConfigurationSection(
							environmentDivisionBodyContext.configurationSection());
				} else {
					LOG.warn("unknown environment division body {}", environmentDivisionBodyContext);
					environmentDivisionBody = null;
				}

				result.addEnvironmentDivisionBody(environmentDivisionBody);
			}

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public IdentificationDivision addIdentificationDivision(final IdentificationDivisionContext ctx) {
		IdentificationDivision result = (IdentificationDivision) getASGElement(ctx);

		if (result == null) {
			result = new IdentificationDivisionImpl(copyBook, this, ctx);

			// program id paragraph
			final ProgramIdParagraph programIdParagraph = addProgramIdParagraph(ctx.programIdParagraph());
			result.setProgramIdParagraph(programIdParagraph);

			for (final IdentificationDivisionBodyContext identificationDivisionBodyContext : ctx
					.identificationDivisionBody()) {
				final IdentificationDivisionBody identificationDivisionBody;

				if (identificationDivisionBodyContext.authorParagraph() != null) {
					identificationDivisionBody = addAuthorParagraph(
							identificationDivisionBodyContext.authorParagraph());
				} else if (identificationDivisionBodyContext.installationParagraph() != null) {
					identificationDivisionBody = addInstallationParagraph(
							identificationDivisionBodyContext.installationParagraph());
				} else if (identificationDivisionBodyContext.dateWrittenParagraph() != null) {
					identificationDivisionBody = addDateWrittenParagraph(
							identificationDivisionBodyContext.dateWrittenParagraph());
				} else if (identificationDivisionBodyContext.dateCompiledParagraph() != null) {
					identificationDivisionBody = addDateCompiledParagraph(
							identificationDivisionBodyContext.dateCompiledParagraph());
				} else if (identificationDivisionBodyContext.securityParagraph() != null) {
					identificationDivisionBody = addSecurityParagraph(
							identificationDivisionBodyContext.securityParagraph());
				} else if (identificationDivisionBodyContext.remarksParagraph() != null) {
					identificationDivisionBody = addRemarksParagraph(
							identificationDivisionBodyContext.remarksParagraph());
				} else {
					LOG.warn("unknown identification division body {}", identificationDivisionBodyContext);
					identificationDivisionBody = null;
				}

				result.addIdentificationDivisionBody(identificationDivisionBody);
			}

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public InstallationParagraph addInstallationParagraph(final InstallationParagraphContext ctx) {
		InstallationParagraph result = (InstallationParagraph) getASGElement(ctx);

		if (result == null) {
			result = new InstallationParagraphImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public Literal addLiteral(final LiteralContext ctx) {
		Literal result = (Literal) getASGElement(ctx);

		if (result == null) {
			final String value = ctx.getText();
			result = new LiteralImpl(value, copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public MoveToStatement addMoveToStatement(final MoveToStatementContext ctx) {
		MoveToStatement result = (MoveToStatement) getASGElement(ctx);

		if (result == null) {
			result = new MoveToStatementImpl(copyBook, this, ctx);

			final MoveToStatementSendingAreaContext moveToStatementSendingArea = ctx.moveToStatementSendingArea();
			final List<IdentifierContext> identifierCtxs = ctx.identifier();

			// sending area value statement
			final ValueStmt sendingAreaValueStmt = addValueStmt(moveToStatementSendingArea);
			result.setSendingAreaValueStmt(sendingAreaValueStmt);

			// receiving area calls
			for (final IdentifierContext identifierCtx : identifierCtxs) {
				final Call receivingAreaCall = addCall(identifierCtx);
				result.addReceivingAreaCall(receivingAreaCall);
			}

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public ObjectComputerParagraph addObjectComputerParagraph(final ObjectComputerParagraphContext ctx) {
		ObjectComputerParagraph result = (ObjectComputerParagraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ObjectComputerParagraphImpl(name, copyBook, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Paragraph addParagraph(final ParagraphContext ctx) {
		Paragraph result = (Paragraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ParagraphImpl(name, copyBook, this, ctx);

			paragraphs.add(result);
			paragraphsByName.put(name, result);

			final ParagraphName paragraphName = addParagraphName(ctx.paragraphName());
			result.addParagraphName(paragraphName);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public ParagraphName addParagraphName(final ParagraphNameContext ctx) {
		ParagraphName result = (ParagraphName) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ParagraphNameImpl(name, copyBook, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public PerformProcedureStatement addPerformProcedureStatement(final PerformProcedureStatementContext ctx) {
		PerformProcedureStatement result = (PerformProcedureStatement) getASGElement(ctx);

		if (result == null) {
			result = new PerformProcedureStatementImpl(copyBook, this, ctx);

			final List<ProcedureNameContext> procedureNames = ctx.procedureName();

			if (procedureNames.isEmpty()) {
				LOG.warn("no calls in {}.", ctx);
			} else {
				final Call firstCall = addCall(procedureNames.get(0));
				result.addCall(firstCall);

				if (procedureNames.size() > 1) {
					final Call lastCall = addCall(procedureNames.get(1));
					result.addCall(lastCall);

					final List<Call> callsThrough = addCallsThrough(firstCall, lastCall, ctx);
					result.addCalls(callsThrough);
				}
			}

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public PerformStatement addPerformStatement(final PerformStatementContext ctx) {
		PerformStatement result = (PerformStatement) getASGElement(ctx);

		if (result == null) {
			result = new PerformStatementImpl(copyBook, this, ctx);

			if (ctx.performProcedureStatement() != null) {
				final PerformProcedureStatement performProcedureStatement = addPerformProcedureStatement(
						ctx.performProcedureStatement());
				result.setPerformProcedureStatement(performProcedureStatement);
			}

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public ProcedureDivision addProcedureDivision(final ProcedureDivisionContext ctx) {
		ProcedureDivision result = (ProcedureDivision) getASGElement(ctx);

		if (result == null) {
			result = new ProcedureDivisionImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public ProgramIdParagraph addProgramIdParagraph(final ProgramIdParagraphContext ctx) {
		ProgramIdParagraph result = (ProgramIdParagraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);

			result = new ProgramIdParagraphImpl(name, copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public RemarksParagraph addRemarksParagraph(final RemarksParagraphContext ctx) {
		RemarksParagraph result = (RemarksParagraph) getASGElement(ctx);

		if (result == null) {
			result = new RemarksParagraphImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public SecurityParagraph addSecurityParagraph(final SecurityParagraphContext ctx) {
		SecurityParagraph result = (SecurityParagraph) getASGElement(ctx);

		if (result == null) {
			result = new SecurityParagraphImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	@Override
	public SourceComputerParagraph addSourceComputerParagraph(final SourceComputerParagraphContext ctx) {
		SourceComputerParagraph result = (SourceComputerParagraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new SourceComputerParagraphImpl(name, copyBook, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public StopStatement addStopStatement(final StopStatementContext ctx) {
		StopStatement result = (StopStatement) getASGElement(ctx);

		if (result == null) {
			result = new StopStatementImpl(copyBook, this, ctx);

			storeScopedElement(result);
		}

		return result;
	}

	public ValueStmt addValueStmt(final LiteralContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new LiteralValueStmtImpl(copyBook, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final MoveToStatementSendingAreaContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			/*
			 * then the delegated value stmt
			 */
			final ValueStmt delegatedValueStmt;

			if (ctx.identifier() != null) {
				delegatedValueStmt = createCallValueStmt(ctx.identifier());
			} else if (ctx.literal() != null) {
				delegatedValueStmt = createLiteralValueStmt(ctx.literal());
			} else {
				LOG.warn("unknown value stmt {}.", ctx);
				delegatedValueStmt = null;
			}

			result = new ValueStmtDelegateImpl(delegatedValueStmt, copyBook, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	protected void associateDataDescriptionEntryCallWithDataDescriptionEntry(
			final DataDescriptionEntryCall dataDescriptionEntryCall, final DataDescriptionEntry dataDescriptionEntry) {
		dataDescriptionEntry.addDataDescriptionEntryCall(dataDescriptionEntryCall);
	}

	protected void associateProcedureCallWithParagraph(final ProcedureCall procedureCall, final Paragraph paragraph) {
		paragraph.addProcedureCall(procedureCall);
	}

	protected CallValueStmt createCallValueStmt(final IdentifierContext ctx) {
		final Call delegatedCall = addCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, copyBook, this, ctx);
		return result;
	}

	protected LiteralValueStmt createLiteralValueStmt(final LiteralContext ctx) {
		final Literal literal = addLiteral(ctx);
		final LiteralValueStmt result = new LiteralValueStmtImpl(copyBook, this, ctx);
		result.setLiteral(literal);
		return result;
	}

	protected String determineName(final ParseTree ctx) {
		return CobolParserContext.getInstance().getNameResolver().determineName(ctx);
	}

	protected ASGElement getASGElement(final ParseTree ctx) {
		final ASGElement result = CobolParserContext.getInstance().getASGElementRegistry().getASGElement(ctx);
		return result;
	}

	@Override
	public DataDescriptionEntry getDataDescriptionEntry(final String name) {
		return dataDescriptionEntriesByName.get(name);
	}

	/**
	 * searches elements of the program by their name such as paragraphs,
	 * variables etc.
	 */
	protected List<ModelElement> getElements(final String name) {
		final List<ModelElement> referencedProgramElements = new ArrayList<ModelElement>();

		if (name == null) {
		} else {
			// search globally in the program for elements with that
			// name
			final List<CobolScopedElement> globalProgramElements = getScopedElementsInHierarchy(name);

			if (globalProgramElements != null) {
				referencedProgramElements.addAll(globalProgramElements);
			}
		}

		while (referencedProgramElements.contains(null)) {
			referencedProgramElements.remove(null);
		}

		return referencedProgramElements;
	}

	@Override
	public Paragraph getParagraph(final String name) {
		return paragraphsByName.get(name);
	}

	private String getScopedElementKey(final String name) {
		return name.toLowerCase();
	}

	@Override
	public List<CobolScopedElement> getScopedElements() {
		return scopedElements;
	}

	@Override
	public List<CobolScopedElement> getScopedElementsInHierarchy(final String name) {
		final List<CobolScopedElement> result;

		if (name == null) {
			result = null;
		} else {
			final List<CobolScopedElement> scopedElementInScope = getScopedElementsInScope(name);

			if (scopedElementInScope != null) {
				result = scopedElementInScope;
			} else if (superScope != null) {
				result = superScope.getScopedElementsInHierarchy(name);
			} else {
				result = null;
			}
		}

		return result;
	}

	@Override
	public List<CobolScopedElement> getScopedElementsInScope(final String name) {
		final List<CobolScopedElement> result;

		if (name == null) {
			result = null;
		} else {
			final String scopedElementKey = getScopedElementKey(name);
			final List<CobolScopedElement> scopedElementInScope = scopedElementsByName.get(scopedElementKey);

			result = scopedElementInScope;
		}

		return result;
	}

	@Override
	public List<CobolScope> getSubScopes() {
		final List<CobolScope> result = new ArrayList<CobolScope>();

		for (final CobolScopedElement scopedElement : scopedElements) {
			if (scopedElement instanceof CobolScope) {
				final CobolScope scope = (CobolScope) scopedElement;
				result.add(scope);
			}
		}

		return result;
	}

	protected void registerASGElement(final ASGElement asgElement) {
		assert asgElement != null;
		assert asgElement.getCtx() != null;

		CobolParserContext.getInstance().getASGElementRegistry().addASGElement(asgElement);
	}

	@Override
	public void storeScopedElement(final CobolScopedElement scopedElement) {
		assert scopedElement != null;
		assert scopedElement.getCtx() != null;

		registerASGElement(scopedElement);

		scopedElements.add(scopedElement);

		/*
		 * expressions should not be stored under their name, as they collide
		 * with declarations under the same name -> only declarations
		 */
		if (scopedElement instanceof Declaration) {
			final NamedElement namedElement = (NamedElement) scopedElement;
			final String scopedElementKey = getScopedElementKey(namedElement.getName());

			if (scopedElementsByName.get(scopedElementKey) == null) {
				scopedElementsByName.put(scopedElementKey, new ArrayList<CobolScopedElement>());
			}

			scopedElementsByName.get(scopedElementKey).add(scopedElement);
		}
	}

}
