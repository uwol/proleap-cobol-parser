package gov.nist.asg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry.DataDescriptionEntryType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.configuration.ConfigurationSection;
import io.proleap.cobol.asg.metamodel.environment.configuration.object.ObjectComputerParagraph;
import io.proleap.cobol.asg.metamodel.environment.configuration.source.SourceComputerParagraph;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Section;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class OBIC1ATest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/gov/nist/OBIC1A.CBL");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.FIXED);
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
					final DataDescriptionEntry sortLink = workingStorageSection.getDataDescriptionEntry("SORT-LINK");
					assertEquals(Integer.valueOf(1), sortLink.getLevelNumber());
					assertEquals(DataDescriptionEntryType.GROUP, sortLink.getDataDescriptionEntryType());

					{
						final DataDescriptionEntryGroup sortLinkGroup = (DataDescriptionEntryGroup) sortLink;
						assertEquals("9", sortLinkGroup.getPictureClause().getPictureString());
						assertEquals(3, sortLinkGroup.getCalls().size());
					}
				}

				{
					final DataDescriptionEntry printLineValues = workingStorageSection
							.getDataDescriptionEntry("PRINT-LINE-VALUES");
					assertEquals(Integer.valueOf(1), printLineValues.getLevelNumber());
					assertEquals(DataDescriptionEntryType.GROUP, printLineValues.getDataDescriptionEntryType());

					final DataDescriptionEntryGroup printLineValuesGroup = (DataDescriptionEntryGroup) printLineValues;

					{
						final DataDescriptionEntry passOrFail = printLineValuesGroup
								.getDataDescriptionEntry("PASS-OR-FAIL");
						assertEquals(Integer.valueOf(2), passOrFail.getLevelNumber());
						assertEquals(DataDescriptionEntryType.GROUP, passOrFail.getDataDescriptionEntryType());

						{
							final DataDescriptionEntryGroup passOrFailGroup = (DataDescriptionEntryGroup) passOrFail;
							assertEquals("X(5)", passOrFailGroup.getPictureClause().getPictureString());
							assertEquals(1, passOrFailGroup.getCalls().size());
						}
					}

					{
						final DataDescriptionEntry rCount = printLineValuesGroup.getDataDescriptionEntry("R-COUNT");
						assertEquals(Integer.valueOf(2), rCount.getLevelNumber());
						assertEquals(DataDescriptionEntryType.GROUP, rCount.getDataDescriptionEntryType());

						{
							final DataDescriptionEntryGroup rCountGroup = (DataDescriptionEntryGroup) rCount;
							assertEquals("99", rCountGroup.getPictureClause().getPictureString());
							assertEquals(1, rCountGroup.getCalls().size());
						}
					}

					{
						final DataDescriptionEntry featureTested = printLineValuesGroup
								.getDataDescriptionEntry("FEATURE-TESTED");
						assertEquals(Integer.valueOf(2), featureTested.getLevelNumber());
						assertEquals(DataDescriptionEntryType.GROUP, featureTested.getDataDescriptionEntryType());

						{
							final DataDescriptionEntryGroup featureTestedGroup = (DataDescriptionEntryGroup) featureTested;
							assertEquals("X(20)", featureTestedGroup.getPictureClause().getPictureString());
							assertEquals(1, featureTestedGroup.getCalls().size());
						}
					}

					{
						final DataDescriptionEntry computedSortKey = printLineValuesGroup
								.getDataDescriptionEntry("COMPUTED-SORT-KEY");
						assertEquals(Integer.valueOf(2), computedSortKey.getLevelNumber());
						assertEquals(DataDescriptionEntryType.GROUP, computedSortKey.getDataDescriptionEntryType());

						{
							final DataDescriptionEntryGroup computedSortKeyGroup = (DataDescriptionEntryGroup) computedSortKey;
							assertEquals("X(20)", computedSortKeyGroup.getPictureClause().getPictureString());
							assertEquals(1, computedSortKeyGroup.getCalls().size());
						}
					}

					{
						final DataDescriptionEntry correctSortKey = printLineValuesGroup
								.getDataDescriptionEntry("CORRECT-SORT-KEY");
						assertEquals(Integer.valueOf(2), correctSortKey.getLevelNumber());
						assertEquals(DataDescriptionEntryType.GROUP, correctSortKey.getDataDescriptionEntryType());

						{
							final DataDescriptionEntryGroup correctSortKeyGroup = (DataDescriptionEntryGroup) correctSortKey;
							assertEquals("X(20)", correctSortKeyGroup.getPictureClause().getPictureString());
							assertEquals(1, correctSortKeyGroup.getCalls().size());
						}
					}

					{
						final DataDescriptionEntry paragraphName = printLineValuesGroup
								.getDataDescriptionEntry("PARAGRAPH-NAME");
						assertEquals(Integer.valueOf(2), paragraphName.getLevelNumber());
						assertEquals(DataDescriptionEntryType.GROUP, paragraphName.getDataDescriptionEntryType());

						{
							final DataDescriptionEntryGroup paragraphNameGroup = (DataDescriptionEntryGroup) paragraphName;
							assertEquals("X(12)", paragraphNameGroup.getPictureClause().getPictureString());
							assertEquals(1, paragraphNameGroup.getCalls().size());
						}
					}
				}

				{
					final DataDescriptionEntry printFlag = workingStorageSection.getDataDescriptionEntry("PRINT-FLAG");
					assertEquals(Integer.valueOf(1), printFlag.getLevelNumber());
					assertEquals(DataDescriptionEntryType.GROUP, printFlag.getDataDescriptionEntryType());

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
			assertEquals(1, procedureDivision.getSections().size());
			assertEquals(0, procedureDivision.getRootParagraphs().size());
			assertEquals(0, procedureDivision.getStatements().size());

			{
				final Section section = procedureDivision.getSections().get(0);
				assertEquals("SECT-IC218-0001", section.getName());
				assertEquals(0, section.getStatements().size());
				assertEquals(3, section.getParagraphs().size());

				{
					final Paragraph paragraph = section.getParagraph("CALL-IC219");
					assertNotNull(paragraph);
					assertEquals(2, paragraph.getStatements().size());

					{
						final Statement statement = paragraph.getStatements().get(0);
						assertEquals(StatementTypeEnum.MOVE, statement.getStatementType());
					}

					{
						final Statement statement = paragraph.getStatements().get(1);
						assertEquals(StatementTypeEnum.CALL, statement.getStatementType());
					}
				}

				{
					final Paragraph paragraph = section.getParagraph("CALL-FAIL");
					assertNotNull(paragraph);
					assertEquals(10, paragraph.getStatements().size());

					{
						final Statement statement = paragraph.getStatements().get(0);
						assertEquals(StatementTypeEnum.MOVE, statement.getStatementType());
					}

					{
						final Statement statement = paragraph.getStatements().get(1);
						assertEquals(StatementTypeEnum.MOVE, statement.getStatementType());
					}

					{
						final Statement statement = paragraph.getStatements().get(2);
						assertEquals(StatementTypeEnum.MOVE, statement.getStatementType());
					}

					{
						final Statement statement = paragraph.getStatements().get(3);
						assertEquals(StatementTypeEnum.MOVE, statement.getStatementType());
					}

					{
						final Statement statement = paragraph.getStatements().get(4);
						assertEquals(StatementTypeEnum.MOVE, statement.getStatementType());
					}

					{
						final Statement statement = paragraph.getStatements().get(5);
						assertEquals(StatementTypeEnum.MOVE, statement.getStatementType());
					}

					{
						final Statement statement = paragraph.getStatements().get(6);
						assertEquals(StatementTypeEnum.MOVE, statement.getStatementType());
					}

					{
						final Statement statement = paragraph.getStatements().get(7);
						assertEquals(StatementTypeEnum.CALL, statement.getStatementType());
					}

					{
						final Statement statement = paragraph.getStatements().get(8);
						assertEquals(StatementTypeEnum.MOVE, statement.getStatementType());
					}

					{
						final Statement statement = paragraph.getStatements().get(9);
						assertEquals(StatementTypeEnum.CALL, statement.getStatementType());
					}
				}

				{
					final Paragraph paragraph = section.getParagraph("END-OF-PROGRAM");
					assertNotNull(paragraph);
					assertEquals(1, paragraph.getStatements().size());

					{
						final Statement statement = paragraph.getStatements().get(0);
						assertEquals(StatementTypeEnum.EXIT, statement.getStatementType());
					}
				}
			}
		}
	}
}
