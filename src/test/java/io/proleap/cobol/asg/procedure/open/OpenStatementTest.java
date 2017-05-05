package io.proleap.cobol.asg.procedure.open;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.FileDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.file.FileSection;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.open.Input;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenExtend;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenInput;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenInputOutput;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenOutput;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenStatement;
import io.proleap.cobol.asg.metamodel.procedure.open.Output;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class OpenStatementTest extends CobolTestSupport {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/open/OpenStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("OpenStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();

		final FileDescriptionEntry fileDescriptionEntry1;
		final FileDescriptionEntry fileDescriptionEntry2;
		final FileDescriptionEntry fileDescriptionEntry3;
		final FileDescriptionEntry fileDescriptionEntry4;
		final FileDescriptionEntry fileDescriptionEntry5;

		{
			final DataDivision dataDivision = programUnit.getDataDivision();
			assertNotNull(dataDivision);

			{
				final FileSection fileSection = dataDivision.getFileSection();
				assertNotNull(fileSection);
				assertEquals(5, fileSection.getFileDescriptionEntries().size());

				{
					fileDescriptionEntry1 = fileSection.getFileDescriptionEntry("SOMEFILE1");
					assertNotNull(fileDescriptionEntry1);
					assertEquals(1, fileDescriptionEntry1.getCalls().size());
				}

				{
					fileDescriptionEntry2 = fileSection.getFileDescriptionEntry("SOMEFILE2");
					assertNotNull(fileDescriptionEntry2);
					assertEquals(1, fileDescriptionEntry2.getCalls().size());
				}

				{
					fileDescriptionEntry3 = fileSection.getFileDescriptionEntry("SOMEFILE3");
					assertNotNull(fileDescriptionEntry3);
					assertEquals(1, fileDescriptionEntry3.getCalls().size());
				}

				{
					fileDescriptionEntry4 = fileSection.getFileDescriptionEntry("SOMEFILE4");
					assertNotNull(fileDescriptionEntry4);
					assertEquals(2, fileDescriptionEntry4.getCalls().size());
				}

				{
					fileDescriptionEntry5 = fileSection.getFileDescriptionEntry("SOMEFILE5");
					assertNotNull(fileDescriptionEntry5);
					assertEquals(2, fileDescriptionEntry5.getCalls().size());
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

				assertEquals(1, openStatement.getOpenInputs().size());
				assertEquals(1, openStatement.getOpenOutputs().size());
				assertEquals(1, openStatement.getOpenInputOutputs().size());
				assertEquals(1, openStatement.getOpenExtends().size());

				{
					final OpenInput openInput = openStatement.getOpenInputs().get(0);
					assertEquals(2, openInput.getInputs().size());

					{
						final Input input = openInput.getInputs().get(0);
						assertEquals(Input.Type.NO_REWIND, input.getType());

						{
							final Call call = input.getFileCall();
							assertNotNull(call);
							assertEquals(Call.CallType.FILE_DESCRIPTION_ENTRY_CALL, call.getCallType());

							{
								final FileDescriptionEntryCall fileDescriptionEntryCall = (FileDescriptionEntryCall) call;
								assertEquals(fileDescriptionEntry1, fileDescriptionEntryCall.getFileDescriptionEntry());
							}
						}
					}

					{
						final Input input = openInput.getInputs().get(1);
						assertEquals(Input.Type.REVERSED, input.getType());

						{
							final Call call = input.getFileCall();
							assertNotNull(call);
							assertEquals(Call.CallType.FILE_DESCRIPTION_ENTRY_CALL, call.getCallType());

							{
								final FileDescriptionEntryCall fileDescriptionEntryCall = (FileDescriptionEntryCall) call;
								assertEquals(fileDescriptionEntry2, fileDescriptionEntryCall.getFileDescriptionEntry());
							}
						}
					}
				}

				{
					final OpenOutput openOutput = openStatement.getOpenOutputs().get(0);
					assertEquals(1, openOutput.getOutputs().size());

					{
						final Output output = openOutput.getOutputs().get(0);
						assertTrue(output.isNoRewind());

						{
							final Call call = output.getFileCall();
							assertNotNull(call);
							assertEquals(Call.CallType.FILE_DESCRIPTION_ENTRY_CALL, call.getCallType());

							{
								final FileDescriptionEntryCall fileDescriptionEntryCall = (FileDescriptionEntryCall) call;
								assertEquals(fileDescriptionEntry3, fileDescriptionEntryCall.getFileDescriptionEntry());
							}
						}
					}
				}

				{
					final OpenInputOutput openInputOutput = openStatement.getOpenInputOutputs().get(0);
					assertEquals(2, openInputOutput.getFileCalls().size());

					{
						final Call call = openInputOutput.getFileCalls().get(0);
						assertNotNull(call);
						assertEquals(Call.CallType.FILE_DESCRIPTION_ENTRY_CALL, call.getCallType());

						{
							final FileDescriptionEntryCall fileDescriptionEntryCall = (FileDescriptionEntryCall) call;
							assertEquals(fileDescriptionEntry4, fileDescriptionEntryCall.getFileDescriptionEntry());
						}
					}

					{
						final Call call = openInputOutput.getFileCalls().get(1);
						assertNotNull(call);
						assertEquals(Call.CallType.FILE_DESCRIPTION_ENTRY_CALL, call.getCallType());

						{
							final FileDescriptionEntryCall fileDescriptionEntryCall = (FileDescriptionEntryCall) call;
							assertEquals(fileDescriptionEntry5, fileDescriptionEntryCall.getFileDescriptionEntry());
						}
					}
				}

				{
					final OpenExtend openExtend = openStatement.getOpenExtends().get(0);
					assertEquals(2, openExtend.getFileCalls().size());

					{
						final Call call = openExtend.getFileCalls().get(0);
						assertNotNull(call);
						assertEquals(Call.CallType.FILE_DESCRIPTION_ENTRY_CALL, call.getCallType());

						{
							final FileDescriptionEntryCall fileDescriptionEntryCall = (FileDescriptionEntryCall) call;
							assertEquals(fileDescriptionEntry4, fileDescriptionEntryCall.getFileDescriptionEntry());
						}
					}

					{
						final Call call = openExtend.getFileCalls().get(1);
						assertNotNull(call);
						assertEquals(Call.CallType.FILE_DESCRIPTION_ENTRY_CALL, call.getCallType());

						{
							final FileDescriptionEntryCall fileDescriptionEntryCall = (FileDescriptionEntryCall) call;
							assertEquals(fileDescriptionEntry5, fileDescriptionEntryCall.getFileDescriptionEntry());
						}
					}
				}
			}
		}
	}
}