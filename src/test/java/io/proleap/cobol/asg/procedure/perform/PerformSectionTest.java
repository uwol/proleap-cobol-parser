package io.proleap.cobol.asg.procedure.perform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Section;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class PerformSectionTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/perform/PerformSection.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("PerformSection");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getStatements().size());
		assertEquals(1, procedureDivision.getSections().size());
		assertEquals(0, procedureDivision.getParagraphs().size());

		{
			final Section section = procedureDivision.getSection("INIT");
			assertNotNull(section);
			assertEquals(2, section.getStatements().size());
			assertEquals(1, section.getCalls().size());

			{
				final Statement statement = section.getStatements().get(0);
				assertEquals(StatementTypeEnum.PERFORM, statement.getStatementType());
			}

			{
				final Statement statement = section.getStatements().get(1);
				assertEquals(StatementTypeEnum.STOP, statement.getStatementType());
			}
		}
	}
}
