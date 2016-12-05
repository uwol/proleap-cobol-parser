package io.proleap.cobol.asg.procedure.enable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.CommunicationDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.enable.EnableStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class EnableStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/enable/EnableStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("EnableStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final CommunicationSection communicationSection = dataDivision.getCommunicationSection();

		final CommunicationDescriptionEntry communicationDescriptionEntry1 = communicationSection
				.getCommunicationDescriptionEntry("SOMECDNAME1");
		assertNotNull(communicationDescriptionEntry1);
		assertEquals(CommunicationDescriptionEntry.Type.Input, communicationDescriptionEntry1.getType());

		final CommunicationDescriptionEntry communicationDescriptionEntry2 = communicationSection
				.getCommunicationDescriptionEntry("SOMECDNAME2");
		assertNotNull(communicationDescriptionEntry2);
		assertEquals(CommunicationDescriptionEntry.Type.InputOutput, communicationDescriptionEntry2.getType());

		final CommunicationDescriptionEntry communicationDescriptionEntry3 = communicationSection
				.getCommunicationDescriptionEntry("SOMECDNAME3");
		assertNotNull(communicationDescriptionEntry3);
		assertEquals(CommunicationDescriptionEntry.Type.Output, communicationDescriptionEntry3.getType());

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(3, procedureDivision.getStatements().size());

		{
			final EnableStatement enableStatement = (EnableStatement) procedureDivision.getStatements().get(0);
			assertNotNull(enableStatement);
			assertEquals(StatementTypeEnum.Enable, enableStatement.getStatementType());
			assertEquals(EnableStatement.Type.Input, enableStatement.getType());
			assertTrue(enableStatement.isTerminal());

			assertNotNull(enableStatement.getCommunicationDescriptionCall());
			assertEquals(Call.CallType.CommunicationDescriptionEntryCall,
					enableStatement.getCommunicationDescriptionCall().getCallType());

			{
				final CommunicationDescriptionEntryCall communicationDescriptionEntryCall1 = (CommunicationDescriptionEntryCall) enableStatement
						.getCommunicationDescriptionCall();
				assertEquals(communicationDescriptionEntry1,
						communicationDescriptionEntryCall1.getCommunicationDescriptionEntry());

				assertNotNull(enableStatement.getKeyCall());
				assertEquals(Call.CallType.UndefinedCall, enableStatement.getKeyCall().getCallType());
			}
		}

		{
			final EnableStatement enableStatement = (EnableStatement) procedureDivision.getStatements().get(1);
			assertNotNull(enableStatement);
			assertEquals(EnableStatement.Type.InputOutput, enableStatement.getType());
			assertTrue(enableStatement.isTerminal());

			assertNotNull(enableStatement.getCommunicationDescriptionCall());
			assertEquals(Call.CallType.CommunicationDescriptionEntryCall,
					enableStatement.getCommunicationDescriptionCall().getCallType());

			final CommunicationDescriptionEntryCall communicationDescriptionEntryCall2 = (CommunicationDescriptionEntryCall) enableStatement
					.getCommunicationDescriptionCall();
			assertEquals(communicationDescriptionEntry2,
					communicationDescriptionEntryCall2.getCommunicationDescriptionEntry());

			assertNotNull(enableStatement.getKeyCall());
			assertEquals(Call.CallType.UndefinedCall, enableStatement.getKeyCall().getCallType());
		}

		{
			final EnableStatement enableStatement = (EnableStatement) procedureDivision.getStatements().get(2);
			assertNotNull(enableStatement);
			assertEquals(StatementTypeEnum.Enable, enableStatement.getStatementType());
			assertEquals(EnableStatement.Type.Output, enableStatement.getType());
			assertFalse(enableStatement.isTerminal());

			assertNotNull(enableStatement.getCommunicationDescriptionCall());
			assertEquals(Call.CallType.CommunicationDescriptionEntryCall,
					enableStatement.getCommunicationDescriptionCall().getCallType());

			{
				final CommunicationDescriptionEntryCall communicationDescriptionEntryCall3 = (CommunicationDescriptionEntryCall) enableStatement
						.getCommunicationDescriptionCall();
				assertEquals(communicationDescriptionEntry3,
						communicationDescriptionEntryCall3.getCommunicationDescriptionEntry());

				assertNotNull(enableStatement.getKeyCall());
				assertEquals(Call.CallType.UndefinedCall, enableStatement.getKeyCall().getCallType());
			}
		}
	}
}