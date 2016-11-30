package io.proleap.cobol.gpl.parser.procedure.cancel;

import static org.junit.Assert.assertEquals;

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
import io.proleap.cobol.parser.metamodel.procedure.cancel.CancelCall;
import io.proleap.cobol.parser.metamodel.procedure.cancel.CancelStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CancelStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/cancel/CancelStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("CancelStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final CancelStatement cancelStatement = (CancelStatement) procedureDivision.getStatements().get(0);

			{
				final CancelCall cancelCall = cancelStatement.getCancelCalls().get(0);
				assertEquals(Call.CallType.UndefinedCall, cancelCall.getCall().getCallType());
			}

			{
				final CancelCall cancelCall = cancelStatement.getCancelCalls().get(1);
				assertEquals(Call.CallType.UndefinedCall, cancelCall.getCall().getCallType());
			}
		}

		{
			final CancelStatement cancelStatement = (CancelStatement) procedureDivision.getStatements().get(1);

			{
				final CancelCall cancelCall = cancelStatement.getCancelCalls().get(0);
				assertEquals(CancelCall.Type.ByFunction, cancelCall.getType());
				assertEquals(Call.CallType.UndefinedCall, cancelCall.getCall().getCallType());
			}
		}
	}
}