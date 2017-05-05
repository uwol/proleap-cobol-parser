package io.proleap.cobol.asg.identification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.asg.metamodel.identification.ProgramIdParagraph;
import io.proleap.cobol.asg.metamodel.identification.ProgramIdParagraph.Attribute;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ProgramIdLibraryTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/identification/ProgramIdLibrary.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ProgramIdLibrary");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final IdentificationDivision identificationDivision = programUnit.getIdentificationDivision();
		final ProgramIdParagraph programIdParagraph = identificationDivision.getProgramIdParagraph();

		assertNotNull(programIdParagraph);
		assertEquals(Attribute.LIBRARY, programIdParagraph.getAttribute());
	}
}
