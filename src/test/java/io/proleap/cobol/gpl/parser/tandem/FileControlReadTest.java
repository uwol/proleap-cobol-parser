package io.proleap.cobol.gpl.parser.tandem;

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
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.DataDescriptionEntryGroup;
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
		{
			final FileControlEntry fileControlEntry = environmentDivision.getFileControlEntry("PERSON");
			assertNotNull(fileControlEntry);
			assertEquals("PERSON", fileControlEntry.getName());
		}

		/*
		 * file section, file description
		 */
		{
			final FileDescriptionEntry fileDescriptionEntry = dataDivision.getFileDescriptionEntry("PERSON");
			assertNotNull(fileDescriptionEntry);

			final DataDescriptionEntry dataDescriptionEntryPersonFile = fileDescriptionEntry
					.getDataDescriptionEntry("PERSON-FILE");
			final DataDescriptionEntry dataDescriptionEntryPersonFileGlobal = dataDivision
					.getDataDescriptionEntry("PERSON-FILE");
			assertNotNull(dataDescriptionEntryPersonFile);
			assertEquals("PERSON-FILE", dataDescriptionEntryPersonFile.getName());
			assertEquals(new Integer(1), dataDescriptionEntryPersonFile.getLevelNumber());
			assertNull(dataDescriptionEntryPersonFile.getDataDescriptionEntryGroup());
			assertEquals(dataDescriptionEntryPersonFile, dataDescriptionEntryPersonFileGlobal);
			assertNull(dataDescriptionEntryPersonFile.getDataDescriptionEntryGroup());

			final DataDescriptionEntry dataDescriptionEntryPersonId = fileDescriptionEntry
					.getDataDescriptionEntry("PERSON-ID");
			assertNotNull(dataDescriptionEntryPersonId);
			assertEquals("PERSON-ID", dataDescriptionEntryPersonId.getName());
			assertEquals(new Integer(5), dataDescriptionEntryPersonId.getLevelNumber());
			assertEquals(dataDescriptionEntryPersonFile, dataDescriptionEntryPersonId.getDataDescriptionEntryGroup());

			final DataDescriptionEntry dataDescriptionEntryName = fileDescriptionEntry.getDataDescriptionEntry("NAME");
			assertNotNull(dataDescriptionEntryName);
			assertEquals("NAME", dataDescriptionEntryName.getName());
			assertEquals(new Integer(5), dataDescriptionEntryName.getLevelNumber());
			assertNull(dataDescriptionEntryPersonFile.getDataDescriptionEntryGroup());
			assertEquals(dataDescriptionEntryPersonFile, dataDescriptionEntryName.getDataDescriptionEntryGroup());
		}

		/*
		 * working storage section
		 */
		{
			final DataDescriptionEntryGroup dataDescriptionEntryWsPerson = dataDivision
					.getDataDescriptionEntryGroup("WS-PERSON");
			assertNotNull(dataDescriptionEntryWsPerson);
			assertEquals("WS-PERSON", dataDescriptionEntryWsPerson.getName());
			assertEquals(new Integer(1), dataDescriptionEntryWsPerson.getLevelNumber());
			assertNull(dataDescriptionEntryWsPerson.getDataDescriptionEntryGroup());

			final DataDescriptionEntryGroup dataDescriptionEntryWsPersonId = dataDivision
					.getDataDescriptionEntryGroup("WS-PERSON-ID");
			assertNotNull(dataDescriptionEntryWsPersonId);
			assertEquals("WS-PERSON-ID", dataDescriptionEntryWsPersonId.getName());
			assertEquals(new Integer(5), dataDescriptionEntryWsPersonId.getLevelNumber());
			assertEquals(dataDescriptionEntryWsPerson, dataDescriptionEntryWsPersonId.getDataDescriptionEntryGroup());

			final DataDescriptionEntryGroup dataDescriptionEntryWsPersonName = dataDivision
					.getDataDescriptionEntryGroup("WS-NAME");
			assertNotNull(dataDescriptionEntryWsPersonName);
			assertEquals("WS-NAME", dataDescriptionEntryWsPersonName.getName());
			assertEquals(new Integer(5), dataDescriptionEntryWsPersonName.getLevelNumber());
			assertEquals(dataDescriptionEntryWsPerson, dataDescriptionEntryWsPersonName.getDataDescriptionEntryGroup());

			final DataDescriptionEntryGroup dataDescriptionEntryWsEof = dataDivision
					.getDataDescriptionEntryGroup("WS-EOF");
			assertNotNull(dataDescriptionEntryWsEof);
			assertEquals("WS-EOF", dataDescriptionEntryWsEof.getName());
			assertEquals(new Integer(1), dataDescriptionEntryWsEof.getLevelNumber());
			assertNull(dataDescriptionEntryWsEof.getDataDescriptionEntryGroup());
		}
	}
}