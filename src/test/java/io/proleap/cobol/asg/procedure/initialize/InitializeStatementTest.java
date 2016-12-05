package io.proleap.cobol.asg.procedure.initialize;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.initialize.By;
import io.proleap.cobol.asg.metamodel.procedure.initialize.InitializeStatement;
import io.proleap.cobol.asg.metamodel.procedure.initialize.Replacing;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class InitializeStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/initialize/InitializeStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("InitializeStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(3, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(1, workingStorageSection.getRootDataDescriptionEntries().size());

		final DataDescriptionEntryGroup someGroup = (DataDescriptionEntryGroup) workingStorageSection
				.findDataDescriptionEntry("SOMEGRP");

		assertNotNull(someGroup);
		assertEquals("SOMEGRP", someGroup.getName());
		assertEquals(DataDescriptionEntry.Type.Group, someGroup.getType());

		final DataDescriptionEntry someName = someGroup.findDataDescriptionEntry("SOMENAME");
		assertNotNull(someName);

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(2, procedureDivision.getStatements().size());

		{
			final InitializeStatement initializeStatement = (InitializeStatement) procedureDivision.getStatements()
					.get(0);
			assertNotNull(initializeStatement);
			assertEquals(StatementTypeEnum.Initialize, initializeStatement.getStatementType());
			assertEquals(1, initializeStatement.getDataItemCalls().size());

			{
				final Call dataItemCall = initializeStatement.getDataItemCalls().get(0);
				assertEquals(Call.CallType.DataDescriptionEntryCall, dataItemCall.getCallType());

				{
					final DataDescriptionEntryCall dataItemDataDescriptionEntryCall = (DataDescriptionEntryCall) dataItemCall;
					assertEquals(someGroup, dataItemDataDescriptionEntryCall.getDataDescriptionEntry());
				}
			}

			assertNull(initializeStatement.getReplacing());
		}

		{
			final InitializeStatement initializeStatement = (InitializeStatement) procedureDivision.getStatements()
					.get(1);
			assertNotNull(initializeStatement);
			assertEquals(StatementTypeEnum.Initialize, initializeStatement.getStatementType());
			assertEquals(1, initializeStatement.getDataItemCalls().size());

			{
				final Call dataItemCall = initializeStatement.getDataItemCalls().get(0);
				assertEquals(Call.CallType.DataDescriptionEntryCall, dataItemCall.getCallType());

				{
					final DataDescriptionEntryCall dataItemDataDescriptionEntryCall = (DataDescriptionEntryCall) dataItemCall;
					assertEquals(someName, dataItemDataDescriptionEntryCall.getDataDescriptionEntry());
				}
			}

			{
				final Replacing replacing = initializeStatement.getReplacing();
				assertNotNull(replacing);
				assertEquals(1, replacing.getBys().size());

				{
					final By by = replacing.getBys().get(0);
					assertEquals(By.Type.Alphanumeric, by.getType());
					assertNotNull(by.getValueCall());
					assertEquals(Call.CallType.UndefinedCall, by.getValueCall().getCallType());
				}
			}
		}
	}
}