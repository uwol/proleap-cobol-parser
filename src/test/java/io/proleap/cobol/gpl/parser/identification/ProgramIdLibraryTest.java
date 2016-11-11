package io.proleap.cobol.gpl.parser.identification;

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
import io.proleap.cobol.parser.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.parser.metamodel.identification.ProgramIdParagraph;
import io.proleap.cobol.parser.metamodel.identification.ProgramIdParagraph.Attribute;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ProgramIdLibraryTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/identification/ProgramIdLibrary.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("ProgramIdLibrary");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final IdentificationDivision identificationDivision = programUnit.getIdentificationDivision();
		final ProgramIdParagraph programIdParagraph = identificationDivision.getProgramIdParagraph();

		assertNotNull(programIdParagraph);
		assertEquals(Attribute.Library, programIdParagraph.getAttribute());
	}
}
