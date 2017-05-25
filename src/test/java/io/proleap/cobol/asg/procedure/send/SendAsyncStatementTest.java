package io.proleap.cobol.asg.procedure.send;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import io.proleap.cobol.asg.metamodel.procedure.send.Async;
import io.proleap.cobol.asg.metamodel.procedure.send.SendStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SendAsyncStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/send/SendAsyncStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SendAsyncStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final SendStatement sendStatement = (SendStatement) procedureDivision.getStatements().get(0);
			assertNotNull(sendStatement);
			assertEquals(StatementTypeEnum.SEND, sendStatement.getStatementType());
			assertEquals(SendStatement.SendType.ASYNC, sendStatement.getSendType());

			{
				final Async async = sendStatement.getAsync();
				assertNotNull(async);
				assertEquals(Async.AsyncType.TOP, async.getAsyncType());

				{
					final Call dataDescriptionEntryCall = async.getDataDescriptionEntryCall();
					assertNotNull(dataDescriptionEntryCall);
					assertEquals(CallType.UNDEFINED_CALL, dataDescriptionEntryCall.getCallType());
				}
			}
		}
	}
}
