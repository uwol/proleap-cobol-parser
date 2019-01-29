package io.proleap.cobol.asg.procedure.write;

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
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.file.FileSection;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.InputOutputSection;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlParagraph;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.write.AdvancingLines;
import io.proleap.cobol.asg.metamodel.procedure.write.AdvancingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.write.AtEndOfPagePhrase;
import io.proleap.cobol.asg.metamodel.procedure.write.From;
import io.proleap.cobol.asg.metamodel.procedure.write.NotAtEndOfPagePhrase;
import io.proleap.cobol.asg.metamodel.procedure.write.WriteStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class WriteStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/write/WriteStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("WriteStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();

		final FileControlEntry fileControlEntry;
		final DataDescriptionEntry dataDescriptionEntry;

		{
			final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();

			{
				final InputOutputSection inputOutputSection = environmentDivision.getInputOutputSection();

				{
					final FileControlParagraph fileControlParagraph = inputOutputSection.getFileControlParagraph();

					{
						fileControlEntry = fileControlParagraph.getFileControlEntry("SOMEFILE1");
						assertNotNull(fileControlEntry);
						assertEquals(2, fileControlEntry.getCalls().size());
					}
				}
			}
		}

		{
			final DataDivision dataDivision = programUnit.getDataDivision();
			assertNotNull(dataDivision);

			{
				final FileSection fileSection = dataDivision.getFileSection();
				assertNotNull(fileSection);
				assertEquals(1, fileSection.getFileDescriptionEntries().size());

				{
					final FileDescriptionEntry fileDescriptionEntry = fileSection.getFileDescriptionEntry("SOMEFILE1");
					assertNotNull(fileDescriptionEntry);
					assertEquals(fileControlEntry, fileDescriptionEntry.getFileControlEntry());

					{
						dataDescriptionEntry = fileDescriptionEntry.getDataDescriptionEntry("ITEMS");
						assertNotNull(dataDescriptionEntry);
					}
				}
			}
		}

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
				assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, recordCall.getCallType());

				final DataDescriptionEntryCall dataDescriptionEntryCall = (DataDescriptionEntryCall) recordCall;
				assertEquals(dataDescriptionEntry, dataDescriptionEntryCall.getDataDescriptionEntry());
			}

			{
				final From from = writeStatement.getFrom();
				assertNotNull(from);

				final CallValueStmt fromCallValueStmt = (CallValueStmt) from.getFromValueStmt();
				assertEquals(CallType.UNDEFINED_CALL, fromCallValueStmt.getCall().getCallType());
			}

			{
				final AtEndOfPagePhrase atEnd = writeStatement.getAtEndOfPagePhrase();
				assertNotNull(atEnd);
				assertEquals(1, atEnd.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) atEnd.getStatements().get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
				}
			}

			{
				final NotAtEndOfPagePhrase notAtEndPhrase = writeStatement.getNotAtEndOfPagePhrase();
				assertNotNull(notAtEndPhrase);
				assertEquals(1, notAtEndPhrase.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) notAtEndPhrase.getStatements().get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
				}
			}

			{
				final InvalidKeyPhrase invalidKey = writeStatement.getInvalidKeyPhrase();
				assertNotNull(invalidKey);
				assertEquals(1, invalidKey.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) invalidKey.getStatements().get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
				}
			}

			{
				final NotInvalidKeyPhrase notInvalidKeyPhrase = writeStatement.getNotInvalidKeyPhrase();
				assertNotNull(notInvalidKeyPhrase);
				assertEquals(1, notInvalidKeyPhrase.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) notInvalidKeyPhrase.getStatements()
							.get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
				}
			}

			{
				final AdvancingPhrase advancingPhrase = writeStatement.getAdvancingPhrase();
				assertNotNull(advancingPhrase);
				assertEquals(AdvancingPhrase.PositionType.BEFORE, advancingPhrase.getPositionType());
				assertEquals(AdvancingPhrase.AdvancingType.LINES, advancingPhrase.getAdvancingType());

				{
					final AdvancingLines advancingLines = advancingPhrase.getAdvancingLines();
					assertNotNull(advancingLines);
					assertNotNull(advancingLines.getLinesValueStmt());

					final LiteralValueStmt linesValueStmt = (LiteralValueStmt) advancingLines.getLinesValueStmt();
					final Literal literal = linesValueStmt.getLiteral();
					assertEquals(new BigDecimal(3), literal.getValue());
				}
			}
		}
	}
}
