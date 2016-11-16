/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.file.impl;

import io.proleap.cobol.parser.metamodel.data.file.ValueOfNameValuePair;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class ValueOfNameValuePairImpl implements ValueOfNameValuePair {

	protected ValueStmt name;

	protected ValueStmt value;

	@Override
	public ValueStmt getName() {
		return name;
	}

	@Override
	public ValueStmt getValue() {
		return value;
	}

	@Override
	public void setName(final ValueStmt name) {
		this.name = name;
	}

	@Override
	public void setValue(final ValueStmt value) {
		this.value = value;

	}
}
