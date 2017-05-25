package io.proleap.cobol.asg.procedure.receive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import io.proleap.cobol.asg.metamodel.procedure.receive.From;
import io.proleap.cobol.asg.metamodel.procedure.receive.ReceiveFromStatement;
import io.proleap.cobol.asg.metamodel.procedure.receive.ReceiveStatement;
import io.proleap.cobol.asg.metamodel.procedure.receive.Size;
import io.proleap.cobol.asg.metamodel.procedure.receive.Status;
import io.proleap.cobol.asg.metamodel.procedure.receive.Thread;
import io.proleap.cobol.asg.metamodel.procedure.receive.With;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ReceiveFromStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/receive/ReceiveFromStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ReceiveFromStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final ReceiveStatement receiveStatement = (ReceiveStatement) procedureDivision.getStatements().get(0);
			assertNotNull(receiveStatement);
			assertEquals(StatementTypeEnum.RECEIVE, receiveStatement.getStatementType());
			assertEquals(ReceiveStatement.ReceiveType.FROM, receiveStatement.getReceiveType());
			assertNotNull(receiveStatement.getReceiveFromStatement());

			{
				final ReceiveFromStatement receiveFromStatement = receiveStatement.getReceiveFromStatement();

				{
					final Call dataCall = receiveFromStatement.getDataCall();
					assertNotNull(dataCall);
					assertEquals(CallType.UNDEFINED_CALL, dataCall.getCallType());
				}

				{
					final From from = receiveFromStatement.getFrom();

					{
						assertEquals(From.FromType.THREAD, from.getFromType());
					}

					{
						final Call threadCall = from.getThreadCall();
						assertNotNull(threadCall);
						assertEquals(CallType.UNDEFINED_CALL, threadCall.getCallType());
					}
				}

				{
					final io.proleap.cobol.asg.metamodel.procedure.receive.Before before = receiveFromStatement
							.getBefore();
					final ValueStmt timeValueStmt = before.getTimeValueStmt();
					assertNotNull(timeValueStmt);

					final CallValueStmt timeCallValueStmt = (CallValueStmt) timeValueStmt;
					assertEquals(CallType.UNDEFINED_CALL, timeCallValueStmt.getCall().getCallType());
				}

				{
					final With with = receiveFromStatement.getWith();
					assertTrue(with.isNoWait());
				}

				{
					final Thread thread = receiveFromStatement.getThread();
					final Call threadInCall = thread.getThreadInCall();

					assertNotNull(threadInCall);
					assertEquals(CallType.UNDEFINED_CALL, threadInCall.getCallType());
				}

				{
					final Size size = receiveFromStatement.getSize();
					final ValueStmt sizeValueStmt = size.getSizeValueStmt();

					assertNotNull(sizeValueStmt);

					final CallValueStmt sizeCallValueStmt = (CallValueStmt) sizeValueStmt;
					assertEquals(CallType.UNDEFINED_CALL, sizeCallValueStmt.getCall().getCallType());
				}

				{
					final Status status = receiveFromStatement.getStatus();
					final Call statusCall = status.getStatusCall();

					assertNotNull(statusCall);
					assertEquals(CallType.UNDEFINED_CALL, statusCall.getCallType());
				}
			}
		}
	}
}
