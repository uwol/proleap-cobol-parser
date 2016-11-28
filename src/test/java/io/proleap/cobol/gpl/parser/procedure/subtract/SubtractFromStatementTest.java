package io.proleap.cobol.gpl.parser.procedure.subtract;

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
import io.proleap.cobol.parser.metamodel.procedure.subtract.Minuend;
import io.proleap.cobol.parser.metamodel.procedure.subtract.SubtractFrom;
import io.proleap.cobol.parser.metamodel.procedure.subtract.SubtractStatement;
import io.proleap.cobol.parser.metamodel.procedure.subtract.Subtrahend;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SubtractFromStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/subtract/SubtractFromStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SubtractFromStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final SubtractStatement subtractStatement = (SubtractStatement) procedureDivision.getStatements().get(0);
		assertEquals(SubtractStatement.Type.From, subtractStatement.getType());
		assertNotNull(subtractStatement.getSubtractFrom());

		final SubtractFrom subtractFrom = subtractStatement.getSubtractFrom();
		assertEquals(2, subtractFrom.getSubtrahends().size());

		{
			final Subtrahend subtrahend = subtractFrom.getSubtrahends().get(0);
			final Call subtrahendCall = subtrahend.getSubtrahendCall();
			assertEquals(Call.CallType.UndefinedCall, subtrahendCall.getCallType());
		}

		{
			final Subtrahend subtrahend = subtractFrom.getSubtrahends().get(1);
			final Call subtrahendCall = subtrahend.getSubtrahendCall();
			assertEquals(Call.CallType.UndefinedCall, subtrahendCall.getCallType());
		}

		assertEquals(2, subtractFrom.getMinuends().size());

		{
			final Minuend minuend = subtractFrom.getMinuends().get(0);
			assertFalse(minuend.isRounded());

			final Call minuendCall = minuend.getMinuendCall();
			assertEquals(Call.CallType.UndefinedCall, minuendCall.getCallType());
		}

		{
			final Minuend minuend = subtractFrom.getMinuends().get(1);
			assertTrue(minuend.isRounded());

			final Call minuendCall = minuend.getMinuendCall();
			assertEquals(Call.CallType.UndefinedCall, minuendCall.getCallType());
		}
	}
}
