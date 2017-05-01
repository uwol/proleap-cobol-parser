package io.proleap.cobol;

import org.junit.Before;

import io.proleap.cobol.asg.applicationcontext.CobolParserContextFactory;

public class CobolTestSupport {

	protected final static Double EPSILON = 0.000001;

	@Before
	public void setUp() throws Exception {
		CobolParserContextFactory.configureDefaultApplicationContext();
	}
}
