package io.proleap.cobol.asg.procedure.write;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.write.Advancing;
import io.proleap.cobol.asg.metamodel.procedure.write.AdvancingLines;
import io.proleap.cobol.asg.metamodel.procedure.write.From;
import io.proleap.cobol.asg.metamodel.procedure.write.WriteStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class WriteStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/write/WriteStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("WriteStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final WriteStatement writeStatement = (WriteStatement) procedureDivision.getStatements().get(0);
			assertNotNull(writeStatement);
			assertEquals(StatementTypeEnum.WRITE, writeStatement.getStatementType());

			{
				final Call recordCall = writeStatement.getRecordCall();
				assertNotNull(recordCall);
				assertEquals(Call.CallType.UNDEFINED_CALL, recordCall.getCallType());
			}

			{
				final From from = writeStatement.getFrom();
				assertNotNull(from);

				final CallValueStmt fromCallValueStmt = (CallValueStmt) from.getFromValueStmt();
				assertEquals(Call.CallType.UNDEFINED_CALL, fromCallValueStmt.getCall().getCallType());
			}

			{
				final Advancing advancing = writeStatement.getAdvancing();
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