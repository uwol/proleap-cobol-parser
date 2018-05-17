/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition;

import java.util.List;

import io.proleap.cobol.CobolParser.ConditionNameSubscriptReferenceContext;
import io.proleap.cobol.CobolParser.InDataContext;
import io.proleap.cobol.CobolParser.InFileContext;
import io.proleap.cobol.CobolParser.InMnemonicContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.in.InData;
import io.proleap.cobol.asg.metamodel.valuestmt.in.InFile;
import io.proleap.cobol.asg.metamodel.valuestmt.in.InMnemonic;

public interface ConditionNameReference extends ValueStmt {

	InData addInData(InDataContext ctx);

	InFile addInFile(InFileContext ctx);

	InMnemonic addInMnemonic(InMnemonicContext ctx);

	ConditionNameSubscriptReference addSubscriptReference(ConditionNameSubscriptReferenceContext ctx);

	Call getConditionCall();

	List<ConditionNameSubscriptReference> getConditionNameSubscriptReferences();

	List<InData> getInDatas();

	InFile getInFile();

	List<InMnemonic> getInMnemonics();

	void setConditionCall(Call conditionCall);

}
