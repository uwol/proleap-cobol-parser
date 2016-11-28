package io.proleap.cobol.gpl.parser.procedure.initiate;

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
import io.proleap.cobol.parser.metamodel.procedure.initiate.InitiateStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class InitiateStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/initiate/InitiateStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("InitiateStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final InitiateStatement initiateStatement = (InitiateStatement) procedureDivision.getStatements().get(0);
			assertNotNull(initiateStatement);

			assertEquals(2, initiateStatement.getReportCalls().size());

			{
				final Call reportCall = initiateStatement.getReportCalls().get(0);
				assertNotNull(reportCall);
				assertEquals(Call.CallType.UndefinedCall, reportCall.getCallType());
			}

			{
				final Call reportCall = initiateStatement.getReportCalls().get(1);
				assertNotNull(reportCall);
				assertEquals(Call.CallType.UndefinedCall, reportCall.getCallType());
			}
		}
	}
}