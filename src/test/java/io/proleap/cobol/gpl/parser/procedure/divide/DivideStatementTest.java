package io.proleap.cobol.gpl.parser.procedure.divide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import io.proleap.cobol.parser.metamodel.procedure.divide.DivideStatement;
import io.proleap.cobol.parser.metamodel.procedure.divide.Giving;
import io.proleap.cobol.parser.metamodel.procedure.divide.Into;
import io.proleap.cobol.parser.metamodel.procedure.divide.IntoByGiving;
import io.proleap.cobol.parser.metamodel.procedure.divide.IntoGiving;
import io.proleap.cobol.parser.metamodel.procedure.divide.Remainder;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DivideStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/divide/DivideStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DivideStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final DivideStatement divideStatement = (DivideStatement) procedureDivision.getStatements().get(0);
			assertNotNull(divideStatement);

			assertEquals(DivideStatement.Type.Into, divideStatement.getType());
			assertNotNull(divideStatement.getDivisorCall());

			final Into into = divideStatement.getInto();
			assertNotNull(into);
			assertNotNull(into.getIntoCall());
			assertEquals(Call.CallType.UndefinedCall, into.getIntoCall().getCallType());

			assertNotNull(into.getGivings());
			assertEquals(1, into.getGivings().getGivings().size());

			{
				final Giving giving = into.getGivings().getGivings().get(0);
				assertTrue(giving.isRounded());
				assertEquals(Call.CallType.UndefinedCall, giving.getCall().getCallType());
			}

			final Remainder remainder = divideStatement.getRemainder();
			assertNotNull(remainder);
			assertNotNull(remainder.getRemainderCall());
			assertEquals(Call.CallType.UndefinedCall, remainder.getRemainderCall().getCallType());
		}

		{
			final DivideStatement divideStatement = (DivideStatement) procedureDivision.getStatements().get(1);
			assertNotNull(divideStatement);

			assertEquals(DivideStatement.Type.IntoGiving, divideStatement.getType());
			assertNotNull(divideStatement.getDivisorCall());

			final IntoGiving intoGiving = divideStatement.getIntoGiving();
			assertNotNull(intoGiving.getGivings());
			assertEquals(2, intoGiving.getGivings().size());

			{
				final Giving giving = intoGiving.getGivings().get(0);
				assertTrue(giving.isRounded());
				assertEquals(Call.CallType.UndefinedCall, giving.getCall().getCallType());
			}

			{
				final Giving giving = intoGiving.getGivings().get(1);
				assertFalse(giving.isRounded());
				assertEquals(Call.CallType.UndefinedCall, giving.getCall().getCallType());
			}

			final Remainder remainder = divideStatement.getRemainder();
			assertNotNull(remainder);
			assertNotNull(remainder.getRemainderCall());
			assertEquals(Call.CallType.UndefinedCall, remainder.getRemainderCall().getCallType());
		}

		{
			final DivideStatement divideStatement = (DivideStatement) procedureDivision.getStatements().get(2);
			assertNotNull(divideStatement);

			assertEquals(DivideStatement.Type.IntoByGiving, divideStatement.getType());
			assertNotNull(divideStatement.getDivisorCall());

			final IntoByGiving intoByGiving = divideStatement.getIntoByGiving();
			assertNotNull(intoByGiving);
			assertNotNull(intoByGiving.getIntoCall());
			assertEquals(Call.CallType.UndefinedCall, intoByGiving.getIntoCall().getCallType());

			assertNotNull(intoByGiving.getGivings());
			assertEquals(1, intoByGiving.getGivings().getGivings().size());

			{
				final Giving giving = intoByGiving.getGivings().getGivings().get(0);
				assertTrue(giving.isRounded());
				assertEquals(Call.CallType.UndefinedCall, giving.getCall().getCallType());
			}

			final Remainder remainder = divideStatement.getRemainder();
			assertNotNull(remainder);
			assertNotNull(remainder.getRemainderCall());
			assertEquals(Call.CallType.UndefinedCall, remainder.getRemainderCall().getCallType());
		}
	}
}