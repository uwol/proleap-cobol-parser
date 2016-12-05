/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.applicationcontext;

import io.proleap.cobol.asg.registry.ASGElementRegistry;
import io.proleap.cobol.asg.resolver.NameResolver;
import io.proleap.cobol.asg.runner.CobolParserRunner;

public class CobolParserContext {

	protected static CobolParserContext instance;

	public static CobolParserContext getInstance() {
		if (instance == null) {
			instance = new CobolParserContext();
		}

		return instance;
	}

	protected ASGElementRegistry asgElementRegistry;

	protected NameResolver nameResolver;

	protected CobolParserRunner parserRunner;

	private CobolParserContext() {
		super();
	}

	public ASGElementRegistry getASGElementRegistry() {
		return asgElementRegistry;
	}

	public NameResolver getNameResolver() {
		return nameResolver;
	}

	public CobolParserRunner getParserRunner() {
		return parserRunner;
	}

	public void reset() {
		instance = null;
	}

	public void setASGElementRegistry(final ASGElementRegistry asgElementRegistry) {
		this.asgElementRegistry = asgElementRegistry;
	}

	public void setNameResolver(final NameResolver nameResolver) {
		this.nameResolver = nameResolver;
	}

	public void setParserRunner(final CobolParserRunner parserRunner) {
		this.parserRunner = parserRunner;
	}

}
