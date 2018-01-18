package io.proleap.cobol.asg.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.List;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.call.FunctionCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry.DataDescriptionEntryType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.asg.metamodel.identification.ProgramIdParagraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveToSendingArea;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveToStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class FunctionCallTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/call/FunctionCall.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.VARIABLE);

		final CompilationUnit compilationUnit = program.getCompilationUnit("FunctionCall");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final IdentificationDivision identificationDivision = programUnit.getIdentificationDivision();
		final ProgramIdParagraph programIdParagraph = identificationDivision.getProgramIdParagraph();
		assertEquals("FUNCTIONCALL", programIdParagraph.getName());

		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(30, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(2, workingStorageSection.getRootDataDescriptionEntries().size());

		final DataDescriptionEntry dataDescriptionEntrySSchalter = workingStorageSection
				.getDataDescriptionEntry("S-SCHALTER");
		final DataDescriptionEntry dataDescriptionEntryV0P190 = workingStorageSection.getDataDescriptionEntry("V0P190");
		final DataDescriptionEntry dataDescriptionEntryV0P190Text;

		{
			assertNotNull(dataDescriptionEntrySSchalter);
			assertEquals("S-SCHALTER", dataDescriptionEntrySSchalter.getName());
			assertEquals(Integer.valueOf(1), dataDescriptionEntrySSchalter.getLevelNumber());
			assertNull(dataDescriptionEntrySSchalter.getParentDataDescriptionEntryGroup());
			assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntrySSchalter.getDataDescriptionEntryType());

			final DataDescriptionEntryGroup dataDescriptionEntryGroupSSchalter = (DataDescriptionEntryGroup) dataDescriptionEntrySSchalter;
			assertNull(dataDescriptionEntryGroupSSchalter.getDataDescriptionEntryPredecessor());
			assertNotNull(dataDescriptionEntryGroupSSchalter.getDataDescriptionEntrySuccessor());
			assertEquals(dataDescriptionEntryV0P190,
					dataDescriptionEntryGroupSSchalter.getDataDescriptionEntrySuccessor());
		}

		{
			assertNotNull(dataDescriptionEntryV0P190);
			assertEquals("V0P190", dataDescriptionEntryV0P190.getName());
			assertEquals(Integer.valueOf(1), dataDescriptionEntryV0P190.getLevelNumber());
			assertNull(dataDescriptionEntryV0P190.getParentDataDescriptionEntryGroup());
			assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryV0P190.getDataDescriptionEntryType());

			final DataDescriptionEntryGroup dataDescriptionEntryGroupV0P190 = (DataDescriptionEntryGroup) dataDescriptionEntryV0P190;
			assertNotNull(dataDescriptionEntryGroupV0P190.getDataDescriptionEntryPredecessor());
			assertNull(dataDescriptionEntryGroupV0P190.getDataDescriptionEntrySuccessor());
			assertEquals(dataDescriptionEntrySSchalter,
					dataDescriptionEntryGroupV0P190.getDataDescriptionEntryPredecessor());

			{
				dataDescriptionEntryV0P190Text = dataDescriptionEntryGroupV0P190.getDataDescriptionEntry("P190-TEXT");
				assertEquals("P190-TEXT", dataDescriptionEntryV0P190Text.getName());
				assertEquals(Integer.valueOf(5), dataDescriptionEntryV0P190Text.getLevelNumber());
				assertNotNull(dataDescriptionEntryV0P190Text.getParentDataDescriptionEntryGroup());
				assertEquals(dataDescriptionEntryV0P190,
						dataDescriptionEntryV0P190Text.getParentDataDescriptionEntryGroup());
				assertEquals(DataDescriptionEntryType.GROUP,
						dataDescriptionEntryV0P190Text.getDataDescriptionEntryType());

				{
					assertEquals(18, dataDescriptionEntryV0P190Text.getCalls().size());
				}
			}
		}

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(14, procedureDivision.getStatements().size());

		{
			final MoveStatement moveStatement = (MoveStatement) procedureDivision.getStatements().get(3);
			assertNotNull(moveStatement);
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

						{
							final Call sendingAreaCall = sendingAreaCallValueStmt.getCall();

							final FunctionCall sendingAreaFunctionCall = (FunctionCall) sendingAreaCall.unwrap();
							assertEquals(CallType.FUNCTION_CALL, sendingAreaFunctionCall.getCallType());

							assertEquals("WHEN-COMPILED", sendingAreaFunctionCall.getName());
						}
					}
				}

				{
					final List<Call> receivingAreaCalls = moveToPhrase.getReceivingAreaCalls();
					assertEquals(1, receivingAreaCalls.size());

					{
						final Call receivingAreaCall = receivingAreaCalls.get(0);
						assertNotNull(receivingAreaCall);
						assertEquals(CallType.TABLE_CALL, receivingAreaCall.getCallType());
					}
				}
			}
		}
	}
}