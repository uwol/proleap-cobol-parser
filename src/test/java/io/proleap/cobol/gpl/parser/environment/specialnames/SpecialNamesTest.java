package io.proleap.cobol.gpl.parser.environment.specialnames;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.environment.specialnames.ChannelClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.SpecialNamesParagraph;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SpecialNamesTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/environment/specialnames/SpecialNames.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("SpecialNames");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();
		final SpecialNamesParagraph specialNamesParagraph = environmentDivision.getSpecialNamesParagraph();

		{
			final ChannelClause channelClause = specialNamesParagraph.getChannelClause();
			assertNotNull(channelClause);
			assertEquals(new Integer(2), channelClause.getIntegerLiteral().getValue());
			assertEquals("SOMEMNEMONIC", channelClause.getMnemonicName().getValue());
		}
	}
}
