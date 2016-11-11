package io.proleap.cobol.gpl.parser.tandem;

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
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DataDescription77Test extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/gpl/parser/tandem/DataDescription77.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("DataDescription77");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();

		final DataDescriptionEntry dataDescriptionEntrySomeText = dataDivision.getDataDescriptionEntry("SOME-TEXT");
		assertNotNull(dataDescriptionEntrySomeText);
		assertEquals("SOME-TEXT", dataDescriptionEntrySomeText.getName());
		assertEquals(new Integer(77), dataDescriptionEntrySomeText.getLevelNumber());

		final DataDescriptionEntry dataDescriptionEntrySomeNumber = dataDivision.getDataDescriptionEntry("SOME-NUMBER");
		assertNotNull(dataDescriptionEntrySomeNumber);
		assertEquals("SOME-NUMBER", dataDescriptionEntrySomeNumber.getName());
		assertEquals(new Integer(77), dataDescriptionEntrySomeNumber.getLevelNumber());
	}
}
