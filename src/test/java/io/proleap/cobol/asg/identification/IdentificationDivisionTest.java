package io.proleap.cobol.asg.identification;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class IdentificationDivisionTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/identification/IdentificationDivision.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.VARIABLE);

		final CompilationUnit compilationUnit = program.getCompilationUnit("IdentificationDivision");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final IdentificationDivision identificationDivision = programUnit.getIdentificationDivision();

		assertNotNull(identificationDivision.getProgramIdParagraph());

		assertNotNull(identificationDivision.getAuthorParagraph());
		assertNotNull(identificationDivision.getInstallationParagraph());
		assertNotNull(identificationDivision.getDateCompiledParagraph());
		assertNotNull(identificationDivision.getDateWrittenParagraph());
		assertNotNull(identificationDivision.getRemarksParagraph());
		assertNotNull(identificationDivision.getSecurityParagraph());
	}
}
