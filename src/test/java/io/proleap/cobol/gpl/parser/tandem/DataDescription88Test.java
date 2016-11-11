package io.proleap.cobol.gpl.parser.tandem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

public class DataDescription88Test extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/gpl/parser/tandem/DataDescription88.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("DataDescription88");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();

		assertEquals(3, dataDivision.getDataDescriptionEntries().size());
		assertEquals(1, dataDivision.getRootDataDescriptionEntries().size());

		final DataDescriptionEntry dataDescriptionEntryGender = dataDivision.getDataDescriptionEntry("GENDER");

		assertNotNull(dataDescriptionEntryGender);
		assertEquals("GENDER", dataDescriptionEntryGender.getName());
		assertEquals(DataDescriptionEntry.Type.Group, dataDescriptionEntryGender.getType());
		assertEquals(new Integer(1), dataDescriptionEntryGender.getLevelNumber());
		assertNull(dataDescriptionEntryGender.getDataDescriptionEntryGroup());

		final DataDescriptionEntry dataDescriptionEntryMale = dataDivision.getDataDescriptionEntry("MALE");

		assertNotNull(dataDescriptionEntryMale);
		assertEquals("MALE", dataDescriptionEntryMale.getName());
		assertEquals(DataDescriptionEntry.Type.Condition, dataDescriptionEntryMale.getType());
		assertEquals(new Integer(88), dataDescriptionEntryMale.getLevelNumber());
		assertEquals(dataDescriptionEntryGender, dataDescriptionEntryMale.getDataDescriptionEntryGroup());

		final DataDescriptionEntry dataDescriptionEntryFemale = dataDivision.getDataDescriptionEntry("FEMALE");

		assertNotNull(dataDescriptionEntryFemale);
		assertEquals("FEMALE", dataDescriptionEntryFemale.getName());
		assertEquals(DataDescriptionEntry.Type.Condition, dataDescriptionEntryFemale.getType());
		assertEquals(new Integer(88), dataDescriptionEntryFemale.getLevelNumber());
		assertEquals(dataDescriptionEntryGender, dataDescriptionEntryFemale.getDataDescriptionEntryGroup());
	}
}
