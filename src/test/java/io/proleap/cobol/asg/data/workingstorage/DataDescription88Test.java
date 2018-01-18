package io.proleap.cobol.asg.data.workingstorage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry.DataDescriptionEntryType;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DataDescription88Test extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/workingstorage/DataDescription88.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DataDescription88");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(3, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(1, workingStorageSection.getRootDataDescriptionEntries().size());

		{
			final DataDescriptionEntry dataDescriptionEntryGender = workingStorageSection
					.getDataDescriptionEntry("GENDER");

			assertNotNull(dataDescriptionEntryGender);
			assertEquals("GENDER", dataDescriptionEntryGender.getName());
			assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryGender.getDataDescriptionEntryType());
			assertEquals(Integer.valueOf(1), dataDescriptionEntryGender.getLevelNumber());
			assertNull(dataDescriptionEntryGender.getParentDataDescriptionEntryGroup());

			{
				final DataDescriptionEntry dataDescriptionEntryMale = workingStorageSection
						.getDataDescriptionEntry("MALE");

				assertNotNull(dataDescriptionEntryMale);
				assertEquals("MALE", dataDescriptionEntryMale.getName());
				assertEquals(DataDescriptionEntryType.CONDITION,
						dataDescriptionEntryMale.getDataDescriptionEntryType());
				assertEquals(Integer.valueOf(88), dataDescriptionEntryMale.getLevelNumber());
				assertEquals(dataDescriptionEntryGender, dataDescriptionEntryMale.getParentDataDescriptionEntryGroup());
			}

			{
				final DataDescriptionEntry dataDescriptionEntryFemale = workingStorageSection
						.getDataDescriptionEntry("FEMALE");

				assertNotNull(dataDescriptionEntryFemale);
				assertEquals("FEMALE", dataDescriptionEntryFemale.getName());
				assertEquals(DataDescriptionEntryType.CONDITION,
						dataDescriptionEntryFemale.getDataDescriptionEntryType());
				assertEquals(Integer.valueOf(88), dataDescriptionEntryFemale.getLevelNumber());
				assertEquals(dataDescriptionEntryGender,
						dataDescriptionEntryFemale.getParentDataDescriptionEntryGroup());
			}
		}
	}
}
