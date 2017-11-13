package io.proleap.cobol.asg.call;

import static org.junit.Assert.assertEquals;

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

public class FileDescriptionEntryFromFileControlClauseCallTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/call/FileDescriptionEntryFromFileControlClauseCall.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.VARIABLE);

		final CompilationUnit compilationUnit = program
				.getCompilationUnit("FileDescriptionEntryFromFileControlClauseCall");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final FileSection fileSection = dataDivision.getFileSection();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		{
			final FileDescriptionEntry fileDescriptionEntry = fileSection.getFileDescriptionEntry("VideoFile");

			{
				final DataDescriptionEntry dataDescriptionEntryVideoRecord = fileDescriptionEntry
						.getDataDescriptionEntry("VideoRecord");
				assertEquals(0, dataDescriptionEntryVideoRecord.getCalls().size());

				{
					final DataDescriptionEntry dataDescriptionEntryVideoCode = fileDescriptionEntry
							.getDataDescriptionEntry("VideoCode");
					assertEquals(1, dataDescriptionEntryVideoCode.getCalls().size());
				}

				{
					final DataDescriptionEntry dataDescriptionEntryVideoTitle = fileDescriptionEntry
							.getDataDescriptionEntry("VideoTitle");
					assertEquals(1, dataDescriptionEntryVideoTitle.getCalls().size());
				}
			}
		}

		{
			final DataDescriptionEntry dataDescriptionEntryVideoFileStatus = workingStorageSection
					.getDataDescriptionEntry("VideoFileStatus");
			assertEquals(1, dataDescriptionEntryVideoFileStatus.getCalls().size());
		}
	}
}