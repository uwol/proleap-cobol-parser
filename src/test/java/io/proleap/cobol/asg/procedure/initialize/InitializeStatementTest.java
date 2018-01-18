package io.proleap.cobol.asg.procedure.initialize;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

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
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry.DataDescriptionEntryType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.initialize.By;
import io.proleap.cobol.asg.metamodel.procedure.initialize.InitializeStatement;
import io.proleap.cobol.asg.metamodel.procedure.initialize.ReplacingPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class InitializeStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/initialize/InitializeStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("InitializeStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(3, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(1, workingStorageSection.getRootDataDescriptionEntries().size());

		final DataDescriptionEntryGroup someGroup = (DataDescriptionEntryGroup) workingStorageSection
				.getDataDescriptionEntry("SOMEGRP");

		assertNotNull(someGroup);
		assertEquals("SOMEGRP", someGroup.getName());
		assertEquals(DataDescriptionEntryType.GROUP, someGroup.getDataDescriptionEntryType());

		final DataDescriptionEntry someName = someGroup.getDataDescriptionEntry("SOMENAME");
		assertNotNull(someName);

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(3, procedureDivision.getStatements().size());

		{
			final InitializeStatement initializeStatement = (InitializeStatement) procedureDivision.getStatements()
					.get(0);
			assertNotNull(initializeStatement);
			assertEquals(StatementTypeEnum.INITIALIZE, initializeStatement.getStatementType());
			assertEquals(1, initializeStatement.getDataItemCalls().size());

			{
				final Call dataItemCall = initializeStatement.getDataItemCalls().get(0);
				assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, dataItemCall.getCallType());

				{
					final DataDescriptionEntryCall dataItemDataDescriptionEntryCall = (DataDescriptionEntryCall) dataItemCall
							.unwrap();
					assertEquals(someGroup, dataItemDataDescriptionEntryCall.getDataDescriptionEntry());
				}
			}

			assertNull(initializeStatement.getReplacingPhrase());
		}

		{
			final InitializeStatement initializeStatement = (InitializeStatement) procedureDivision.getStatements()
					.get(1);
			assertNotNull(initializeStatement);
			assertEquals(StatementTypeEnum.INITIALIZE, initializeStatement.getStatementType());
			assertEquals(1, initializeStatement.getDataItemCalls().size());

			{
				final Call dataItemCall = initializeStatement.getDataItemCalls().get(0);
				assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, dataItemCall.getCallType());

				{
					final DataDescriptionEntryCall dataItemDataDescriptionEntryCall = (DataDescriptionEntryCall) dataItemCall
							.unwrap();
					assertEquals(someName, dataItemDataDescriptionEntryCall.getDataDescriptionEntry());
				}
			}

			{
				final ReplacingPhrase replacingPhrase = initializeStatement.getReplacingPhrase();
				assertNotNull(replacingPhrase);
				assertEquals(1, replacingPhrase.getBys().size());

				{
					final By by = replacingPhrase.getBys().get(0);
					assertEquals(By.ByType.ALPHANUMERIC, by.getByType());
					assertNotNull(by.getValueStmt());

					final LiteralValueStmt valueStmt = (LiteralValueStmt) by.getValueStmt();
					final Literal literal = valueStmt.getLiteral();
					assertEquals("ABC", literal.getValue());
				}
			}
		}

		{
			final InitializeStatement initializeStatement = (InitializeStatement) procedureDivision.getStatements()
					.get(2);
			assertNotNull(initializeStatement);
			assertEquals(StatementTypeEnum.INITIALIZE, initializeStatement.getStatementType());
			assertEquals(1, initializeStatement.getDataItemCalls().size());

			{
				final Call dataItemCall = initializeStatement.getDataItemCalls().get(0);
				assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, dataItemCall.getCallType());

				{
					final DataDescriptionEntryCall dataItemDataDescriptionEntryCall = (DataDescriptionEntryCall) dataItemCall
							.unwrap();
					assertEquals(someName, dataItemDataDescriptionEntryCall.getDataDescriptionEntry());
				}
			}

			assertNull(initializeStatement.getReplacingPhrase());
		}
	}
}