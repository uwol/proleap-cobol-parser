package io.proleap.cobol.gpl.parser.procedure.move;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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
import io.proleap.cobol.parser.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class MoveStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/move/MoveStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("MoveStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		{
			final DataDescriptionEntry dataDescriptionEntry1 = workingStorageSection
					.getDataDescriptionEntry("SOME-TEXT");

			assertNotNull(dataDescriptionEntry1);
			assertFalse(dataDescriptionEntry1.getCalls().isEmpty());
			assertEquals(2, dataDescriptionEntry1.getCalls().size());
		}

		{
			final DataDescriptionEntry dataDescriptionEntry2 = workingStorageSection
					.getDataDescriptionEntry("SOME-NUMBER");

			assertNotNull(dataDescriptionEntry2);
			assertFalse(dataDescriptionEntry2.getCalls().isEmpty());
			assertEquals(1, dataDescriptionEntry2.getCalls().size());
		}

		{
			final DataDescriptionEntry dataDescriptionEntry3 = workingStorageSection
					.getDataDescriptionEntry("SOME-TEXT2");

			assertNotNull(dataDescriptionEntry3);
			assertFalse(dataDescriptionEntry3.getCalls().isEmpty());
			assertEquals(1, dataDescriptionEntry3.getCalls().size());
		}
	}
}
