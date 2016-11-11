package io.proleap.cobol.gpl.parser.tandem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntryGroup;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntryRename;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DataDescription66Test extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/gpl/parser/tandem/DataDescription66.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("DataDescription66");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();

		final DataDescriptionEntry dataDescriptionEntryItems = dataDivision.getDataDescriptionEntry("ITEMS");
		final DataDescriptionEntryGroup dataDescriptionEntryGroupItems = dataDivision
				.getDataDescriptionEntryGroup("ITEMS");

		assertNotNull(dataDescriptionEntryItems);
		assertEquals("ITEMS", dataDescriptionEntryItems.getName());
		assertEquals(new Integer(1), dataDescriptionEntryItems.getLevelNumber());
		assertEquals(dataDescriptionEntryItems, dataDescriptionEntryGroupItems);

		final DataDescriptionEntry dataDescriptionEntryItem1 = dataDivision.getDataDescriptionEntry("ITEM1");
		assertNotNull(dataDescriptionEntryItem1);
		assertEquals("ITEM1", dataDescriptionEntryItem1.getName());
		assertEquals(new Integer(2), dataDescriptionEntryItem1.getLevelNumber());

		final DataDescriptionEntry dataDescriptionEntryItem2 = dataDivision.getDataDescriptionEntry("ITEM2");
		assertNotNull(dataDescriptionEntryItem2);
		assertEquals("ITEM2", dataDescriptionEntryItem2.getName());
		assertEquals(new Integer(2), dataDescriptionEntryItem2.getLevelNumber());

		final DataDescriptionEntry dataDescriptionEntryItem3 = dataDivision.getDataDescriptionEntry("ITEM3");
		assertNotNull(dataDescriptionEntryItem3);
		assertEquals("ITEM3", dataDescriptionEntryItem3.getName());
		assertEquals(new Integer(2), dataDescriptionEntryItem3.getLevelNumber());

		final DataDescriptionEntry dataDescriptionEntryItem4 = dataDivision.getDataDescriptionEntry("ITEM4");
		assertNotNull(dataDescriptionEntryItem4);
		assertEquals("ITEM4", dataDescriptionEntryItem4.getName());
		assertEquals(new Integer(2), dataDescriptionEntryItem4.getLevelNumber());

		final DataDescriptionEntry dataDescriptionEntryItemz = dataDivision.getDataDescriptionEntry("ITEMZ");
		final DataDescriptionEntryRename dataDescriptionEntryRenameItemz = dataDivision
				.getDataDescriptionEntryRename("ITEMZ");

		assertNotNull(dataDescriptionEntryItemz);
		assertEquals("ITEMZ", dataDescriptionEntryItemz.getName());
		assertEquals(new Integer(66), dataDescriptionEntryItemz.getLevelNumber());
		assertEquals(dataDescriptionEntryItemz, dataDescriptionEntryRenameItemz);
	}
}
