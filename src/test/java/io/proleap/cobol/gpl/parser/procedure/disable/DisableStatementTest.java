package io.proleap.cobol.gpl.parser.procedure.disable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CompilationUnit;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.call.CommunicationDescriptionEntryCall;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.disable.DisableStatement;
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
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/disable/DisableStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DisableStatement");
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

		{
			final DisableStatement disableStatement = (DisableStatement) procedureDivision.getStatements().get(0);
			assertNotNull(disableStatement);
			assertEquals(DisableStatement.Type.Input, disableStatement.getType());
			assertTrue(disableStatement.isTerminal());

			assertNotNull(disableStatement.getCommunicationDescriptionCall());
			assertEquals(Call.CallType.CommunicationDescriptionEntryCall,
					disableStatement.getCommunicationDescriptionCall().getCallType());

			final CommunicationDescriptionEntryCall communicationDescriptionEntryCall1 = (CommunicationDescriptionEntryCall) disableStatement
					.getCommunicationDescriptionCall();
			assertEquals(communicationDescriptionEntry1,
					communicationDescriptionEntryCall1.getCommunicationDescriptionEntry());

			assertNotNull(disableStatement.getKeyCall());
			assertEquals(Call.CallType.UndefinedCall, disableStatement.getKeyCall().getCallType());
		}

		{
			final DisableStatement disableStatement = (DisableStatement) procedureDivision.getStatements().get(1);
			assertNotNull(disableStatement);
			assertEquals(DisableStatement.Type.InputOutput, disableStatement.getType());
			assertTrue(disableStatement.isTerminal());

			assertNotNull(disableStatement.getCommunicationDescriptionCall());
			assertEquals(Call.CallType.CommunicationDescriptionEntryCall,
					disableStatement.getCommunicationDescriptionCall().getCallType());

			final CommunicationDescriptionEntryCall communicationDescriptionEntryCall2 = (CommunicationDescriptionEntryCall) disableStatement
					.getCommunicationDescriptionCall();
			assertEquals(communicationDescriptionEntry2,
					communicationDescriptionEntryCall2.getCommunicationDescriptionEntry());

			assertNotNull(disableStatement.getKeyCall());
			assertEquals(Call.CallType.UndefinedCall, disableStatement.getKeyCall().getCallType());
		}

		{
			final DisableStatement disableStatement = (DisableStatement) procedureDivision.getStatements().get(2);
			assertNotNull(disableStatement);
			assertEquals(DisableStatement.Type.Output, disableStatement.getType());
			assertFalse(disableStatement.isTerminal());

			assertNotNull(disableStatement.getCommunicationDescriptionCall());
			assertEquals(Call.CallType.CommunicationDescriptionEntryCall,
					disableStatement.getCommunicationDescriptionCall().getCallType());

			final CommunicationDescriptionEntryCall communicationDescriptionEntryCall3 = (CommunicationDescriptionEntryCall) disableStatement
					.getCommunicationDescriptionCall();
			assertEquals(communicationDescriptionEntry3,
					communicationDescriptionEntryCall3.getCommunicationDescriptionEntry());

			assertNotNull(disableStatement.getKeyCall());
			assertEquals(Call.CallType.UndefinedCall, disableStatement.getKeyCall().getCallType());
		}
	}
}