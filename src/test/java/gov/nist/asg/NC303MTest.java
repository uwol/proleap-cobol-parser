package gov.nist.asg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
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

public class NC303MTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/gov/nist/NC303M.CBL");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.FIXED);
		final CompilationUnit compilationUnit = program.getCompilationUnit("NC303M");
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
			assertNull(programUnit.getDataDivision());
		}

		{
			final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
			assertEquals(3, procedureDivision.getParagraphs().size());
			assertEquals(0, procedureDivision.getStatements().size());

			{
				final Paragraph paragraph = procedureDivision.getParagraphs().get(0);
				assertEquals("NC303M-CONTROL", paragraph.getParagraphName().getName());
				assertEquals(2, paragraph.getStatements().size());

				{
					final Statement statement = paragraph.getStatements().get(0);
					assertEquals(StatementTypeEnum.ALTER, statement.getStatementType());
				}

				{
					final Statement statement = paragraph.getStatements().get(1);
					assertEquals(StatementTypeEnum.STOP, statement.getStatementType());
				}
			}

			{
				final Paragraph paragraph = procedureDivision.getParagraphs().get(1);
				assertEquals("NC303M-GOTO", paragraph.getParagraphName().getName());
				assertEquals(0, paragraph.getStatements().size());
			}

			{
				final Paragraph paragraph = procedureDivision.getParagraphs().get(2);
				assertEquals("NC303M-GOTO-2", paragraph.getParagraphName().getName());
				assertEquals(0, paragraph.getStatements().size());
			}
		}
	}
}
