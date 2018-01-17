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
import io.proleap.cobol.asg.metamodel.FigurativeConstant.FigurativeConstantType;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.subtract.Giving;
import io.proleap.cobol.asg.metamodel.procedure.subtract.MinuendGiving;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractFromGivingStatement;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractStatement;
import io.proleap.cobol.asg.metamodel.procedure.subtract.Subtrahend;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SubtractFromGivingStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/subtract/SubtractFromGivingStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SubtractFromGivingStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(2, procedureDivision.getStatements().size());

		{
			final SubtractStatement subtractStatement = (SubtractStatement) procedureDivision.getStatements().get(0);
			assertNotNull(subtractStatement);
			assertEquals(StatementTypeEnum.SUBTRACT, subtractStatement.getStatementType());
			assertEquals(SubtractStatement.SubtractType.FROM_GIVING, subtractStatement.getSubtractType());
			assertNotNull(subtractStatement.getSubtractFromGivingStatement());

			{
				final SubtractFromGivingStatement subtractFromGivingStatement = subtractStatement
						.getSubtractFromGivingStatement();
				assertEquals(2, subtractFromGivingStatement.getSubtrahends().size());

				{
					final Subtrahend subtrahend = subtractFromGivingStatement.getSubtrahends().get(0);
					final ValueStmt subtrahendValueStmt = subtrahend.getSubtrahendValueStmt();

					final CallValueStmt subtrahendCallValueStmt = (CallValueStmt) subtrahendValueStmt;
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, subtrahendCallValueStmt.getCall().getCallType());
				}

				{
					final Subtrahend subtrahend = subtractFromGivingStatement.getSubtrahends().get(1);
					final LiteralValueStmt subtrahendValueStmt = (LiteralValueStmt) subtrahend.getSubtrahendValueStmt();
					final Literal literal = subtrahendValueStmt.getLiteral();
					assertEquals(BigDecimal.ONE, literal.getValue());
				}

				{
					final MinuendGiving minuend = subtractFromGivingStatement.getMinuend();
					final ValueStmt minuendValueStmt = minuend.getMinuendValueStmt();
					final CallValueStmt minuendCallValueStmt = (CallValueStmt) minuendValueStmt;
					final Call minuendCall = minuendCallValueStmt.getCall();
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, minuendCall.getCallType());
				}

				assertEquals(2, subtractFromGivingStatement.getGivings().size());

				{
					final Giving giving = subtractFromGivingStatement.getGivings().get(0);
					assertFalse(giving.isRounded());

					final Call givingCall = giving.getGivingCall();
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, givingCall.getCallType());
				}

				{
					final Giving giving = subtractFromGivingStatement.getGivings().get(1);
					assertTrue(giving.isRounded());

					final Call givingCall = giving.getGivingCall();
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, givingCall.getCallType());
				}
			}
		}

		{
			final SubtractStatement subtractStatement = (SubtractStatement) procedureDivision.getStatements().get(1);
			assertNotNull(subtractStatement);
			assertEquals(StatementTypeEnum.SUBTRACT, subtractStatement.getStatementType());
			assertEquals(SubtractStatement.SubtractType.FROM_GIVING, subtractStatement.getSubtractType());
			assertNotNull(subtractStatement.getSubtractFromGivingStatement());

			{
				final SubtractFromGivingStatement subtractFromGivingStatement = subtractStatement
						.getSubtractFromGivingStatement();
				assertEquals(1, subtractFromGivingStatement.getSubtrahends().size());

				{
					final Subtrahend subtrahend = subtractFromGivingStatement.getSubtrahends().get(0);
					final ValueStmt subtrahendValueStmt = subtrahend.getSubtrahendValueStmt();

					final LiteralValueStmt subtrahendLiteralValueStmt = (LiteralValueStmt) subtrahendValueStmt;
					final Literal literal = subtrahendLiteralValueStmt.getLiteral();
					assertEquals(BigDecimal.ZERO, literal.getValue());
				}

				{
					final MinuendGiving minuend = subtractFromGivingStatement.getMinuend();
					final ValueStmt minuendValueStmt = minuend.getMinuendValueStmt();

					final LiteralValueStmt literalMinuendValueStmt = (LiteralValueStmt) minuendValueStmt;
					final Literal literal = literalMinuendValueStmt.getLiteral();
					assertEquals(Literal.LiteralType.FIGURATIVE_CONSTANT, literal.getLiteralType());

					assertNotNull(literal.getFigurativeConstant());
					assertEquals(FigurativeConstantType.ZERO,
							literal.getFigurativeConstant().getFigurativeConstantType());
				}

				assertEquals(1, subtractFromGivingStatement.getGivings().size());

				{
					final Giving giving = subtractFromGivingStatement.getGivings().get(0);
					assertFalse(giving.isRounded());

					final Call givingCall = giving.getGivingCall();
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, givingCall.getCallType());
				}
			}
		}
	}
}
