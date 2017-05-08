package io.proleap.cobol.asg.call;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.datadescription.OccursClause;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.display.Operand;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveTo;
import io.proleap.cobol.asg.metamodel.procedure.move.SendingArea;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class TableCallTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/call/TableCall.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("TableCall");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final DataDescriptionEntry dataDescriptionEntryTbl;

		{
			final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();
			assertEquals(1, workingStorageSection.getRootDataDescriptionEntries().size());

			{
				dataDescriptionEntryTbl = workingStorageSection.getRootDataDescriptionEntries().get(0);
				assertEquals("WS-TBL", dataDescriptionEntryTbl.getName());
				assertEquals(new Integer(1), dataDescriptionEntryTbl.getLevelNumber());
				assertEquals(DataDescriptionEntry.Type.GROUP, dataDescriptionEntryTbl.getType());

				final DataDescriptionEntryGroup dataDescriptionEntryGroupTbl = (DataDescriptionEntryGroup) dataDescriptionEntryTbl;
				assertEquals(1, dataDescriptionEntryGroupTbl.getDataDescriptionEntries().size());

				{
					final DataDescriptionEntry dataDescriptionEntryRecord = dataDescriptionEntryGroupTbl
							.getDataDescriptionEntries().get(0);
					assertEquals("WS-RECORD", dataDescriptionEntryRecord.getName());
					assertEquals(new Integer(5), dataDescriptionEntryRecord.getLevelNumber());
					assertEquals(DataDescriptionEntry.Type.GROUP, dataDescriptionEntryRecord.getType());

					final DataDescriptionEntryGroup dataDescriptionEntryGroupRecord = (DataDescriptionEntryGroup) dataDescriptionEntryRecord;
					assertEquals(2, dataDescriptionEntryGroupRecord.getDataDescriptionEntries().size());

					{
						final OccursClause occursClause = dataDescriptionEntryGroupRecord.getOccursClauses().get(0);
						assertEquals(1, occursClause.getIndexCalls().size());

						{
							final Call indexCall = occursClause.getIndexCalls().get(0);
							assertEquals("I", indexCall.getName());
						}
					}

					{
						final DataDescriptionEntry dataDescriptionEntryDelimiter = dataDescriptionEntryGroupRecord
								.getDataDescriptionEntries().get(0);
						assertEquals("WS-DELIMITER", dataDescriptionEntryDelimiter.getName());
						assertEquals(new Integer(10), dataDescriptionEntryDelimiter.getLevelNumber());
						assertEquals(DataDescriptionEntry.Type.GROUP, dataDescriptionEntryDelimiter.getType());
					}

					{
						final DataDescriptionEntry dataDescriptionEntryContent = dataDescriptionEntryGroupRecord
								.getDataDescriptionEntries().get(1);
						assertEquals("WS-CONTENT", dataDescriptionEntryContent.getName());
						assertEquals(new Integer(10), dataDescriptionEntryContent.getLevelNumber());
						assertEquals(DataDescriptionEntry.Type.GROUP, dataDescriptionEntryContent.getType());

						final DataDescriptionEntryGroup dataDescriptionEntryGroupContent = (DataDescriptionEntryGroup) dataDescriptionEntryContent;
						assertEquals(1, dataDescriptionEntryGroupContent.getDataDescriptionEntries().size());

						{
							final OccursClause occursClause = dataDescriptionEntryGroupContent.getOccursClauses()
									.get(0);
							assertEquals(1, occursClause.getIndexCalls().size());

							{
								final Call indexCall = occursClause.getIndexCalls().get(0);
								assertEquals("J", indexCall.getName());
							}
						}

						{
							final DataDescriptionEntry dataDescriptionEntryColumn = dataDescriptionEntryGroupContent
									.getDataDescriptionEntries().get(0);
							assertEquals("WS-COLUMN", dataDescriptionEntryColumn.getName());
							assertEquals(new Integer(20), dataDescriptionEntryColumn.getLevelNumber());
							assertEquals(DataDescriptionEntry.Type.GROUP, dataDescriptionEntryColumn.getType());
						}
					}
				}
			}
		}

		assertEquals(3, procedureDivision.getStatements().size());
		assertEquals(2, procedureDivision.getParagraphs().size());

		{
			final MoveStatement moveStatement = (MoveStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.MOVE, moveStatement.getStatementType());
			assertEquals(MoveStatement.Type.MOVE_TO, moveStatement.getType());

			{
				final MoveTo moveTo = moveStatement.getMoveTo();

				{
					final SendingArea sendingArea = moveTo.getSendingArea();
					assertEquals("12ABCABC34ABCABC56ABCABC", sendingArea.getSendingAreaValueStmt().getValue());
				}

				{
					assertEquals(1, moveTo.getReceivingAreaCalls().size());

					{
						final Call receivingCall = moveTo.getReceivingAreaCalls().get(0);
						assertEquals(Call.CallType.DATA_DESCRIPTION_ENTRY_CALL, receivingCall.getCallType());

						final DataDescriptionEntryCall dataDescriptionEntryReceivingCall = (DataDescriptionEntryCall) receivingCall;
						assertEquals(dataDescriptionEntryTbl,
								dataDescriptionEntryReceivingCall.getDataDescriptionEntry());
					}
				}
			}
		}

		{
			final PerformStatement performStatement = (PerformStatement) procedureDivision.getStatements().get(1);
			assertEquals(StatementTypeEnum.PERFORM, performStatement.getStatementType());
		}

		{
			final StopStatement stopStatement = (StopStatement) procedureDivision.getStatements().get(2);
			assertEquals(StatementTypeEnum.STOP, stopStatement.getStatementType());
		}

		{
			final Paragraph paragraph = procedureDivision.getParagraph("DISPLAY-RECORD");

			{
				final PerformStatement performStatement = (PerformStatement) paragraph.getStatements().get(0);
				assertEquals(StatementTypeEnum.PERFORM, performStatement.getStatementType());
			}
		}

		{
			final Paragraph paragraph = procedureDivision.getParagraph("DISPLAY-CONTENT");

			{
				final DisplayStatement displayStatement = (DisplayStatement) paragraph.getStatements().get(0);
				assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());

				{
					final List<Operand> operands = displayStatement.getOperands();
					final Operand operand = operands.get(0);
					final ValueStmt operandValueStmt = operand.getOperandValueStmt();
				}
			}
		}
	}
}