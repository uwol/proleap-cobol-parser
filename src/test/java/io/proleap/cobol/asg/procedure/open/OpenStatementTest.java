package io.proleap.cobol.asg.procedure.open;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.call.FileControlEntryCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.file.FileSection;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.InputOutputSection;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlParagraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.open.ExtendPhrase;
import io.proleap.cobol.asg.metamodel.procedure.open.Input;
import io.proleap.cobol.asg.metamodel.procedure.open.InputOutputPhrase;
import io.proleap.cobol.asg.metamodel.procedure.open.InputPhrase;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenStatement;
import io.proleap.cobol.asg.metamodel.procedure.open.Output;
import io.proleap.cobol.asg.metamodel.procedure.open.OutputPhrase;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class OpenStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/open/OpenStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("OpenStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();

		final FileControlEntry fileControlEntry1;
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
						fileControlEntry1 = fileControlParagraph.getFileControlEntry("SOMEFILE1");
						assertNotNull(fileControlEntry1);
						assertEquals(3, fileControlEntry1.getCalls().size());
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
						assertEquals(4, fileControlEntry4.getCalls().size());
					}

					{
						fileControlEntry5 = fileControlParagraph.getFileControlEntry("SOMEFILE5");
						assertNotNull(fileControlEntry5);
						assertEquals(4, fileControlEntry5.getCalls().size());
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
				assertEquals(5, fileSection.getFileDescriptionEntries().size());

				{
					final FileDescriptionEntry fileDescriptionEntry = fileSection.getFileDescriptionEntry("SOMEFILE1");
					assertNotNull(fileDescriptionEntry);

					{
						final FileControlEntry fileControlEntry = fileDescriptionEntry.getFileControlEntry();
						assertNotNull(fileControlEntry);
						assertEquals(fileDescriptionEntry, fileControlEntry.getFileDescriptionEntry());
					}
				}

				{
					final FileDescriptionEntry fileDescriptionEntry = fileSection.getFileDescriptionEntry("SOMEFILE2");
					assertNotNull(fileDescriptionEntry);

					{
						final FileControlEntry fileControlEntry = fileDescriptionEntry.getFileControlEntry();
						assertNotNull(fileControlEntry);
						assertEquals(fileDescriptionEntry, fileControlEntry.getFileDescriptionEntry());
					}
				}

				{
					final FileDescriptionEntry fileDescriptionEntry = fileSection.getFileDescriptionEntry("SOMEFILE3");
					assertNotNull(fileDescriptionEntry);

					{
						final FileControlEntry fileControlEntry = fileDescriptionEntry.getFileControlEntry();
						assertNotNull(fileControlEntry);
						assertEquals(fileDescriptionEntry, fileControlEntry.getFileDescriptionEntry());
					}
				}

				{
					final FileDescriptionEntry fileDescriptionEntry = fileSection.getFileDescriptionEntry("SOMEFILE4");
					assertNotNull(fileDescriptionEntry);

					{
						final FileControlEntry fileControlEntry = fileDescriptionEntry.getFileControlEntry();
						assertNotNull(fileControlEntry);
						assertEquals(fileDescriptionEntry, fileControlEntry.getFileDescriptionEntry());
					}
				}

				{
					final FileDescriptionEntry fileDescriptionEntry = fileSection.getFileDescriptionEntry("SOMEFILE5");
					assertNotNull(fileDescriptionEntry);

					{
						final FileControlEntry fileControlEntry = fileDescriptionEntry.getFileControlEntry();
						assertNotNull(fileControlEntry);
						assertEquals(fileDescriptionEntry, fileControlEntry.getFileDescriptionEntry());
					}
				}
			}
		}

		{
			final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
			assertEquals(0, procedureDivision.getParagraphs().size());
			assertEquals(1, procedureDivision.getStatements().size());

			{
				final OpenStatement openStatement = (OpenStatement) procedureDivision.getStatements().get(0);
				assertNotNull(openStatement);
				assertEquals(StatementTypeEnum.OPEN, openStatement.getStatementType());

				assertEquals(1, openStatement.getInputPhrases().size());
				assertEquals(1, openStatement.getOutputPhrases().size());
				assertEquals(1, openStatement.getInputOutputPhrases().size());
				assertEquals(1, openStatement.getExtendPhrases().size());

				{
					final InputPhrase inputPhrase = openStatement.getInputPhrases().get(0);
					assertEquals(2, inputPhrase.getInputs().size());

					{
						final Input input = inputPhrase.getInputs().get(0);
						assertEquals(Input.InputType.NO_REWIND, input.getInputType());

						{
							final Call call = input.getFileCall();
							assertNotNull(call);
							assertEquals(CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

							{
								final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
								assertEquals(fileControlEntry1, fileControlEntryCall.getFileControlEntry());
							}
						}
					}

					{
						final Input input = inputPhrase.getInputs().get(1);
						assertEquals(Input.InputType.REVERSED, input.getInputType());

						{
							final Call call = input.getFileCall();
							assertNotNull(call);
							assertEquals(CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

							{
								final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
								assertEquals(fileControlEntry2, fileControlEntryCall.getFileControlEntry());
							}
						}
					}
				}

				{
					final OutputPhrase outputPhrase = openStatement.getOutputPhrases().get(0);
					assertEquals(1, outputPhrase.getOutputs().size());

					{
						final Output output = outputPhrase.getOutputs().get(0);
						assertTrue(output.isNoRewind());

						{
							final Call call = output.getFileCall();
							assertNotNull(call);
							assertEquals(CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

							{
								final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
								assertEquals(fileControlEntry3, fileControlEntryCall.getFileControlEntry());
							}
						}
					}
				}

				{
					final InputOutputPhrase inputOutputPhrase = openStatement.getInputOutputPhrases().get(0);
					assertEquals(2, inputOutputPhrase.getFileCalls().size());

					{
						final Call call = inputOutputPhrase.getFileCalls().get(0);
						assertNotNull(call);
						assertEquals(CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

						{
							final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
							assertEquals(fileControlEntry4, fileControlEntryCall.getFileControlEntry());
						}
					}

					{
						final Call call = inputOutputPhrase.getFileCalls().get(1);
						assertNotNull(call);
						assertEquals(CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

						{
							final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
							assertEquals(fileControlEntry5, fileControlEntryCall.getFileControlEntry());
						}
					}
				}

				{
					final ExtendPhrase extendPhrase = openStatement.getExtendPhrases().get(0);
					assertEquals(2, extendPhrase.getFileCalls().size());

					{
						final Call call = extendPhrase.getFileCalls().get(0);
						assertNotNull(call);
						assertEquals(CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

						{
							final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
							assertEquals(fileControlEntry4, fileControlEntryCall.getFileControlEntry());
						}
					}

					{
						final Call call = extendPhrase.getFileCalls().get(1);
						assertNotNull(call);
						assertEquals(CallType.FILE_CONTROL_ENTRY_CALL, call.getCallType());

						{
							final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) call;
							assertEquals(fileControlEntry5, fileControlEntryCall.getFileControlEntry());
						}
					}
				}
			}
		}
	}
}
