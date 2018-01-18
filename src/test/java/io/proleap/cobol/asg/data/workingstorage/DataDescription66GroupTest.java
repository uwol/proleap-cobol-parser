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
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry.DataDescriptionEntryType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryRename;
import io.proleap.cobol.asg.metamodel.data.datadescription.RenamesClause;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DataDescription66GroupTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/workingstorage/DataDescription66Group.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DataDescription66Group");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(4, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(2, workingStorageSection.getRootDataDescriptionEntries().size());

		final DataDescriptionEntry dataDescriptionEntryItems;

		{
			dataDescriptionEntryItems = workingStorageSection.getDataDescriptionEntry("ITEMS");

			assertNotNull(dataDescriptionEntryItems);
			assertEquals("ITEMS", dataDescriptionEntryItems.getName());
			assertEquals(Integer.valueOf(1), dataDescriptionEntryItems.getLevelNumber());
			assertNull(dataDescriptionEntryItems.getParentDataDescriptionEntryGroup());
			assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItems.getDataDescriptionEntryType());

			{
				final DataDescriptionEntry dataDescriptionEntryItem1 = workingStorageSection
						.getDataDescriptionEntry("ITEM1");
				assertNotNull(dataDescriptionEntryItem1);
				assertEquals("ITEM1", dataDescriptionEntryItem1.getName());
				assertEquals(Integer.valueOf(2), dataDescriptionEntryItem1.getLevelNumber());
				assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem1.getParentDataDescriptionEntryGroup());
				assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItem1.getDataDescriptionEntryType());
			}

			{
				final DataDescriptionEntry dataDescriptionEntryItem2 = workingStorageSection
						.getDataDescriptionEntry("ITEM2");
				assertNotNull(dataDescriptionEntryItem2);
				assertEquals("ITEM2", dataDescriptionEntryItem2.getName());
				assertEquals(Integer.valueOf(2), dataDescriptionEntryItem2.getLevelNumber());
				assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem2.getParentDataDescriptionEntryGroup());
				assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItem2.getDataDescriptionEntryType());
			}
		}

		{
			final DataDescriptionEntry dataDescriptionEntryItemz = workingStorageSection
					.getDataDescriptionEntry("ITEMZ");

			assertNotNull(dataDescriptionEntryItemz);
			assertEquals("ITEMZ", dataDescriptionEntryItemz.getName());
			assertEquals(Integer.valueOf(66), dataDescriptionEntryItemz.getLevelNumber());
			assertNull(dataDescriptionEntryItemz.getParentDataDescriptionEntryGroup());
			assertEquals(DataDescriptionEntryType.RENAME, dataDescriptionEntryItemz.getDataDescriptionEntryType());

			final DataDescriptionEntryRename dataDescriptionEntryItemzRename = (DataDescriptionEntryRename) dataDescriptionEntryItemz;

			{
				final RenamesClause renamesClause = dataDescriptionEntryItemzRename.getRenamesClause();

				{
					assertNotNull(renamesClause.getFrom());
					final DataDescriptionEntryCall from = (DataDescriptionEntryCall) renamesClause.getFrom().unwrap();
					assertEquals(dataDescriptionEntryItems, from.getDataDescriptionEntry());
				}

				assertNull(renamesClause.getTo());

				{
					assertEquals(1, renamesClause.getCalls().size());

					{
						final DataDescriptionEntryCall call1 = (DataDescriptionEntryCall) renamesClause.getCalls()
								.get(0).unwrap();
						assertEquals(dataDescriptionEntryItems, call1.getDataDescriptionEntry());
					}
				}
			}
		}
	}
}
