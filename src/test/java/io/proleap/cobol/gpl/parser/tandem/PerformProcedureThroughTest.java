package io.proleap.cobol.gpl.parser.tandem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Paragraph;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
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
				"src/test/resources/io/proleap/cobol/gpl/parser/tandem/PerformProcedureThrough.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("PERFORMPROCEDURETHROUGH");
		final List<ProgramUnit> programUnits = copyBook.getProgramUnits();
		final ProgramUnit programUnit = programUnits.get(0);
		final Paragraph paragraphProc1 = programUnit.getParagraph("PROC1");

		assertNotNull(paragraphProc1);
		assertTrue(paragraphProc1.getProcedureCalls().isEmpty());

		final Paragraph paragraphProc2 = programUnit.getParagraph("PROC2");

		assertNotNull(paragraphProc2);
		assertFalse(paragraphProc2.getProcedureCalls().isEmpty());
		assertEquals(1, paragraphProc2.getProcedureCalls().size());

		final Paragraph paragraphProc3 = programUnit.getParagraph("PROC3");

		assertNotNull(paragraphProc3);
		assertFalse(paragraphProc3.getProcedureCalls().isEmpty());
		assertEquals(2, paragraphProc3.getProcedureCalls().size());

		final Paragraph paragraphProc4 = programUnit.getParagraph("PROC4");

		assertNotNull(paragraphProc4);
		assertFalse(paragraphProc4.getProcedureCalls().isEmpty());
		assertEquals(1, paragraphProc4.getProcedureCalls().size());
	}
}
