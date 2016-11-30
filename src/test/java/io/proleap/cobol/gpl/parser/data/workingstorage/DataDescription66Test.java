package io.proleap.cobol.gpl.parser.data.workingstorage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CompilationUnit;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DataDescription66Test extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/data/workingstorage/DataDescription66.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DataDescription66");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(6, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(2, workingStorageSection.getRootDataDescriptionEntries().size());

		final DataDescriptionEntry dataDescriptionEntryItems = workingStorageSection.getDataDescriptionEntry("ITEMS");

		assertNotNull(dataDescriptionEntryItems);
		assertEquals("ITEMS", dataDescriptionEntryItems.getName());
		assertEquals(DataDescriptionEntry.Type.Group, dataDescriptionEntryItems.getType());
		assertEquals(new Integer(1), dataDescriptionEntryItems.getLevelNumber());
		assertNull(dataDescriptionEntryItems.getParentDataDescriptionEntryGroup());

		final DataDescriptionEntry dataDescriptionEntryItem1 = workingStorageSection.getDataDescriptionEntry("ITEM1");
		assertNotNull(dataDescriptionEntryItem1);
		assertEquals("ITEM1", dataDescriptionEntryItem1.getName());
		assertEquals(DataDescriptionEntry.Type.Group, dataDescriptionEntryItem1.getType());
		assertEquals(new Integer(2), dataDescriptionEntryItem1.getLevelNumber());
		assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem1.getParentDataDescriptionEntryGroup());

		final DataDescriptionEntry dataDescriptionEntryItem2 = workingStorageSection.getDataDescriptionEntry("ITEM2");
		assertNotNull(dataDescriptionEntryItem2);
		assertEquals("ITEM2", dataDescriptionEntryItem2.getName());
		assertEquals(DataDescriptionEntry.Type.Group, dataDescriptionEntryItem2.getType());
		assertEquals(new Integer(2), dataDescriptionEntryItem2.getLevelNumber());
		assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem2.getParentDataDescriptionEntryGroup());

		final DataDescriptionEntry dataDescriptionEntryItem3 = workingStorageSection.getDataDescriptionEntry("ITEM3");
		assertNotNull(dataDescriptionEntryItem3);
		assertEquals("ITEM3", dataDescriptionEntryItem3.getName());
		assertEquals(DataDescriptionEntry.Type.Group, dataDescriptionEntryItem3.getType());
		assertEquals(new Integer(2), dataDescriptionEntryItem3.getLevelNumber());
		assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem3.getParentDataDescriptionEntryGroup());

		final DataDescriptionEntry dataDescriptionEntryItem4 = workingStorageSection.getDataDescriptionEntry("ITEM4");
		assertNotNull(dataDescriptionEntryItem4);
		assertEquals("ITEM4", dataDescriptionEntryItem4.getName());
		assertEquals(DataDescriptionEntry.Type.Group, dataDescriptionEntryItem4.getType());
		assertEquals(new Integer(2), dataDescriptionEntryItem4.getLevelNumber());
		assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem4.getParentDataDescriptionEntryGroup());

		final DataDescriptionEntry dataDescriptionEntryItemz = workingStorageSection.getDataDescriptionEntry("ITEMZ");

		assertNotNull(dataDescriptionEntryItemz);
		assertEquals("ITEMZ", dataDescriptionEntryItemz.getName());
		assertEquals(DataDescriptionEntry.Type.Rename, dataDescriptionEntryItemz.getType());
		assertEquals(new Integer(66), dataDescriptionEntryItemz.getLevelNumber());
		assertNull(dataDescriptionEntryItemz.getParentDataDescriptionEntryGroup());
	}
}
