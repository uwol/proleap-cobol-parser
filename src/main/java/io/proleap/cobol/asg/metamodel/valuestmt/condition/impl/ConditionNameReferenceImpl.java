/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.CobolParser.ConditionNameReferenceContext;
import io.proleap.cobol.CobolParser.ConditionNameSubscriptReferenceContext;
import io.proleap.cobol.CobolParser.InDataContext;
import io.proleap.cobol.CobolParser.InFileContext;
import io.proleap.cobol.CobolParser.InMnemonicContext;
import io.proleap.cobol.CobolParser.SubscriptContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.ConditionNameReference;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.ConditionNameSubscriptReference;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.in.InData;
import io.proleap.cobol.asg.metamodel.valuestmt.in.InFile;
import io.proleap.cobol.asg.metamodel.valuestmt.in.InMnemonic;
import io.proleap.cobol.asg.metamodel.valuestmt.in.impl.InDataImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.in.impl.InFileImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.in.impl.InMnemonicImpl;

public class ConditionNameReferenceImpl extends ValueStmtImpl implements ConditionNameReference {

	protected Call conditionCall;

	protected ConditionNameReferenceContext ctx;

	protected List<InData> inDatas = new ArrayList<InData>();

	protected InFile inFile;

	protected List<InMnemonic> inMnemonics = new ArrayList<InMnemonic>();

	protected List<ConditionNameSubscriptReference> subscriptReferences = new ArrayList<ConditionNameSubscriptReference>();

	public ConditionNameReferenceImpl(final ProgramUnit programUnit, final ConditionNameReferenceContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public InData addInData(final InDataContext ctx) {
		InData result = (InData) getASGElement(ctx);

		if (result == null) {
			result = new InDataImpl(programUnit, ctx);

			final Call dataCall = createCall(ctx.dataName());
			result.setDataCall(dataCall);

			inDatas.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public InFile addInFile(final InFileContext ctx) {
		InFile result = (InFile) getASGElement(ctx);

		if (result == null) {
			result = new InFileImpl(programUnit, ctx);

			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			inFile = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public InMnemonic addInMnemonic(final InMnemonicContext ctx) {
		InMnemonic result = (InMnemonic) getASGElement(ctx);

		if (result == null) {
			result = new InMnemonicImpl(programUnit, ctx);

			final Call mnemonicCall = createCall(ctx.mnemonicName());
			result.setMnemonicCall(mnemonicCall);

			inMnemonics.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ConditionNameSubscriptReference addSubscriptReference(final ConditionNameSubscriptReferenceContext ctx) {
		ConditionNameSubscriptReference result = (ConditionNameSubscriptReference) getASGElement(ctx);

		if (result == null) {
			result = new ConditionNameSubscriptReferenceImpl(programUnit, ctx);

			for (final SubscriptContext subscriptContext : ctx.subscript()) {
				result.addSubscript(subscriptContext);
			}

			subscriptReferences.add(result);
			subValueStmts.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Call getConditionCall() {
		return conditionCall;
	}

	@Override
	public List<ConditionNameSubscriptReference> getConditionNameSubscriptReferences() {
		return subscriptReferences;
	}

	@Override
	public List<InData> getInDatas() {
		return inDatas;
	}

	@Override
	public InFile getInFile() {
		return inFile;
	}

	@Override
	public List<InMnemonic> getInMnemonics() {
		return inMnemonics;
	}

	@Override
	public void setConditionCall(final Call conditionCall) {
		this.conditionCall = conditionCall;
	}
}
