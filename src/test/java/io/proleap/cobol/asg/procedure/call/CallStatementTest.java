package io.proleap.cobol.asg.procedure.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.call.ByContent;
import io.proleap.cobol.asg.metamodel.procedure.call.ByContentPhrase;
import io.proleap.cobol.asg.metamodel.procedure.call.ByReference;
import io.proleap.cobol.asg.metamodel.procedure.call.ByReferencePhrase;
import io.proleap.cobol.asg.metamodel.procedure.call.ByValue;
import io.proleap.cobol.asg.metamodel.procedure.call.ByValuePhrase;
import io.proleap.cobol.asg.metamodel.procedure.call.CallStatement;
import io.proleap.cobol.asg.metamodel.procedure.call.GivingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.call.UsingParameter;
import io.proleap.cobol.asg.metamodel.procedure.call.UsingPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CallStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/call/CallStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("CallStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final CallStatement callStatement = (CallStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.CALL, callStatement.getStatementType());

			{
				final ValueStmt programValueStmt = callStatement.getProgramValueStmt();
				assertNotNull(programValueStmt);

				final CallValueStmt programCallValueStmt = (CallValueStmt) programValueStmt;
				assertEquals(CallType.UNDEFINED_CALL, programCallValueStmt.getCall().getCallType());
			}

			{
				final GivingPhrase givingPhrase = callStatement.getGivingPhrase();
				assertNotNull(givingPhrase.getGivingCall());
				assertEquals(CallType.UNDEFINED_CALL, givingPhrase.getGivingCall().getCallType());
			}

			{
				final UsingPhrase usingPhrase = callStatement.getUsingPhrase();
				assertEquals(3, usingPhrase.getUsingParameters().size());

				{
					final UsingParameter parameter = usingPhrase.getUsingParameters().get(0);
					assertEquals(UsingParameter.ParameterType.REFERENCE, parameter.getParameterType());

					{
						final ByReferencePhrase byReferencePhrase = parameter.getByReferencePhrase();
						assertEquals(2, byReferencePhrase.getByReferences().size());

						{
							final ByReference byReference = byReferencePhrase.getByReferences().get(0);
							assertEquals(ByReference.ByReferenceType.INTEGER, byReference.getByReferenceType());

							final CallValueStmt callValueStmt = (CallValueStmt) byReference.getValueStmt();
							assertEquals(CallType.UNDEFINED_CALL, callValueStmt.getCall().getCallType());
						}

						{
							final ByReference byReference = byReferencePhrase.getByReferences().get(1);
							assertNull(byReference.getByReferenceType());

							final CallValueStmt callValueStmt = (CallValueStmt) byReference.getValueStmt();
							assertEquals(CallType.UNDEFINED_CALL, callValueStmt.getCall().getCallType());
						}
					}
				}

				{
					final UsingParameter parameter = usingPhrase.getUsingParameters().get(1);
					assertEquals(UsingParameter.ParameterType.VALUE, parameter.getParameterType());

					final ByValuePhrase byValuePhrase = parameter.getByValuePhrase();
					assertEquals(3, byValuePhrase.getByValues().size());

					{
						final ByValue byValue = byValuePhrase.getByValues().get(0);
						final ValueStmt valueStmt = byValue.getValueStmt();

						final LiteralValueStmt literalValueStmt = (LiteralValueStmt) valueStmt;
						final Literal literal = literalValueStmt.getLiteral();
						assertEquals(BigDecimal.ONE, literal.getValue());
					}

					{
						final ByValue byValue = byValuePhrase.getByValues().get(1);
						final ValueStmt valueStmt = byValue.getValueStmt();

						final LiteralValueStmt literalValueStmt = (LiteralValueStmt) valueStmt;
						final Literal literal = literalValueStmt.getLiteral();
						assertEquals(new BigDecimal(2), literal.getValue());
					}

					{
						final ByValue byValue = byValuePhrase.getByValues().get(2);
						assertNotNull(byValue.getValueStmt());
					}
				}

				{
					final UsingParameter parameter = usingPhrase.getUsingParameters().get(2);
					assertEquals(UsingParameter.ParameterType.CONTENT, parameter.getParameterType());

					{
						final ByContentPhrase byContentPhrase = parameter.getByContentPhrase();
						assertEquals(3, byContentPhrase.getByContents().size());

						{
							final ByContent byContent = byContentPhrase.getByContents().get(0);
							assertEquals(ByContent.ByContentType.ADDRESS_OF, byContent.getByContentType());
							assertNotNull(byContent.getValueStmt());

							final CallValueStmt callValueStmt = (CallValueStmt) byContent.getValueStmt();
							assertEquals(CallType.UNDEFINED_CALL, callValueStmt.getCall().getCallType());
						}

						{
							final ByContent byContent = byContentPhrase.getByContents().get(1);
							assertEquals(ByContent.ByContentType.LENGTH_OF, byContent.getByContentType());
							assertNotNull(byContent.getValueStmt());

							final CallValueStmt callValueStmt = (CallValueStmt) byContent.getValueStmt();
							assertEquals(CallType.UNDEFINED_CALL, callValueStmt.getCall().getCallType());
						}

						{
							final ByContent byContent = byContentPhrase.getByContents().get(2);
							assertNull(byContent.getByContentType());
							assertNotNull(byContent.getValueStmt());

							final LiteralValueStmt literalValueStmt = (LiteralValueStmt) byContent.getValueStmt();
							final Literal literal = literalValueStmt.getLiteral();
							assertEquals(new BigDecimal(4), literal.getValue());
						}
					}
				}
			}
		}
	}
}
