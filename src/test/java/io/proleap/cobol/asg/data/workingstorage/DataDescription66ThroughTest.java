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
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryRename;
import io.proleap.cobol.asg.metamodel.data.datadescription.RenamesClause;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DataDescription66ThroughTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/workingstorage/DataDescription66Through.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DataDescription66Through");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(6, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(2, workingStorageSection.getRootDataDescriptionEntries().size());

		{
			final DataDescriptionEntry dataDescriptionEntryItems = workingStorageSection
					.getDataDescriptionEntry("ITEMS");

			assertNotNull(dataDescriptionEntryItems);
			assertEquals("ITEMS", dataDescriptionEntryItems.getName());
			assertEquals(DataDescriptionEntry.Type.GROUP, dataDescriptionEntryItems.getType());
			assertEquals(new Integer(1), dataDescriptionEntryItems.getLevelNumber());
			assertNull(dataDescriptionEntryItems.getParentDataDescriptionEntryGroup());

			{
				final DataDescriptionEntry dataDescriptionEntryItem1 = workingStorageSection
						.getDataDescriptionEntry("ITEM1");
				assertNotNull(dataDescriptionEntryItem1);
				assertEquals("ITEM1", dataDescriptionEntryItem1.getName());
				assertEquals(DataDescriptionEntry.Type.GROUP, dataDescriptionEntryItem1.getType());
				assertEquals(new Integer(2), dataDescriptionEntryItem1.getLevelNumber());
				assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem1.getParentDataDescriptionEntryGroup());
			}

			{
				final DataDescriptionEntry dataDescriptionEntryItem2 = workingStorageSection
						.getDataDescriptionEntry("ITEM2");
				assertNotNull(dataDescriptionEntryItem2);
				assertEquals("ITEM2", dataDescriptionEntryItem2.getName());
				assertEquals(DataDescriptionEntry.Type.GROUP, dataDescriptionEntryItem2.getType());
				assertEquals(new Integer(2), dataDescriptionEntryItem2.getLevelNumber());
				assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem2.getParentDataDescriptionEntryGroup());
			}

			{
				final DataDescriptionEntry dataDescriptionEntryItem3 = workingStorageSection
						.getDataDescriptionEntry("ITEM3");
				assertNotNull(dataDescriptionEntryItem3);
				assertEquals("ITEM3", dataDescriptionEntryItem3.getName());
				assertEquals(DataDescriptionEntry.Type.GROUP, dataDescriptionEntryItem3.getType());
				assertEquals(new Integer(2), dataDescriptionEntryItem3.getLevelNumber());
				assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem3.getParentDataDescriptionEntryGroup());
			}

			{
				final DataDescriptionEntry dataDescriptionEntryItem4 = workingStorageSection
						.getDataDescriptionEntry("ITEM4");
				assertNotNull(dataDescriptionEntryItem4);
				assertEquals("ITEM4", dataDescriptionEntryItem4.getName());
				assertEquals(DataDescriptionEntry.Type.GROUP, dataDescriptionEntryItem4.getType());
				assertEquals(new Integer(2), dataDescriptionEntryItem4.getLevelNumber());
				assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem4.getParentDataDescriptionEntryGroup());
			}
		}

		{
			final DataDescriptionEntry dataDescriptionEntryItemz = workingStorageSection
					.getDataDescriptionEntry("ITEMZ");

			assertNotNull(dataDescriptionEntryItemz);
			assertEquals("ITEMZ", dataDescriptionEntryItemz.getName());
			assertEquals(new Integer(66), dataDescriptionEntryItemz.getLevelNumber());
			assertNull(dataDescriptionEntryItemz.getParentDataDescriptionEntryGroup());

			final DataDescriptionEntryRename dataDescriptionEntryItemzRename = (DataDescriptionEntryRename) dataDescriptionEntryItemz;
			assertEquals(DataDescriptionEntry.Type.RENAME, dataDescriptionEntryItemz.getType());

			{
				final RenamesClause renamesClause = dataDescriptionEntryItemzRename.getRenamesClause();
				assertNotNull(renamesClause.getFrom());
				assertNotNull(renamesClause.getTo());
			}
		}
	}
}
