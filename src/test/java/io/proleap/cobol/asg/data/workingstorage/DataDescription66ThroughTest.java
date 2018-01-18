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
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
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

		final DataDescriptionEntry dataDescriptionEntryItem1;
		final DataDescriptionEntry dataDescriptionEntryItem2;
		final DataDescriptionEntry dataDescriptionEntryItem3;

		{
			final DataDescriptionEntry dataDescriptionEntryItems = workingStorageSection
					.getDataDescriptionEntry("ITEMS");

			assertNotNull(dataDescriptionEntryItems);
			assertEquals("ITEMS", dataDescriptionEntryItems.getName());
			assertEquals(Integer.valueOf(1), dataDescriptionEntryItems.getLevelNumber());
			assertNull(dataDescriptionEntryItems.getParentDataDescriptionEntryGroup());
			assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItems.getDataDescriptionEntryType());

			final DataDescriptionEntryGroup dataDescriptionEntryGroupItems = (DataDescriptionEntryGroup) dataDescriptionEntryItems;
			assertEquals(0, dataDescriptionEntryGroupItems.getCalls().size());

			{
				dataDescriptionEntryItem1 = workingStorageSection.getDataDescriptionEntry("ITEM1");
				assertNotNull(dataDescriptionEntryItem1);
				assertEquals("ITEM1", dataDescriptionEntryItem1.getName());
				assertEquals(Integer.valueOf(2), dataDescriptionEntryItem1.getLevelNumber());
				assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem1.getParentDataDescriptionEntryGroup());
				assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItem1.getDataDescriptionEntryType());

				final DataDescriptionEntryGroup dataDescriptionEntryGroupItem1 = (DataDescriptionEntryGroup) dataDescriptionEntryItem1;
				assertEquals(1, dataDescriptionEntryGroupItem1.getCalls().size());
			}

			{
				dataDescriptionEntryItem2 = workingStorageSection.getDataDescriptionEntry("ITEM2");
				assertNotNull(dataDescriptionEntryItem2);
				assertEquals("ITEM2", dataDescriptionEntryItem2.getName());
				assertEquals(Integer.valueOf(2), dataDescriptionEntryItem2.getLevelNumber());
				assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem2.getParentDataDescriptionEntryGroup());
				assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItem2.getDataDescriptionEntryType());

				final DataDescriptionEntryGroup dataDescriptionEntryGroupItem2 = (DataDescriptionEntryGroup) dataDescriptionEntryItem2;
				assertEquals(1, dataDescriptionEntryGroupItem2.getCalls().size());
			}

			{
				dataDescriptionEntryItem3 = workingStorageSection.getDataDescriptionEntry("ITEM3");
				assertNotNull(dataDescriptionEntryItem3);
				assertEquals("ITEM3", dataDescriptionEntryItem3.getName());
				assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItem3.getDataDescriptionEntryType());
				assertEquals(Integer.valueOf(2), dataDescriptionEntryItem3.getLevelNumber());
				assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem3.getParentDataDescriptionEntryGroup());

				final DataDescriptionEntryGroup dataDescriptionEntryGroupItem3 = (DataDescriptionEntryGroup) dataDescriptionEntryItem3;
				assertEquals(1, dataDescriptionEntryGroupItem3.getCalls().size());
			}

			{
				final DataDescriptionEntry dataDescriptionEntryItem4 = workingStorageSection
						.getDataDescriptionEntry("ITEM4");
				assertNotNull(dataDescriptionEntryItem4);
				assertEquals("ITEM4", dataDescriptionEntryItem4.getName());
				assertEquals(Integer.valueOf(2), dataDescriptionEntryItem4.getLevelNumber());
				assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem4.getParentDataDescriptionEntryGroup());
				assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItem4.getDataDescriptionEntryType());

				final DataDescriptionEntryGroup dataDescriptionEntryGroupItem4 = (DataDescriptionEntryGroup) dataDescriptionEntryItem4;
				assertEquals(0, dataDescriptionEntryGroupItem4.getCalls().size());
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
					assertEquals(dataDescriptionEntryItem1, from.getDataDescriptionEntry());
				}

				{
					assertNotNull(renamesClause.getTo());

					final DataDescriptionEntryCall to = (DataDescriptionEntryCall) renamesClause.getTo().unwrap();
					assertEquals(dataDescriptionEntryItem3, to.getDataDescriptionEntry());
				}

				{
					assertEquals(3, renamesClause.getCalls().size());

					{
						final DataDescriptionEntryCall call1 = (DataDescriptionEntryCall) renamesClause.getCalls()
								.get(0).unwrap();
						assertEquals(dataDescriptionEntryItem1, call1.getDataDescriptionEntry());
					}

					{
						final DataDescriptionEntryCall call2 = (DataDescriptionEntryCall) renamesClause.getCalls()
								.get(1).unwrap();
						assertEquals(dataDescriptionEntryItem2, call2.getDataDescriptionEntry());
					}

					{
						final DataDescriptionEntryCall call3 = (DataDescriptionEntryCall) renamesClause.getCalls()
								.get(2).unwrap();
						assertEquals(dataDescriptionEntryItem3, call3.getDataDescriptionEntry());
					}
				}
			}
		}
	}
}
