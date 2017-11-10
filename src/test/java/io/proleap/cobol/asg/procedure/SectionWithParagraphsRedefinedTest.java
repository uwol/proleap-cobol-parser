package io.proleap.cobol.asg.procedure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Section;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SectionWithParagraphsRedefinedTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/SectionWithParagraphsRedefined.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SectionWithParagraphsRedefined");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(1, procedureDivision.getRootParagraphs().size());
		assertEquals(1, procedureDivision.getSections().size());
		assertEquals(0, procedureDivision.getStatements().size());

		{
			final Paragraph paragraph = procedureDivision.getParagraph("INIT");
			assertNotNull(paragraph);
			assertEquals(2, paragraph.getCalls().size());

			{
				final PerformStatement performStatement = (PerformStatement) paragraph.getStatements().get(0);
				assertNotNull(performStatement);
				assertEquals(StatementTypeEnum.PERFORM, performStatement.getStatementType());
			}
		}

		{
			final Section section = procedureDivision.getSection("SOME-SECTION");
			assertNotNull(section);
			assertEquals(2, section.getParagraphs().size());

			{
				final Paragraph paragraph = section.getParagraph("INIT");
				assertNotNull(paragraph);
				assertNotEquals(procedureDivision.getParagraph("INIT"), paragraph);

				{
					final PerformStatement performStatement = (PerformStatement) paragraph.getStatements().get(0);
					assertNotNull(performStatement);
					assertEquals(StatementTypeEnum.PERFORM, performStatement.getStatementType());
				}
			}

			{
				final Paragraph paragraph = section.getParagraph("AFTER-INIT");
				assertNotNull(paragraph);

				{
					final StopStatement stopStatement = (StopStatement) paragraph.getStatements().get(0);
					assertNotNull(stopStatement);
					assertEquals(StatementTypeEnum.STOP, stopStatement.getStatementType());
				}
			}
		}
	}
}
