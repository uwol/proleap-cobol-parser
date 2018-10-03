package io.proleap.cobol.asg.procedure.close;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.call.FileControlEntryCall;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.InputOutputSection;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlParagraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.close.AssociatedDataLengthPhrase;
import io.proleap.cobol.asg.metamodel.procedure.close.AssociatedDataPhrase;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseDispositionPhrase;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseFile;
import io.proleap.cobol.asg.metamodel.procedure.close.ClosePortFileIoStatement;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseReelUnitStatement;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseRelativeStatement;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseStatement;
import io.proleap.cobol.asg.metamodel.procedure.close.Using;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CloseStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/close/CloseStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("CloseStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();

		final FileControlEntry fileControlEntry1a;
		final FileControlEntry fileControlEntry1b;
		final FileControlEntry fileControlEntry2;
		final FileControlEntry fileControlEntry3;
		final FileControlEntry fileControlEntry4;
		final FileControlEntry fileControlEntry5;

		{
			final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();

			{
				final InputOutputSection inputOutputSection = environmentDivision.getInputOutputSection();

				{
					final FileControlParagraph fileControlParagraph = inputOutputSection.getFileControlParagraph();

					{
						fileControlEntry1a = fileControlParagraph.getFileControlEntry("SOMEFILE1A");
						assertNotNull(fileControlEntry1a);
						assertEquals(3, fileControlEntry1a.getCalls().size());
					}

					{
						fileControlEntry1b = fileControlParagraph.getFileControlEntry("SOMEFILE1B");
						assertNotNull(fileControlEntry1b);
						assertEquals(3, fileControlEntry1b.getCalls().size());
					}

					{
						fileControlEntry2 = fileControlParagraph.getFileControlEntry("SOMEFILE2");
						assertNotNull(fileControlEntry2);
						assertEquals(3, fileControlEntry2.getCalls().size());
					}

					{
						fileControlEntry3 = fileControlParagraph.getFileControlEntry("SOMEFILE3");
						assertNotNull(fileControlEntry3);
						assertEquals(3, fileControlEntry3.getCalls().size());
					}

					{
						fileControlEntry4 = fileControlParagraph.getFileControlEntry("SOMEFILE4");
						assertNotNull(fileControlEntry4);
						assertEquals(3, fileControlEntry4.getCalls().size());
					}

					{
						fileControlEntry5 = fileControlParagraph.getFileControlEntry("SOMEFILE5");
						assertNotNull(fileControlEntry5);
						assertEquals(3, fileControlEntry5.getCalls().size());
					}
				}
			}
		}

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(5, procedureDivision.getStatements().size());

		{
			final CloseStatement closeStatement = (CloseStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.CLOSE, closeStatement.getStatementType());

			{
				final CloseFile closeFile = closeStatement.getCloseFiles().get(0);

				{
					final Call call = closeFile.getFileCall();
					assertNotNull(call);
					assertEquals(CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

					{
						final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
						assertEquals(fileControlEntry1a, fileControlEntryCall.getFileControlEntry());
					}
				}

				assertEquals(CloseFile.CloseFileType.REEL_UNIT, closeFile.getCloseFileType());

				{
					final CloseReelUnitStatement closeReelUnitStatement = closeFile.getCloseReelUnitStatement();
					assertEquals(CloseReelUnitStatement.CloseReelUnitType.UNIT,
							closeReelUnitStatement.getCloseReelUnitType());
					assertTrue(closeReelUnitStatement.isForRemovel());
					assertEquals(CloseReelUnitStatement.WithType.LOCK, closeReelUnitStatement.getWithType());
				}
			}

			{
				final CloseFile closeFile = closeStatement.getCloseFiles().get(1);

				{
					final Call call = closeFile.getFileCall();
					assertNotNull(call);
					assertEquals(CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

					{
						final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
						assertEquals(fileControlEntry1b, fileControlEntryCall.getFileControlEntry());
					}
				}

				assertEquals(CloseFile.CloseFileType.REEL_UNIT, closeFile.getCloseFileType());

				{
					final CloseReelUnitStatement closeReelUnitStatement = closeFile.getCloseReelUnitStatement();
					assertEquals(CloseReelUnitStatement.CloseReelUnitType.REEL,
							closeReelUnitStatement.getCloseReelUnitType());
					assertFalse(closeReelUnitStatement.isForRemovel());
				}
			}
		}

		{
			final CloseStatement closeStatement = (CloseStatement) procedureDivision.getStatements().get(1);
			assertEquals(StatementTypeEnum.CLOSE, closeStatement.getStatementType());

			{
				final CloseFile closeFile = closeStatement.getCloseFiles().get(0);

				{
					final Call call = closeFile.getFileCall();
					assertNotNull(call);
					assertEquals(CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

					{
						final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
						assertEquals(fileControlEntry2, fileControlEntryCall.getFileControlEntry());
					}
				}

				assertEquals(CloseFile.CloseFileType.RELATIVE, closeFile.getCloseFileType());

				{
					final CloseRelativeStatement closeRelativeStatement = closeFile.getCloseRelativeStatement();
					assertEquals(CloseRelativeStatement.WithType.LOCK, closeRelativeStatement.getWithType());
				}
			}
		}

		{
			final CloseStatement closeStatement = (CloseStatement) procedureDivision.getStatements().get(2);
			assertEquals(StatementTypeEnum.CLOSE, closeStatement.getStatementType());

			{
				final CloseFile closeFile = closeStatement.getCloseFiles().get(0);

				{
					final Call call = closeFile.getFileCall();
					assertNotNull(call);
					assertEquals(CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

					{
						final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
						assertEquals(fileControlEntry3, fileControlEntryCall.getFileControlEntry());
					}
				}

				assertEquals(CloseFile.CloseFileType.PORT_FILE_IO, closeFile.getCloseFileType());

				{
					final ClosePortFileIoStatement closePortFileIOStatement = closeFile.getClosePortFileIOStatement();
					assertEquals(ClosePortFileIoStatement.WithType.WAIT, closePortFileIOStatement.getWithType());

					{
						final List<Using> usings = closePortFileIOStatement.getUsings();
						final Using using = usings.get(0);
						assertEquals(Using.UsingType.CLOSE_DISPOSITION, using.getUsingType());

						{
							final CloseDispositionPhrase closeDispositionPhrase = using.getCloseDispositionPhrase();
							assertEquals(CloseDispositionPhrase.UsingCloseDispositionType.ORDERLY,
									closeDispositionPhrase.getUsingCloseDispositionType());
						}
					}
				}
			}
		}

		{
			final CloseStatement closeStatement = (CloseStatement) procedureDivision.getStatements().get(3);
			assertEquals(StatementTypeEnum.CLOSE, closeStatement.getStatementType());

			{
				final CloseFile closeFile = closeStatement.getCloseFiles().get(0);

				{
					final Call call = closeFile.getFileCall();
					assertNotNull(call);
					assertEquals(CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

					{
						final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
						assertEquals(fileControlEntry4, fileControlEntryCall.getFileControlEntry());
					}
				}

				assertEquals(CloseFile.CloseFileType.PORT_FILE_IO, closeFile.getCloseFileType());

				{
					final ClosePortFileIoStatement closePortFileIOStatement = closeFile.getClosePortFileIOStatement();
					assertEquals(ClosePortFileIoStatement.WithType.NO_WAIT, closePortFileIOStatement.getWithType());

					{
						final List<Using> usings = closePortFileIOStatement.getUsings();
						final Using using = usings.get(0);
						assertEquals(Using.UsingType.ASSOCIATED_DATA, using.getUsingType());

						{
							final AssociatedDataPhrase associatedDataPhrase = using.getAssociatedDataPhrase();
							assertNotNull(associatedDataPhrase.getDataValueStmt());

							final IntegerLiteralValueStmt dataLiteralValueStmt = (IntegerLiteralValueStmt) associatedDataPhrase
									.getDataValueStmt();
							final IntegerLiteral literal = dataLiteralValueStmt.getLiteral();
							assertEquals(new BigDecimal(4), literal.getValue());
						}
					}
				}
			}
		}

		{
			final CloseStatement closeStatement = (CloseStatement) procedureDivision.getStatements().get(4);
			assertEquals(StatementTypeEnum.CLOSE, closeStatement.getStatementType());

			{
				final CloseFile closeFile = closeStatement.getCloseFiles().get(0);

				{
					final Call call = closeFile.getFileCall();
					assertNotNull(call);
					assertEquals(CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

					{
						final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
						assertEquals(fileControlEntry5, fileControlEntryCall.getFileControlEntry());
					}
				}

				assertEquals(CloseFile.CloseFileType.PORT_FILE_IO, closeFile.getCloseFileType());

				{
					final ClosePortFileIoStatement closePortFileIOStatement = closeFile.getClosePortFileIOStatement();
					assertEquals(ClosePortFileIoStatement.WithType.NO_WAIT, closePortFileIOStatement.getWithType());

					{
						final List<Using> usings = closePortFileIOStatement.getUsings();
						final Using using = usings.get(0);
						assertEquals(Using.UsingType.ASSOCIATED_DATA_LENGTH, using.getUsingType());

						{
							final AssociatedDataLengthPhrase associatedDataLengthPhrase = using
									.getAssociatedDataLengthPhrase();
							assertNotNull(associatedDataLengthPhrase.getDataLengthValueStmt());

							final CallValueStmt dataLengthCallValueStmt = (CallValueStmt) associatedDataLengthPhrase
									.getDataLengthValueStmt();
							assertEquals(CallType.UNDEFINED_CALL, dataLengthCallValueStmt.getCall().getCallType());
						}
					}
				}
			}
		}
	}
}