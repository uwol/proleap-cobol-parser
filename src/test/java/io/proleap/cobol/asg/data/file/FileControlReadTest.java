package io.proleap.cobol.asg.data.file;

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
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.file.FileSection;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.InputOutputSection;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class FileControlReadTest extends CobolTestSupport {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/data/file/FileControlRead.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("FileControlRead");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();

		final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();
		final InputOutputSection inputOutputSection = environmentDivision.getInputOutputSection();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final FileSection fileSection = dataDivision.getFileSection();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		/*
		 * file control
		 */
		{
			final FileControlEntry fileControlEntry = inputOutputSection.getFileControlParagraph()
					.getFileControlEntry("PERSON");
			assertNotNull(fileControlEntry);
			assertEquals("PERSON", fileControlEntry.getName());
		}

		/*
		 * file section, file description
		 */
		{
			final FileDescriptionEntry fileDescriptionEntry = fileSection.getFileDescriptionEntry("PERSON");
			assertNotNull(fileDescriptionEntry);

			{
				final DataDescriptionEntry dataDescriptionEntryPersonFile = fileDescriptionEntry
						.findDataDescriptionEntry("PERSON-FILE");
				assertNotNull(dataDescriptionEntryPersonFile);
				assertEquals("PERSON-FILE", dataDescriptionEntryPersonFile.getName());
				assertEquals(new Integer(1), dataDescriptionEntryPersonFile.getLevelNumber());
				assertNull(dataDescriptionEntryPersonFile.getParentDataDescriptionEntryGroup());
				assertNull(dataDescriptionEntryPersonFile.getParentDataDescriptionEntryGroup());

				{
					final DataDescriptionEntry dataDescriptionEntryPersonId = fileDescriptionEntry
							.findDataDescriptionEntry("PERSON-ID");
					assertNotNull(dataDescriptionEntryPersonId);
					assertEquals("PERSON-ID", dataDescriptionEntryPersonId.getName());
					assertEquals(new Integer(5), dataDescriptionEntryPersonId.getLevelNumber());
					assertEquals(dataDescriptionEntryPersonFile,
							dataDescriptionEntryPersonId.getParentDataDescriptionEntryGroup());
				}

				{
					final DataDescriptionEntry dataDescriptionEntryName = fileDescriptionEntry
							.findDataDescriptionEntry("NAME");
					assertNotNull(dataDescriptionEntryName);
					assertEquals("NAME", dataDescriptionEntryName.getName());
					assertEquals(new Integer(5), dataDescriptionEntryName.getLevelNumber());
					assertNull(dataDescriptionEntryPersonFile.getParentDataDescriptionEntryGroup());
					assertEquals(dataDescriptionEntryPersonFile,
							dataDescriptionEntryName.getParentDataDescriptionEntryGroup());
				}
			}
		}

		/*
		 * working storage section
		 */
		{
			final DataDescriptionEntryGroup dataDescriptionEntryWsPerson = (DataDescriptionEntryGroup) workingStorageSection
					.findDataDescriptionEntry("WS-PERSON");
			assertNotNull(dataDescriptionEntryWsPerson);
			assertEquals("WS-PERSON", dataDescriptionEntryWsPerson.getName());
			assertEquals(new Integer(1), dataDescriptionEntryWsPerson.getLevelNumber());
			assertNull(dataDescriptionEntryWsPerson.getParentDataDescriptionEntryGroup());

			{
				final DataDescriptionEntry dataDescriptionEntryWsPersonId = workingStorageSection
						.findDataDescriptionEntry("WS-PERSON-ID");
				assertNotNull(dataDescriptionEntryWsPersonId);
				assertEquals("WS-PERSON-ID", dataDescriptionEntryWsPersonId.getName());
				assertEquals(new Integer(5), dataDescriptionEntryWsPersonId.getLevelNumber());
				assertEquals(dataDescriptionEntryWsPerson,
						dataDescriptionEntryWsPersonId.getParentDataDescriptionEntryGroup());
			}

			{
				final DataDescriptionEntry dataDescriptionEntryWsPersonName = workingStorageSection
						.findDataDescriptionEntry("WS-NAME");
				assertNotNull(dataDescriptionEntryWsPersonName);
				assertEquals("WS-NAME", dataDescriptionEntryWsPersonName.getName());
				assertEquals(new Integer(5), dataDescriptionEntryWsPersonName.getLevelNumber());
				assertEquals(dataDescriptionEntryWsPerson,
						dataDescriptionEntryWsPersonName.getParentDataDescriptionEntryGroup());
			}

			{
				final DataDescriptionEntryGroup dataDescriptionEntryWsEof = (DataDescriptionEntryGroup) workingStorageSection
						.findDataDescriptionEntry("WS-EOF");
				assertNotNull(dataDescriptionEntryWsEof);
				assertEquals("WS-EOF", dataDescriptionEntryWsEof.getName());
				assertEquals(new Integer(1), dataDescriptionEntryWsEof.getLevelNumber());
				assertNull(dataDescriptionEntryWsEof.getParentDataDescriptionEntryGroup());
			}
		}
	}
}