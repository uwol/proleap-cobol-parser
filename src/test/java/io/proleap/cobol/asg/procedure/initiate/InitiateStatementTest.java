package io.proleap.cobol.asg.procedure.initiate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.initiate.InitiateStatement;
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
				"src/test/resources/io/proleap/cobol/asg/procedure/initiate/InitiateStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("InitiateStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final InitiateStatement initiateStatement = (InitiateStatement) procedureDivision.getStatements().get(0);
			assertNotNull(initiateStatement);
			assertEquals(StatementTypeEnum.Initiate, initiateStatement.getStatementType());
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