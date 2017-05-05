package io.proleap.cobol.asg.data.localstorage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.localstorage.LocalStorageSection;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DataDescription02Test extends CobolTestSupport {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/localstorage/DataDescription02.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DataDescription02");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final LocalStorageSection localStorageSection = dataDivision.getLocalStorageSection();

		assertNull(localStorageSection.getName());

		assertEquals(2, localStorageSection.getDataDescriptionEntries().size());
		assertEquals(1, localStorageSection.getRootDataDescriptionEntries().size());

		{
			final DataDescriptionEntry dataDescriptionEntryItems = localStorageSection
					.getDataDescriptionEntry("ITEMS");
			assertNotNull(dataDescriptionEntryItems);
			assertEquals("ITEMS", dataDescriptionEntryItems.getName());
			assertEquals(DataDescriptionEntry.Type.GROUP, dataDescriptionEntryItems.getType());
			assertEquals(new Integer(1), dataDescriptionEntryItems.getLevelNumber());
			assertNull(dataDescriptionEntryItems.getParentDataDescriptionEntryGroup());

			{
				final DataDescriptionEntry dataDescriptionEntryItem1 = localStorageSection
						.getDataDescriptionEntry("ITEM1");
				assertNotNull(dataDescriptionEntryItem1);
				assertEquals("ITEM1", dataDescriptionEntryItem1.getName());
				assertEquals(DataDescriptionEntry.Type.GROUP, dataDescriptionEntryItem1.getType());
				assertEquals(new Integer(2), dataDescriptionEntryItem1.getLevelNumber());
				assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem1.getParentDataDescriptionEntryGroup());
			}
		}
	}
}
