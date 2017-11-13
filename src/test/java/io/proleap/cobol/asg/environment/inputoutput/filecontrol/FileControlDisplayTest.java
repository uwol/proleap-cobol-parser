package io.proleap.cobol.asg.environment.inputoutput.filecontrol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.AssignClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.SelectClause;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class FileControlDisplayTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/environment/inputoutput/filecontrol/FileControlDisplay.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("FileControlDisplay");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();

		{
			final FileControlEntry fileControlEntry = environmentDivision.getInputOutputSection()
					.getFileControlParagraph().getFileControlEntry("standard-input");
			assertNotNull(fileControlEntry);

			final SelectClause selectClause = fileControlEntry.getSelectClause();
			assertNotNull(selectClause);
			assertEquals("standard-input", selectClause.getName());

			{
				final AssignClause assignClause = fileControlEntry.getAssignClause();
				assertNotNull(assignClause);
				assertEquals(AssignClause.AssignClauseType.KEYBOARD, assignClause.getAssignClauseType());
			}
		}

		{
			final FileControlEntry fileControlEntry = environmentDivision.getInputOutputSection()
					.getFileControlParagraph().getFileControlEntry("standard-output");
			assertNotNull(fileControlEntry);

			final SelectClause selectClause = fileControlEntry.getSelectClause();
			assertNotNull(selectClause);
			assertEquals("standard-output", selectClause.getName());

			{
				final AssignClause assignClause = fileControlEntry.getAssignClause();
				assertNotNull(assignClause);
				assertEquals(AssignClause.AssignClauseType.DISPLAY, assignClause.getAssignClauseType());
			}
		}
	}
}
