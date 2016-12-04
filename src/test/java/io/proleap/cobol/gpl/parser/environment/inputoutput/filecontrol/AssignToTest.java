package io.proleap.cobol.gpl.parser.environment.inputoutput.filecontrol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CompilationUnit;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.AssignClause;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
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
				"src/test/resources/io/proleap/cobol/gpl/parser/environment/inputoutput/filecontrol/AssignTo.cbl");
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
		assertEquals(AssignClause.Type.Disk, assignClause.getType());
		assertNull(assignClause.getToCall());
	}
}
