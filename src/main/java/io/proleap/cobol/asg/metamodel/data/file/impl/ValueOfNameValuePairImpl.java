/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file.impl;

import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.file.ValueOfNameValuePair;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class ValueOfNameValuePairImpl implements ValueOfNameValuePair {

	protected Call nameCall;

	protected ValueStmt value;

	@Override
	public Call getNameCall() {
		return nameCall;
	}

	@Override
	public ValueStmt getValue() {
		return value;
	}

	@Override
	public void setNameCall(final Call nameCall) {
		this.nameCall = nameCall;
	}

	@Override
	public void setValue(final ValueStmt value) {
		this.value = value;

	}
}
