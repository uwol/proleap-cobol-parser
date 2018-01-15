package io.proleap.cobol.asg.data.communication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntryOutput;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.asg.metamodel.data.communication.DestinationCountClause;
import io.proleap.cobol.asg.metamodel.data.communication.DestinationTableClause;
import io.proleap.cobol.asg.metamodel.data.communication.ErrorKeyClause;
import io.proleap.cobol.asg.metamodel.data.communication.StatusKeyClause;
import io.proleap.cobol.asg.metamodel.data.communication.SymbolicDestinationClause;
import io.proleap.cobol.asg.metamodel.data.communication.TextLengthClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryContainer.DataDescriptionEntryContainerType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CommunicationDescriptionOutputTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/communication/CommunicationDescriptionOutput.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("CommunicationDescriptionOutput");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final CommunicationSection communicationSection = dataDivision.getCommunicationSection();

		assertEquals(DataDescriptionEntryContainerType.COMMUNICATION_SECTION, communicationSection.getContainerType());

		{
			final CommunicationDescriptionEntry communicationDescriptionEntry = communicationSection
					.getCommunicationDescriptionEntry("SOMECD");
			assertNotNull(communicationDescriptionEntry);
			assertEquals(CommunicationDescriptionEntry.CommunicationDescriptionEntryType.OUTPUT,
					communicationDescriptionEntry.getCommunicationDescriptionEntryType());

			final CommunicationDescriptionEntryOutput communicationDescriptionEntryOutput = (CommunicationDescriptionEntryOutput) communicationDescriptionEntry;

			{
				final DestinationCountClause destinationCountClause = communicationDescriptionEntryOutput
						.getDestinationCountClause();
				assertNotNull(destinationCountClause);
				assertNotNull(destinationCountClause.getDataDescCall());
			}

			{
				final TextLengthClause textLengthClause = communicationDescriptionEntryOutput.getTextLengthClause();
				assertNotNull(textLengthClause);
				assertNotNull(textLengthClause.getDataDescCall());
			}

			{
				final StatusKeyClause statusKeyClause = communicationDescriptionEntryOutput.getStatusKeyClause();
				assertNotNull(statusKeyClause);
				assertNotNull(statusKeyClause.getDataDescCall());
			}

			{
				final DestinationTableClause destinationTableClause = communicationDescriptionEntryOutput
						.getDestinationTableClause();
				assertNotNull(destinationTableClause);
				assertEquals(new BigDecimal(5), destinationTableClause.getOccursIntegerLiteral().getValue());
				assertEquals(2, destinationTableClause.getIndexCalls().size());
			}

			{
				final ErrorKeyClause errorKeyClause = communicationDescriptionEntryOutput.getErrorKeyClause();
				assertNotNull(errorKeyClause);
				assertNotNull(errorKeyClause.getDataDescCall());
			}

			{
				final SymbolicDestinationClause symbolicDestinationClause = communicationDescriptionEntryOutput
						.getSymbolicDestinationClause();
				assertNotNull(symbolicDestinationClause);
				assertNotNull(symbolicDestinationClause.getDataDescCall());
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