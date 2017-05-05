package io.proleap.cobol.asg.procedure.display;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.display.Operand;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DisplayStatementTest extends CobolTestSupport {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/display/DisplayStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DisplayStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final DisplayStatement displayStatement = (DisplayStatement) procedureDivision.getStatements().get(0);
			assertNotNull(displayStatement);
			assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
			assertEquals(3, displayStatement.getOperands().size());

			{
				final Operand operand = displayStatement.getOperands().get(0);
				assertNotNull(operand.getOperandValueStmt());

				final CallValueStmt operandCallValueStmt = (CallValueStmt) operand.getOperandValueStmt();
				assertEquals(Call.CallType.UNDEFINED_CALL, operandCallValueStmt.getCall().getCallType());
			}

			{
				final Operand operand = displayStatement.getOperands().get(1);
				assertNotNull(operand.getOperandValueStmt());
				assertEquals("2", operand.getOperandValueStmt().getValue());
			}

			{
				final Operand operand = displayStatement.getOperands().get(2);
				assertNotNull(operand.getOperandValueStmt());
				assertEquals(3, operand.getOperandValueStmt().getValue());
			}

			{
				assertNotNull(displayStatement.getUpon());

				final Call uponCall = displayStatement.getUpon().getUponCall();
				assertNotNull(uponCall);
				assertEquals(Call.CallType.MNEMONIC_CALL, uponCall.getCallType());
			}

			{
				assertNotNull(displayStatement.getWith());
				assertTrue(displayStatement.getWith().isNoAdvancing());
			}
		}
	}
}