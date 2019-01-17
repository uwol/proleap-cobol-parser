package io.proleap.cobol.asg.procedure.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.call.ByReferencePhrase;
import io.proleap.cobol.asg.metamodel.procedure.call.ByValue;
import io.proleap.cobol.asg.metamodel.procedure.call.ByValuePhrase;
import io.proleap.cobol.asg.metamodel.procedure.call.CallStatement;
import io.proleap.cobol.asg.metamodel.procedure.call.UsingParameter;
import io.proleap.cobol.asg.metamodel.procedure.call.UsingPhrase;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CallStatementFunctionTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/call/CallStatementFunction.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("CallStatementFunction");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(3, procedureDivision.getStatements().size());

		{
			final CallStatement callStatement = (CallStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.CALL, callStatement.getStatementType());

			{
				final UsingPhrase usingPhrase = callStatement.getUsingPhrase();
				assertEquals(2, usingPhrase.getUsingParameters().size());

				{
					final UsingParameter parameter = usingPhrase.getUsingParameters().get(0);
					assertEquals(UsingParameter.ParameterType.REFERENCE, parameter.getParameterType());

					{
						final ByReferencePhrase byReferencePhrase = parameter.getByReferencePhrase();
						assertNotNull(byReferencePhrase);
						assertEquals(1, byReferencePhrase.getByReferences().size());
					}
				}

				{
					final UsingParameter parameter = usingPhrase.getUsingParameters().get(1);
					assertEquals(UsingParameter.ParameterType.VALUE, parameter.getParameterType());

					{
						final ByValuePhrase byValuePhrase = parameter.getByValuePhrase();
						assertNotNull(byValuePhrase);
						assertEquals(1, byValuePhrase.getByValues().size());

						{
							final ByValue byValue = byValuePhrase.getByValues().get(0);
							assertEquals(ByValue.ByValueType.LENGTH_OF, byValue.getByValueType());
						}
					}
				}
			}
		}

		{
			final CallStatement callStatement = (CallStatement) procedureDivision.getStatements().get(1);
			assertEquals(StatementTypeEnum.CALL, callStatement.getStatementType());

			{
				final UsingPhrase usingPhrase = callStatement.getUsingPhrase();
				assertEquals(1, usingPhrase.getUsingParameters().size());

				{
					final UsingParameter parameter = usingPhrase.getUsingParameters().get(0);
					assertEquals(UsingParameter.ParameterType.REFERENCE, parameter.getParameterType());

					{
						final ByReferencePhrase byReferencePhrase = parameter.getByReferencePhrase();
						assertNotNull(byReferencePhrase);
						assertEquals(2, byReferencePhrase.getByReferences().size());
					}
				}
			}
		}

		{
			final CallStatement callStatement = (CallStatement) procedureDivision.getStatements().get(2);
			assertEquals(StatementTypeEnum.CALL, callStatement.getStatementType());

			{
				final UsingPhrase usingPhrase = callStatement.getUsingPhrase();
				assertEquals(2, usingPhrase.getUsingParameters().size());

				{
					final UsingParameter parameter = usingPhrase.getUsingParameters().get(0);
					assertEquals(UsingParameter.ParameterType.REFERENCE, parameter.getParameterType());

					{
						final ByReferencePhrase byReferencePhrase = parameter.getByReferencePhrase();
						assertNotNull(byReferencePhrase);
						assertEquals(1, byReferencePhrase.getByReferences().size());
					}
				}

				{
					final UsingParameter parameter = usingPhrase.getUsingParameters().get(1);
					assertEquals(UsingParameter.ParameterType.VALUE, parameter.getParameterType());

					{
						final ByValuePhrase byValuePhrase = parameter.getByValuePhrase();
						assertNotNull(byValuePhrase);
						assertEquals(1, byValuePhrase.getByValues().size());

						{
							final ByValue byValue = byValuePhrase.getByValues().get(0);
							assertEquals(ByValue.ByValueType.LENGTH_OF, byValue.getByValueType());
						}
					}
				}
			}
		}
	}
}
