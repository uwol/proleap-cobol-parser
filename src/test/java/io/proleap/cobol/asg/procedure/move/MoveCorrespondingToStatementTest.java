package io.proleap.cobol.asg.procedure.move;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveCorrespondingToSendingArea;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveCorrespondingToStatetement;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class MoveCorrespondingToStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/move/MoveCorrespondingToStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("MoveCorrespondingToStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		{
			final DataDescriptionEntry dataDescriptionEntry = workingStorageSection
					.getDataDescriptionEntry("SOME-TEXT");

			assertNotNull(dataDescriptionEntry);
			assertFalse(dataDescriptionEntry.getCalls().isEmpty());
			assertEquals(1, dataDescriptionEntry.getCalls().size());
		}

		{
			final DataDescriptionEntry dataDescriptionEntry = workingStorageSection
					.getDataDescriptionEntry("SOME-TEXT2");

			assertNotNull(dataDescriptionEntry);
			assertFalse(dataDescriptionEntry.getCalls().isEmpty());
			assertEquals(1, dataDescriptionEntry.getCalls().size());
		}

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(2, procedureDivision.getStatements().size());

		{
			final MoveStatement moveStatement = (MoveStatement) procedureDivision.getStatements().get(0);
			assertNotNull(moveStatement);
			assertEquals(StatementTypeEnum.MOVE, moveStatement.getStatementType());
			assertEquals(MoveStatement.MoveType.MOVE_CORRESPONDING, moveStatement.getMoveType());

			{
				final MoveCorrespondingToStatetement moveCorrespondingToStatement = moveStatement
						.getMoveCorrespondingToStatement();

				{
					final MoveCorrespondingToSendingArea sendingArea = moveCorrespondingToStatement
							.getMoveToCorrespondingSendingArea();
					final Call sendingCall = sendingArea.getSendingAreaCall();
					assertNotNull(sendingCall);
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, sendingCall.getCallType());
				}

				{
					final List<Call> receivingAreaCalls = moveCorrespondingToStatement.getReceivingAreaCalls();
					assertEquals(1, receivingAreaCalls.size());

					{
						final Call receivingAreaCall = receivingAreaCalls.get(0);
						assertNotNull(receivingAreaCall);
						assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, receivingAreaCall.getCallType());
					}
				}
			}
		}

		{
			final MoveStatement moveStatement = (MoveStatement) procedureDivision.getStatements().get(1);
			assertNotNull(moveStatement);
			assertEquals(StatementTypeEnum.MOVE, moveStatement.getStatementType());
			assertEquals(MoveStatement.MoveType.MOVE_CORRESPONDING, moveStatement.getMoveType());

			{
				final MoveCorrespondingToStatetement moveCorrespondingToStatement = moveStatement
						.getMoveCorrespondingToStatement();

				{
					final MoveCorrespondingToSendingArea sendingArea = moveCorrespondingToStatement
							.getMoveToCorrespondingSendingArea();
					final Call sendingCall = sendingArea.getSendingAreaCall();
					assertNotNull(sendingCall);
					assertEquals(CallType.UNDEFINED_CALL, sendingCall.getCallType());
				}

				{
					final List<Call> receivingAreaCalls = moveCorrespondingToStatement.getReceivingAreaCalls();
					assertEquals(1, receivingAreaCalls.size());

					{
						final Call receivingAreaCall = receivingAreaCalls.get(0);
						assertNotNull(receivingAreaCall);
						assertEquals(CallType.UNDEFINED_CALL, receivingAreaCall.getCallType());
					}
				}
			}
		}
	}
}
