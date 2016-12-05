package io.proleap.cobol.asg.procedure.perform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class PerformProcedureTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/perform/PerformProcedure.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("PERFORMPROCEDURE");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(6, procedureDivision.getStatements().size());

		{
			final Paragraph paragraphProc1 = procedureDivision.getParagraph("PROC1");
			assertNotNull(paragraphProc1);
			assertFalse(paragraphProc1.getCalls().isEmpty());
			assertEquals(2, paragraphProc1.getCalls().size());
		}

		{
			final Paragraph paragraphProc2 = procedureDivision.getParagraph("PROC2");
			assertNotNull(paragraphProc2);
			assertFalse(paragraphProc2.getCalls().isEmpty());
			assertEquals(1, paragraphProc2.getCalls().size());
		}
	}
}
