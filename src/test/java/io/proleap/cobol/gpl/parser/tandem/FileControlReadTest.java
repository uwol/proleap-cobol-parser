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
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.FileDescriptionEntry;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.environment.FileControlEntry;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class FileControlReadTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/gpl/parser/tandem/FileControlRead.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("FileControlRead");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();

		/*
		 * file control
		 */
		final FileControlEntry fileControlEntry = environmentDivision.getFileControlEntry("PERSON");
		assertNotNull(fileControlEntry);

		/*
		 * file section, file description
		 */
		final FileDescriptionEntry fileDescriptionEntry = dataDivision.getFileDescriptionEntry("PERSON");
		assertNotNull(fileDescriptionEntry);

		final DataDescriptionEntry dataDescriptionEntryPersonFileLocal = fileDescriptionEntry
				.getDataDescriptionEntry("PERSON-FILE");
		assertNotNull(dataDescriptionEntryPersonFileLocal);

		final DataDescriptionEntry dataDescriptionEntryPersonFileGlobal = dataDivision
				.getDataDescriptionEntry("PERSON-FILE");
		assertNotNull(dataDescriptionEntryPersonFileGlobal);

		assertEquals(dataDescriptionEntryPersonFileLocal, dataDescriptionEntryPersonFileGlobal);

		/*
		 * working storage section
		 */
		final DataDescriptionEntry dataDescriptionEntryWsPerson = dataDivision.getDataDescriptionEntry("WS-PERSON");
		assertNotNull(dataDescriptionEntryWsPerson);

		final DataDescriptionEntry dataDescriptionEntryWsEof = dataDivision.getDataDescriptionEntry("WS-EOF");
		assertNotNull(dataDescriptionEntryWsEof);
	}
}
