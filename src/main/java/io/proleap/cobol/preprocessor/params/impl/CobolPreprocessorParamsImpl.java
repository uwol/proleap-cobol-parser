/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.params.impl;

import io.proleap.cobol.preprocessor.params.CobolDialect;
import io.proleap.cobol.preprocessor.params.CobolPreprocessorParams;

public class CobolPreprocessorParamsImpl implements CobolPreprocessorParams {

	protected CobolDialect dialect;

	@Override
	public CobolDialect getDialect() {
		return dialect;
	}

	@Override
	public void setDialect(final CobolDialect dialect) {
		this.dialect = dialect;
	}
}
