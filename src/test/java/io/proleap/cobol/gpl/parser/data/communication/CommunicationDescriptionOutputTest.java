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
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntryOutput;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.parser.metamodel.data.communication.DestinationCountClause;
import io.proleap.cobol.parser.metamodel.data.communication.DestinationTableClause;
import io.proleap.cobol.parser.metamodel.data.communication.ErrorKeyClause;
import io.proleap.cobol.parser.metamodel.data.communication.StatusKeyClause;
import io.proleap.cobol.parser.metamodel.data.communication.SymbolicDestinationClause;
import io.proleap.cobol.parser.metamodel.data.communication.TextLengthClause;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CommunicationDescriptionOutputTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/data/communication/CommunicationDescriptionOutput.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("CommunicationDescriptionOutput");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final CommunicationSection communicationSection = dataDivision.getCommunicationSection();

		final CommunicationDescriptionEntry communicationDescriptionEntry = communicationSection
				.getCommunicationDescriptionEntry("SOMECD");
		assertNotNull(communicationDescriptionEntry);
		assertEquals(CommunicationDescriptionEntry.Type.Output, communicationDescriptionEntry.getType());

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
			assertEquals(new Integer(5), destinationTableClause.getOccursIntegerLiteral().getValue());
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
			assertEquals(new Integer(1), dataDescriptionEntryWsPerson.getLevelNumber());
			assertNull(dataDescriptionEntryWsPerson.getParentDataDescriptionEntryGroup());

			final DataDescriptionEntry dataDescriptionEntryWsPersonId = communicationSection
					.getDataDescriptionEntry("WS-PERSON-ID");
			assertNotNull(dataDescriptionEntryWsPersonId);
			assertEquals("WS-PERSON-ID", dataDescriptionEntryWsPersonId.getName());
			assertEquals(new Integer(5), dataDescriptionEntryWsPersonId.getLevelNumber());
			assertEquals(dataDescriptionEntryWsPerson,
					dataDescriptionEntryWsPersonId.getParentDataDescriptionEntryGroup());
		}
	}
}