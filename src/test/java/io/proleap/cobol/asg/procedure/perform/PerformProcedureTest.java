package io.proleap.cobol.asg.procedure.perform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class PerformProcedureTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/perform/PerformProcedure.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("PERFORMPROCEDURE");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(3, procedureDivision.getParagraphs().size());
		assertEquals(0, procedureDivision.getStatements().size());

		{
			final Paragraph paragraph = procedureDivision.getParagraph("INIT");
			assertNotNull(paragraph);

			{
				final Statement statement = paragraph.getStatements().get(0);
				assertEquals(StatementTypeEnum.PERFORM, statement.getStatementType());
			}

			{
				final Statement statement = paragraph.getStatements().get(1);
				assertEquals(StatementTypeEnum.PERFORM, statement.getStatementType());
			}

			{
				final Statement statement = paragraph.getStatements().get(2);
				assertEquals(StatementTypeEnum.PERFORM, statement.getStatementType());
			}

			{
				final Statement statement = paragraph.getStatements().get(3);
				assertEquals(StatementTypeEnum.STOP, statement.getStatementType());
			}
		}

		{
			final Paragraph paragraph = procedureDivision.getParagraph("PROC1");
			assertNotNull(paragraph);
			assertEquals(1, paragraph.getStatements().size());
			assertFalse(paragraph.getCalls().isEmpty());
			assertEquals(2, paragraph.getCalls().size());

			{
				final Statement statement = paragraph.getStatements().get(0);
				assertEquals(StatementTypeEnum.DISPLAY, statement.getStatementType());
			}
		}

		{
			final Paragraph paragraph = procedureDivision.getParagraph("PROC2");
			assertNotNull(paragraph);
			assertFalse(paragraph.getCalls().isEmpty());
			assertEquals(1, paragraph.getCalls().size());

			{
				final Statement statement = paragraph.getStatements().get(0);
				assertEquals(StatementTypeEnum.DISPLAY, statement.getStatementType());
			}
		}
	}
}
