package io.proleap.cobol.asg.procedure.enable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.call.CommunicationDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.enable.EnableStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class EnableStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/enable/EnableStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("EnableStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final CommunicationSection communicationSection = dataDivision.getCommunicationSection();

		final CommunicationDescriptionEntry communicationDescriptionEntry1 = communicationSection
				.getCommunicationDescriptionEntry("SOMECDNAME1");
		assertNotNull(communicationDescriptionEntry1);
		assertEquals(CommunicationDescriptionEntry.CommunicationDescriptionEntryType.INPUT,
				communicationDescriptionEntry1.getCommunicationDescriptionEntryType());

		final CommunicationDescriptionEntry communicationDescriptionEntry2 = communicationSection
				.getCommunicationDescriptionEntry("SOMECDNAME2");
		assertNotNull(communicationDescriptionEntry2);
		assertEquals(CommunicationDescriptionEntry.CommunicationDescriptionEntryType.INPUT_OUTPUT,
				communicationDescriptionEntry2.getCommunicationDescriptionEntryType());

		final CommunicationDescriptionEntry communicationDescriptionEntry3 = communicationSection
				.getCommunicationDescriptionEntry("SOMECDNAME3");
		assertNotNull(communicationDescriptionEntry3);
		assertEquals(CommunicationDescriptionEntry.CommunicationDescriptionEntryType.OUTPUT,
				communicationDescriptionEntry3.getCommunicationDescriptionEntryType());

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(3, procedureDivision.getStatements().size());

		{
			final EnableStatement enableStatement = (EnableStatement) procedureDivision.getStatements().get(0);
			assertNotNull(enableStatement);
			assertEquals(StatementTypeEnum.ENABLE, enableStatement.getStatementType());
			assertEquals(EnableStatement.EnableType.INPUT, enableStatement.getEnableType());
			assertTrue(enableStatement.isTerminal());

			assertNotNull(enableStatement.getCommunicationDescriptionCall());
			assertEquals(CallType.COMMUNICATION_DESCRIPTION_ENTRY_CALL,
					enableStatement.getCommunicationDescriptionCall().getCallType());

			{
				final CommunicationDescriptionEntryCall communicationDescriptionEntryCall1 = (CommunicationDescriptionEntryCall) enableStatement
						.getCommunicationDescriptionCall();
				assertEquals(communicationDescriptionEntry1,
						communicationDescriptionEntryCall1.getCommunicationDescriptionEntry());

				assertNotNull(enableStatement.getKeyValueStmt());

				final CallValueStmt keyCallValueStmt = (CallValueStmt) enableStatement.getKeyValueStmt();
				assertEquals(CallType.UNDEFINED_CALL, keyCallValueStmt.getCall().getCallType());
			}
		}

		{
			final EnableStatement enableStatement = (EnableStatement) procedureDivision.getStatements().get(1);
			assertNotNull(enableStatement);
			assertEquals(EnableStatement.EnableType.INPUT_OUTPUT, enableStatement.getEnableType());
			assertTrue(enableStatement.isTerminal());

			assertNotNull(enableStatement.getCommunicationDescriptionCall());
			assertEquals(CallType.COMMUNICATION_DESCRIPTION_ENTRY_CALL,
					enableStatement.getCommunicationDescriptionCall().getCallType());

			{
				final CommunicationDescriptionEntryCall communicationDescriptionEntryCall2 = (CommunicationDescriptionEntryCall) enableStatement
						.getCommunicationDescriptionCall();
				assertEquals(communicationDescriptionEntry2,
						communicationDescriptionEntryCall2.getCommunicationDescriptionEntry());

				assertNotNull(enableStatement.getKeyValueStmt());

				final CallValueStmt keyCallValueStmt = (CallValueStmt) enableStatement.getKeyValueStmt();
				assertEquals(CallType.UNDEFINED_CALL, keyCallValueStmt.getCall().getCallType());
			}
		}

		{
			final EnableStatement enableStatement = (EnableStatement) procedureDivision.getStatements().get(2);
			assertNotNull(enableStatement);
			assertEquals(StatementTypeEnum.ENABLE, enableStatement.getStatementType());
			assertEquals(EnableStatement.EnableType.OUTPUT, enableStatement.getEnableType());
			assertFalse(enableStatement.isTerminal());

			assertNotNull(enableStatement.getCommunicationDescriptionCall());
			assertEquals(CallType.COMMUNICATION_DESCRIPTION_ENTRY_CALL,
					enableStatement.getCommunicationDescriptionCall().getCallType());

			{
				final CommunicationDescriptionEntryCall communicationDescriptionEntryCall3 = (CommunicationDescriptionEntryCall) enableStatement
						.getCommunicationDescriptionCall();
				assertEquals(communicationDescriptionEntry3,
						communicationDescriptionEntryCall3.getCommunicationDescriptionEntry());

				assertNotNull(enableStatement.getKeyValueStmt());

				final CallValueStmt keyCallValueStmt = (CallValueStmt) enableStatement.getKeyValueStmt();
				assertEquals(CallType.UNDEFINED_CALL, keyCallValueStmt.getCall().getCallType());
			}
		}
	}
}
