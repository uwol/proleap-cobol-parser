package io.proleap.cobol.asg.procedure.move;

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
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry.DataDescriptionEntryType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryContainer.DataDescriptionEntryContainerType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
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

public class MoveToStatementQualifiedDataNameTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/move/MoveToStatementQualifiedDataName.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("MoveToStatementQualifiedDataName");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(3, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(1, workingStorageSection.getRootDataDescriptionEntries().size());

		assertEquals(DataDescriptionEntryContainerType.WORKING_STORAGE_SECTION,
				workingStorageSection.getContainerType());

		final DataDescriptionEntry dataDescriptionEntryItems1 = workingStorageSection.getDataDescriptionEntry("ITEMS1");
		final DataDescriptionEntry dataDescriptionEntryItem1 = workingStorageSection.getDataDescriptionEntry("ITEM1");
		final DataDescriptionEntry dataDescriptionEntryItem2 = workingStorageSection.getDataDescriptionEntry("ITEM2");

		{
			assertNotNull(dataDescriptionEntryItems1);
			assertEquals("ITEMS1", dataDescriptionEntryItems1.getName());
			assertEquals(Integer.valueOf(1), dataDescriptionEntryItems1.getLevelNumber());
			assertNull(dataDescriptionEntryItems1.getParentDataDescriptionEntryGroup());
			assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItems1.getDataDescriptionEntryType());

			final DataDescriptionEntryGroup dataDescriptionEntryGroupItems = (DataDescriptionEntryGroup) dataDescriptionEntryItems1;
			assertNull(dataDescriptionEntryGroupItems.getDataDescriptionEntryPredecessor());

			{
				assertNotNull(dataDescriptionEntryItem1);
				assertEquals("ITEM1", dataDescriptionEntryItem1.getName());
				assertEquals(Integer.valueOf(2), dataDescriptionEntryItem1.getLevelNumber());
				assertEquals(dataDescriptionEntryItems1,
						dataDescriptionEntryItem1.getParentDataDescriptionEntryGroup());
				assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItem1.getDataDescriptionEntryType());

				final DataDescriptionEntryGroup dataDescriptionEntryGroupItem1 = (DataDescriptionEntryGroup) dataDescriptionEntryItem1;
				assertNull(dataDescriptionEntryGroupItem1.getDataDescriptionEntryPredecessor());
				assertNotNull(dataDescriptionEntryGroupItem1.getDataDescriptionEntrySuccessor());
				assertEquals(dataDescriptionEntryItem2, dataDescriptionEntryItem1.getDataDescriptionEntrySuccessor());
			}

			{
				assertNotNull(dataDescriptionEntryItem2);
				assertEquals("ITEM2", dataDescriptionEntryItem2.getName());
				assertEquals(Integer.valueOf(2), dataDescriptionEntryItem2.getLevelNumber());
				assertEquals(dataDescriptionEntryItems1,
						dataDescriptionEntryItem2.getParentDataDescriptionEntryGroup());
				assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItem2.getDataDescriptionEntryType());

				final DataDescriptionEntryGroup dataDescriptionEntryGroupItem2 = (DataDescriptionEntryGroup) dataDescriptionEntryItem2;
				assertNotNull(dataDescriptionEntryGroupItem2.getDataDescriptionEntryPredecessor());
				assertEquals(dataDescriptionEntryItem1, dataDescriptionEntryItem2.getDataDescriptionEntryPredecessor());
				assertNull(dataDescriptionEntryGroupItem2.getDataDescriptionEntrySuccessor());
			}
		}

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(2, procedureDivision.getStatements().size());

		{
			final MoveStatement moveStatement = (MoveStatement) procedureDivision.getStatements().get(0);
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
						final Call sendingAreaCall = sendingAreaCallValueStmt.getCall().unwrap();
						assertNotNull(sendingAreaCall);
						assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, sendingAreaCall.getCallType());

						{
							final DataDescriptionEntryCall sendingAreaDataDescriptionEntryCall = (DataDescriptionEntryCall) sendingAreaCall;
							assertEquals(dataDescriptionEntryItem1,
									sendingAreaDataDescriptionEntryCall.getDataDescriptionEntry());
						}
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

		{
			final MoveStatement moveStatement = (MoveStatement) procedureDivision.getStatements().get(1);
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
						final Call sendingAreaCall = sendingAreaCallValueStmt.getCall().unwrap();
						assertNotNull(sendingAreaCall);
						assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, sendingAreaCall.getCallType());

						{
							final DataDescriptionEntryCall sendingAreaDataDescriptionEntryCall = (DataDescriptionEntryCall) sendingAreaCall;
							assertEquals(dataDescriptionEntryItem2,
									sendingAreaDataDescriptionEntryCall.getDataDescriptionEntry());
						}
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
