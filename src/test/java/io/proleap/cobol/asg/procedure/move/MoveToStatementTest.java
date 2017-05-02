package io.proleap.cobol.asg.procedure.move;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveTo;
import io.proleap.cobol.asg.metamodel.procedure.move.SendingArea;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class MoveToStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/move/MoveToStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("MoveToStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		{
			final DataDescriptionEntry dataDescriptionEntry = workingStorageSection
					.findDataDescriptionEntry("SOME-TEXT");

			assertNotNull(dataDescriptionEntry);
			assertFalse(dataDescriptionEntry.getCalls().isEmpty());
			assertEquals(2, dataDescriptionEntry.getCalls().size());
		}

		{
			final DataDescriptionEntry dataDescriptionEntry = workingStorageSection
					.findDataDescriptionEntry("SOME-NUMBER");

			assertNotNull(dataDescriptionEntry);
			assertFalse(dataDescriptionEntry.getCalls().isEmpty());
			assertEquals(1, dataDescriptionEntry.getCalls().size());
		}

		{
			final DataDescriptionEntry dataDescriptionEntry = workingStorageSection
					.findDataDescriptionEntry("SOME-TEXT2");

			assertNotNull(dataDescriptionEntry);
			assertFalse(dataDescriptionEntry.getCalls().isEmpty());
			assertEquals(1, dataDescriptionEntry.getCalls().size());
		}

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(3, procedureDivision.getStatements().size());

		{
			final MoveStatement moveStatement = (MoveStatement) procedureDivision.getStatements().get(0);
			assertNotNull(moveStatement);
			assertEquals(StatementTypeEnum.MOVE, moveStatement.getStatementType());
			assertEquals(MoveStatement.Type.MOVE_TO, moveStatement.getType());

			{
				final MoveTo moveTo = moveStatement.getMoveTo();

				{
					final SendingArea sendingArea = moveTo.getSendingArea();
					assertNotNull(sendingArea);

					{
						final ValueStmt sendingAreaValueStmt = sendingArea.getSendingAreaValueStmt();
						assertNotNull(sendingAreaValueStmt);
						assertEquals("\"Test\"", sendingAreaValueStmt.getValue());
					}
				}

				{
					final List<Call> receivingAreaCalls = moveTo.getReceivingAreaCalls();
					assertEquals(1, receivingAreaCalls.size());

					{
						final Call receivingAreaCall = receivingAreaCalls.get(0);
						assertNotNull(receivingAreaCall);
						assertEquals(Call.CallType.DATA_DESCRIPTION_ENTRY_CALL, receivingAreaCall.getCallType());
					}
				}
			}
		}

		{
			final MoveStatement moveStatement = (MoveStatement) procedureDivision.getStatements().get(1);
			assertNotNull(moveStatement);
			assertEquals(StatementTypeEnum.MOVE, moveStatement.getStatementType());
			assertEquals(MoveStatement.Type.MOVE_TO, moveStatement.getType());

			{
				final MoveTo moveTo = moveStatement.getMoveTo();

				{
					final SendingArea sendingArea = moveTo.getSendingArea();
					assertNotNull(sendingArea);

					{
						final ValueStmt sendingAreaValueStmt = sendingArea.getSendingAreaValueStmt();
						assertNotNull(sendingAreaValueStmt);
						assertEquals(1, sendingAreaValueStmt.getValue());
					}
				}

				{
					final List<Call> receivingAreaCalls = moveTo.getReceivingAreaCalls();
					assertEquals(1, receivingAreaCalls.size());

					{
						final Call receivingAreaCall = receivingAreaCalls.get(0);
						assertNotNull(receivingAreaCall);
						assertEquals(Call.CallType.DATA_DESCRIPTION_ENTRY_CALL, receivingAreaCall.getCallType());
					}
				}
			}
		}

		{
			final MoveStatement moveStatement = (MoveStatement) procedureDivision.getStatements().get(2);
			assertNotNull(moveStatement);
			assertEquals(StatementTypeEnum.MOVE, moveStatement.getStatementType());
			assertEquals(MoveStatement.Type.MOVE_TO, moveStatement.getType());

			{
				final MoveTo moveTo = moveStatement.getMoveTo();

				{
					final SendingArea sendingArea = moveTo.getSendingArea();
					assertNotNull(sendingArea);

					{
						final ValueStmt sendingAreaValueStmt = sendingArea.getSendingAreaValueStmt();
						assertNotNull(sendingAreaValueStmt);

						final CallValueStmt sendingAreaCallValueStmt = (CallValueStmt) sendingAreaValueStmt;
						assertEquals(Call.CallType.DATA_DESCRIPTION_ENTRY_CALL,
								sendingAreaCallValueStmt.getCall().getCallType());
					}
				}

				{
					final List<Call> receivingAreaCalls = moveTo.getReceivingAreaCalls();
					assertEquals(1, receivingAreaCalls.size());

					{
						final Call receivingAreaCall = receivingAreaCalls.get(0);
						assertNotNull(receivingAreaCall);
						assertEquals(Call.CallType.DATA_DESCRIPTION_ENTRY_CALL, receivingAreaCall.getCallType());
					}
				}
			}
		}
	}
}
