package io.proleap.cobol.asg.procedure.send;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.send.Advancing;
import io.proleap.cobol.asg.metamodel.procedure.send.AdvancingLines;
import io.proleap.cobol.asg.metamodel.procedure.send.From;
import io.proleap.cobol.asg.metamodel.procedure.send.SendStatement;
import io.proleap.cobol.asg.metamodel.procedure.send.Sync;
import io.proleap.cobol.asg.metamodel.procedure.send.With;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SendSyncStatementTest extends CobolTestSupport {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/send/SendSyncStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SendSyncStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final SendStatement sendStatement = (SendStatement) procedureDivision.getStatements().get(0);
			assertNotNull(sendStatement);
			assertEquals(StatementTypeEnum.SEND, sendStatement.getStatementType());
			assertEquals(SendStatement.Type.SYNC, sendStatement.getType());

			{
				final Sync sync = sendStatement.getSync();
				assertNotNull(sync);

				{
					final ValueStmt receivingProgramValueStmt = sync.getReceivingProgramValueStmt();
					assertNotNull(receivingProgramValueStmt);

					final CallValueStmt receivingProgramCallValueStmt = (CallValueStmt) receivingProgramValueStmt;
					assertEquals(Call.CallType.UNDEFINED_CALL, receivingProgramCallValueStmt.getCall().getCallType());
				}

				{
					final From from = sync.getFrom();
					assertNotNull(from);
					assertEquals(Call.CallType.UNDEFINED_CALL, from.getFromCall().getCallType());
				}

				{
					final With with = sync.getWith();
					assertNotNull(with);
					assertEquals(With.Type.CALL, with.getType());
					assertEquals(Call.CallType.UNDEFINED_CALL, with.getWithCall().getCallType());
				}

				assertTrue(sync.isReplacing());

				{
					final Advancing advancing = sync.getAdvancing();
					assertNotNull(advancing);
					assertEquals(Advancing.PositionType.BEFORE, advancing.getPositionType());
					assertEquals(Advancing.Type.LINES, advancing.getType());

					{
						final AdvancingLines advancingLines = advancing.getAdvancingLines();
						assertNotNull(advancingLines);
						assertNotNull(advancingLines.getLinesValueStmt());
						assertEquals(3, advancingLines.getLinesValueStmt().getValue());
					}
				}
			}
		}
	}
}