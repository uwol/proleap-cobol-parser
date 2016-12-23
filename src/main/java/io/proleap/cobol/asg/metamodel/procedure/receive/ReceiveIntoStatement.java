/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.receive;

import io.proleap.cobol.Cobol85Parser.ReceiveNoDataContext;
import io.proleap.cobol.Cobol85Parser.ReceiveWithDataContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface ReceiveIntoStatement extends CobolDivisionElement {

	enum Type {
		MESSAGE, SEGMENT
	}

	NoData addNoData(ReceiveNoDataContext ctx);

	WithData addWithData(ReceiveWithDataContext ctx);

	Call getCommunicationDescriptionCall();

	Call getIntoCall();

	NoData getNoData();

	Type getType();

	WithData getWithData();

	void setCommunicationDescriptionCall(Call communicationDescriptionCall);

	void setIntoCall(Call intoCall);

	void setType(Type type);

}
