package io.proleap.cobol.asg.procedure.send;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import io.proleap.cobol.asg.metamodel.procedure.send.Async;
import io.proleap.cobol.asg.metamodel.procedure.send.SendStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SendAsyncStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/send/SendAsyncStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SendAsyncStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final SendStatement sendStatement = (SendStatement) procedureDivision.getStatements().get(0);
			assertNotNull(sendStatement);
			assertEquals(StatementTypeEnum.Send, sendStatement.getStatementType());
			assertEquals(SendStatement.Type.Async, sendStatement.getType());

			{
				final Async async = sendStatement.getAsync();
				assertNotNull(async);
				assertEquals(Async.Type.Top, async.getType());

				{
					final Call dataDescriptionEntryCall = async.getDataDescriptionEntryCall();
					assertNotNull(dataDescriptionEntryCall);
					assertEquals(Call.CallType.UndefinedCall, dataDescriptionEntryCall.getCallType());
				}
			}
		}
	}
}