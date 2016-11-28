package io.proleap.cobol.gpl.parser.procedure.receive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CompilationUnit;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.receive.From;
import io.proleap.cobol.parser.metamodel.procedure.receive.ReceiveFromStatement;
import io.proleap.cobol.parser.metamodel.procedure.receive.ReceiveStatement;
import io.proleap.cobol.parser.metamodel.procedure.receive.Size;
import io.proleap.cobol.parser.metamodel.procedure.receive.Status;
import io.proleap.cobol.parser.metamodel.procedure.receive.Thread;
import io.proleap.cobol.parser.metamodel.procedure.receive.With;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ReceiveFromStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/receive/ReceiveFromStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ReceiveFromStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final ReceiveStatement receiveStatement = (ReceiveStatement) procedureDivision.getStatements().get(0);
			assertNotNull(receiveStatement);

			assertEquals(ReceiveStatement.Type.From, receiveStatement.getType());
			assertNotNull(receiveStatement.getReceiveFromStatement());

			final ReceiveFromStatement receiveFromStatement = receiveStatement.getReceiveFromStatement();

			{
				final Call dataCall = receiveFromStatement.getDataCall();
				assertNotNull(dataCall);
				assertEquals(Call.CallType.UndefinedCall, dataCall.getCallType());
			}

			{
				final From from = receiveFromStatement.getFrom();

				{
					assertEquals(From.Type.Thread, from.getType());
				}

				{
					final Call threadCall = from.getThreadCall();
					assertNotNull(threadCall);
					assertEquals(Call.CallType.UndefinedCall, threadCall.getCallType());
				}
			}

			{
				final io.proleap.cobol.parser.metamodel.procedure.receive.Before before = receiveFromStatement
						.getBefore();
				final Call timeCall = before.getTimeCall();

				assertNotNull(timeCall);
				assertEquals(Call.CallType.UndefinedCall, timeCall.getCallType());
			}

			{
				final With with = receiveFromStatement.getWith();
				assertTrue(with.isNoWait());
			}

			{
				final Thread thread = receiveFromStatement.getThread();
				final Call threadInCall = thread.getThreadInCall();

				assertNotNull(threadInCall);
				assertEquals(Call.CallType.UndefinedCall, threadInCall.getCallType());
			}

			{
				final Size size = receiveFromStatement.getSize();
				final Call sizeCall = size.getSizeCall();

				assertNotNull(sizeCall);
				assertEquals(Call.CallType.UndefinedCall, sizeCall.getCallType());
			}

			{
				final Status status = receiveFromStatement.getStatus();
				final Call statusCall = status.getStatusCall();

				assertNotNull(statusCall);
				assertEquals(Call.CallType.UndefinedCall, statusCall.getCallType());
			}
		}
	}
}