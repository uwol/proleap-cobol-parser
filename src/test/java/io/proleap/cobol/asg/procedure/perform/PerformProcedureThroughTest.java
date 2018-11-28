package io.proleap.cobol.asg.procedure.perform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.ProcedureCall;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformProcedureStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class PerformProcedureThroughTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/perform/PerformProcedureThrough.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("PERFORMPROCEDURETHROUGH");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(5, procedureDivision.getParagraphs().size());
		assertEquals(0, procedureDivision.getStatements().size());

		{
			final Paragraph paragraph = procedureDivision.getParagraph("INIT");
			assertNotNull(paragraph);
			assertEquals(3, paragraph.getStatements().size());
			assertTrue(paragraph.getCalls().isEmpty());

			{
				final Statement statement = paragraph.getStatements().get(0);
				assertEquals(StatementTypeEnum.PERFORM, statement.getStatementType());

				final PerformStatement performStatement = (PerformStatement) statement;
				assertEquals(PerformStatement.PerformStatementType.PROCEDURE,
						performStatement.getPerformStatementType());

				{
					final PerformProcedureStatement performProcedureStatement = performStatement
							.getPerformProcedureStatement();
					assertNull(performProcedureStatement.getPerformType());

					assertEquals(3, performProcedureStatement.getCalls().size());

					{
						final Call call = performProcedureStatement.getCalls().get(0);
						final ProcedureCall procedureCall = (ProcedureCall) call;

						assertNotNull(procedureCall.getParagraph());
						assertEquals(procedureDivision.getParagraph("PROC2"), procedureCall.getParagraph());
					}

					{
						final Call call = performProcedureStatement.getCalls().get(1);
						final ProcedureCall procedureCall = (ProcedureCall) call;

						assertNotNull(procedureCall.getParagraph());
						// yes, has to be 3, not 4!
						assertEquals(procedureDivision.getParagraph("PROC3"), procedureCall.getParagraph());
					}

					{
						final Call call = performProcedureStatement.getCalls().get(2);
						final ProcedureCall procedureCall = (ProcedureCall) call;

						assertNotNull(procedureCall.getParagraph());
						assertEquals(procedureDivision.getParagraph("PROC4"), procedureCall.getParagraph());
					}
				}
			}

			{
				final Statement statement = paragraph.getStatements().get(1);
				assertEquals(StatementTypeEnum.PERFORM, statement.getStatementType());

				final PerformStatement performStatement = (PerformStatement) statement;
				assertEquals(PerformStatement.PerformStatementType.PROCEDURE,
						performStatement.getPerformStatementType());

				{
					final PerformProcedureStatement performProcedureStatement = performStatement
							.getPerformProcedureStatement();
					assertNull(performProcedureStatement.getPerformType());

					assertEquals(1, performProcedureStatement.getCalls().size());

					{
						final Call call = performProcedureStatement.getCalls().get(0);
						final ProcedureCall procedureCall = (ProcedureCall) call;

						assertNotNull(procedureCall.getParagraph());
						assertEquals(procedureDivision.getParagraph("PROC3"), procedureCall.getParagraph());
					}
				}
			}

			{
				final Statement statement = paragraph.getStatements().get(2);
				assertEquals(StatementTypeEnum.STOP, statement.getStatementType());
			}
		}

		{
			final Paragraph paragraph = procedureDivision.getParagraph("PROC1");
			assertNotNull(paragraph);
			assertEquals(1, paragraph.getStatements().size());
			assertTrue(paragraph.getCalls().isEmpty());

			{
				final Statement statement = paragraph.getStatements().get(0);
				assertEquals(StatementTypeEnum.DISPLAY, statement.getStatementType());
			}
		}

		{
			final Paragraph paragraph = procedureDivision.getParagraph("PROC2");
			assertNotNull(paragraph);
			assertEquals(1, paragraph.getStatements().size());
			assertFalse(paragraph.getCalls().isEmpty());
			assertEquals(1, paragraph.getCalls().size());

			{
				final Statement statement = paragraph.getStatements().get(0);
				assertEquals(StatementTypeEnum.DISPLAY, statement.getStatementType());
			}
		}

		{
			final Paragraph paragraph = procedureDivision.getParagraph("PROC3");
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
			final Paragraph paragraph = procedureDivision.getParagraph("PROC4");
			assertNotNull(paragraph);
			assertEquals(1, paragraph.getStatements().size());
			assertFalse(paragraph.getCalls().isEmpty());
			assertEquals(1, paragraph.getCalls().size());

			{
				final Statement statement = paragraph.getStatements().get(0);
				assertEquals(StatementTypeEnum.DISPLAY, statement.getStatementType());
			}
		}
	}
}
