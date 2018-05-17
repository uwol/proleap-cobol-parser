/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.CobolParser.DataDivisionContext;
import io.proleap.cobol.CobolParser.EnvironmentDivisionBodyContext;
import io.proleap.cobol.CobolParser.EnvironmentDivisionContext;
import io.proleap.cobol.CobolParser.IdentificationDivisionBodyContext;
import io.proleap.cobol.CobolParser.IdentificationDivisionContext;
import io.proleap.cobol.CobolParser.ProcedureDivisionContext;
import io.proleap.cobol.CobolParser.ProgramUnitContext;
import io.proleap.cobol.asg.metamodel.ASGElement;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.impl.DataDivisionImpl;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.impl.EnvironmentDivisionImpl;
import io.proleap.cobol.asg.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.asg.metamodel.identification.impl.IdentificationDivisionImpl;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.impl.ProcedureDivisionImpl;

public class ProgramUnitImpl extends CompilationUnitElementImpl implements ProgramUnit {

	private final static Logger LOG = LoggerFactory.getLogger(ProgramUnitImpl.class);

	protected final ProgramUnitContext ctx;

	protected DataDivision dataDivision;

	protected EnvironmentDivision environmentDivision;

	protected IdentificationDivision identificationDivision;

	protected ProcedureDivision procedureDivision;

	public ProgramUnitImpl(final CompilationUnit compilationUnit, final ProgramUnitContext ctx) {
		super(compilationUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public DataDivision addDataDivision(final DataDivisionContext ctx) {
		DataDivision result = (DataDivision) getASGElement(ctx);

		if (result == null) {
			result = new DataDivisionImpl(this, ctx);
			dataDivision = result;

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public EnvironmentDivision addEnvironmentDivision(final EnvironmentDivisionContext ctx) {
		EnvironmentDivision result = (EnvironmentDivision) getASGElement(ctx);

		if (result == null) {
			result = new EnvironmentDivisionImpl(this, ctx);
			environmentDivision = result;

			for (final EnvironmentDivisionBodyContext environmentDivisionBodyContext : ctx.environmentDivisionBody()) {
				if (environmentDivisionBodyContext.configurationSection() != null) {
					result.addConfigurationSection(environmentDivisionBodyContext.configurationSection());
				} else if (environmentDivisionBodyContext.inputOutputSection() != null) {
					result.addInputOutputSection(environmentDivisionBodyContext.inputOutputSection());
				} else if (environmentDivisionBodyContext.specialNamesParagraph() != null) {
					result.addSpecialNamesParagraph(environmentDivisionBodyContext.specialNamesParagraph());
				} else {
					LOG.warn("unknown environment division body {}", environmentDivisionBodyContext);
				}
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public IdentificationDivision addIdentificationDivision(final IdentificationDivisionContext ctx) {
		IdentificationDivision result = (IdentificationDivision) getASGElement(ctx);

		if (result == null) {
			result = new IdentificationDivisionImpl(this, ctx);
			identificationDivision = result;

			if (ctx.programIdParagraph() != null) {
				result.addProgramIdParagraph(ctx.programIdParagraph());
			}

			for (final IdentificationDivisionBodyContext identificationDivisionBodyContext : ctx
					.identificationDivisionBody()) {
				if (identificationDivisionBodyContext.authorParagraph() != null) {
					result.addAuthorParagraph(identificationDivisionBodyContext.authorParagraph());
				} else if (identificationDivisionBodyContext.dateWrittenParagraph() != null) {
					result.addDateWrittenParagraph(identificationDivisionBodyContext.dateWrittenParagraph());
				} else if (identificationDivisionBodyContext.dateCompiledParagraph() != null) {
					result.addDateCompiledParagraph(identificationDivisionBodyContext.dateCompiledParagraph());
				} else if (identificationDivisionBodyContext.installationParagraph() != null) {
					result.addInstallationParagraph(identificationDivisionBodyContext.installationParagraph());
				} else if (identificationDivisionBodyContext.remarksParagraph() != null) {
					result.addRemarksParagraph(identificationDivisionBodyContext.remarksParagraph());
				} else if (identificationDivisionBodyContext.securityParagraph() != null) {
					result.addSecurityParagraph(identificationDivisionBodyContext.securityParagraph());
				} else {
					LOG.warn("unknown identification division body {}", identificationDivisionBodyContext);
				}
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ProcedureDivision addProcedureDivision(final ProcedureDivisionContext ctx) {
		ProcedureDivision result = (ProcedureDivision) getASGElement(ctx);

		if (result == null) {
			result = new ProcedureDivisionImpl(this, ctx);
			procedureDivision = result;

			registerASGElement(result);
		}

		return result;
	}

	protected ASGElement getASGElement(final ParserRuleContext ctx) {
		final ASGElement result = compilationUnit.getProgram().getASGElementRegistry().getASGElement(ctx);
		return result;
	}

	@Override
	public DataDivision getDataDivision() {
		return dataDivision;
	}

	@Override
	public EnvironmentDivision getEnvironmentDivision() {
		return environmentDivision;
	}

	@Override
	public IdentificationDivision getIdentificationDivision() {
		return identificationDivision;
	}

	@Override
	public ProcedureDivision getProcedureDivision() {
		return procedureDivision;
	}

	protected void registerASGElement(final ASGElement asgElement) {
		compilationUnit.getProgram().getASGElementRegistry().addASGElement(asgElement);
	}
}
