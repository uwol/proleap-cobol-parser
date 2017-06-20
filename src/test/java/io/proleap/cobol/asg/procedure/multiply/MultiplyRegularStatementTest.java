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
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.multiply.ByOperand;
import io.proleap.cobol.asg.metamodel.procedure.multiply.ByPhrase;
import io.proleap.cobol.asg.metamodel.procedure.multiply.MultiplyStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class MultiplyRegularStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/multiply/MultiplyRegularStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("MultiplyRegularStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final MultiplyStatement multiplyStatement = (MultiplyStatement) procedureDivision.getStatements().get(0);
			assertNotNull(multiplyStatement);
			assertEquals(StatementTypeEnum.MULTIPLY, multiplyStatement.getStatementType());
			assertEquals(MultiplyStatement.MultiplyType.BY, multiplyStatement.getMultiplyType());

			{
				final ValueStmt operandValueStmt = multiplyStatement.getOperandValueStmt();
				assertNotNull(operandValueStmt);

				final CallValueStmt operandCallValueStmt = (CallValueStmt) operandValueStmt;
				assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, operandCallValueStmt.getCall().getCallType());
			}

			{
				final ByPhrase byPhrase = multiplyStatement.getByPhrase();
				assertNotNull(byPhrase);
				assertEquals(2, byPhrase.getByOperands().size());

				{
					final ByOperand byOperand = byPhrase.getByOperands().get(0);
					assertTrue(byOperand.isRounded());

					final Call operandCall = byOperand.getOperandCall();
					assertNotNull(operandCall);
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, operandCall.getCallType());
				}

				{
					final ByOperand byOperand = byPhrase.getByOperands().get(1);
					assertFalse(byOperand.isRounded());

					final Call operandCall = byOperand.getOperandCall();
					assertNotNull(operandCall);
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, operandCall.getCallType());
				}
			}

			{
				final OnSizeErrorPhrase onSizeErrorPhrase = multiplyStatement.getOnSizeErrorPhrase();
				assertNotNull(onSizeErrorPhrase);
				assertEquals(1, onSizeErrorPhrase.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) onSizeErrorPhrase.getStatements().get(0);
				assertNotNull(displayStatement);
			}

			{
				final NotOnSizeErrorPhrase notOnSizeErrorPhrase = multiplyStatement.getNotOnSizeErrorPhrase();
				assertNotNull(notOnSizeErrorPhrase);
				assertEquals(1, notOnSizeErrorPhrase.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) notOnSizeErrorPhrase.getStatements()
						.get(0);
				assertNotNull(displayStatement);
			}
		}
	}
}
