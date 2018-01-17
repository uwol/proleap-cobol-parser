package io.proleap.cobol.asg.procedure.display;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.display.Operand;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DisplayAtStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/display/DisplayAtStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DisplayAtStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final DisplayStatement displayStatement = (DisplayStatement) procedureDivision.getStatements().get(0);
			assertNotNull(displayStatement);
			assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
			assertEquals(2, displayStatement.getOperands().size());

			{
				final Operand operand = displayStatement.getOperands().get(0);
				assertNotNull(operand.getOperandValueStmt());

				final CallValueStmt operandCallValueStmt = (CallValueStmt) operand.getOperandValueStmt();
				assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, operandCallValueStmt.getCall().getCallType());
			}

			{
				final Operand operand = displayStatement.getOperands().get(1);
				final LiteralValueStmt operandValueStmt = (LiteralValueStmt) operand.getOperandValueStmt();
				final Literal literal = operandValueStmt.getLiteral();
				assertEquals("2", literal.getValue());
			}

			{
				assertNotNull(displayStatement.getAt());

				final ValueStmt atValueStmt = displayStatement.getAt().getAtValueStmt();
				assertNotNull(atValueStmt);

				final CallValueStmt atCallValueStmt = (CallValueStmt) atValueStmt;
				assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, atCallValueStmt.getCall().getCallType());
			}
		}
	}
}
