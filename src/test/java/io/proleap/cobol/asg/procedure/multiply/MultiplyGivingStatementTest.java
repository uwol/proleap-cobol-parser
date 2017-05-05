package io.proleap.cobol.asg.procedure.multiply;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeError;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeError;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.multiply.Giving;
import io.proleap.cobol.asg.metamodel.procedure.multiply.GivingOperand;
import io.proleap.cobol.asg.metamodel.procedure.multiply.GivingResult;
import io.proleap.cobol.asg.metamodel.procedure.multiply.MultiplyStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class MultiplyGivingStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/multiply/MultiplyGivingStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("MultiplyGivingStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final MultiplyStatement multiplyStatement = (MultiplyStatement) procedureDivision.getStatements().get(0);
			assertNotNull(multiplyStatement);
			assertEquals(StatementTypeEnum.MULTIPLY, multiplyStatement.getStatementType());
			assertEquals(MultiplyStatement.Type.GIVING, multiplyStatement.getType());

			{
				final ValueStmt operandValueStmt = multiplyStatement.getOperandValueStmt();
				assertNotNull(operandValueStmt);

				final CallValueStmt operandCallValueStmt = (CallValueStmt) operandValueStmt;
				assertEquals(Call.CallType.UNDEFINED_CALL, operandCallValueStmt.getCall().getCallType());
			}

			{
				final Giving giving = multiplyStatement.getGiving();
				assertNotNull(giving);

				{
					final GivingOperand operand = giving.getOperand();
					assertNotNull(operand);

					final ValueStmt operandValueStmt = operand.getOperandValueStmt();
					assertNotNull(operandValueStmt);

					final CallValueStmt operandCallValueStmt = (CallValueStmt) operandValueStmt;
					assertEquals(Call.CallType.UNDEFINED_CALL, operandCallValueStmt.getCall().getCallType());
				}

				assertEquals(2, giving.getResults().size());

				{
					final GivingResult result = giving.getResults().get(0);
					assertFalse(result.isRounded());

					final Call resultCall = result.getResultCall();
					assertNotNull(resultCall);
					assertEquals(Call.CallType.UNDEFINED_CALL, resultCall.getCallType());
				}

				{
					final GivingResult result = giving.getResults().get(1);
					assertTrue(result.isRounded());

					final Call resultCall = result.getResultCall();
					assertNotNull(resultCall);
					assertEquals(Call.CallType.UNDEFINED_CALL, resultCall.getCallType());
				}
			}

			{
				final OnSizeError onSizeError = multiplyStatement.getOnSizeError();
				assertNotNull(onSizeError);
				assertEquals(1, onSizeError.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) onSizeError.getStatements().get(0);
				assertNotNull(displayStatement);
			}

			{
				final NotOnSizeError notOnSizeError = multiplyStatement.getNotOnSizeError();
				assertNotNull(notOnSizeError);
				assertEquals(1, notOnSizeError.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) notOnSizeError.getStatements().get(0);
				assertNotNull(displayStatement);
			}
		}
	}
}
