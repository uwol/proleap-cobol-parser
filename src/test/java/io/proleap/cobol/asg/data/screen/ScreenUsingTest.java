package io.proleap.cobol.asg.data.screen;

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
import io.proleap.cobol.asg.metamodel.data.screen.ScreenDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.screen.ScreenSection;
import io.proleap.cobol.asg.metamodel.data.screen.UsingClause;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ScreenUsingTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/data/screen/ScreenUsing.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ScreenUsing");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ScreenSection screenSection = dataDivision.getScreenSection();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertNotNull(screenSection);
		assertEquals(1, screenSection.getScreenDescriptionEntries().size());
		assertNotNull(workingStorageSection);

		final ScreenDescriptionEntry screenDescriptionEntry = screenSection.getScreenDescriptionEntry("SOME-SCREEN");
		final DataDescriptionEntry dataDescriptionEntry = workingStorageSection.getDataDescriptionEntry("SOME-DATA");

		{
			assertEquals(Integer.valueOf(1), screenDescriptionEntry.getLevelNumber());
			assertEquals("SOME-SCREEN", screenDescriptionEntry.getName());

			{
				final UsingClause usingClause = screenDescriptionEntry.getUsingClause();
				assertNotNull(usingClause);
			}
		}

		{
			assertEquals(1, dataDescriptionEntry.getCalls().size());
		}
	}
}
