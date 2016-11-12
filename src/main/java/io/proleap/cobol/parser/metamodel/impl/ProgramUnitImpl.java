/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.DataDivisionBodyContext;
import io.proleap.cobol.Cobol85Parser.DataDivisionContext;
import io.proleap.cobol.Cobol85Parser.EnvironmentDivisionBodyContext;
import io.proleap.cobol.Cobol85Parser.EnvironmentDivisionContext;
import io.proleap.cobol.Cobol85Parser.IdentificationDivisionBodyContext;
import io.proleap.cobol.Cobol85Parser.IdentificationDivisionContext;
import io.proleap.cobol.Cobol85Parser.ProcedureDivisionContext;
import io.proleap.cobol.Cobol85Parser.ProgramUnitContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.DataDivisionBody;
import io.proleap.cobol.parser.metamodel.data.impl.DataDivisionImpl;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.environment.impl.EnvironmentDivisionImpl;
import io.proleap.cobol.parser.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.parser.metamodel.identification.IdentificationDivisionBody;
import io.proleap.cobol.parser.metamodel.identification.ProgramIdParagraph;
import io.proleap.cobol.parser.metamodel.identification.impl.IdentificationDivisionImpl;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.impl.ProcedureDivisionImpl;

public class ProgramUnitImpl extends CompilationUnitElementImpl implements ProgramUnit {

	private final static Logger LOG = LogManager.getLogger(ProgramUnitImpl.class);

	protected final CopyBook copyBook;

	protected final ProgramUnitContext ctx;

	protected DataDivision dataDivision;

	protected EnvironmentDivision environmentDivision;

	protected IdentificationDivision identificationDivision;

	protected ProcedureDivision procedureDivision;

	public ProgramUnitImpl(final CopyBook copyBook, final ProgramUnitContext ctx) {
		super(ctx);

		this.ctx = ctx;
		this.copyBook = copyBook;
	}

	@Override
	public DataDivision addDataDivision(final DataDivisionContext ctx) {
		DataDivision result = (DataDivision) getASGElement(ctx);

		if (result == null) {
			result = new DataDivisionImpl(this, ctx);

			final DataDivisionBodyContext dataDivisionBodyContext = ctx.dataDivisionBody();
			final DataDivisionBody dataDivisionBody = result.addDataDivisionBody(dataDivisionBodyContext);
			result.setDataDivisionBody(dataDivisionBody);

			registerASGElement(result);
			dataDivision = result;
		}

		return result;
	}

	@Override
	public EnvironmentDivision addEnvironmentDivision(final EnvironmentDivisionContext ctx) {
		EnvironmentDivision result = (EnvironmentDivision) getASGElement(ctx);

		if (result == null) {
			result = new EnvironmentDivisionImpl(this, ctx);

			for (final EnvironmentDivisionBodyContext environmentDivisionBodyContext : ctx.environmentDivisionBody()) {
				if (environmentDivisionBodyContext.configurationSection() != null) {
					result.addConfigurationSection(environmentDivisionBodyContext.configurationSection());
				} else if (environmentDivisionBodyContext.specialNamesParagraph() != null) {
					result.addSpecialNamesParagraph(environmentDivisionBodyContext.specialNamesParagraph());
				} else if (environmentDivisionBodyContext.inputOutputSection() != null) {
					result.addInputOutputSection(environmentDivisionBodyContext.inputOutputSection());
				} else {
					LOG.warn("unknown environment division body {}", environmentDivisionBodyContext);
				}
			}

			registerASGElement(result);
			environmentDivision = result;
		}

		return result;
	}

	@Override
	public IdentificationDivision addIdentificationDivision(final IdentificationDivisionContext ctx) {
		IdentificationDivision result = (IdentificationDivision) getASGElement(ctx);

		if (result == null) {
			result = new IdentificationDivisionImpl(this, ctx);

			// program id paragraph
			final ProgramIdParagraph programIdParagraph = result.addProgramIdParagraph(ctx.programIdParagraph());
			result.setProgramIdParagraph(programIdParagraph);

			for (final IdentificationDivisionBodyContext identificationDivisionBodyContext : ctx
					.identificationDivisionBody()) {
				final IdentificationDivisionBody identificationDivisionBody;

				if (identificationDivisionBodyContext.authorParagraph() != null) {
					identificationDivisionBody = result
							.addAuthorParagraph(identificationDivisionBodyContext.authorParagraph());
				} else if (identificationDivisionBodyContext.installationParagraph() != null) {
					identificationDivisionBody = result
							.addInstallationParagraph(identificationDivisionBodyContext.installationParagraph());
				} else if (identificationDivisionBodyContext.dateWrittenParagraph() != null) {
					identificationDivisionBody = result
							.addDateWrittenParagraph(identificationDivisionBodyContext.dateWrittenParagraph());
				} else if (identificationDivisionBodyContext.dateCompiledParagraph() != null) {
					identificationDivisionBody = result
							.addDateCompiledParagraph(identificationDivisionBodyContext.dateCompiledParagraph());
				} else if (identificationDivisionBodyContext.securityParagraph() != null) {
					identificationDivisionBody = result
							.addSecurityParagraph(identificationDivisionBodyContext.securityParagraph());
				} else if (identificationDivisionBodyContext.remarksParagraph() != null) {
					identificationDivisionBody = result
							.addRemarksParagraph(identificationDivisionBodyContext.remarksParagraph());
				} else {
					LOG.warn("unknown identification division body {}", identificationDivisionBodyContext);
					identificationDivisionBody = null;
				}

				result.addIdentificationDivisionBody(identificationDivisionBody);
			}

			registerASGElement(result);
			identificationDivision = result;
		}

		return result;
	}

	@Override
	public ProcedureDivision addProcedureDivision(final ProcedureDivisionContext ctx) {
		ProcedureDivision result = (ProcedureDivision) getASGElement(ctx);

		if (result == null) {
			result = new ProcedureDivisionImpl(this, ctx);

			registerASGElement(result);
			procedureDivision = result;
		}

		return result;
	}

	@Override
	public CopyBook getCopyBook() {
		return copyBook;
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

}
