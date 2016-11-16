package io.proleap.cobol.gpl.parser.data.localstorage;

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
import io.proleap.cobol.parser.metamodel.data.localstorage.LocalStorageSection;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DataDescription01Test extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/data/localstorage/DataDescription01.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("DataDescription01");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final LocalStorageSection localStorageSection = dataDivision.getLocalStorageSection();

		assertEquals("SOMELD", localStorageSection.getName());

		assertEquals(3, localStorageSection.getDataDescriptionEntries().size());
		assertEquals(1, localStorageSection.getRootDataDescriptionEntries().size());

		final DataDescriptionEntry dataDescriptionEntryItems = localStorageSection.getDataDescriptionEntry("ITEMS");

		assertNotNull(dataDescriptionEntryItems);
		assertEquals("ITEMS", dataDescriptionEntryItems.getName());
		assertEquals(DataDescriptionEntry.Type.Group, dataDescriptionEntryItems.getType());
		assertEquals(new Integer(1), dataDescriptionEntryItems.getLevelNumber());
		assertNull(dataDescriptionEntryItems.getDataDescriptionEntryGroup());

		final DataDescriptionEntry dataDescriptionEntryItem1 = localStorageSection.getDataDescriptionEntry("ITEM1");
		assertNotNull(dataDescriptionEntryItem1);
		assertEquals("ITEM1", dataDescriptionEntryItem1.getName());
		assertEquals(DataDescriptionEntry.Type.Group, dataDescriptionEntryItem1.getType());
		assertEquals(new Integer(2), dataDescriptionEntryItem1.getLevelNumber());
		assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem1.getDataDescriptionEntryGroup());

		final DataDescriptionEntry dataDescriptionEntryItem2 = localStorageSection.getDataDescriptionEntry("ITEM2");
		assertNotNull(dataDescriptionEntryItem2);
		assertEquals("ITEM2", dataDescriptionEntryItem2.getName());
		assertEquals(DataDescriptionEntry.Type.Group, dataDescriptionEntryItem2.getType());
		assertEquals(new Integer(2), dataDescriptionEntryItem2.getLevelNumber());
		assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem2.getDataDescriptionEntryGroup());
	}
}
