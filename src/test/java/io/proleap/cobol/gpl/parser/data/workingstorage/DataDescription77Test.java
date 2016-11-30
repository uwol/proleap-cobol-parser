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

public class DataDescription77Test extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/data/workingstorage/DataDescription77.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DataDescription77");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(2, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(2, workingStorageSection.getRootDataDescriptionEntries().size());

		final DataDescriptionEntry dataDescriptionEntrySomeText = workingStorageSection
				.getDataDescriptionEntry("SOME-TEXT");

		assertNotNull(dataDescriptionEntrySomeText);
		assertEquals("SOME-TEXT", dataDescriptionEntrySomeText.getName());
		assertEquals(DataDescriptionEntry.Type.Scalar, dataDescriptionEntrySomeText.getType());
		assertEquals(new Integer(77), dataDescriptionEntrySomeText.getLevelNumber());
		assertNull(dataDescriptionEntrySomeText.getParentDataDescriptionEntryGroup());

		final DataDescriptionEntry dataDescriptionEntrySomeNumber = workingStorageSection
				.getDataDescriptionEntry("SOME-NUMBER");

		assertNotNull(dataDescriptionEntrySomeNumber);
		assertEquals("SOME-NUMBER", dataDescriptionEntrySomeNumber.getName());
		assertEquals(DataDescriptionEntry.Type.Scalar, dataDescriptionEntrySomeText.getType());
		assertEquals(new Integer(77), dataDescriptionEntrySomeNumber.getLevelNumber());
		assertNull(dataDescriptionEntrySomeNumber.getParentDataDescriptionEntryGroup());
	}
}
