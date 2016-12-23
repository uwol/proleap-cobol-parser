/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.open.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.FileNameContext;
import io.proleap.cobol.Cobol85Parser.OpenExtendStatementContext;
import io.proleap.cobol.Cobol85Parser.OpenIOStatementContext;
import io.proleap.cobol.Cobol85Parser.OpenInputContext;
import io.proleap.cobol.Cobol85Parser.OpenInputStatementContext;
import io.proleap.cobol.Cobol85Parser.OpenOutputContext;
import io.proleap.cobol.Cobol85Parser.OpenOutputStatementContext;
import io.proleap.cobol.Cobol85Parser.OpenStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenExtend;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenInput;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenInputOutput;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenOutput;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenStatement;

public class OpenStatementImpl extends StatementImpl implements OpenStatement {

	protected final OpenStatementContext ctx;

	protected List<OpenExtend> openExtends = new ArrayList<OpenExtend>();

	protected List<OpenInputOutput> openInputOutputs = new ArrayList<OpenInputOutput>();

	protected List<OpenInput> openInputs = new ArrayList<OpenInput>();

	protected List<OpenOutput> openOutputs = new ArrayList<OpenOutput>();

	protected final StatementType statementType = StatementTypeEnum.OPEN;

	public OpenStatementImpl(final ProgramUnit programUnit, final Scope scope, final OpenStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public OpenExtend addOpenExtend(final OpenExtendStatementContext ctx) {
		OpenExtend result = (OpenExtend) getASGElement(ctx);

		if (result == null) {
			result = new OpenExtendImpl(programUnit, ctx);

			for (final FileNameContext fileNameContext : ctx.fileName()) {
				final Call fileCall = createCall(fileNameContext);
				result.addFileCall(fileCall);
			}

			openExtends.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public OpenInput addOpenInput(final OpenInputStatementContext ctx) {
		OpenInput result = (OpenInput) getASGElement(ctx);

		if (result == null) {
			result = new OpenInputImpl(programUnit, ctx);

			for (final OpenInputContext openInputContext : ctx.openInput()) {
				result.addOpenInput(openInputContext);
			}

			openInputs.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public OpenInputOutput addOpenInputOutput(final OpenIOStatementContext ctx) {
		OpenInputOutput result = (OpenInputOutput) getASGElement(ctx);

		if (result == null) {
			result = new OpenInputOutputImpl(programUnit, ctx);

			for (final FileNameContext fileNameContext : ctx.fileName()) {
				final Call fileCall = createCall(fileNameContext);
				result.addFileCall(fileCall);
			}

			openInputOutputs.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public OpenOutput addOpenOutput(final OpenOutputStatementContext ctx) {
		OpenOutput result = (OpenOutput) getASGElement(ctx);

		if (result == null) {
			result = new OpenOutputImpl(programUnit, ctx);

			for (final OpenOutputContext openOutputContext : ctx.openOutput()) {
				result.addOpenOutput(openOutputContext);
			}

			openOutputs.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<OpenExtend> getOpenExtends() {
		return openExtends;
	}

	@Override
	public List<OpenInputOutput> getOpenInputOutputs() {
		return openInputOutputs;
	}

	@Override
	public List<OpenInput> getOpenInputs() {
		return openInputs;
	}

	@Override
	public List<OpenOutput> getOpenOutputs() {
		return openOutputs;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
	}

}
