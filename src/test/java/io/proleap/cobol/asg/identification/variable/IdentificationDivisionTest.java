package io.proleap.cobol.asg.identification.variable;

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
				"src/test/resources/io/proleap/cobol/asg/identification/variable/IdentificationDivision.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.VARIABLE);

		final CompilationUnit compilationUnit = program.getCompilationUnit("IdentificationDivision");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final IdentificationDivision identificationDivision = programUnit.getIdentificationDivision();

		assertNotNull(identificationDivision.getProgramIdParagraph());

		assertNotNull(identificationDivision.getAuthorParagraph());
		assertEquals("SOMEAUTH.", identificationDivision.getAuthorParagraph().getAuthor());

		assertNotNull(identificationDivision.getInstallationParagraph());
		assertEquals("SOMEINSTALL.", identificationDivision.getInstallationParagraph().getInstallation());

		assertNotNull(identificationDivision.getDateCompiledParagraph());
		assertEquals("SOMECOMPILED.", identificationDivision.getDateCompiledParagraph().getDateCompiled());

		assertNotNull(identificationDivision.getDateWrittenParagraph());
		assertEquals("SOMEWRITTEN.", identificationDivision.getDateWrittenParagraph().getDateWritten());

		assertNotNull(identificationDivision.getRemarksParagraph());
		assertEquals("SOMEREM.", identificationDivision.getRemarksParagraph().getRemarks());

		assertNotNull(identificationDivision.getSecurityParagraph());
		assertEquals("SOMESEC.", identificationDivision.getSecurityParagraph().getSecurity());
	}
}
