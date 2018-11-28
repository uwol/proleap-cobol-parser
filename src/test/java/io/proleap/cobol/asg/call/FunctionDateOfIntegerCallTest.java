package io.proleap.cobol.asg.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveToSendingArea;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveToStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class FunctionDateOfIntegerCallTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/call/FunctionDateOfIntegerCall.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("FunctionDateOfIntegerCall");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();

		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();
		assertEquals(1, workingStorageSection.getDataDescriptionEntries().size());

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final MoveStatement moveStatement = (MoveStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.MOVE, moveStatement.getStatementType());
			assertEquals(MoveStatement.MoveType.MOVE_TO, moveStatement.getMoveType());

			{
				final MoveToStatement moveToPhrase = moveStatement.getMoveToStatement();

				{
					final MoveToSendingArea sendingArea = moveToPhrase.getSendingArea();
					assertNotNull(sendingArea);

					{
						final ValueStmt sendingAreaValueStmt = sendingArea.getSendingAreaValueStmt();
						assertNotNull(sendingAreaValueStmt);

						final CallValueStmt sendingAreaCallValueStmt = (CallValueStmt) sendingAreaValueStmt;
						final Call call = sendingAreaCallValueStmt.getCall();
						assertEquals("DATE-OF-INTEGER", call.getName());
						assertEquals(CallType.FUNCTION_CALL, call.getCallType());
					}
				}

				{
					final List<Call> receivingAreaCalls = moveToPhrase.getReceivingAreaCalls();
					assertEquals(1, receivingAreaCalls.size());

					{
						final Call receivingAreaCall = receivingAreaCalls.get(0);
						assertNotNull(receivingAreaCall);
						assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, receivingAreaCall.getCallType());
					}
				}
			}
		}
	}
}