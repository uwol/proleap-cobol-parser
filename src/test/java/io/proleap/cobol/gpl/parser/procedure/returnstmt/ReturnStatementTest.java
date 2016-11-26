package io.proleap.cobol.gpl.parser.procedure.returnstmt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.returnstmt.Into;
import io.proleap.cobol.parser.metamodel.procedure.returnstmt.ReturnStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ReturnStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/returnstmt/ReturnStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("ReturnStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final ReturnStatement returnStatement = (ReturnStatement) procedureDivision.getStatements().get(0);
			assertNotNull(returnStatement);

			{
				final Call fileCall = returnStatement.getFileCall();
				assertNotNull(fileCall);
				assertEquals(Call.CallType.UndefinedCall, fileCall.getCallType());
			}

			{
				final Into into = returnStatement.getInto();
				assertNotNull(into);

				final Call intoCall = into.getIntoCall();
				assertNotNull(intoCall);
				assertEquals(Call.CallType.UndefinedCall, intoCall.getCallType());
			}

			assertNotNull(returnStatement.getAtEnd());
			assertNotNull(returnStatement.getNotAtEnd());
		}
	}
}