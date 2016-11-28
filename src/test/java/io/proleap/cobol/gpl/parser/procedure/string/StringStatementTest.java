package io.proleap.cobol.gpl.parser.procedure.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CompilationUnit;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.string.DelimitedBy;
import io.proleap.cobol.parser.metamodel.procedure.string.For;
import io.proleap.cobol.parser.metamodel.procedure.string.Into;
import io.proleap.cobol.parser.metamodel.procedure.string.Sendings;
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

		final CompilationUnit compilationUnit = program.getCompilationUnit("StringStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final StringStatement stringStatement = (StringStatement) procedureDivision.getStatements().get(0);
		assertNotNull(stringStatement);
		assertEquals(2, stringStatement.getSendings().size());

		{
			final Sendings sendings = stringStatement.getSendings().get(0);
			assertEquals(Sendings.Type.DelimitedBy, sendings.getType());

			final DelimitedBy delimitedBy = sendings.getDelimitedBy();
			assertEquals(DelimitedBy.Type.Characters, delimitedBy.getType());
			assertEquals(Call.CallType.UndefinedCall, delimitedBy.getCharactersCall().getCallType());
		}

		{
			final Sendings sendings = stringStatement.getSendings().get(1);
			assertEquals(Sendings.Type.For, sendings.getType());

			final For sendingFor = sendings.getFor();
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