/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive;

import io.proleap.cobol.CobolParser.ReceiveNoDataContext;
import io.proleap.cobol.CobolParser.ReceiveWithDataContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface ReceiveIntoStatement extends CobolDivisionElement {

	enum ReceiveIntoType {
		MESSAGE, SEGMENT
	}

	NoData addNoData(ReceiveNoDataContext ctx);

	WithData addWithData(ReceiveWithDataContext ctx);

	Call getCommunicationDescriptionCall();

	Call getIntoCall();

	NoData getNoData();

	ReceiveIntoType getReceiveIntoType();

	WithData getWithData();

	void setCommunicationDescriptionCall(Call communicationDescriptionCall);

	void setIntoCall(Call intoCall);

	void setReceiveIntoType(ReceiveIntoType receiveIntoType);
}
