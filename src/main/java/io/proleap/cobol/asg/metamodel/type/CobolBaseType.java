/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.type;

public enum CobolBaseType implements BaseType {
	BOOLEAN("Boolean"), FLOAT("Float"), INTEGER("Integer"), STRING("String");

	public static CobolBaseType forString(final String name) {
		for (final CobolBaseType cobolType : values()) {
			if (cobolType.getName().equals(name)) {
				return cobolType;
			}
		}

		return null;
	}

	protected final String name;

	private CobolBaseType(final String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
}
