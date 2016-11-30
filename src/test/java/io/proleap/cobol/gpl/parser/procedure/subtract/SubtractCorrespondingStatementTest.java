package io.proleap.cobol.gpl.parser.procedure.subtract;

import static org.junit.Assert.assertEquals;
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
import io.proleap.cobol.parser.metamodel.procedure.subtract.MinuendCorresponding;
import io.proleap.cobol.parser.metamodel.procedure.subtract.SubtractCorresponding;
import io.proleap.cobol.parser.metamodel.procedure.subtract.SubtractStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SubtractCorrespondingStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/subtract/SubtractCorrespondingStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SubtractCorrespondingStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final SubtractStatement subtractStatement = (SubtractStatement) procedureDivision.getStatements().get(0);
		assertEquals(SubtractStatement.Type.Corresponding, subtractStatement.getType());
		assertNotNull(subtractStatement.getSubtractCorresponding());

		final SubtractCorresponding subtractCorresponding = subtractStatement.getSubtractCorresponding();

		{
			final Call subtrahendCall = subtractCorresponding.getSubtrahendCall();
			assertEquals(Call.CallType.UndefinedCall, subtrahendCall.getCallType());
		}

		{
			final MinuendCorresponding minuend = subtractCorresponding.getMinuend();
			assertTrue(minuend.isRounded());

			final Call minuendCall = minuend.getMinuendCall();
			assertEquals(Call.CallType.UndefinedCall, minuendCall.getCallType());
		}
	}
}
