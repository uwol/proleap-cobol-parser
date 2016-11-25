package io.proleap.cobol.gpl.parser.procedure.ifstmt;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.ifstmt.Else;
import io.proleap.cobol.parser.metamodel.procedure.ifstmt.IfStatement;
import io.proleap.cobol.parser.metamodel.procedure.ifstmt.Then;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class IfStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/ifstmt/IfStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("IfStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final IfStatement ifStatement = (IfStatement) procedureDivision.getStatements().get(0);
		assertNotNull(ifStatement);

		{
			assertNotNull(ifStatement.getCondition());
		}

		{
			final Then then = ifStatement.getThen();
			assertNotNull(then);
		}

		{
			final Else ifElse = ifStatement.getElse();
			assertNotNull(ifElse);
		}
	}
}