package io.proleap.cobol.asg.procedure.cancel;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.cancel.CancelCall;
import io.proleap.cobol.asg.metamodel.procedure.cancel.CancelStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CancelStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/cancel/CancelStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

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

				assertEquals(CallType.UNDEFINED_CALL, callValueStmt.getCall().getCallType());
			}

			{
				final CancelCall cancelCall = cancelStatement.getCancelCalls().get(1);
				final LiteralValueStmt literalValueStmt = (LiteralValueStmt) cancelCall.getValueStmt();
				final Literal literal = literalValueStmt.getLiteral();
				assertEquals("123", literal.getValue());
			}
		}

		{
			final CancelStatement cancelStatement = (CancelStatement) procedureDivision.getStatements().get(1);
			assertEquals(StatementTypeEnum.CANCEL, cancelStatement.getStatementType());

			{
				final CancelCall cancelCall = cancelStatement.getCancelCalls().get(0);
				final CallValueStmt callValueStmt = (CallValueStmt) cancelCall.getValueStmt();

				assertEquals(CancelCall.CancelType.BY_FUNCTION, cancelCall.getCancelType());
				assertEquals(CallType.UNDEFINED_CALL, callValueStmt.getCall().getCallType());
			}
		}
	}
}
