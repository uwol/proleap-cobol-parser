package io.proleap.cobol.gpl.parser.procedure.send;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.send.Advancing;
import io.proleap.cobol.parser.metamodel.procedure.send.AdvancingLines;
import io.proleap.cobol.parser.metamodel.procedure.send.From;
import io.proleap.cobol.parser.metamodel.procedure.send.SendStatement;
import io.proleap.cobol.parser.metamodel.procedure.send.Sync;
import io.proleap.cobol.parser.metamodel.procedure.send.With;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SendSyncStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/send/SendSyncStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("SendSyncStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final SendStatement sendStatement = (SendStatement) procedureDivision.getStatements().get(0);
		assertNotNull(sendStatement);
		assertEquals(SendStatement.Type.Sync, sendStatement.getType());

		final Sync sync = sendStatement.getSync();
		assertNotNull(sync);

		{
			final Call receivingProgramCall = sync.getReceivingProgramCall();
			assertNotNull(receivingProgramCall);
			assertEquals(Call.CallType.UndefinedCall, receivingProgramCall.getCallType());
		}

		{
			final From from = sync.getFrom();
			assertNotNull(from);
			assertEquals(Call.CallType.UndefinedCall, from.getFromCall().getCallType());
		}

		{
			final With with = sync.getWith();
			assertNotNull(with);
			assertEquals(Call.CallType.UndefinedCall, with.getWithCall().getCallType());
		}

		assertTrue(sync.isReplacing());

		{
			final Advancing advancing = sync.getAdvancing();
			assertNotNull(advancing);
			assertEquals(Advancing.PositionType.Before, advancing.getPositionType());
			assertEquals(Advancing.Type.Lines, advancing.getType());

			{
				final AdvancingLines advancingLines = advancing.getAdvancingLines();
				assertNotNull(advancingLines);
				assertNotNull(advancingLines.getLinesCall());
				assertEquals(Call.CallType.UndefinedCall, advancingLines.getLinesCall().getCallType());
			}
		}
	}
}