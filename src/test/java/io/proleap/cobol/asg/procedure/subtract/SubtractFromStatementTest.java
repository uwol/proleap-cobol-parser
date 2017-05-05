package io.proleap.cobol.asg.procedure.subtract;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import io.proleap.cobol.asg.metamodel.procedure.subtract.Minuend;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractFrom;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractStatement;
import io.proleap.cobol.asg.metamodel.procedure.subtract.Subtrahend;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SubtractFromStatementTest extends CobolTestSupport {

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
			assertEquals(SubtractStatement.Type.FROM, subtractStatement.getType());
			assertNotNull(subtractStatement.getSubtractFrom());

			{
				final SubtractFrom subtractFrom = subtractStatement.getSubtractFrom();
				assertEquals(2, subtractFrom.getSubtrahends().size());

				{
					final Subtrahend subtrahend = subtractFrom.getSubtrahends().get(0);
					final ValueStmt subtrahendValueStmt = subtrahend.getSubtrahendValueStmt();

					final CallValueStmt subtrahendCallValueStmt = (CallValueStmt) subtrahendValueStmt;
					assertEquals(Call.CallType.UNDEFINED_CALL, subtrahendCallValueStmt.getCall().getCallType());
				}

				{
					final Subtrahend subtrahend = subtractFrom.getSubtrahends().get(1);
					final ValueStmt subtrahendValueStmt = subtrahend.getSubtrahendValueStmt();
					assertEquals("1", subtrahendValueStmt.getValue());
				}

				assertEquals(2, subtractFrom.getMinuends().size());

				{
					final Minuend minuend = subtractFrom.getMinuends().get(0);
					assertFalse(minuend.isRounded());

					final Call minuendCall = minuend.getMinuendCall();
					assertEquals(Call.CallType.UNDEFINED_CALL, minuendCall.getCallType());
				}

				{
					final Minuend minuend = subtractFrom.getMinuends().get(1);
					assertTrue(minuend.isRounded());

					final Call minuendCall = minuend.getMinuendCall();
					assertEquals(Call.CallType.UNDEFINED_CALL, minuendCall.getCallType());
				}
			}
		}
	}
}
