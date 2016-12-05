package io.proleap.cobol;

import org.junit.Before;

import io.proleap.cobol.asg.applicationcontext.CobolParserContextFactory;

public class CobolTestSupport {

	@Before
	public void setUp() throws Exception {
		CobolParserContextFactory.configureDefaultApplicationContext();
	}
}
