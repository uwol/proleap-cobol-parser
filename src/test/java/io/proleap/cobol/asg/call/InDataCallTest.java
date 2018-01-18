package io.proleap.cobol.asg.call;

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

public class InDataCallTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/call/InDataCall.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("InDataCall");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(4, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(2, workingStorageSection.getRootDataDescriptionEntries().size());

		assertEquals(DataDescriptionEntryContainerType.WORKING_STORAGE_SECTION,
				workingStorageSection.getContainerType());

		final DataDescriptionEntry dataDescriptionEntryItems1 = workingStorageSection.getDataDescriptionEntry("ITEMS1");
		final DataDescriptionEntry dataDescriptionEntryItems2 = workingStorageSection.getDataDescriptionEntry("ITEMS2");

		{
			assertNotNull(dataDescriptionEntryItems1);
			assertEquals("ITEMS1", dataDescriptionEntryItems1.getName());
			assertEquals(Integer.valueOf(1), dataDescriptionEntryItems1.getLevelNumber());
			assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItems1.getDataDescriptionEntryType());
			assertNull(dataDescriptionEntryItems1.getParentDataDescriptionEntryGroup());
			assertEquals(0, dataDescriptionEntryItems1.getCalls().size());

			final DataDescriptionEntryGroup dataDescriptionEntryGroupItems1 = (DataDescriptionEntryGroup) dataDescriptionEntryItems1;

			{
				final DataDescriptionEntry dataDescriptionEntryItem = dataDescriptionEntryGroupItems1
						.getDataDescriptionEntry("ITEM");
				assertEquals(dataDescriptionEntryItems1, dataDescriptionEntryItem.getParentDataDescriptionEntryGroup());
				assertEquals(dataDescriptionEntryItem, workingStorageSection.getDataDescriptionEntries("ITEM").get(0));

				assertNotNull(dataDescriptionEntryItem);
				assertEquals("ITEM", dataDescriptionEntryItem.getName());
				assertEquals(Integer.valueOf(2), dataDescriptionEntryItem.getLevelNumber());
				assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItem.getDataDescriptionEntryType());
				assertEquals(0, dataDescriptionEntryItem.getCalls().size());
			}
		}

		{
			assertNotNull(dataDescriptionEntryItems2);
			assertEquals("ITEMS2", dataDescriptionEntryItems2.getName());
			assertEquals(Integer.valueOf(1), dataDescriptionEntryItems2.getLevelNumber());
			assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItems2.getDataDescriptionEntryType());
			assertNull(dataDescriptionEntryItems2.getParentDataDescriptionEntryGroup());
			assertEquals(0, dataDescriptionEntryItems2.getCalls().size());

			final DataDescriptionEntryGroup dataDescriptionEntryGroupItems2 = (DataDescriptionEntryGroup) dataDescriptionEntryItems2;

			{
				final DataDescriptionEntry dataDescriptionEntryItem = dataDescriptionEntryGroupItems2
						.getDataDescriptionEntry("ITEM");
				assertEquals(dataDescriptionEntryItems2, dataDescriptionEntryItem.getParentDataDescriptionEntryGroup());
				assertEquals(dataDescriptionEntryItem, workingStorageSection.getDataDescriptionEntries("ITEM").get(1));

				assertNotNull(dataDescriptionEntryItem);
				assertEquals("ITEM", dataDescriptionEntryItem.getName());
				assertEquals(Integer.valueOf(2), dataDescriptionEntryItem.getLevelNumber());
				assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItem.getDataDescriptionEntryType());
				assertEquals(1, dataDescriptionEntryItem.getCalls().size());
			}
		}
	}
}