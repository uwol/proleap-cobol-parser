/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive;

import io.proleap.cobol.CobolParser.ReceiveBeforeContext;
import io.proleap.cobol.CobolParser.ReceiveFromContext;
import io.proleap.cobol.CobolParser.ReceiveSizeContext;
import io.proleap.cobol.CobolParser.ReceiveStatusContext;
import io.proleap.cobol.CobolParser.ReceiveThreadContext;
import io.proleap.cobol.CobolParser.ReceiveWithContext;
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
