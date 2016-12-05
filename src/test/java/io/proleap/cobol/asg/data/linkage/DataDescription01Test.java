package io.proleap.cobol.asg.data.linkage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.linkage.LinkageSection;
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
				"src/test/resources/io/proleap/cobol/asg/data/linkage/DataDescription01.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DataDescription01");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final LinkageSection linkageSection = dataDivision.getLinkageSection();

		assertEquals(3, linkageSection.getDataDescriptionEntries().size());
		assertEquals(1, linkageSection.getRootDataDescriptionEntries().size());

		final DataDescriptionEntry dataDescriptionEntryItems = linkageSection.getDataDescriptionEntry("ITEMS");

		assertNotNull(dataDescriptionEntryItems);
		assertEquals("ITEMS", dataDescriptionEntryItems.getName());
		assertEquals(DataDescriptionEntry.Type.Group, dataDescriptionEntryItems.getType());
		assertEquals(new Integer(1), dataDescriptionEntryItems.getLevelNumber());
		assertNull(dataDescriptionEntryItems.getParentDataDescriptionEntryGroup());

		final DataDescriptionEntry dataDescriptionEntryItem1 = linkageSection.getDataDescriptionEntry("ITEM1");
		assertNotNull(dataDescriptionEntryItem1);
		assertEquals("ITEM1", dataDescriptionEntryItem1.getName());
		assertEquals(DataDescriptionEntry.Type.Group, dataDescriptionEntryItem1.getType());
		assertEquals(new Integer(2), dataDescriptionEntryItem1.getLevelNumber());
		assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem1.getParentDataDescriptionEntryGroup());

		final DataDescriptionEntry dataDescriptionEntryItem2 = linkageSection.getDataDescriptionEntry("ITEM2");
		assertNotNull(dataDescriptionEntryItem2);
		assertEquals("ITEM2", dataDescriptionEntryItem2.getName());
		assertEquals(DataDescriptionEntry.Type.Group, dataDescriptionEntryItem2.getType());
		assertEquals(new Integer(2), dataDescriptionEntryItem2.getLevelNumber());
		assertEquals(dataDescriptionEntryItems, dataDescriptionEntryItem2.getParentDataDescriptionEntryGroup());
	}
}
