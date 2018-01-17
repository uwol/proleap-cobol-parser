package io.proleap.cobol.asg.data.screen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.math.BigDecimal;

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

		final ScreenDescriptionEntry screenDescriptionEntry1 = screenSection.getRootScreenDescriptionEntries().get(0);
		final ScreenDescriptionEntry screenDescriptionEntry2 = screenSection.getRootScreenDescriptionEntries().get(1);

		{
			assertEquals(Integer.valueOf(1), screenDescriptionEntry1.getLevelNumber());
			assertEquals("SOMESCREEN1", screenDescriptionEntry1.getName());
			assertNull(screenDescriptionEntry1.getPredecessor());
			assertNotNull(screenDescriptionEntry1.getSuccessor());
			assertEquals(screenDescriptionEntry2, screenDescriptionEntry1.getSuccessor());

			assertEquals(3, screenDescriptionEntry1.getScreenDescriptionEntries().size());

			final ScreenDescriptionEntry subScreenDescriptionEntry1 = screenDescriptionEntry1
					.getScreenDescriptionEntries().get(0);
			final ScreenDescriptionEntry subScreenDescriptionEntry2 = screenDescriptionEntry1
					.getScreenDescriptionEntries().get(1);
			final ScreenDescriptionEntry subScreenDescriptionEntry3 = screenDescriptionEntry1
					.getScreenDescriptionEntries().get(2);

			{
				assertNull(subScreenDescriptionEntry1.getName());
				assertEquals(Integer.valueOf(5), subScreenDescriptionEntry1.getLevelNumber());
				assertEquals(screenDescriptionEntry1, subScreenDescriptionEntry1.getParentScreenDescriptionEntry());
				assertNull(subScreenDescriptionEntry1.getPredecessor());
				assertNotNull(subScreenDescriptionEntry1.getSuccessor());
				assertEquals(subScreenDescriptionEntry2, subScreenDescriptionEntry1.getSuccessor());

				{
					final BlankClause blankClause = subScreenDescriptionEntry1.getBlankClause();
					assertNotNull(blankClause);
					assertEquals(BlankClause.BlankClauseType.SCREEN, blankClause.getBlankClauseType());
				}
			}

			{
				assertEquals("SOMELINE1", subScreenDescriptionEntry2.getName());
				assertEquals(Integer.valueOf(5), subScreenDescriptionEntry2.getLevelNumber());
				assertEquals(screenDescriptionEntry1, subScreenDescriptionEntry2.getParentScreenDescriptionEntry());
				assertNotNull(subScreenDescriptionEntry2.getPredecessor());
				assertEquals(subScreenDescriptionEntry1, subScreenDescriptionEntry2.getPredecessor());
				assertNotNull(subScreenDescriptionEntry2.getSuccessor());
				assertEquals(subScreenDescriptionEntry3, subScreenDescriptionEntry2.getSuccessor());

				{
					final LineNumberClause lineNumberClause = subScreenDescriptionEntry2.getLineNumberClause();
					assertNotNull(lineNumberClause);
					assertEquals(BigDecimal.ONE, lineNumberClause.getIntegerLiteral().getValue());
				}

				{
					final ColumnNumberClause columnNumberClause = subScreenDescriptionEntry2.getColumnNumberClause();
					assertNotNull(columnNumberClause);
					assertEquals(new BigDecimal(16), columnNumberClause.getIntegerLiteral().getValue());
				}

				{
					final ValueClause valueClause = subScreenDescriptionEntry2.getValueClause();
					assertNotNull(valueClause);
					assertEquals("E M P L O Y E E", valueClause.getLiteral().getNonNumericLiteral());
				}

				{
					final LightClause lightClause = subScreenDescriptionEntry2.getLightClause();
					assertNotNull(lightClause);
					assertEquals(LightClause.LightClauseType.HIGHLIGHT, lightClause.getLightClauseType());
				}
			}

			{
				assertNull(subScreenDescriptionEntry3.getName());
				assertEquals(Integer.valueOf(5), subScreenDescriptionEntry3.getLevelNumber());
				assertEquals(screenDescriptionEntry1, subScreenDescriptionEntry3.getParentScreenDescriptionEntry());
				assertNotNull(subScreenDescriptionEntry3.getPredecessor());
				assertEquals(subScreenDescriptionEntry2, subScreenDescriptionEntry3.getPredecessor());
				assertNull(subScreenDescriptionEntry3.getSuccessor());

				{
					final LineNumberClause lineNumberClause = subScreenDescriptionEntry3.getLineNumberClause();
					assertNotNull(lineNumberClause);
					assertEquals(new BigDecimal(2), lineNumberClause.getIntegerLiteral().getValue());
				}

				{
					final ColumnNumberClause columnNumberClause = subScreenDescriptionEntry3.getColumnNumberClause();
					assertNotNull(columnNumberClause);
					assertEquals(new BigDecimal(35), columnNumberClause.getIntegerLiteral().getValue());
				}

				{
					final ValueClause valueClause = subScreenDescriptionEntry3.getValueClause();
					assertNotNull(valueClause);
					assertEquals("A D D R E S S", valueClause.getLiteral().getNonNumericLiteral());
				}

				{
					final LightClause lightClause = subScreenDescriptionEntry3.getLightClause();
					assertNotNull(lightClause);
					assertEquals(LightClause.LightClauseType.LOWLIGHT, lightClause.getLightClauseType());
				}
			}
		}

		{
			assertEquals(Integer.valueOf(1), screenDescriptionEntry2.getLevelNumber());
			assertEquals("SOMESCREEN2", screenDescriptionEntry2.getName());
			assertNotNull(screenDescriptionEntry2.getPredecessor());
			assertEquals(screenDescriptionEntry1, screenDescriptionEntry2.getPredecessor());
			assertNull(screenDescriptionEntry2.getSuccessor());
		}
	}
}
