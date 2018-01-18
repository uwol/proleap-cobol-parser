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

public class DataDescription77Test extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/workingstorage/DataDescription77.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DataDescription77");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(2, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(2, workingStorageSection.getRootDataDescriptionEntries().size());

		{
			final DataDescriptionEntry dataDescriptionEntrySomeText = workingStorageSection
					.getDataDescriptionEntry("SOME-TEXT");

			assertNotNull(dataDescriptionEntrySomeText);
			assertEquals("SOME-TEXT", dataDescriptionEntrySomeText.getName());
			assertEquals(DataDescriptionEntryType.SCALAR, dataDescriptionEntrySomeText.getDataDescriptionEntryType());
			assertEquals(Integer.valueOf(77), dataDescriptionEntrySomeText.getLevelNumber());
			assertNull(dataDescriptionEntrySomeText.getParentDataDescriptionEntryGroup());
		}

		{
			final DataDescriptionEntry dataDescriptionEntrySomeNumber = workingStorageSection
					.getDataDescriptionEntry("SOME-NUMBER");

			assertNotNull(dataDescriptionEntrySomeNumber);
			assertEquals("SOME-NUMBER", dataDescriptionEntrySomeNumber.getName());
			assertEquals(DataDescriptionEntryType.SCALAR, dataDescriptionEntrySomeNumber.getDataDescriptionEntryType());
			assertEquals(Integer.valueOf(77), dataDescriptionEntrySomeNumber.getLevelNumber());
			assertNull(dataDescriptionEntrySomeNumber.getParentDataDescriptionEntryGroup());
		}
	}
}
