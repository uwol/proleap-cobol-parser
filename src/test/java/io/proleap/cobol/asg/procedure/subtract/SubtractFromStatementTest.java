package io.proleap.cobol.asg.procedure.subtract;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.subtract.Minuend;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractFromStatement;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractStatement;
import io.proleap.cobol.asg.metamodel.procedure.subtract.Subtrahend;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SubtractFromStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/subtract/SubtractFromStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SubtractFromStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final SubtractStatement subtractStatement = (SubtractStatement) procedureDivision.getStatements().get(0);
			assertNotNull(subtractStatement);
			assertEquals(StatementTypeEnum.SUBTRACT, subtractStatement.getStatementType());
			assertEquals(SubtractStatement.SubtractType.FROM, subtractStatement.getSubtractType());
			assertNotNull(subtractStatement.getSubtractFromStatement());

			{
				final SubtractFromStatement subtractFromStatement = subtractStatement.getSubtractFromStatement();
				assertEquals(2, subtractFromStatement.getSubtrahends().size());

				{
					final Subtrahend subtrahend = subtractFromStatement.getSubtrahends().get(0);
					final ValueStmt subtrahendValueStmt = subtrahend.getSubtrahendValueStmt();

					final CallValueStmt subtrahendCallValueStmt = (CallValueStmt) subtrahendValueStmt;
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, subtrahendCallValueStmt.getCall().getCallType());
				}

				{
					final Subtrahend subtrahend = subtractFromStatement.getSubtrahends().get(1);
					final LiteralValueStmt subtrahendValueStmt = (LiteralValueStmt) subtrahend.getSubtrahendValueStmt();
					final Literal literal = subtrahendValueStmt.getLiteral();
					assertEquals(BigDecimal.ONE, literal.getValue());
				}

				assertEquals(2, subtractFromStatement.getMinuends().size());

				{
					final Minuend minuend = subtractFromStatement.getMinuends().get(0);
					assertFalse(minuend.isRounded());

					final Call minuendCall = minuend.getMinuendCall();
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, minuendCall.getCallType());
				}

				{
					final Minuend minuend = subtractFromStatement.getMinuends().get(1);
					assertTrue(minuend.isRounded());

					final Call minuendCall = minuend.getMinuendCall();
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, minuendCall.getCallType());
				}
			}
		}
	}
}
