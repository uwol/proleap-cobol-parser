package io.proleap.cobol.asg.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.call.ProcedureCall;
import io.proleap.cobol.asg.metamodel.call.TableCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.datadescription.OccursClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.PictureClause;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.asg.metamodel.identification.ProgramIdParagraph;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.display.Operand;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveTo;
import io.proleap.cobol.asg.metamodel.procedure.move.SendingArea;
import io.proleap.cobol.asg.metamodel.procedure.perform.By;
import io.proleap.cobol.asg.metamodel.procedure.perform.From;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformProcedureStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformType;
import io.proleap.cobol.asg.metamodel.procedure.perform.Until;
import io.proleap.cobol.asg.metamodel.procedure.perform.Varying;
import io.proleap.cobol.asg.metamodel.procedure.perform.VaryingClause;
import io.proleap.cobol.asg.metamodel.procedure.perform.VaryingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.Subscript;
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
		final IdentificationDivision identificationDivision = programUnit.getIdentificationDivision();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final ProgramIdParagraph programIdParagraph = identificationDivision.getProgramIdParagraph();
			assertEquals("TBLCLL", programIdParagraph.getName());
		}

		final DataDescriptionEntry dataDescriptionEntryTbl;

		final Paragraph paragraphDisplayRecord = procedureDivision.getParagraph("DISPLAY-RECORD");
		final Paragraph paragraphDisplayContent = procedureDivision.getParagraph("DISPLAY-CONTENT");

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
							final IntegerLiteral from = occursClause.getFrom();
							assertEquals(new Integer(3), from.getValue());
						}

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

						final DataDescriptionEntryGroup dataDescriptionEntryGroupDelimiter = (DataDescriptionEntryGroup) dataDescriptionEntryDelimiter;

						{
							final PictureClause pictureClause = dataDescriptionEntryGroupDelimiter.getPictureClause();
							assertEquals("A(2)", pictureClause.getPictureString());
						}
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
								final IntegerLiteral from = occursClause.getFrom();
								assertEquals(new Integer(2), from.getValue());
							}

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

							final DataDescriptionEntryGroup dataDescriptionEntryGroupColumn = (DataDescriptionEntryGroup) dataDescriptionEntryColumn;

							{
								final PictureClause pictureClause = dataDescriptionEntryGroupColumn.getPictureClause();
								assertEquals("X(3)", pictureClause.getPictureString());
							}
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
			assertEquals(PerformStatement.Type.PROCEDURE, performStatement.getType());

			{
				final PerformProcedureStatement performProcedureStatement = performStatement
						.getPerformProcedureStatement();
				assertNotNull(performProcedureStatement);
				assertEquals(1, performProcedureStatement.getCalls().size());

				{
					final Call call = performProcedureStatement.getCalls().get(0);
					assertEquals(CallType.PROCEDURE_CALL, call.getCallType());
					final ProcedureCall procedureCall = (ProcedureCall) call;
					assertEquals(paragraphDisplayRecord, procedureCall.getParagraph());
				}

				{
					final PerformType performType = performProcedureStatement.getPerformType();
					assertEquals(PerformType.Type.VARYING, performType.getType());

					{
						final Varying varying = performType.getVarying();
						final VaryingClause varyingClause = varying.getVaryingClause();
						final VaryingPhrase varyingPhrase = varyingClause.getVaryingPhrase();

						{
							final From from = varyingPhrase.getFrom();
							assertEquals(1, from.getFromValueStmt().getValue());
						}

						{
							final By by = varyingPhrase.getBy();
							assertEquals(1, by.getByValueStmt().getValue());
						}

						{
							final Until until = varyingPhrase.getUntil();
							assertNotNull(until.getCondition());
						}
					}
				}
			}
		}

		{
			final StopStatement stopStatement = (StopStatement) procedureDivision.getStatements().get(2);
			assertEquals(StatementTypeEnum.STOP, stopStatement.getStatementType());
		}

		{
			final PerformStatement performStatement = (PerformStatement) paragraphDisplayRecord.getStatements().get(0);
			assertEquals(StatementTypeEnum.PERFORM, performStatement.getStatementType());
			assertEquals(PerformStatement.Type.PROCEDURE, performStatement.getType());

			{
				final PerformProcedureStatement performProcedureStatement = performStatement
						.getPerformProcedureStatement();
				assertNotNull(performProcedureStatement);
				assertEquals(1, performProcedureStatement.getCalls().size());

				{
					final Call call = performProcedureStatement.getCalls().get(0);
					assertEquals(CallType.PROCEDURE_CALL, call.getCallType());
					final ProcedureCall procedureCall = (ProcedureCall) call;
					assertEquals(paragraphDisplayContent, procedureCall.getParagraph());
				}

				{
					final PerformType performType = performProcedureStatement.getPerformType();
					assertEquals(PerformType.Type.VARYING, performType.getType());

					{
						final Varying varying = performType.getVarying();
						final VaryingClause varyingClause = varying.getVaryingClause();
						final VaryingPhrase varyingPhrase = varyingClause.getVaryingPhrase();

						{
							final From from = varyingPhrase.getFrom();
							assertEquals(1, from.getFromValueStmt().getValue());
						}

						{
							final By by = varyingPhrase.getBy();
							assertEquals(1, by.getByValueStmt().getValue());
						}

						{
							final Until until = varyingPhrase.getUntil();
							assertNotNull(until.getCondition());
						}
					}
				}
			}
		}

		{
			final DisplayStatement displayStatement = (DisplayStatement) paragraphDisplayContent.getStatements().get(0);
			assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());

			{
				assertEquals(1, displayStatement.getOperands().size());

				{
					final Operand operand = displayStatement.getOperands().get(0);
					final ValueStmt operandValueStmt = operand.getOperandValueStmt();
					final CallValueStmt operandCallValueStmt = (CallValueStmt) operandValueStmt;

					{
						final Call call = operandCallValueStmt.getCall();
						assertEquals(Call.CallType.TABLE_CALL, call.getCallType());

						final TableCall tableCall = (TableCall) call.unwrap();
						assertEquals(2, tableCall.getSubscripts().size());

						{
							final Subscript subscript = tableCall.getSubscripts().get(0);
							final ValueStmt subscriptValueStmt = subscript.getSubscriptValueStmt();
							final CallValueStmt subscriptCallValueStmt = (CallValueStmt) subscriptValueStmt;

							{
								final Call subscriptCall = subscriptCallValueStmt.getCall();
								assertEquals(Call.CallType.UNDEFINED_CALL, subscriptCall.getCallType());
							}
						}

						{
							final Subscript subscript = tableCall.getSubscripts().get(1);
							final ValueStmt subscriptValueStmt = subscript.getSubscriptValueStmt();
							final CallValueStmt subscriptCallValueStmt = (CallValueStmt) subscriptValueStmt;

							{
								final Call subscriptCall = subscriptCallValueStmt.getCall();
								assertEquals(Call.CallType.UNDEFINED_CALL, subscriptCall.getCallType());
							}
						}
					}
				}
			}
		}
	}
}