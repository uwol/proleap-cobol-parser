package io.proleap.cobol.asg.procedure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Section;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SectionRedefinedTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/SectionRedefined.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SectionRedefined");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(2, procedureDivision.getSections().size());
		assertEquals(0, procedureDivision.getStatements().size());

		final List<Section> sections = procedureDivision.getSections("SOME-SECTION");
		assertEquals(2, sections.size());

		{
			final Section section = sections.get(0);
			assertEquals(procedureDivision.getSection("SOME-SECTION"), section);

			{
				final StopStatement stopStatement = (StopStatement) section.getStatements().get(0);
				assertNotNull(stopStatement);
				assertEquals(StatementTypeEnum.STOP, stopStatement.getStatementType());
			}
		}

		{
			final Section section = sections.get(1);
			assertNotEquals(procedureDivision.getSection("SOME-SECTION"), section);

			{
				final DisplayStatement displayStatement = (DisplayStatement) section.getStatements().get(0);
				assertNotNull(displayStatement);
				assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
			}
		}
	}
}
