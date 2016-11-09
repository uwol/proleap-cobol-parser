package io.proleap.cobol.gpl.parser.tandem;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry;
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
		final List<ProgramUnit> programUnits = copyBook.getProgramUnits();
		final ProgramUnit programUnit = programUnits.get(0);
		final DataDivision dataDivision = programUnit.getDataDivision();

		final DataDescriptionEntry dataDescriptionEntry1 = dataDivision.getDataDescriptionEntry("ITEMS");

		assertNotNull(dataDescriptionEntry1);

		final DataDescriptionEntry dataDescriptionEntry2 = dataDivision.getDataDescriptionEntry("ITEMZ");

		assertNotNull(dataDescriptionEntry2);
	}
}
