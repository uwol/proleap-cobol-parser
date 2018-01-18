package io.proleap.cobol.asg.procedure.gotostmt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.call.ProcedureCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry.DataDescriptionEntryType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryContainer.DataDescriptionEntryContainerType;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.DependingOnPhrase;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.GoToStatement;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.Simple;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class GoToStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/gotostmt/GoToStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("GoToStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(1, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(1, workingStorageSection.getRootDataDescriptionEntries().size());

		assertEquals(DataDescriptionEntryContainerType.WORKING_STORAGE_SECTION,
				workingStorageSection.getContainerType());

		final DataDescriptionEntry dataDescriptionEntry;

		{
			dataDescriptionEntry = workingStorageSection.getDataDescriptionEntry("SOMEDATA1");

			assertNotNull(dataDescriptionEntry);
			assertEquals("SOMEDATA1", dataDescriptionEntry.getName());
			assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntry.getDataDescriptionEntryType());
			assertEquals(Integer.valueOf(1), dataDescriptionEntry.getLevelNumber());
			assertNull(dataDescriptionEntry.getParentDataDescriptionEntryGroup());
		}

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(5, procedureDivision.getParagraphs().size());
		assertEquals(0, procedureDivision.getStatements().size());

		final Paragraph paragraph1 = procedureDivision.getParagraph("SOMEPROC1");
		assertNotNull(paragraph1);

		final Paragraph paragraph2 = procedureDivision.getParagraph("SOMEPROC2");
		assertNotNull(paragraph2);

		final Paragraph paragraph3 = procedureDivision.getParagraph("SOMEPROC3");
		assertNotNull(paragraph3);

		final Paragraph paragraph4 = procedureDivision.getParagraph("SOMEPROC4");
		assertNotNull(paragraph4);

		final Paragraph paragraph5 = procedureDivision.getParagraph("SOMEPROC5");
		assertNotNull(paragraph5);

		{
			assertEquals(1, paragraph1.getStatements().size());

			{
				final GoToStatement statement = (GoToStatement) paragraph1.getStatements().get(0);
				assertNotNull(statement);
				assertEquals(StatementTypeEnum.GO_TO, statement.getStatementType());
				assertEquals(GoToStatement.GoToType.SIMPLE, statement.getGoToType());

				{
					final Simple simple = statement.getSimple();
					assertEquals(CallType.PROCEDURE_CALL, simple.getProcedureCall().getCallType());

					{
						final ProcedureCall procedureCall = (ProcedureCall) simple.getProcedureCall();
						assertEquals(paragraph2, procedureCall.getParagraph());
					}
				}
			}
		}

		{
			assertEquals(1, paragraph2.getStatements().size());

			{
				final GoToStatement statement = (GoToStatement) paragraph2.getStatements().get(0);
				assertNotNull(statement);
				assertEquals(StatementTypeEnum.GO_TO, statement.getStatementType());
				assertEquals(GoToStatement.GoToType.DEPENDING_ON, statement.getGoToType());

				{
					final DependingOnPhrase dependingOnPhrase = statement.getDependingOnPhrase();
					assertEquals(3, dependingOnPhrase.getProcedureCalls().size());

					{
						final Call call = dependingOnPhrase.getProcedureCalls().get(0);
						assertEquals(CallType.PROCEDURE_CALL, call.getCallType());

						{
							final ProcedureCall procedureCall = (ProcedureCall) call;
							assertEquals(paragraph3, procedureCall.getParagraph());
						}
					}

					{
						final Call call = dependingOnPhrase.getProcedureCalls().get(1);
						assertEquals(CallType.PROCEDURE_CALL, call.getCallType());

						{
							final ProcedureCall procedureCall = (ProcedureCall) call;
							assertEquals(paragraph4, procedureCall.getParagraph());
						}
					}

					{
						final Call call = dependingOnPhrase.getProcedureCalls().get(2);
						assertEquals(CallType.PROCEDURE_CALL, call.getCallType());

						{
							final ProcedureCall procedureCall = (ProcedureCall) call;
							assertEquals(paragraph5, procedureCall.getParagraph());
						}
					}

					{
						final Call dependingOnCall = dependingOnPhrase.getDependingOnCall();
						assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, dependingOnCall.getCallType());

						final DataDescriptionEntryCall dataDescriptionEntryCall = (DataDescriptionEntryCall) dependingOnCall
								.unwrap();
						assertEquals(dataDescriptionEntry, dataDescriptionEntryCall.getDataDescriptionEntry());
					}
				}
			}
		}

		{
			assertEquals(1, paragraph3.getStatements().size());

			{
				final Statement statement = paragraph3.getStatements().get(0);
				assertEquals(StatementTypeEnum.DISPLAY, statement.getStatementType());
			}
		}

		{
			assertEquals(1, paragraph4.getStatements().size());

			{
				final Statement statement = paragraph4.getStatements().get(0);
				assertEquals(StatementTypeEnum.DISPLAY, statement.getStatementType());
			}
		}

		{
			assertEquals(1, paragraph5.getStatements().size());

			{
				final GoToStatement statement = (GoToStatement) paragraph5.getStatements().get(0);
				assertNotNull(statement);
				assertEquals(StatementTypeEnum.GO_TO, statement.getStatementType());
				assertEquals(GoToStatement.GoToType.DEPENDING_ON, statement.getGoToType());

				{
					final DependingOnPhrase dependingOnPhrase = statement.getDependingOnPhrase();
					assertTrue(dependingOnPhrase.isMoreLabels());
				}
			}
		}
	}
}
