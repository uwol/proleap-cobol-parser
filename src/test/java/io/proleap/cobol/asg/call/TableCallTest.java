package io.proleap.cobol.asg.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.call.IndexCall;
import io.proleap.cobol.asg.metamodel.call.ProcedureCall;
import io.proleap.cobol.asg.metamodel.call.TableCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry.DataDescriptionEntryType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.datadescription.Index;
import io.proleap.cobol.asg.metamodel.data.datadescription.OccursClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.OccursIndexed;
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
import io.proleap.cobol.asg.metamodel.procedure.move.MoveToSendingArea;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveToStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.ByPhrase;
import io.proleap.cobol.asg.metamodel.procedure.perform.FromPhrase;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformProcedureStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformType;
import io.proleap.cobol.asg.metamodel.procedure.perform.Until;
import io.proleap.cobol.asg.metamodel.procedure.perform.Varying;
import io.proleap.cobol.asg.metamodel.procedure.perform.VaryingClause;
import io.proleap.cobol.asg.metamodel.procedure.perform.VaryingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.RelationConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.Subscript;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.Basis;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDivs;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.Powers;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.CombinableCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.SimpleCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.ArithmeticComparison;
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

		final Index indexI;
		final Index indexJ;

		{
			final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();
			assertEquals(1, workingStorageSection.getRootDataDescriptionEntries().size());

			{
				dataDescriptionEntryTbl = workingStorageSection.getRootDataDescriptionEntries().get(0);
				assertEquals("WS-TBL", dataDescriptionEntryTbl.getName());
				assertEquals(Integer.valueOf(1), dataDescriptionEntryTbl.getLevelNumber());
				assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryTbl.getDataDescriptionEntryType());

				final DataDescriptionEntryGroup dataDescriptionEntryGroupTbl = (DataDescriptionEntryGroup) dataDescriptionEntryTbl;
				assertEquals(1, dataDescriptionEntryGroupTbl.getDataDescriptionEntries().size());

				{
					final DataDescriptionEntry dataDescriptionEntryRecord = dataDescriptionEntryGroupTbl
							.getDataDescriptionEntries().get(0);
					assertEquals("WS-RECORD", dataDescriptionEntryRecord.getName());
					assertEquals(Integer.valueOf(5), dataDescriptionEntryRecord.getLevelNumber());
					assertEquals(DataDescriptionEntryType.GROUP,
							dataDescriptionEntryRecord.getDataDescriptionEntryType());

					final DataDescriptionEntryGroup dataDescriptionEntryGroupRecord = (DataDescriptionEntryGroup) dataDescriptionEntryRecord;
					assertEquals(2, dataDescriptionEntryGroupRecord.getDataDescriptionEntries().size());

					{
						final OccursClause occursClause = dataDescriptionEntryGroupRecord.getOccursClauses().get(0);
						final OccursIndexed occursIndexed = occursClause.getOccursIndexed();
						assertEquals(1, occursIndexed.getIndices().size());

						{
							final IntegerLiteralValueStmt from = (IntegerLiteralValueStmt) occursClause.getFrom();
							assertEquals(new BigDecimal(3), from.getLiteral().getValue());
						}

						{
							indexI = occursIndexed.getIndices().get(0);
							assertEquals("I", indexI.getName());
							assertEquals(3, indexI.getCalls().size());
						}
					}

					{
						final DataDescriptionEntry dataDescriptionEntryDelimiter = dataDescriptionEntryGroupRecord
								.getDataDescriptionEntries().get(0);
						assertEquals("WS-DELIMITER", dataDescriptionEntryDelimiter.getName());
						assertEquals(Integer.valueOf(10), dataDescriptionEntryDelimiter.getLevelNumber());
						assertEquals(DataDescriptionEntryType.GROUP,
								dataDescriptionEntryDelimiter.getDataDescriptionEntryType());

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
						assertEquals(Integer.valueOf(10), dataDescriptionEntryContent.getLevelNumber());
						assertEquals(DataDescriptionEntryType.GROUP,
								dataDescriptionEntryContent.getDataDescriptionEntryType());

						final DataDescriptionEntryGroup dataDescriptionEntryGroupContent = (DataDescriptionEntryGroup) dataDescriptionEntryContent;
						assertEquals(1, dataDescriptionEntryGroupContent.getDataDescriptionEntries().size());
						assertEquals(1, dataDescriptionEntryGroupContent.getCalls().size());

						{
							final OccursClause occursClause = dataDescriptionEntryGroupContent.getOccursClauses()
									.get(0);
							final OccursIndexed occursIndexed = occursClause.getOccursIndexed();
							assertEquals(1, occursIndexed.getIndices().size());

							{
								final IntegerLiteralValueStmt from = (IntegerLiteralValueStmt) occursClause.getFrom();
								assertEquals(new BigDecimal(2), from.getLiteral().getValue());
							}

							{
								indexJ = occursIndexed.getIndices().get(0);
								assertEquals("J", indexJ.getName());
								assertEquals(3, indexJ.getCalls().size());
							}
						}

						{
							final DataDescriptionEntry dataDescriptionEntryColumn = dataDescriptionEntryGroupContent
									.getDataDescriptionEntries().get(0);
							assertEquals("WS-COLUMN", dataDescriptionEntryColumn.getName());
							assertEquals(Integer.valueOf(20), dataDescriptionEntryColumn.getLevelNumber());
							assertEquals(DataDescriptionEntryType.GROUP,
									dataDescriptionEntryColumn.getDataDescriptionEntryType());

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
			assertEquals(MoveStatement.MoveType.MOVE_TO, moveStatement.getMoveType());

			{
				final MoveToStatement moveTo = moveStatement.getMoveToStatement();

				{
					final MoveToSendingArea sendingArea = moveTo.getSendingArea();
					final LiteralValueStmt sendingAreaValueStmt = (LiteralValueStmt) sendingArea
							.getSendingAreaValueStmt();
					final Literal sendingAreaLiteral = sendingAreaValueStmt.getLiteral();
					assertEquals("12ABCABC34ABCABC56ABCABC", sendingAreaLiteral.getValue());
				}

				{
					assertEquals(1, moveTo.getReceivingAreaCalls().size());

					{
						final Call receivingCall = moveTo.getReceivingAreaCalls().get(0);
						assertEquals(Call.CallType.DATA_DESCRIPTION_ENTRY_CALL, receivingCall.getCallType());

						final DataDescriptionEntryCall dataDescriptionEntryReceivingCall = (DataDescriptionEntryCall) receivingCall
								.unwrap();
						assertEquals(dataDescriptionEntryTbl,
								dataDescriptionEntryReceivingCall.getDataDescriptionEntry());
					}
				}
			}
		}

		{
			final PerformStatement performStatement = (PerformStatement) procedureDivision.getStatements().get(1);
			assertEquals(StatementTypeEnum.PERFORM, performStatement.getStatementType());
			assertEquals(PerformStatement.PerformStatementType.PROCEDURE, performStatement.getPerformStatementType());

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
					assertEquals(PerformType.PerformTypeType.VARYING, performType.getPerformTypeType());

					{
						final Varying varying = performType.getVarying();
						final VaryingClause varyingClause = varying.getVaryingClause();
						final VaryingPhrase varyingPhrase = varyingClause.getVaryingPhrase();

						{
							final ValueStmt varyingValueStmt = varyingPhrase.getVaryingValueStmt();
							final CallValueStmt varyingCallValueStmt = (CallValueStmt) varyingValueStmt;

							{
								final Call call = varyingCallValueStmt.getCall();
								assertEquals(Call.CallType.INDEX_CALL, call.getCallType());

								final IndexCall indexCall = (IndexCall) call.unwrap();
								assertEquals(indexI, indexCall.getIndex());
							}
						}

						{
							final FromPhrase from = varyingPhrase.getFrom();
							final LiteralValueStmt fromValueStmt = (LiteralValueStmt) from.getFromValueStmt();
							final Literal fromLiteral = fromValueStmt.getLiteral();
							assertEquals(BigDecimal.ONE, fromLiteral.getValue());
						}

						{
							final ByPhrase by = varyingPhrase.getBy();
							final LiteralValueStmt byValueStmt = (LiteralValueStmt) by.getByValueStmt();
							final Literal byLiteral = byValueStmt.getLiteral();
							assertEquals(BigDecimal.ONE, byLiteral.getValue());
						}

						{
							final Until until = varyingPhrase.getUntil();
							assertNotNull(until.getCondition());

							{
								final ConditionValueStmt condition = until.getCondition();
								final CombinableCondition combinableCondition = condition.getCombinableCondition();
								final SimpleCondition simpleCondition = combinableCondition.getSimpleCondition();
								final RelationConditionValueStmt relationCondition = simpleCondition
										.getRelationCondition();
								final ArithmeticComparison arithmeticComparison = relationCondition
										.getArithmeticComparison();

								{
									final ArithmeticValueStmt arithmeticExpressionLeft = arithmeticComparison
											.getArithmeticExpressionLeft();
									final ValueStmt basisValueStmt = arithmeticExpressionLeft.getMultDivs().getPowers()
											.getBasis().getBasisValueStmt();

									final CallValueStmt basisCallValueStmt = (CallValueStmt) basisValueStmt;
									final Call call = basisCallValueStmt.getCall();
									final IndexCall indexCall = (IndexCall) call.unwrap();
									assertEquals(indexI, indexCall.getIndex());
								}

								{
									final ArithmeticValueStmt arithmeticExpressionRight = arithmeticComparison
											.getArithmeticExpressionRight();
									final MultDivs multDivs = arithmeticExpressionRight.getMultDivs();
									final Powers powers = multDivs.getPowers();
									final Basis basis = powers.getBasis();
									final LiteralValueStmt basisValueStmt = (LiteralValueStmt) basis
											.getBasisValueStmt();
									final Literal basisLiteral = basisValueStmt.getLiteral();
									assertEquals(new BigDecimal(3), basisLiteral.getValue());
								}
							}
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
			assertEquals(PerformStatement.PerformStatementType.PROCEDURE, performStatement.getPerformStatementType());

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
					assertEquals(PerformType.PerformTypeType.VARYING, performType.getPerformTypeType());

					{
						final Varying varying = performType.getVarying();
						final VaryingClause varyingClause = varying.getVaryingClause();
						final VaryingPhrase varyingPhrase = varyingClause.getVaryingPhrase();

						{
							final ValueStmt varyingValueStmt = varyingPhrase.getVaryingValueStmt();
							final CallValueStmt varyingCallValueStmt = (CallValueStmt) varyingValueStmt;

							{
								final Call call = varyingCallValueStmt.getCall();
								assertEquals(Call.CallType.INDEX_CALL, call.getCallType());

								final IndexCall indexCall = (IndexCall) call.unwrap();
								assertEquals(indexJ, indexCall.getIndex());
							}
						}

						{
							final FromPhrase from = varyingPhrase.getFrom();
							final LiteralValueStmt fromValueStmt = (LiteralValueStmt) from.getFromValueStmt();
							final Literal fromLiteral = fromValueStmt.getLiteral();
							assertEquals(BigDecimal.ONE, fromLiteral.getValue());
						}

						{
							final ByPhrase by = varyingPhrase.getBy();
							final LiteralValueStmt byValueStmt = (LiteralValueStmt) by.getByValueStmt();
							final Literal byLiteral = byValueStmt.getLiteral();
							assertEquals(BigDecimal.ONE, byLiteral.getValue());
						}

						{
							final Until until = varyingPhrase.getUntil();
							assertNotNull(until.getCondition());

							{
								final ConditionValueStmt condition = until.getCondition();
								final CombinableCondition combinableCondition = condition.getCombinableCondition();
								final SimpleCondition simpleCondition = combinableCondition.getSimpleCondition();
								final RelationConditionValueStmt relationCondition = simpleCondition
										.getRelationCondition();
								final ArithmeticComparison arithmeticComparison = relationCondition
										.getArithmeticComparison();

								{
									final ArithmeticValueStmt arithmeticExpressionLeft = arithmeticComparison
											.getArithmeticExpressionLeft();
									final ValueStmt basisValueStmt = arithmeticExpressionLeft.getMultDivs().getPowers()
											.getBasis().getBasisValueStmt();

									final CallValueStmt basisCallValueStmt = (CallValueStmt) basisValueStmt;
									final Call call = basisCallValueStmt.getCall();
									final IndexCall indexCall = (IndexCall) call.unwrap();
									assertEquals(indexJ, indexCall.getIndex());
								}

								{
									final ArithmeticValueStmt arithmeticExpressionRight = arithmeticComparison
											.getArithmeticExpressionRight();
									final MultDivs multDivs = arithmeticExpressionRight.getMultDivs();
									final Powers powers = multDivs.getPowers();
									final Basis basis = powers.getBasis();
									final LiteralValueStmt basisValueStmt = (LiteralValueStmt) basis
											.getBasisValueStmt();
									final Literal literal = basisValueStmt.getLiteral();
									assertEquals(new BigDecimal(2), literal.getValue());
								}
							}
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
								assertEquals(Call.CallType.INDEX_CALL, subscriptCall.getCallType());

								final IndexCall subscriptIndexCall = (IndexCall) subscriptCall.unwrap();
								assertEquals(indexI, subscriptIndexCall.getIndex());
							}
						}

						{
							final Subscript subscript = tableCall.getSubscripts().get(1);
							final ValueStmt subscriptValueStmt = subscript.getSubscriptValueStmt();
							final CallValueStmt subscriptCallValueStmt = (CallValueStmt) subscriptValueStmt;

							{
								final Call subscriptCall = subscriptCallValueStmt.getCall();
								assertEquals(Call.CallType.INDEX_CALL, subscriptCall.getCallType());

								final IndexCall subscriptIndexCall = (IndexCall) subscriptCall.unwrap();
								assertEquals(indexJ, subscriptIndexCall.getIndex());
							}
						}
					}
				}
			}
		}
	}
}