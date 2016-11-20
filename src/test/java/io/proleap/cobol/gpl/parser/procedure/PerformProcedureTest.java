package io.proleap.cobol.gpl.parser.procedure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.Paragraph;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
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
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/PerformProcedure.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("PERFORMPROCEDURE");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final Paragraph paragraphProc1 = procedureDivision.getParagraph("PROC1");

		assertNotNull(paragraphProc1);
		assertFalse(paragraphProc1.getCalls().isEmpty());
		assertEquals(2, paragraphProc1.getCalls().size());

		final Paragraph paragraphProc2 = procedureDivision.getParagraph("PROC2");

		assertNotNull(paragraphProc2);
		assertFalse(paragraphProc2.getCalls().isEmpty());
		assertEquals(1, paragraphProc2.getCalls().size());
	}
}
