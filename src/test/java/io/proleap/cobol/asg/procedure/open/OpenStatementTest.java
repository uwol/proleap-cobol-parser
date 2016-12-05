package io.proleap.cobol.asg.procedure.open;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.open.Input;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenExtend;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenInput;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenInputOutput;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenOutput;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenStatement;
import io.proleap.cobol.asg.metamodel.procedure.open.Output;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class OpenStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/open/OpenStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("OpenStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final OpenStatement openStatement = (OpenStatement) procedureDivision.getStatements().get(0);
			assertNotNull(openStatement);
			assertEquals(StatementTypeEnum.Open, openStatement.getStatementType());

			assertEquals(1, openStatement.getOpenInputs().size());
			assertEquals(1, openStatement.getOpenOutputs().size());
			assertEquals(1, openStatement.getOpenInputOutputs().size());
			assertEquals(1, openStatement.getOpenExtends().size());

			{
				final OpenInput openInput = openStatement.getOpenInputs().get(0);
				assertEquals(2, openInput.getInputs().size());

				{
					final Input input = openInput.getInputs().get(0);
					assertEquals(Input.Type.NoRewind, input.getType());

					{
						final Call fileCall = input.getFileCall();
						assertNotNull(fileCall);
						assertEquals(Call.CallType.UndefinedCall, fileCall.getCallType());
					}
				}

				{
					final Input input = openInput.getInputs().get(1);
					assertEquals(Input.Type.Reversed, input.getType());

					{
						final Call fileCall = input.getFileCall();
						assertNotNull(fileCall);
						assertEquals(Call.CallType.UndefinedCall, fileCall.getCallType());
					}
				}
			}

			{
				final OpenOutput openOutput = openStatement.getOpenOutputs().get(0);
				assertEquals(1, openOutput.getOutputs().size());

				{
					final Output output = openOutput.getOutputs().get(0);
					assertTrue(output.isNoRewind());

					{
						final Call fileCall = output.getFileCall();
						assertNotNull(fileCall);
						assertEquals(Call.CallType.UndefinedCall, fileCall.getCallType());
					}
				}
			}

			{
				final OpenInputOutput openInputOutput = openStatement.getOpenInputOutputs().get(0);
				assertEquals(2, openInputOutput.getFileCalls().size());

				{
					final Call fileCall = openInputOutput.getFileCalls().get(0);
					assertNotNull(fileCall);
					assertEquals(Call.CallType.UndefinedCall, fileCall.getCallType());
				}

				{
					final Call fileCall = openInputOutput.getFileCalls().get(1);
					assertNotNull(fileCall);
					assertEquals(Call.CallType.UndefinedCall, fileCall.getCallType());
				}
			}

			{
				final OpenExtend openExtend = openStatement.getOpenExtends().get(0);
				assertEquals(2, openExtend.getFileCalls().size());

				{
					final Call fileCall = openExtend.getFileCalls().get(0);
					assertNotNull(fileCall);
					assertEquals(Call.CallType.UndefinedCall, fileCall.getCallType());
				}

				{
					final Call fileCall = openExtend.getFileCalls().get(1);
					assertNotNull(fileCall);
					assertEquals(Call.CallType.UndefinedCall, fileCall.getCallType());
				}
			}
		}
	}
}