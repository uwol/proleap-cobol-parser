package io.proleap.cobol.asg.procedure.perform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

public class PerformProcedureThroughTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/perform/PerformProcedureThrough.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("PERFORMPROCEDURETHROUGH");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(5, procedureDivision.getParagraphs().size());
		assertEquals(7, procedureDivision.getStatements().size());

		{
			final Paragraph paragraphProc1 = procedureDivision.getParagraph("PROC1");
			assertNotNull(paragraphProc1);
			assertTrue(paragraphProc1.getCalls().isEmpty());
		}

		{
			final Paragraph paragraphProc2 = procedureDivision.getParagraph("PROC2");
			assertNotNull(paragraphProc2);
			assertFalse(paragraphProc2.getCalls().isEmpty());
			assertEquals(1, paragraphProc2.getCalls().size());
		}

		{
			final Paragraph paragraphProc3 = procedureDivision.getParagraph("PROC3");
			assertNotNull(paragraphProc3);
			assertFalse(paragraphProc3.getCalls().isEmpty());
			assertEquals(2, paragraphProc3.getCalls().size());
		}

		{
			final Paragraph paragraphProc4 = procedureDivision.getParagraph("PROC4");
			assertNotNull(paragraphProc4);
			assertFalse(paragraphProc4.getCalls().isEmpty());
			assertEquals(1, paragraphProc4.getCalls().size());
		}
	}
}
