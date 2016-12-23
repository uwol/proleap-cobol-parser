package io.proleap.cobol.asg.data.screen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
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
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ScreenTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/data/screen/Screen.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("Screen");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ScreenSection screenSection = dataDivision.getScreenSection();

		assertNotNull(screenSection);
		assertEquals(4, screenSection.getScreenDescriptionEntries().size());
		assertEquals(1, screenSection.getRootScreenDescriptionEntries().size());

		{
			final ScreenDescriptionEntry screenDescriptionEntry = screenSection.getScreenDescriptionEntries().get(0);
			assertEquals(new Integer(1), screenDescriptionEntry.getLevelNumber());
			assertEquals("SOMESCREEN1", screenDescriptionEntry.getName());

			assertEquals(3, screenDescriptionEntry.getScreenDescriptionEntries().size());

			{
				final ScreenDescriptionEntry subScreenDescriptionEntry = screenDescriptionEntry
						.getScreenDescriptionEntries().get(0);
				assertNull(subScreenDescriptionEntry.getName());
				assertEquals(new Integer(5), subScreenDescriptionEntry.getLevelNumber());

				{
					final BlankClause blankClause = subScreenDescriptionEntry.getBlankClause();
					assertNotNull(blankClause);
					assertEquals(BlankClause.Type.SCREEN, blankClause.getType());
				}
			}

			{
				final ScreenDescriptionEntry subScreenDescriptionEntry = screenDescriptionEntry
						.getScreenDescriptionEntries().get(1);
				assertEquals("SOMELINE1", subScreenDescriptionEntry.getName());
				assertEquals(new Integer(5), subScreenDescriptionEntry.getLevelNumber());

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
					assertEquals("\"E M P L O Y E E\"", valueClause.getLiteral().getValue());
				}

				{
					final LightClause lightClause = subScreenDescriptionEntry.getLightClause();
					assertNotNull(lightClause);
					assertEquals(LightClause.Type.HIGHLIGHT, lightClause.getType());
				}
			}

			{
				final ScreenDescriptionEntry subScreenDescriptionEntry = screenDescriptionEntry
						.getScreenDescriptionEntries().get(2);
				assertNull(subScreenDescriptionEntry.getName());
				assertEquals(new Integer(5), subScreenDescriptionEntry.getLevelNumber());

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
					assertEquals("\"A D D R E S S\"", valueClause.getLiteral().getValue());
				}

				{
					final LightClause lightClause = subScreenDescriptionEntry.getLightClause();
					assertNotNull(lightClause);
					assertEquals(LightClause.Type.LOWLIGHT, lightClause.getType());
				}
			}
		}
	}
}
