package gov.nist.asg;

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
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry.DataDescriptionEntryType;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.configuration.ConfigurationSection;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.ObjectComputerParagraph;
import io.proleap.cobol.asg.metamodel.environment.configuration.source.SourceComputerParagraph;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CM303MTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/gov/nist/CM303M.CBL");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.FIXED);
		final CompilationUnit compilationUnit = program.getCompilationUnit("CM303M");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();

		{
			final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();

			{
				final ConfigurationSection configurationSection = environmentDivision.getConfigurationSection();
				final SourceComputerParagraph sourceComputerParagraph = configurationSection
						.getSourceComputerParagraph();
				assertEquals("XXXXX082", sourceComputerParagraph.getName());

				final ObjectComputerParagraph objectComputerParagraph = configurationSection
						.getObjectComputerParagraph();
				assertEquals("XXXXX083", objectComputerParagraph.getName());
			}
		}

		{
			final DataDivision dataDivision = programUnit.getDataDivision();
			assertNull(dataDivision.getWorkingStorageSection());
			assertNotNull(dataDivision.getFileSection());
			assertNotNull(dataDivision.getCommunicationSection());

			{
				final CommunicationSection communicationSection = dataDivision.getCommunicationSection();
				assertEquals(1, communicationSection.getCommunicationDescriptionEntries().size());

				{
					final CommunicationDescriptionEntry communicationDescriptionEntry = communicationSection
							.getCommunicationDescriptionEntry("COMMNAME");
					assertNotNull(communicationDescriptionEntry);
					assertEquals(CommunicationDescriptionEntry.CommunicationDescriptionEntryType.INPUT,
							communicationDescriptionEntry.getCommunicationDescriptionEntryType());
				}

				{
					assertEquals(2, communicationSection.getDataDescriptionEntries().size());

					{
						final DataDescriptionEntry dataDescriptionEntry = communicationSection
								.getDataDescriptionEntry("CREC");
						assertNotNull(dataDescriptionEntry);
						assertEquals(DataDescriptionEntryType.GROUP,
								dataDescriptionEntry.getDataDescriptionEntryType());
						assertEquals(Integer.valueOf(1), dataDescriptionEntry.getLevelNumber());
					}

					{
						final DataDescriptionEntry dataDescriptionEntry = communicationSection
								.getDataDescriptionEntry("CNAME1");
						assertNotNull(dataDescriptionEntry);
						assertEquals(DataDescriptionEntryType.GROUP,
								dataDescriptionEntry.getDataDescriptionEntryType());
						assertEquals(Integer.valueOf(3), dataDescriptionEntry.getLevelNumber());
					}
				}
			}
		}

		{
			final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
			assertEquals(3, procedureDivision.getParagraphs().size());
			assertEquals(0, procedureDivision.getStatements().size());

			{
				final Paragraph paragraph = procedureDivision.getParagraphs().get(0);
				assertEquals("CM303M-CONTROL", paragraph.getParagraphName().getName());
				assertEquals(2, paragraph.getStatements().size());

				{
					final Statement statement = paragraph.getStatements().get(0);
					assertEquals(StatementTypeEnum.PERFORM, statement.getStatementType());
				}

				{
					final Statement statement = paragraph.getStatements().get(1);
					assertEquals(StatementTypeEnum.STOP, statement.getStatementType());
				}
			}

			{
				final Paragraph paragraph = procedureDivision.getParagraphs().get(1);
				assertEquals("CM303M-DISABLE", paragraph.getParagraphName().getName());
				assertEquals(1, paragraph.getStatements().size());

				{
					final Statement statement = paragraph.getStatements().get(0);
					assertEquals(StatementTypeEnum.DISABLE, statement.getStatementType());
				}
			}

			{
				final Paragraph paragraph = procedureDivision.getParagraphs().get(2);
				assertEquals("CM303M-ENABLE", paragraph.getParagraphName().getName());
				assertEquals(1, paragraph.getStatements().size());

				{
					final Statement statement = paragraph.getStatements().get(0);
					assertEquals(StatementTypeEnum.ENABLE, statement.getStatementType());
				}
			}
		}
	}
}
