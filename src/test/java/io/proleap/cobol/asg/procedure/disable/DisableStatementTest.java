package io.proleap.cobol.asg.procedure.disable;

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
import io.proleap.cobol.asg.metamodel.procedure.disable.DisableStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DisableStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/disable/DisableStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DisableStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final CommunicationSection communicationSection = dataDivision.getCommunicationSection();

		final CommunicationDescriptionEntry communicationDescriptionEntry1 = communicationSection
				.getCommunicationDescriptionEntry("SOMECDNAME1");
		assertNotNull(communicationDescriptionEntry1);
		assertEquals(CommunicationDescriptionEntry.Type.INPUT, communicationDescriptionEntry1.getType());

		final CommunicationDescriptionEntry communicationDescriptionEntry2 = communicationSection
				.getCommunicationDescriptionEntry("SOMECDNAME2");
		assertNotNull(communicationDescriptionEntry2);
		assertEquals(CommunicationDescriptionEntry.Type.INPUT_OUTPUT, communicationDescriptionEntry2.getType());

		final CommunicationDescriptionEntry communicationDescriptionEntry3 = communicationSection
				.getCommunicationDescriptionEntry("SOMECDNAME3");
		assertNotNull(communicationDescriptionEntry3);
		assertEquals(CommunicationDescriptionEntry.Type.OUTPUT, communicationDescriptionEntry3.getType());

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(3, procedureDivision.getStatements().size());

		{
			final DisableStatement disableStatement = (DisableStatement) procedureDivision.getStatements().get(0);
			assertNotNull(disableStatement);
			assertEquals(StatementTypeEnum.DISABLE, disableStatement.getStatementType());
			assertEquals(DisableStatement.Type.INPUT, disableStatement.getType());
			assertTrue(disableStatement.isTerminal());

			assertNotNull(disableStatement.getCommunicationDescriptionCall());
			assertEquals(Call.CallType.COMMUNICATION_DESCRIPTION_ENTRY_CALL,
					disableStatement.getCommunicationDescriptionCall().getCallType());

			{
				final CommunicationDescriptionEntryCall communicationDescriptionEntryCall1 = (CommunicationDescriptionEntryCall) disableStatement
						.getCommunicationDescriptionCall();
				assertEquals(communicationDescriptionEntry1,
						communicationDescriptionEntryCall1.getCommunicationDescriptionEntry());

				assertNotNull(disableStatement.getKeyCall());
				assertEquals(Call.CallType.UNDEFINED_CALL, disableStatement.getKeyCall().getCallType());
			}
		}

		{
			final DisableStatement disableStatement = (DisableStatement) procedureDivision.getStatements().get(1);
			assertNotNull(disableStatement);
			assertEquals(StatementTypeEnum.DISABLE, disableStatement.getStatementType());
			assertEquals(DisableStatement.Type.INPUT_OUTPUT, disableStatement.getType());
			assertTrue(disableStatement.isTerminal());

			assertNotNull(disableStatement.getCommunicationDescriptionCall());
			assertEquals(Call.CallType.COMMUNICATION_DESCRIPTION_ENTRY_CALL,
					disableStatement.getCommunicationDescriptionCall().getCallType());

			{
				final CommunicationDescriptionEntryCall communicationDescriptionEntryCall2 = (CommunicationDescriptionEntryCall) disableStatement
						.getCommunicationDescriptionCall();
				assertEquals(communicationDescriptionEntry2,
						communicationDescriptionEntryCall2.getCommunicationDescriptionEntry());

				assertNotNull(disableStatement.getKeyCall());
				assertEquals(Call.CallType.UNDEFINED_CALL, disableStatement.getKeyCall().getCallType());
			}
		}

		{
			final DisableStatement disableStatement = (DisableStatement) procedureDivision.getStatements().get(2);
			assertNotNull(disableStatement);
			assertEquals(StatementTypeEnum.DISABLE, disableStatement.getStatementType());
			assertEquals(DisableStatement.Type.OUTPUT, disableStatement.getType());
			assertFalse(disableStatement.isTerminal());

			assertNotNull(disableStatement.getCommunicationDescriptionCall());
			assertEquals(Call.CallType.COMMUNICATION_DESCRIPTION_ENTRY_CALL,
					disableStatement.getCommunicationDescriptionCall().getCallType());

			{
				final CommunicationDescriptionEntryCall communicationDescriptionEntryCall3 = (CommunicationDescriptionEntryCall) disableStatement
						.getCommunicationDescriptionCall();
				assertEquals(communicationDescriptionEntry3,
						communicationDescriptionEntryCall3.getCommunicationDescriptionEntry());

				assertNotNull(disableStatement.getKeyCall());
				assertEquals(Call.CallType.UNDEFINED_CALL, disableStatement.getKeyCall().getCallType());
			}
		}
	}
}