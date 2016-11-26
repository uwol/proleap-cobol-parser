package io.proleap.cobol.gpl.parser.procedure.read;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import io.proleap.cobol.parser.metamodel.procedure.read.Into;
import io.proleap.cobol.parser.metamodel.procedure.read.Key;
import io.proleap.cobol.parser.metamodel.procedure.read.ReadStatement;
import io.proleap.cobol.parser.metamodel.procedure.read.With;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ReadStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/read/ReadStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("ReadStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final ReadStatement readStatement = (ReadStatement) procedureDivision.getStatements().get(0);
			assertNotNull(readStatement);

			{
				assertNotNull(readStatement.getFileCall());
				assertEquals(Call.CallType.UndefinedCall, readStatement.getFileCall().getCallType());
			}

			assertTrue(readStatement.isNextRecord());

			{
				final Into into = readStatement.getInto();
				assertNotNull(into);
				assertNotNull(into.getIntoCall());
				assertEquals(Call.CallType.UndefinedCall, into.getIntoCall().getCallType());
			}

			{
				final With with = readStatement.getWith();
				assertNotNull(with);
				assertNotNull(with.getType());
				assertEquals(With.Type.Wait, with.getType());
			}

			{
				final Key key = readStatement.getKey();
				assertNotNull(key.getKeyCall());
				assertEquals(Call.CallType.UndefinedCall, key.getKeyCall().getCallType());
			}

			assertNotNull(readStatement.getInvalidKey());
			assertNotNull(readStatement.getNotInvalidKey());
			assertNotNull(readStatement.getAtEnd());
			assertNotNull(readStatement.getNotAtEnd());
		}
	}
}