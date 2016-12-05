package gov.nist.asg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.configuration.ConfigurationSection;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.ObjectComputerParagraph;
import io.proleap.cobol.asg.metamodel.environment.configuration.source.SourceComputerParagraph;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
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
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.FIXED);
		final CompilationUnit compilationUnit = program.getCompilationUnit("OBIC1A");
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

			{
				final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

				{
					final DataDescriptionEntry sortLink = workingStorageSection.findDataDescriptionEntry("SORT-LINK");
					assertEquals(new Integer(1), sortLink.getLevelNumber());
					assertEquals(DataDescriptionEntry.Type.Group, sortLink.getType());

					{
						final DataDescriptionEntryGroup sortLinkGroup = (DataDescriptionEntryGroup) sortLink;
						assertEquals("9", sortLinkGroup.getPictureClause().getPictureString());
						assertEquals(3, sortLinkGroup.getCalls().size());
					}
				}

				{
					final DataDescriptionEntry printLineValues = workingStorageSection
							.findDataDescriptionEntry("PRINT-LINE-VALUES");
					assertEquals(new Integer(1), printLineValues.getLevelNumber());
					assertEquals(DataDescriptionEntry.Type.Group, printLineValues.getType());

					final DataDescriptionEntryGroup printLineValuesGroup = (DataDescriptionEntryGroup) printLineValues;

					{
						final DataDescriptionEntry passOrFail = printLineValuesGroup
								.findDataDescriptionEntry("PASS-OR-FAIL");
						assertEquals(new Integer(2), passOrFail.getLevelNumber());
						assertEquals(DataDescriptionEntry.Type.Group, passOrFail.getType());

						{
							final DataDescriptionEntryGroup passOrFailGroup = (DataDescriptionEntryGroup) passOrFail;
							assertEquals("X(5)", passOrFailGroup.getPictureClause().getPictureString());
							assertEquals(1, passOrFailGroup.getCalls().size());
						}
					}

					{
						final DataDescriptionEntry rCount = printLineValuesGroup.findDataDescriptionEntry("R-COUNT");
						assertEquals(new Integer(2), rCount.getLevelNumber());
						assertEquals(DataDescriptionEntry.Type.Group, rCount.getType());

						{
							final DataDescriptionEntryGroup rCountGroup = (DataDescriptionEntryGroup) rCount;
							assertEquals("99", rCountGroup.getPictureClause().getPictureString());
							assertEquals(1, rCountGroup.getCalls().size());
						}
					}

					{
						final DataDescriptionEntry featureTested = printLineValuesGroup
								.findDataDescriptionEntry("FEATURE-TESTED");
						assertEquals(new Integer(2), featureTested.getLevelNumber());
						assertEquals(DataDescriptionEntry.Type.Group, featureTested.getType());

						{
							final DataDescriptionEntryGroup featureTestedGroup = (DataDescriptionEntryGroup) featureTested;
							assertEquals("X(20)", featureTestedGroup.getPictureClause().getPictureString());
							assertEquals(1, featureTestedGroup.getCalls().size());
						}
					}

					{
						final DataDescriptionEntry computedSortKey = printLineValuesGroup
								.findDataDescriptionEntry("COMPUTED-SORT-KEY");
						assertEquals(new Integer(2), computedSortKey.getLevelNumber());
						assertEquals(DataDescriptionEntry.Type.Group, computedSortKey.getType());

						{
							final DataDescriptionEntryGroup computedSortKeyGroup = (DataDescriptionEntryGroup) computedSortKey;
							assertEquals("X(20)", computedSortKeyGroup.getPictureClause().getPictureString());
							assertEquals(1, computedSortKeyGroup.getCalls().size());
						}
					}

					{
						final DataDescriptionEntry correctSortKey = printLineValuesGroup
								.findDataDescriptionEntry("CORRECT-SORT-KEY");
						assertEquals(new Integer(2), correctSortKey.getLevelNumber());
						assertEquals(DataDescriptionEntry.Type.Group, correctSortKey.getType());

						{
							final DataDescriptionEntryGroup correctSortKeyGroup = (DataDescriptionEntryGroup) correctSortKey;
							assertEquals("X(20)", correctSortKeyGroup.getPictureClause().getPictureString());
							assertEquals(1, correctSortKeyGroup.getCalls().size());
						}
					}

					{
						final DataDescriptionEntry paragraphName = printLineValuesGroup
								.findDataDescriptionEntry("PARAGRAPH-NAME");
						assertEquals(new Integer(2), paragraphName.getLevelNumber());
						assertEquals(DataDescriptionEntry.Type.Group, paragraphName.getType());

						{
							final DataDescriptionEntryGroup paragraphNameGroup = (DataDescriptionEntryGroup) paragraphName;
							assertEquals("X(12)", paragraphNameGroup.getPictureClause().getPictureString());
							assertEquals(1, paragraphNameGroup.getCalls().size());
						}
					}
				}

				{
					final DataDescriptionEntry printFlag = workingStorageSection.findDataDescriptionEntry("PRINT-FLAG");
					assertEquals(new Integer(1), printFlag.getLevelNumber());
					assertEquals(DataDescriptionEntry.Type.Group, printFlag.getType());

					{
						final DataDescriptionEntryGroup printFlagGroup = (DataDescriptionEntryGroup) printFlag;
						assertEquals("9", printFlagGroup.getPictureClause().getPictureString());
						assertEquals(4, printFlagGroup.getCalls().size());
					}
				}
			}
		}

		{
			final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
			assertEquals(3, procedureDivision.getParagraphs().size());
			assertEquals(0, procedureDivision.getStatements().size());

			{
				final Paragraph paragraph = procedureDivision.getParagraph("CALL-IC219");
				assertNotNull(paragraph);
				assertEquals(2, paragraph.getStatements().size());

				{
					final Statement statement = paragraph.getStatements().get(0);
					assertEquals(StatementTypeEnum.Move, statement.getStatementType());
				}

				{
					final Statement statement = paragraph.getStatements().get(1);
					assertEquals(StatementTypeEnum.Call, statement.getStatementType());
				}
			}

			{
				final Paragraph paragraph = procedureDivision.getParagraph("CALL-FAIL");
				assertNotNull(paragraph);
				assertEquals(10, paragraph.getStatements().size());

				{
					final Statement statement = paragraph.getStatements().get(0);
					assertEquals(StatementTypeEnum.Move, statement.getStatementType());
				}

				{
					final Statement statement = paragraph.getStatements().get(1);
					assertEquals(StatementTypeEnum.Move, statement.getStatementType());
				}

				{
					final Statement statement = paragraph.getStatements().get(2);
					assertEquals(StatementTypeEnum.Move, statement.getStatementType());
				}

				{
					final Statement statement = paragraph.getStatements().get(3);
					assertEquals(StatementTypeEnum.Move, statement.getStatementType());
				}

				{
					final Statement statement = paragraph.getStatements().get(4);
					assertEquals(StatementTypeEnum.Move, statement.getStatementType());
				}

				{
					final Statement statement = paragraph.getStatements().get(5);
					assertEquals(StatementTypeEnum.Move, statement.getStatementType());
				}

				{
					final Statement statement = paragraph.getStatements().get(6);
					assertEquals(StatementTypeEnum.Move, statement.getStatementType());
				}

				{
					final Statement statement = paragraph.getStatements().get(7);
					assertEquals(StatementTypeEnum.Call, statement.getStatementType());
				}

				{
					final Statement statement = paragraph.getStatements().get(8);
					assertEquals(StatementTypeEnum.Move, statement.getStatementType());
				}

				{
					final Statement statement = paragraph.getStatements().get(9);
					assertEquals(StatementTypeEnum.Call, statement.getStatementType());
				}
			}

			{
				final Paragraph paragraph = procedureDivision.getParagraph("END-OF-PROGRAM");
				assertNotNull(paragraph);
				assertEquals(1, paragraph.getStatements().size());

				{
					final Statement statement = paragraph.getStatements().get(0);
					assertEquals(StatementTypeEnum.Exit, statement.getStatementType());
				}
			}
		}
	}
}
