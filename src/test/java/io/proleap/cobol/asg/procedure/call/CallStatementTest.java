package io.proleap.cobol.asg.procedure.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.call.ByContent;
import io.proleap.cobol.asg.metamodel.procedure.call.ByReference;
import io.proleap.cobol.asg.metamodel.procedure.call.CallByContent;
import io.proleap.cobol.asg.metamodel.procedure.call.CallByReference;
import io.proleap.cobol.asg.metamodel.procedure.call.CallByValue;
import io.proleap.cobol.asg.metamodel.procedure.call.CallStatement;
import io.proleap.cobol.asg.metamodel.procedure.call.Giving;
import io.proleap.cobol.asg.metamodel.procedure.call.Parameter;
import io.proleap.cobol.asg.metamodel.procedure.call.Using;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
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
				final Giving giving = callStatement.getGiving();
				assertNotNull(giving.getGivingCall());
				assertEquals(Call.CallType.UNDEFINED_CALL, giving.getGivingCall().getCallType());
			}

			{
				final Using using = callStatement.getUsing();
				assertEquals(3, using.getParameters().size());

				{
					final Parameter parameter = using.getParameters().get(0);
					assertEquals(Parameter.ParameterType.REFERENCE, parameter.getParameterType());

					{
						final CallByReference callByReferenceStatement = parameter.getCallByReference();
						assertEquals(2, callByReferenceStatement.getByReferences().size());

						{
							final ByReference byReference = callByReferenceStatement.getByReferences().get(0);
							assertEquals(ByReference.ByReferenceType.INTEGER, byReference.getByReferenceType());
							assertEquals(Call.CallType.UNDEFINED_CALL, byReference.getCall().getCallType());
						}

						{
							final ByReference byReference = callByReferenceStatement.getByReferences().get(1);
							assertNull(byReference.getByReferenceType());
							assertEquals(Call.CallType.UNDEFINED_CALL, byReference.getCall().getCallType());
						}
					}
				}

				{
					final Parameter parameter = using.getParameters().get(1);
					assertEquals(Parameter.ParameterType.VALUE, parameter.getParameterType());

					final CallByValue callByValueStatement = parameter.getCallByValue();
					assertEquals(3, callByValueStatement.getValueStmts().size());

					{
						final ValueStmt valueStmt = callByValueStatement.getValueStmts().get(0);
						assertEquals(1, valueStmt.getValue());
					}

					{
						final ValueStmt valueStmt = callByValueStatement.getValueStmts().get(1);
						assertEquals(2, valueStmt.getValue());
					}

					{
						final ValueStmt valueStmt = callByValueStatement.getValueStmts().get(2);
						assertNotNull(valueStmt);
					}
				}

				{
					final Parameter parameter = using.getParameters().get(2);
					assertEquals(Parameter.ParameterType.CONTENT, parameter.getParameterType());

					{
						final CallByContent callByContentStatement = parameter.getCallByContent();
						assertEquals(3, callByContentStatement.getByContents().size());

						{
							final ByContent byContent = callByContentStatement.getByContents().get(0);
							assertEquals(ByContent.ByContentType.ADDRESS_OF, byContent.getByContentType());
							assertNotNull(byContent.getValueStmt());

							final CallValueStmt callValueStmt = (CallValueStmt) byContent.getValueStmt();
							assertEquals(Call.CallType.UNDEFINED_CALL, callValueStmt.getCall().getCallType());
						}

						{
							final ByContent byContent = callByContentStatement.getByContents().get(1);
							assertEquals(ByContent.ByContentType.LENGTH_OF, byContent.getByContentType());
							assertNotNull(byContent.getValueStmt());

							final CallValueStmt callValueStmt = (CallValueStmt) byContent.getValueStmt();
							assertEquals(Call.CallType.UNDEFINED_CALL, callValueStmt.getCall().getCallType());
						}

						{
							final ByContent byContent = callByContentStatement.getByContents().get(2);
							assertNull(byContent.getByContentType());
							assertNotNull(byContent.getValueStmt());

							assertEquals(4, byContent.getValueStmt().getValue());
						}
					}
				}
			}
		}
	}
}
