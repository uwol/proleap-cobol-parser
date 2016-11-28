package io.proleap.cobol.gpl.parser.data.communication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CompilationUnit;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntryInputOutput;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.parser.metamodel.data.communication.EndKeyClause;
import io.proleap.cobol.parser.metamodel.data.communication.MessageDateClause;
import io.proleap.cobol.parser.metamodel.data.communication.MessageTimeClause;
import io.proleap.cobol.parser.metamodel.data.communication.StatusKeyClause;
import io.proleap.cobol.parser.metamodel.data.communication.SymbolicTerminalClause;
import io.proleap.cobol.parser.metamodel.data.communication.TextLengthClause;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CommunicationDescriptionInputOutputTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/data/communication/CommunicationDescriptionInputOutput.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("CommunicationDescriptionInputOutput");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final CommunicationSection communicationSection = dataDivision.getCommunicationSection();

		final CommunicationDescriptionEntry communicationDescriptionEntry = communicationSection
				.getCommunicationDescriptionEntry("SOMECD");
		assertNotNull(communicationDescriptionEntry);
		assertEquals(CommunicationDescriptionEntry.Type.InputOutput, communicationDescriptionEntry.getType());

		final CommunicationDescriptionEntryInputOutput communicationDescriptionEntryInputOutput = (CommunicationDescriptionEntryInputOutput) communicationDescriptionEntry;

		{
			final MessageDateClause messageDateClause = communicationDescriptionEntryInputOutput.getMessageDateClause();
			assertNotNull(messageDateClause);
			assertNotNull(messageDateClause.getDataDescValueStmt());
		}

		{
			final MessageTimeClause messageTimeClause = communicationDescriptionEntryInputOutput.getMessageTimeClause();
			assertNotNull(messageTimeClause);
			assertNotNull(messageTimeClause.getDataDescValueStmt());
		}

		{
			final SymbolicTerminalClause symbolicTerminalClause = communicationDescriptionEntryInputOutput
					.getSymbolicTerminalClause();
			assertNotNull(symbolicTerminalClause);
			assertNotNull(symbolicTerminalClause.getDataDescValueStmt());
		}

		{
			final TextLengthClause textLengthClause = communicationDescriptionEntryInputOutput.getTextLengthClause();
			assertNotNull(textLengthClause);
			assertNotNull(textLengthClause.getDataDescValueStmt());
		}

		{
			final EndKeyClause endKeyClause = communicationDescriptionEntryInputOutput.getEndKeyClause();
			assertNotNull(endKeyClause);
			assertNotNull(endKeyClause.getDataDescValueStmt());
		}

		{
			final StatusKeyClause statusKeyClause = communicationDescriptionEntryInputOutput.getStatusKeyClause();
			assertNotNull(statusKeyClause);
			assertNotNull(statusKeyClause.getDataDescValueStmt());
		}

		assertEquals(2, communicationSection.getDataDescriptionEntries().size());

		{
			final DataDescriptionEntryGroup dataDescriptionEntryWsPerson = (DataDescriptionEntryGroup) communicationSection
					.getDataDescriptionEntry("WS-PERSON");
			assertNotNull(dataDescriptionEntryWsPerson);
			assertEquals("WS-PERSON", dataDescriptionEntryWsPerson.getName());
			assertEquals(new Integer(1), dataDescriptionEntryWsPerson.getLevelNumber());
			assertNull(dataDescriptionEntryWsPerson.getParentDataDescriptionEntryGroup());

			final DataDescriptionEntry dataDescriptionEntryWsPersonId = communicationSection
					.getDataDescriptionEntry("WS-PERSON-ID");
			assertNotNull(dataDescriptionEntryWsPersonId);
			assertEquals("WS-PERSON-ID", dataDescriptionEntryWsPersonId.getName());
			assertEquals(new Integer(5), dataDescriptionEntryWsPersonId.getLevelNumber());
			assertEquals(dataDescriptionEntryWsPerson, dataDescriptionEntryWsPersonId.getParentDataDescriptionEntryGroup());
		}
	}
}