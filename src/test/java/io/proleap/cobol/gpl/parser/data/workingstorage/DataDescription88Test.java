package io.proleap.cobol.gpl.parser.data.workingstorage;

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
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DataDescription88Test extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/data/workingstorage/DataDescription88.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("DataDescription88");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(3, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(1, workingStorageSection.getRootDataDescriptionEntries().size());

		final DataDescriptionEntry dataDescriptionEntryGender = workingStorageSection.getDataDescriptionEntry("GENDER");

		assertNotNull(dataDescriptionEntryGender);
		assertEquals("GENDER", dataDescriptionEntryGender.getName());
		assertEquals(DataDescriptionEntry.Type.Group, dataDescriptionEntryGender.getType());
		assertEquals(new Integer(1), dataDescriptionEntryGender.getLevelNumber());
		assertNull(dataDescriptionEntryGender.getDataDescriptionEntryGroup());

		final DataDescriptionEntry dataDescriptionEntryMale = workingStorageSection.getDataDescriptionEntry("MALE");

		assertNotNull(dataDescriptionEntryMale);
		assertEquals("MALE", dataDescriptionEntryMale.getName());
		assertEquals(DataDescriptionEntry.Type.Condition, dataDescriptionEntryMale.getType());
		assertEquals(new Integer(88), dataDescriptionEntryMale.getLevelNumber());
		assertEquals(dataDescriptionEntryGender, dataDescriptionEntryMale.getDataDescriptionEntryGroup());

		final DataDescriptionEntry dataDescriptionEntryFemale = workingStorageSection.getDataDescriptionEntry("FEMALE");

		assertNotNull(dataDescriptionEntryFemale);
		assertEquals("FEMALE", dataDescriptionEntryFemale.getName());
		assertEquals(DataDescriptionEntry.Type.Condition, dataDescriptionEntryFemale.getType());
		assertEquals(new Integer(88), dataDescriptionEntryFemale.getLevelNumber());
		assertEquals(dataDescriptionEntryGender, dataDescriptionEntryFemale.getDataDescriptionEntryGroup());
	}
}
