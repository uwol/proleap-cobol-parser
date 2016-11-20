package io.proleap.cobol.gpl.parser.data.communication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntryInput;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.parser.metamodel.data.communication.EndKeyClause;
import io.proleap.cobol.parser.metamodel.data.communication.MessageCountClause;
import io.proleap.cobol.parser.metamodel.data.communication.MessageDateClause;
import io.proleap.cobol.parser.metamodel.data.communication.MessageTimeClause;
import io.proleap.cobol.parser.metamodel.data.communication.StatusKeyClause;
import io.proleap.cobol.parser.metamodel.data.communication.SymbolicQueueClause;
import io.proleap.cobol.parser.metamodel.data.communication.SymbolicSourceClause;
import io.proleap.cobol.parser.metamodel.data.communication.SymbolicSubQueueClause;
import io.proleap.cobol.parser.metamodel.data.communication.TextLengthClause;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CommunicationDescriptionInputTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/data/communication/CommunicationDescriptionInput.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("CommunicationDescriptionInput");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final CommunicationSection communicationSection = dataDivision.getCommunicationSection();

		final CommunicationDescriptionEntry communicationDescriptionEntry = communicationSection
				.getCommunicationDescriptionEntry("SOMECD");
		assertNotNull(communicationDescriptionEntry);
		assertEquals(CommunicationDescriptionEntry.Type.Input, communicationDescriptionEntry.getType());

		final CommunicationDescriptionEntryInput communicationDescriptionEntryInput = (CommunicationDescriptionEntryInput) communicationDescriptionEntry;

		{
			final SymbolicQueueClause symbolicQueueClause = communicationDescriptionEntryInput.getSymbolicQueueClause();
			assertNotNull(symbolicQueueClause);
			assertNotNull(symbolicQueueClause.getDataDescValueStmt());
		}

		{
			final SymbolicSubQueueClause symbolicSubQueueClause = communicationDescriptionEntryInput
					.getSymbolicSubQueueClause();
			assertNotNull(symbolicSubQueueClause);
			assertEquals(SymbolicSubQueueClause.Type.SubQueue2, symbolicSubQueueClause.getType());
			assertNotNull(symbolicSubQueueClause.getDataDescValueStmt());
		}

		{
			final MessageDateClause messageDateClause = communicationDescriptionEntryInput.getMessageDateClause();
			assertNotNull(messageDateClause);
			assertNotNull(messageDateClause.getDataDescValueStmt());
		}

		{
			final MessageTimeClause messageTimeClause = communicationDescriptionEntryInput.getMessageTimeClause();
			assertNotNull(messageTimeClause);
			assertNotNull(messageTimeClause.getDataDescValueStmt());
		}

		{
			final SymbolicSourceClause symbolicSourceClause = communicationDescriptionEntryInput
					.getSymbolicSourceClause();
			assertNotNull(symbolicSourceClause);
			assertNotNull(symbolicSourceClause.getDataDescValueStmt());
		}

		{
			final TextLengthClause textLengthClause = communicationDescriptionEntryInput.getTextLengthClause();
			assertNotNull(textLengthClause);
			assertNotNull(textLengthClause.getDataDescValueStmt());
		}

		{
			final EndKeyClause endKeyClause = communicationDescriptionEntryInput.getEndKeyClause();
			assertNotNull(endKeyClause);
			assertNotNull(endKeyClause.getDataDescValueStmt());
		}

		{
			final StatusKeyClause statusKeyClause = communicationDescriptionEntryInput.getStatusKeyClause();
			assertNotNull(statusKeyClause);
			assertNotNull(statusKeyClause.getDataDescValueStmt());
		}

		{
			final MessageCountClause messageCountClause = communicationDescriptionEntryInput.getMessageCountClause();
			assertNotNull(messageCountClause);
			assertNotNull(messageCountClause.getDataDescValueStmt());
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