package io.proleap.cobol.asg.procedure.read;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.call.FileControlEntryCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.InputOutputSection;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlParagraph;
import io.proleap.cobol.asg.metamodel.procedure.AtEndPhrase;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotAtEndPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.read.Into;
import io.proleap.cobol.asg.metamodel.procedure.read.Key;
import io.proleap.cobol.asg.metamodel.procedure.read.ReadStatement;
import io.proleap.cobol.asg.metamodel.procedure.read.With;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ReadStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/read/ReadStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ReadStatement");
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
				final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();
				assertNotNull(workingStorageSection);

				{
					dataDescriptionEntry = workingStorageSection.getDataDescriptionEntry("ITEMS");
					assertNotNull(dataDescriptionEntry);
				}
			}
		}

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final ReadStatement readStatement = (ReadStatement) procedureDivision.getStatements().get(0);
			assertNotNull(readStatement);
			assertEquals(StatementTypeEnum.READ, readStatement.getStatementType());

			{
				assertNotNull(readStatement.getFileCall());
				assertEquals(CallType.FILE_CONTROL_ENTRY_CALL, readStatement.getFileCall().getCallType());

				final FileControlEntryCall fileControlEntryCall = (FileControlEntryCall) readStatement.getFileCall();
				assertNotNull(fileControlEntryCall.getFileControlEntry());
				assertEquals(fileControlEntry, fileControlEntryCall.getFileControlEntry());
			}

			assertTrue(readStatement.isNextRecord());

			{
				final Into into = readStatement.getInto();
				assertNotNull(into);
				assertNotNull(into.getIntoCall());
				assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, into.getIntoCall().getCallType());

				final DataDescriptionEntryCall dataDescriptionEntryCall = (DataDescriptionEntryCall) into.getIntoCall()
						.unwrap();
				assertNotNull(dataDescriptionEntryCall.getDataDescriptionEntry());
				assertEquals(dataDescriptionEntry, dataDescriptionEntryCall.getDataDescriptionEntry());
			}

			{
				final With with = readStatement.getWith();
				assertNotNull(with);
				assertNotNull(with.getWithType());
				assertEquals(With.WithType.WAIT, with.getWithType());
			}

			{
				final Key key = readStatement.getKey();
				assertNotNull(key.getKeyCall());
				assertEquals(CallType.UNDEFINED_CALL, key.getKeyCall().getCallType());
			}

			{
				final InvalidKeyPhrase invalidKey = readStatement.getInvalidKeyPhrase();
				assertNotNull(invalidKey);
				assertEquals(1, invalidKey.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) invalidKey.getStatements().get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
				}
			}

			{
				final NotInvalidKeyPhrase notInvalidKeyPhrase = readStatement.getNotInvalidKeyPhrase();
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
				final AtEndPhrase atEnd = readStatement.getAtEnd();
				assertNotNull(atEnd);
				assertEquals(1, atEnd.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) atEnd.getStatements().get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
				}
			}

			{
				final NotAtEndPhrase notAtEndPhrase = readStatement.getNotAtEndPhrase();
				assertNotNull(notAtEndPhrase);
				assertEquals(1, notAtEndPhrase.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) notAtEndPhrase.getStatements().get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
				}
			}
		}
	}
}
