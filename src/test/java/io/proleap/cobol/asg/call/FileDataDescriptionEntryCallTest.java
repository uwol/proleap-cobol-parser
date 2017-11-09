package io.proleap.cobol.asg.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.file.FileSection;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class FileDataDescriptionEntryCallTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/call/FileDataDescriptionEntryCall.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.VARIABLE);

		final CompilationUnit compilationUnit = program.getCompilationUnit("FileDataDescriptionEntryCall");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final FileSection fileSection = dataDivision.getFileSection();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		{
			final FileDescriptionEntry fileDescriptionEntry = fileSection.getFileDescriptionEntry("D111E");
			assertEquals(30, fileDescriptionEntry.getDataDescriptionEntries().size());
			assertEquals(1, fileDescriptionEntry.getRootDataDescriptionEntries().size());

			{
				final DataDescriptionEntry dataDescriptionEntry = fileDescriptionEntry
						.getDataDescriptionEntry("D111E-DATEI");
				assertNotNull(dataDescriptionEntry);
				assertEquals(1, dataDescriptionEntry.getCalls().size());
			}
		}

		assertEquals(5, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(2, workingStorageSection.getRootDataDescriptionEntries().size());

		{
			final DataDescriptionEntry dataDescriptionEntryWsA = workingStorageSection.getDataDescriptionEntry("WS-A");
			assertNotNull(dataDescriptionEntryWsA);
		}

		{
			final DataDescriptionEntry dataDescriptionEntryAusgabe = workingStorageSection
					.getDataDescriptionEntry("AUSGABE");
			assertNotNull(dataDescriptionEntryAusgabe);
			assertEquals(1, dataDescriptionEntryAusgabe.getCalls().size());

			{
				final DataDescriptionEntry dataDescriptionEntryAusVor = workingStorageSection
						.getDataDescriptionEntry("AUS-VOR");
				assertNotNull(dataDescriptionEntryAusVor);
				assertEquals(1, dataDescriptionEntryAusVor.getCalls().size());
			}

			{
				final DataDescriptionEntry dataDescriptionEntryAusText = workingStorageSection
						.getDataDescriptionEntry("AUS-TEXT");
				assertNotNull(dataDescriptionEntryAusText);
				assertEquals(2, dataDescriptionEntryAusText.getCalls().size());
			}

			{
				final DataDescriptionEntry dataDescriptionEntryAusFontIndex = workingStorageSection
						.getDataDescriptionEntry("AUS-FONT-INDEX");
				assertNotNull(dataDescriptionEntryAusFontIndex);
				assertEquals(0, dataDescriptionEntryAusFontIndex.getCalls().size());
			}
		}
	}
}
