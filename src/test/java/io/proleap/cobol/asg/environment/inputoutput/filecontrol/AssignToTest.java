package io.proleap.cobol.asg.environment.inputoutput.filecontrol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.AssignClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class AssignToTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/environment/inputoutput/filecontrol/AssignTo.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("AssignTo");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();

		final FileControlEntry fileControlEntry = environmentDivision.getInputOutputSection().getFileControlParagraph()
				.getFileControlEntry("TEACHER");
		assertNotNull(fileControlEntry);

		final AssignClause assignClause = fileControlEntry.getAssignClause();
		assertNotNull(assignClause);
		assertEquals(AssignClause.Type.DISK, assignClause.getType());
		assertNull(assignClause.getToCall());
	}
}
