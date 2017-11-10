package io.proleap.cobol.asg.procedure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ParagraphRedefinedTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/ParagraphRedefined.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ParagraphRedefined");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(2, procedureDivision.getParagraphs().size());
		assertEquals(0, procedureDivision.getSections().size());
		assertEquals(0, procedureDivision.getStatements().size());

		final List<Paragraph> paragraphs = procedureDivision.getParagraphs("INIT");
		assertEquals(2, paragraphs.size());

		{
			final Paragraph paragraph = paragraphs.get(0);
			assertEquals(procedureDivision.getParagraph("INIT"), paragraph);

			{
				final StopStatement stopStatement = (StopStatement) paragraph.getStatements().get(0);
				assertNotNull(stopStatement);
				assertEquals(StatementTypeEnum.STOP, stopStatement.getStatementType());
			}
		}

		{
			final Paragraph paragraph = paragraphs.get(1);
			assertNotEquals(procedureDivision.getParagraph("INIT"), paragraph);

			{
				final DisplayStatement displayStatement = (DisplayStatement) paragraph.getStatements().get(0);
				assertNotNull(displayStatement);
				assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
			}
		}
	}
}
