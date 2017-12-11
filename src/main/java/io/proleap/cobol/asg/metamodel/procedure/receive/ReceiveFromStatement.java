/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive;

import io.proleap.cobol.Cobol85Parser.ReceiveBeforeContext;
import io.proleap.cobol.Cobol85Parser.ReceiveFromContext;
import io.proleap.cobol.Cobol85Parser.ReceiveSizeContext;
import io.proleap.cobol.Cobol85Parser.ReceiveStatusContext;
import io.proleap.cobol.Cobol85Parser.ReceiveThreadContext;
import io.proleap.cobol.Cobol85Parser.ReceiveWithContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface ReceiveFromStatement extends CobolDivisionElement {

	Before addBefore(ReceiveBeforeContext ctx);

	From addFrom(ReceiveFromContext ctx);

	Size addSize(ReceiveSizeContext ctx);

	Status addStatus(ReceiveStatusContext ctx);

	Thread addThread(ReceiveThreadContext ctx);

	With addWith(ReceiveWithContext ctx);

	Before getBefore();

	Call getDataCall();

	From getFrom();

	Size getSize();

	Status getStatus();

	Thread getThread();

	With getWith();

	void setDataCall(Call dataCall);

}
