package io.proleap.cobol.gpl.parser.procedure.string;

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
import io.proleap.cobol.parser.metamodel.procedure.string.DelimitedBy;
import io.proleap.cobol.parser.metamodel.procedure.string.For;
import io.proleap.cobol.parser.metamodel.procedure.string.Into;
import io.proleap.cobol.parser.metamodel.procedure.string.Sending;
import io.proleap.cobol.parser.metamodel.procedure.string.StringStatement;
import io.proleap.cobol.parser.metamodel.procedure.string.WithPointer;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class StringStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/string/StringStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("StringStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final StringStatement stringStatement = (StringStatement) procedureDivision.getStatements().get(0);
		assertNotNull(stringStatement);
		assertEquals(2, stringStatement.getSendings().size());

		{
			final Sending sending = stringStatement.getSendings().get(0);
			assertEquals(Sending.Type.DelimitedBy, sending.getType());

			final DelimitedBy delimitedBy = sending.getDelimitedBy();
			assertEquals(DelimitedBy.Type.Characters, delimitedBy.getType());
			assertEquals(Call.CallType.UndefinedCall, delimitedBy.getCharactersCall().getCallType());
		}

		{
			final Sending sending = stringStatement.getSendings().get(1);
			assertEquals(Sending.Type.For, sending.getType());

			final For sendingFor = sending.getFor();
			assertEquals(Call.CallType.UndefinedCall, sendingFor.getForCall().getCallType());
		}

		{
			final Into into = stringStatement.getInto();
			assertNotNull(into);

			final Call intoCall = into.getIntoCall();
			assertEquals(Call.CallType.UndefinedCall, intoCall.getCallType());
		}

		{
			final WithPointer withPointer = stringStatement.getWithPointer();
			assertNotNull(withPointer);

			final Call pointerCall = withPointer.getPointerCall();
			assertEquals(Call.CallType.UndefinedCall, pointerCall.getCallType());
		}

		assertNotNull(stringStatement.getOnOverflow());
		assertNotNull(stringStatement.getNotOnOverflow());
	}
}