package io.proleap.cobol.asg.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.call.FileControlEntryCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.file.FileSection;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.InputOutputSection;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlParagraph;
import io.proleap.cobol.asg.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.asg.metamodel.identification.ProgramIdParagraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseFile;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseStatement;
import io.proleap.cobol.asg.metamodel.procedure.open.Input;
import io.proleap.cobol.asg.metamodel.procedure.open.InputPhrase;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenStatement;
import io.proleap.cobol.asg.metamodel.procedure.open.Output;
import io.proleap.cobol.asg.metamodel.procedure.open.OutputPhrase;
import io.proleap.cobol.asg.metamodel.procedure.read.ReadStatement;
import io.proleap.cobol.asg.metamodel.procedure.write.From;
import io.proleap.cobol.asg.metamodel.procedure.write.WriteStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class RecordCallTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/call/RecordCall.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("RecordCall");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final IdentificationDivision identificationDivision = programUnit.getIdentificationDivision();
		final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final FileControlEntry fileControlEntryFileIn;
		final FileControlEntry fileControlEntryFileOut;

		final FileDescriptionEntry fileDescriptionEntryFileIn;
		final FileDescriptionEntry fileDescriptionEntryFileOut;

		final DataDescriptionEntry dataDescriptionEntryFileInZ;
		final DataDescriptionEntry dataDescriptionEntryFileOutZ;

		{
			final ProgramIdParagraph programIdParagraph = identificationDivision.getProgramIdParagraph();
			assertEquals("RECCLL", programIdParagraph.getName());
		}

		{
			final InputOutputSection inputOutputSection = environmentDivision.getInputOutputSection();

			{
				final FileControlParagraph fileControlParagraph = inputOutputSection.getFileControlParagraph();

				{
					fileControlEntryFileIn = fileControlParagraph.getFileControlEntry("FILEIN");
					assertNotNull(fileControlEntryFileIn.getAssignClause());
					assertNotNull(fileControlEntryFileIn.getFileDescriptionEntry());
				}

				{
					fileControlEntryFileOut = fileControlParagraph.getFileControlEntry("FILEOUT");
					assertNotNull(fileControlEntryFileOut.getAssignClause());
					assertNotNull(fileControlEntryFileIn.getFileDescriptionEntry());
				}
			}
		}

		{
			final FileSection fileSection = dataDivision.getFileSection();

			{
				fileDescriptionEntryFileIn = fileSection.getFileDescriptionEntry("FILEIN");
				assertEquals(fileControlEntryFileIn, fileDescriptionEntryFileIn.getFileControlEntry());

				{
					dataDescriptionEntryFileInZ = fileDescriptionEntryFileIn.getDataDescriptionEntry("FILEIN-Z");
					assertEquals(Integer.valueOf(1), dataDescriptionEntryFileInZ.getLevelNumber());
				}
			}

			{
				fileDescriptionEntryFileOut = fileSection.getFileDescriptionEntry("FILEOUT");
				assertEquals(fileControlEntryFileOut, fileDescriptionEntryFileOut.getFileControlEntry());

				{
					dataDescriptionEntryFileOutZ = fileDescriptionEntryFileOut.getDataDescriptionEntry("FILEOUT-Z");
					assertEquals(Integer.valueOf(1), dataDescriptionEntryFileOutZ.getLevelNumber());
				}
			}
		}

		assertEquals(4, procedureDivision.getStatements().size());

		{
			final OpenStatement openStatement = (OpenStatement) procedureDivision.getStatements().get(0);
			assertEquals(1, openStatement.getInputPhrases().size());
			assertEquals(1, openStatement.getOutputPhrases().size());

			{
				final InputPhrase openInput = openStatement.getInputPhrases().get(0);
				assertEquals(1, openInput.getInputs().size());

				{
					final Input input = openInput.getInputs().get(0);
					final Call call = input.getFileCall();
					assertNotNull(call);
					assertEquals(Call.CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

					final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
					assertEquals(fileControlEntryFileIn, fileControlEntryCall.getFileControlEntry());
				}
			}

			{
				final OutputPhrase openOutput = openStatement.getOutputPhrases().get(0);
				assertEquals(1, openOutput.getOutputs().size());

				{
					final Output output = openOutput.getOutputs().get(0);
					final Call call = output.getFileCall();
					assertNotNull(call);
					assertEquals(Call.CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

					final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
					assertEquals(fileControlEntryFileOut, fileControlEntryCall.getFileControlEntry());
				}
			}
		}

		{
			final ReadStatement readStatement = (ReadStatement) procedureDivision.getStatements().get(1);

			{
				final Call call = readStatement.getFileCall();
				assertNotNull(call);
				assertEquals(Call.CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

				final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
				assertEquals(fileControlEntryFileIn, fileControlEntryCall.getFileControlEntry());
			}
		}

		{
			final WriteStatement writeStatement = (WriteStatement) procedureDivision.getStatements().get(2);

			{
				final Call call = writeStatement.getRecordCall();
				assertNotNull(call);
				assertEquals(Call.CallType.DATA_DESCRIPTION_ENTRY_CALL, call.getCallType());

				{
					final DataDescriptionEntryCall dataDescriptionEntryCall = (DataDescriptionEntryCall) call;
					assertEquals(dataDescriptionEntryFileOutZ, dataDescriptionEntryCall.getDataDescriptionEntry());
				}
			}

			{
				final From from = writeStatement.getFrom();
				assertNotNull(from);

				{
					final ValueStmt fromValueStmt = from.getFromValueStmt();
					final CallValueStmt fromCallValueStmt = (CallValueStmt) fromValueStmt;

					final Call call = fromCallValueStmt.getCall();
					assertEquals(Call.CallType.DATA_DESCRIPTION_ENTRY_CALL, call.getCallType());

					final DataDescriptionEntryCall dataDescriptionEntryCall = (DataDescriptionEntryCall) call.unwrap();
					assertEquals(dataDescriptionEntryFileInZ, dataDescriptionEntryCall.getDataDescriptionEntry());
				}
			}
		}

		{
			final CloseStatement closeStatement = (CloseStatement) procedureDivision.getStatements().get(3);
			assertEquals(2, closeStatement.getCloseFiles().size());

			{
				final CloseFile closeFile = closeStatement.getCloseFiles().get(0);

				{
					final Call call = closeFile.getFileCall();
					assertNotNull(call);
					assertEquals(Call.CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

					final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
					assertEquals(fileControlEntryFileIn, fileControlEntryCall.getFileControlEntry());
				}
			}

			{
				final CloseFile closeFile = closeStatement.getCloseFiles().get(1);

				{
					final Call call = closeFile.getFileCall();
					assertNotNull(call);
					assertEquals(Call.CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

					final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
					assertEquals(fileControlEntryFileOut, fileControlEntryCall.getFileControlEntry());
				}
			}
		}
	}
}