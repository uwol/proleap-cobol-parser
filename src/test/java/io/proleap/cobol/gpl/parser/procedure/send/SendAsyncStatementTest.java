package io.proleap.cobol.gpl.parser.procedure.send;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import io.proleap.cobol.parser.metamodel.procedure.send.Async;
import io.proleap.cobol.parser.metamodel.procedure.send.SendStatement;
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
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/send/SendAsyncStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("SendAsyncStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final SendStatement sendStatement = (SendStatement) procedureDivision.getStatements().get(0);
		assertNotNull(sendStatement);
		assertEquals(SendStatement.Type.Async, sendStatement.getType());

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