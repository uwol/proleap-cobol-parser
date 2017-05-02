package io.proleap.cobol.asg.procedure.cancel;

import static org.junit.Assert.assertEquals;

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
import io.proleap.cobol.asg.metamodel.procedure.cancel.CancelCall;
import io.proleap.cobol.asg.metamodel.procedure.cancel.CancelStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CancelStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/cancel/CancelStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("CancelStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(2, procedureDivision.getStatements().size());

		{
			final CancelStatement cancelStatement = (CancelStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.CANCEL, cancelStatement.getStatementType());

			{
				final CancelCall cancelCall = cancelStatement.getCancelCalls().get(0);
				final CallValueStmt callValueStmt = (CallValueStmt) cancelCall.getValueStmt();

				assertEquals(Call.CallType.UNDEFINED_CALL, callValueStmt.getCall().getCallType());
			}

			{
				final CancelCall cancelCall = cancelStatement.getCancelCalls().get(1);
				assertEquals("123", cancelCall.getValueStmt().getValue());
			}
		}

		{
			final CancelStatement cancelStatement = (CancelStatement) procedureDivision.getStatements().get(1);
			assertEquals(StatementTypeEnum.CANCEL, cancelStatement.getStatementType());

			{
				final CancelCall cancelCall = cancelStatement.getCancelCalls().get(0);
				final CallValueStmt callValueStmt = (CallValueStmt) cancelCall.getValueStmt();

				assertEquals(CancelCall.Type.BY_FUNCTION, cancelCall.getType());
				assertEquals(Call.CallType.UNDEFINED_CALL, callValueStmt.getCall().getCallType());
			}
		}
	}
}