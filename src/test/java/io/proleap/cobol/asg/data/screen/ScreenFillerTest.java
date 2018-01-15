package io.proleap.cobol.asg.data.screen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.screen.ScreenDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.screen.ScreenSection;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ScreenFillerTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/data/screen/ScreenFiller.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ScreenFiller");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ScreenSection screenSection = dataDivision.getScreenSection();

		assertNotNull(screenSection);
		assertEquals(1, screenSection.getScreenDescriptionEntries().size());
		assertEquals(1, screenSection.getRootScreenDescriptionEntries().size());

		{
			final ScreenDescriptionEntry screenDescriptionEntry = screenSection.getScreenDescriptionEntries().get(0);
			assertEquals(Integer.valueOf(1), screenDescriptionEntry.getLevelNumber());
			assertNull(screenDescriptionEntry.getName());
			assertTrue(screenDescriptionEntry.getFiller());
		}
	}
}
