package io.proleap.cobol.asg.data.communication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntryInputOutput;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.asg.metamodel.data.communication.EndKeyClause;
import io.proleap.cobol.asg.metamodel.data.communication.MessageDateClause;
import io.proleap.cobol.asg.metamodel.data.communication.MessageTimeClause;
import io.proleap.cobol.asg.metamodel.data.communication.StatusKeyClause;
import io.proleap.cobol.asg.metamodel.data.communication.SymbolicTerminalClause;
import io.proleap.cobol.asg.metamodel.data.communication.TextLengthClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryContainer.DataDescriptionEntryContainerType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CommunicationDescriptionInputOutputTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/communication/CommunicationDescriptionInputOutput.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("CommunicationDescriptionInputOutput");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final CommunicationSection communicationSection = dataDivision.getCommunicationSection();

		assertEquals(DataDescriptionEntryContainerType.COMMUNICATION_SECTION, communicationSection.getContainerType());

		{
			final CommunicationDescriptionEntry communicationDescriptionEntry = communicationSection
					.getCommunicationDescriptionEntry("SOMECD");
			assertNotNull(communicationDescriptionEntry);
			assertEquals(CommunicationDescriptionEntry.CommunicationDescriptionEntryType.INPUT_OUTPUT,
					communicationDescriptionEntry.getCommunicationDescriptionEntryType());

			final CommunicationDescriptionEntryInputOutput communicationDescriptionEntryInputOutput = (CommunicationDescriptionEntryInputOutput) communicationDescriptionEntry;

			{
				final MessageDateClause messageDateClause = communicationDescriptionEntryInputOutput
						.getMessageDateClause();
				assertNotNull(messageDateClause);
				assertNotNull(messageDateClause.getDataDescCall());
			}

			{
				final MessageTimeClause messageTimeClause = communicationDescriptionEntryInputOutput
						.getMessageTimeClause();
				assertNotNull(messageTimeClause);
				assertNotNull(messageTimeClause.getDataDescCall());
			}

			{
				final SymbolicTerminalClause symbolicTerminalClause = communicationDescriptionEntryInputOutput
						.getSymbolicTerminalClause();
				assertNotNull(symbolicTerminalClause);
				assertNotNull(symbolicTerminalClause.getDataDescCall());
			}

			{
				final TextLengthClause textLengthClause = communicationDescriptionEntryInputOutput
						.getTextLengthClause();
				assertNotNull(textLengthClause);
				assertNotNull(textLengthClause.getDataDescCall());
			}

			{
				final EndKeyClause endKeyClause = communicationDescriptionEntryInputOutput.getEndKeyClause();
				assertNotNull(endKeyClause);
				assertNotNull(endKeyClause.getDataDescCall());
			}

			{
				final StatusKeyClause statusKeyClause = communicationDescriptionEntryInputOutput.getStatusKeyClause();
				assertNotNull(statusKeyClause);
				assertNotNull(statusKeyClause.getDataDescCall());
			}

			assertEquals(2, communicationSection.getDataDescriptionEntries().size());

			{
				final DataDescriptionEntryGroup dataDescriptionEntryWsPerson = (DataDescriptionEntryGroup) communicationSection
						.getDataDescriptionEntry("WS-PERSON");
				assertNotNull(dataDescriptionEntryWsPerson);
				assertEquals("WS-PERSON", dataDescriptionEntryWsPerson.getName());
				assertEquals(Integer.valueOf(1), dataDescriptionEntryWsPerson.getLevelNumber());
				assertNull(dataDescriptionEntryWsPerson.getParentDataDescriptionEntryGroup());

				{
					final DataDescriptionEntry dataDescriptionEntryWsPersonId = communicationSection
							.getDataDescriptionEntry("WS-PERSON-ID");
					assertNotNull(dataDescriptionEntryWsPersonId);
					assertEquals("WS-PERSON-ID", dataDescriptionEntryWsPersonId.getName());
					assertEquals(Integer.valueOf(5), dataDescriptionEntryWsPersonId.getLevelNumber());
					assertEquals(dataDescriptionEntryWsPerson,
							dataDescriptionEntryWsPersonId.getParentDataDescriptionEntryGroup());
				}
			}
		}
	}
}