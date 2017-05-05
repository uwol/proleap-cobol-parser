package io.proleap.cobol.asg.procedure.gotostmt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.ProcedureCall;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.DependingOn;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.GoToStatement;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.Simple;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class GoToStatementTest extends CobolTestSupport {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/gotostmt/GoToStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("GoToStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(3, procedureDivision.getParagraphs().size());
		assertEquals(0, procedureDivision.getStatements().size());

		final Paragraph paragraph1 = procedureDivision.getParagraph("SOMEPROC1");
		assertNotNull(paragraph1);

		final Paragraph paragraph2 = procedureDivision.getParagraph("SOMEPROC2");
		assertNotNull(paragraph2);

		final Paragraph paragraph3 = procedureDivision.getParagraph("SOMEPROC3");
		assertNotNull(paragraph3);

		{
			assertEquals(3, paragraph1.getStatements().size());

			{
				final GoToStatement statement = (GoToStatement) paragraph1.getStatements().get(0);
				assertNotNull(statement);
				assertEquals(StatementTypeEnum.GO_TO, statement.getStatementType());
				assertEquals(GoToStatement.Type.SIMPLE, statement.getType());

				{
					final Simple simple = statement.getSimple();
					assertEquals(Call.CallType.PROCEDURE_CALL, simple.getProcedureCall().getCallType());

					{
						final ProcedureCall procedureCall = (ProcedureCall) simple.getProcedureCall();
						assertEquals(paragraph1, procedureCall.getParagraph());
					}
				}
			}

			{
				final GoToStatement statement = (GoToStatement) paragraph1.getStatements().get(1);
				assertNotNull(statement);
				assertEquals(StatementTypeEnum.GO_TO, statement.getStatementType());
				assertEquals(GoToStatement.Type.DEPENDING_ON, statement.getType());

				{
					final DependingOn dependingOn = statement.getDependingOn();
					assertEquals(2, dependingOn.getProcedureCalls().size());

					{
						final Call call = dependingOn.getProcedureCalls().get(0);
						assertEquals(Call.CallType.PROCEDURE_CALL, call.getCallType());

						{
							final ProcedureCall procedureCall = (ProcedureCall) call;
							assertEquals(paragraph2, procedureCall.getParagraph());
						}
					}

					{
						final Call call = dependingOn.getProcedureCalls().get(1);
						assertEquals(Call.CallType.PROCEDURE_CALL, call.getCallType());

						{
							final ProcedureCall procedureCall = (ProcedureCall) call;
							assertEquals(paragraph3, procedureCall.getParagraph());
						}
					}

					{
						final Call dependingOnCall = dependingOn.getDependingOnCall();
						assertEquals(Call.CallType.UNDEFINED_CALL, dependingOnCall.getCallType());
					}
				}
			}

			{
				final GoToStatement statement = (GoToStatement) paragraph1.getStatements().get(2);
				assertNotNull(statement);
				assertEquals(StatementTypeEnum.GO_TO, statement.getStatementType());
				assertEquals(GoToStatement.Type.DEPENDING_ON, statement.getType());

				{
					final DependingOn dependingOn = statement.getDependingOn();
					assertTrue(dependingOn.isMoreLabels());
				}
			}
		}

		{
			assertEquals(1, paragraph2.getStatements().size());

			{
				final Statement statement = paragraph2.getStatements().get(0);
				assertEquals(StatementTypeEnum.DISPLAY, statement.getStatementType());
			}
		}

		{
			assertEquals(1, paragraph3.getStatements().size());

			{
				final Statement statement = paragraph3.getStatements().get(0);
				assertEquals(StatementTypeEnum.DISPLAY, statement.getStatementType());
			}
		}
	}
}