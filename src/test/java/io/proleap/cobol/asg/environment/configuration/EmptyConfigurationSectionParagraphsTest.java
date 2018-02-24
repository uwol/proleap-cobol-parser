package io.proleap.cobol.asg.environment.configuration;

import static org.junit.Assert.assertNotNull;

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
import io.proleap.cobol.asg.metamodel.environment.specialnames.SpecialNamesParagraph;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class EmptyConfigurationSectionParagraphsTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/environment/configuration/EmptyConfigurationSectionParagraphs.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("EmptyConfigurationSectionParagraphs");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();

		{
			final ConfigurationSection configurationSection = environmentDivision.getConfigurationSection();

			final ObjectComputerParagraph objectComputerParagraph = configurationSection.getObjectComputerParagraph();
			assertNotNull(objectComputerParagraph);

			final SourceComputerParagraph sourceComputerParagraph = configurationSection.getSourceComputerParagraph();
			assertNotNull(sourceComputerParagraph);
		}

		final SpecialNamesParagraph specialNamesParagraph = environmentDivision.getSpecialNamesParagraph();
		assertNotNull(specialNamesParagraph);
	}
}
