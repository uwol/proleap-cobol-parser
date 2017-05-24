package io.proleap.cobol.asg.data.screen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.screen.BlankClause;
import io.proleap.cobol.asg.metamodel.data.screen.ColumnNumberClause;
import io.proleap.cobol.asg.metamodel.data.screen.LightClause;
import io.proleap.cobol.asg.metamodel.data.screen.LineNumberClause;
import io.proleap.cobol.asg.metamodel.data.screen.ScreenDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.screen.ScreenSection;
import io.proleap.cobol.asg.metamodel.data.screen.ValueClause;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ScreenTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/data/screen/Screen.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("Screen");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ScreenSection screenSection = dataDivision.getScreenSection();

		assertNotNull(screenSection);
		assertEquals(5, screenSection.getScreenDescriptionEntries().size());
		assertEquals(2, screenSection.getRootScreenDescriptionEntries().size());

		{
			final ScreenDescriptionEntry screenDescriptionEntry1 = screenSection.getRootScreenDescriptionEntries()
					.get(0);
			assertEquals(new Integer(1), screenDescriptionEntry1.getLevelNumber());
			assertEquals("SOMESCREEN1", screenDescriptionEntry1.getName());
			assertNull(screenDescriptionEntry1.getPredecessor());
			// FIXME: should be SOMESCREEN2
			assertNull(screenDescriptionEntry1.getSuccessor());

			assertEquals(3, screenDescriptionEntry1.getScreenDescriptionEntries().size());

			{
				final ScreenDescriptionEntry subScreenDescriptionEntry = screenDescriptionEntry1
						.getScreenDescriptionEntries().get(0);
				assertNull(subScreenDescriptionEntry.getName());
				assertEquals(new Integer(5), subScreenDescriptionEntry.getLevelNumber());
				assertEquals(screenDescriptionEntry1, subScreenDescriptionEntry.getParentScreenDescriptionEntry());
				assertNull(subScreenDescriptionEntry.getPredecessor());
				assertNotNull(subScreenDescriptionEntry.getSuccessor());

				{
					final BlankClause blankClause = subScreenDescriptionEntry.getBlankClause();
					assertNotNull(blankClause);
					assertEquals(BlankClause.Type.SCREEN, blankClause.getType());
				}
			}

			{
				final ScreenDescriptionEntry subScreenDescriptionEntry = screenDescriptionEntry1
						.getScreenDescriptionEntries().get(1);
				assertEquals("SOMELINE1", subScreenDescriptionEntry.getName());
				assertEquals(new Integer(5), subScreenDescriptionEntry.getLevelNumber());
				assertEquals(screenDescriptionEntry1, subScreenDescriptionEntry.getParentScreenDescriptionEntry());
				assertNotNull(subScreenDescriptionEntry.getPredecessor());
				assertNotNull(subScreenDescriptionEntry.getSuccessor());

				{
					final LineNumberClause lineNumberClause = subScreenDescriptionEntry.getLineNumberClause();
					assertNotNull(lineNumberClause);
					assertEquals(new Integer(1), lineNumberClause.getIntegerLiteral().getValue());
				}

				{
					final ColumnNumberClause columnNumberClause = subScreenDescriptionEntry.getColumnNumberClause();
					assertNotNull(columnNumberClause);
					assertEquals(new Integer(16), columnNumberClause.getIntegerLiteral().getValue());
				}

				{
					final ValueClause valueClause = subScreenDescriptionEntry.getValueClause();
					assertNotNull(valueClause);
					assertEquals("E M P L O Y E E", valueClause.getLiteral().getNonNumericLiteral());
				}

				{
					final LightClause lightClause = subScreenDescriptionEntry.getLightClause();
					assertNotNull(lightClause);
					assertEquals(LightClause.Type.HIGHLIGHT, lightClause.getType());
				}
			}

			{
				final ScreenDescriptionEntry subScreenDescriptionEntry = screenDescriptionEntry1
						.getScreenDescriptionEntries().get(2);
				assertNull(subScreenDescriptionEntry.getName());
				assertEquals(new Integer(5), subScreenDescriptionEntry.getLevelNumber());
				assertEquals(screenDescriptionEntry1, subScreenDescriptionEntry.getParentScreenDescriptionEntry());
				assertNotNull(subScreenDescriptionEntry.getPredecessor());
				assertNull(subScreenDescriptionEntry.getSuccessor());

				{
					final LineNumberClause lineNumberClause = subScreenDescriptionEntry.getLineNumberClause();
					assertNotNull(lineNumberClause);
					assertEquals(new Integer(2), lineNumberClause.getIntegerLiteral().getValue());
				}

				{
					final ColumnNumberClause columnNumberClause = subScreenDescriptionEntry.getColumnNumberClause();
					assertNotNull(columnNumberClause);
					assertEquals(new Integer(35), columnNumberClause.getIntegerLiteral().getValue());
				}

				{
					final ValueClause valueClause = subScreenDescriptionEntry.getValueClause();
					assertNotNull(valueClause);
					assertEquals("A D D R E S S", valueClause.getLiteral().getNonNumericLiteral());
				}

				{
					final LightClause lightClause = subScreenDescriptionEntry.getLightClause();
					assertNotNull(lightClause);
					assertEquals(LightClause.Type.LOWLIGHT, lightClause.getType());
				}
			}
		}

		{
			final ScreenDescriptionEntry screenDescriptionEntry2 = screenSection.getRootScreenDescriptionEntries()
					.get(1);
			assertEquals(new Integer(1), screenDescriptionEntry2.getLevelNumber());
			assertEquals("SOMESCREEN2", screenDescriptionEntry2.getName());
			// FIXME: should be SOMESCREEN1
			assertNull(screenDescriptionEntry2.getPredecessor());
			assertNull(screenDescriptionEntry2.getSuccessor());
		}
	}
}
