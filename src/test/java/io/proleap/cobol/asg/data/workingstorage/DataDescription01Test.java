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
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryContainer.DataDescriptionEntryContainerType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DataDescription01Test extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/workingstorage/DataDescription01.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DataDescription01");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(4, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(2, workingStorageSection.getRootDataDescriptionEntries().size());

		assertEquals(DataDescriptionEntryContainerType.WORKING_STORAGE_SECTION,
				workingStorageSection.getContainerType());

		final DataDescriptionEntry dataDescriptionEntryItems1 = workingStorageSection.getDataDescriptionEntry("ITEMS1");
		final DataDescriptionEntry dataDescriptionEntryItems2 = workingStorageSection.getDataDescriptionEntry("ITEMS2");

		final DataDescriptionEntry dataDescriptionEntryItem1 = workingStorageSection.getDataDescriptionEntry("ITEM1");
		final DataDescriptionEntry dataDescriptionEntryItem2 = workingStorageSection.getDataDescriptionEntry("ITEM2");

		{
			assertNotNull(dataDescriptionEntryItems1);
			assertEquals("ITEMS1", dataDescriptionEntryItems1.getName());
			assertEquals(Integer.valueOf(1), dataDescriptionEntryItems1.getLevelNumber());
			assertNull(dataDescriptionEntryItems1.getParentDataDescriptionEntryGroup());
			assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItems1.getDataDescriptionEntryType());

			final DataDescriptionEntryGroup dataDescriptionEntryGroupItems = (DataDescriptionEntryGroup) dataDescriptionEntryItems1;
			assertNull(dataDescriptionEntryGroupItems.getDataDescriptionEntryPredecessor());
			assertNotNull(dataDescriptionEntryGroupItems.getDataDescriptionEntrySuccessor());
			assertEquals(dataDescriptionEntryItems2, dataDescriptionEntryItems1.getDataDescriptionEntrySuccessor());

			{
				assertNotNull(dataDescriptionEntryItem1);
				assertEquals("ITEM1", dataDescriptionEntryItem1.getName());
				assertEquals(Integer.valueOf(2), dataDescriptionEntryItem1.getLevelNumber());
				assertEquals(dataDescriptionEntryItems1,
						dataDescriptionEntryItem1.getParentDataDescriptionEntryGroup());
				assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItem1.getDataDescriptionEntryType());

				final DataDescriptionEntryGroup dataDescriptionEntryGroupItem1 = (DataDescriptionEntryGroup) dataDescriptionEntryItem1;
				assertNull(dataDescriptionEntryGroupItem1.getDataDescriptionEntryPredecessor());
				assertNotNull(dataDescriptionEntryGroupItem1.getDataDescriptionEntrySuccessor());
				assertEquals(dataDescriptionEntryItem2, dataDescriptionEntryItem1.getDataDescriptionEntrySuccessor());
			}

			{
				assertNotNull(dataDescriptionEntryItem2);
				assertEquals("ITEM2", dataDescriptionEntryItem2.getName());
				assertEquals(Integer.valueOf(2), dataDescriptionEntryItem2.getLevelNumber());
				assertEquals(dataDescriptionEntryItems1,
						dataDescriptionEntryItem2.getParentDataDescriptionEntryGroup());
				assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItem2.getDataDescriptionEntryType());

				final DataDescriptionEntryGroup dataDescriptionEntryGroupItem2 = (DataDescriptionEntryGroup) dataDescriptionEntryItem2;
				assertNotNull(dataDescriptionEntryGroupItem2.getDataDescriptionEntryPredecessor());
				assertEquals(dataDescriptionEntryItem1, dataDescriptionEntryItem2.getDataDescriptionEntryPredecessor());
				assertNull(dataDescriptionEntryGroupItem2.getDataDescriptionEntrySuccessor());
			}
		}

		{
			assertNotNull(dataDescriptionEntryItems2);
			assertEquals("ITEMS2", dataDescriptionEntryItems2.getName());
			assertEquals(Integer.valueOf(1), dataDescriptionEntryItems2.getLevelNumber());
			assertNull(dataDescriptionEntryItems2.getParentDataDescriptionEntryGroup());
			assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItems2.getDataDescriptionEntryType());

			final DataDescriptionEntryGroup dataDescriptionEntryGroupItems = (DataDescriptionEntryGroup) dataDescriptionEntryItems2;
			assertNotNull(dataDescriptionEntryGroupItems.getDataDescriptionEntryPredecessor());
			assertEquals(dataDescriptionEntryItems1, dataDescriptionEntryItems2.getDataDescriptionEntryPredecessor());
			assertNull(dataDescriptionEntryGroupItems.getDataDescriptionEntrySuccessor());
		}
	}
}
