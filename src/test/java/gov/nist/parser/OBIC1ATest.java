package gov.nist.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.parser.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.environment.configuration.ConfigurationSection;
import io.proleap.cobol.parser.metamodel.environment.configuration.object.ObjectComputerParagraph;
import io.proleap.cobol.parser.metamodel.environment.configuration.source.SourceComputerParagraph;
import io.proleap.cobol.parser.metamodel.procedure.Paragraph;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class OBIC1ATest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/gov/nist/OBIC1A.CBL");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.FIXED);

		final CopyBook copyBook = program.getCopyBook("OBIC1A");
		final ProgramUnit programUnit = copyBook.getProgramUnit();

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

			{
				final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

				{
					final DataDescriptionEntry sortLink = workingStorageSection.getDataDescriptionEntry("SORT-LINK");
					assertEquals(new Integer(1), sortLink.getLevelNumber());
					assertEquals(DataDescriptionEntry.Type.Group, sortLink.getType());

					final DataDescriptionEntryGroup sortLinkGroup = (DataDescriptionEntryGroup) sortLink;
					assertEquals("9", sortLinkGroup.getPictureClause().getPictureString());
					assertEquals(3, sortLinkGroup.getCalls().size());
				}

				{
					final DataDescriptionEntry printLineValues = workingStorageSection
							.getDataDescriptionEntry("PRINT-LINE-VALUES");
					assertEquals(new Integer(1), printLineValues.getLevelNumber());
					assertEquals(DataDescriptionEntry.Type.Group, printLineValues.getType());

					final DataDescriptionEntryGroup printLineValuesGroup = (DataDescriptionEntryGroup) printLineValues;

					{
						final DataDescriptionEntry passOrFail = printLineValuesGroup
								.getDataDescriptionEntry("PASS-OR-FAIL");
						assertEquals(new Integer(2), passOrFail.getLevelNumber());
						assertEquals(DataDescriptionEntry.Type.Group, passOrFail.getType());

						final DataDescriptionEntryGroup passOrFailGroup = (DataDescriptionEntryGroup) passOrFail;
						assertEquals("X(5)", passOrFailGroup.getPictureClause().getPictureString());
						assertEquals(1, passOrFailGroup.getCalls().size());
					}

					{
						final DataDescriptionEntry rCount = printLineValuesGroup.getDataDescriptionEntry("R-COUNT");
						assertEquals(new Integer(2), rCount.getLevelNumber());
						assertEquals(DataDescriptionEntry.Type.Group, rCount.getType());

						final DataDescriptionEntryGroup rCountGroup = (DataDescriptionEntryGroup) rCount;
						assertEquals("99", rCountGroup.getPictureClause().getPictureString());
						assertEquals(1, rCountGroup.getCalls().size());
					}

					{
						final DataDescriptionEntry featureTested = printLineValuesGroup
								.getDataDescriptionEntry("FEATURE-TESTED");
						assertEquals(new Integer(2), featureTested.getLevelNumber());
						assertEquals(DataDescriptionEntry.Type.Group, featureTested.getType());

						final DataDescriptionEntryGroup featureTestedGroup = (DataDescriptionEntryGroup) featureTested;
						assertEquals("X(20)", featureTestedGroup.getPictureClause().getPictureString());
						assertEquals(1, featureTestedGroup.getCalls().size());
					}

					{
						final DataDescriptionEntry computedSortKey = printLineValuesGroup
								.getDataDescriptionEntry("COMPUTED-SORT-KEY");
						assertEquals(new Integer(2), computedSortKey.getLevelNumber());
						assertEquals(DataDescriptionEntry.Type.Group, computedSortKey.getType());

						final DataDescriptionEntryGroup computedSortKeyGroup = (DataDescriptionEntryGroup) computedSortKey;
						assertEquals("X(20)", computedSortKeyGroup.getPictureClause().getPictureString());
						assertEquals(1, computedSortKeyGroup.getCalls().size());
					}

					{
						final DataDescriptionEntry correctSortKey = printLineValuesGroup
								.getDataDescriptionEntry("CORRECT-SORT-KEY");
						assertEquals(new Integer(2), correctSortKey.getLevelNumber());
						assertEquals(DataDescriptionEntry.Type.Group, correctSortKey.getType());

						final DataDescriptionEntryGroup correctSortKeyGroup = (DataDescriptionEntryGroup) correctSortKey;
						assertEquals("X(20)", correctSortKeyGroup.getPictureClause().getPictureString());
						assertEquals(1, correctSortKeyGroup.getCalls().size());
					}

					{
						final DataDescriptionEntry paragraphName = printLineValuesGroup
								.getDataDescriptionEntry("PARAGRAPH-NAME");
						assertEquals(new Integer(2), paragraphName.getLevelNumber());
						assertEquals(DataDescriptionEntry.Type.Group, paragraphName.getType());

						final DataDescriptionEntryGroup paragraphNameGroup = (DataDescriptionEntryGroup) paragraphName;
						assertEquals("X(12)", paragraphNameGroup.getPictureClause().getPictureString());
						assertEquals(1, paragraphNameGroup.getCalls().size());
					}
				}

				{
					final DataDescriptionEntry printFlag = workingStorageSection.getDataDescriptionEntry("PRINT-FLAG");
					assertEquals(new Integer(1), printFlag.getLevelNumber());
					assertEquals(DataDescriptionEntry.Type.Group, printFlag.getType());

					final DataDescriptionEntryGroup printFlagGroup = (DataDescriptionEntryGroup) printFlag;
					assertEquals("9", printFlagGroup.getPictureClause().getPictureString());
					assertEquals(4, printFlagGroup.getCalls().size());
				}
			}
		}

		{
			final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
			assertEquals(3, procedureDivision.getParagraphs().size());

			{
				final Paragraph callIc219 = procedureDivision.getParagraph("CALL-IC219");
				assertNotNull(callIc219);
			}

			{
				final Paragraph callFail = procedureDivision.getParagraph("CALL-FAIL");
				assertNotNull(callFail);
			}

			{
				final Paragraph endOfProgram = procedureDivision.getParagraph("END-OF-PROGRAM");
				assertNotNull(endOfProgram);
			}
		}
	}
}
