package io.proleap.cobol.asg.procedure.divide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.Giving;
import io.proleap.cobol.asg.metamodel.procedure.divide.Into;
import io.proleap.cobol.asg.metamodel.procedure.divide.IntoByGiving;
import io.proleap.cobol.asg.metamodel.procedure.divide.IntoGiving;
import io.proleap.cobol.asg.metamodel.procedure.divide.Remainder;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DivideStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/divide/DivideStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DivideStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(3, procedureDivision.getStatements().size());

		{
			final DivideStatement divideStatement = (DivideStatement) procedureDivision.getStatements().get(0);
			assertNotNull(divideStatement);
			assertEquals(StatementTypeEnum.DIVIDE, divideStatement.getStatementType());
			assertEquals(DivideStatement.Type.INTO, divideStatement.getType());

			{
				assertNotNull(divideStatement.getDivisorValueStmt());
				final CallValueStmt divisorCallValueStmt = (CallValueStmt) divideStatement.getDivisorValueStmt();
				assertEquals(Call.CallType.UNDEFINED_CALL, divisorCallValueStmt.getCall().getCallType());
			}

			{
				final Into into = divideStatement.getInto();
				assertNotNull(into);
				assertNotNull(into.getIntoValueStmt());

				final CallValueStmt intoCallValueStmt = (CallValueStmt) into.getIntoValueStmt();
				assertEquals(Call.CallType.UNDEFINED_CALL, intoCallValueStmt.getCall().getCallType());

				assertNotNull(into.getGivings());
				assertEquals(1, into.getGivings().getGivings().size());

				{
					final Giving giving = into.getGivings().getGivings().get(0);
					assertTrue(giving.isRounded());
					assertEquals(Call.CallType.UNDEFINED_CALL, giving.getCall().getCallType());
				}
			}

			{
				final Remainder remainder = divideStatement.getRemainder();
				assertNotNull(remainder);
				assertNotNull(remainder.getRemainderCall());
				assertEquals(Call.CallType.UNDEFINED_CALL, remainder.getRemainderCall().getCallType());
			}
		}

		{
			final DivideStatement divideStatement = (DivideStatement) procedureDivision.getStatements().get(1);
			assertNotNull(divideStatement);
			assertEquals(StatementTypeEnum.DIVIDE, divideStatement.getStatementType());

			assertEquals(DivideStatement.Type.INTO_GIVING, divideStatement.getType());
			{
				assertNotNull(divideStatement.getDivisorValueStmt());
				final CallValueStmt divisorCallValueStmt = (CallValueStmt) divideStatement.getDivisorValueStmt();
				assertEquals(Call.CallType.UNDEFINED_CALL, divisorCallValueStmt.getCall().getCallType());
			}

			{
				final IntoGiving intoGiving = divideStatement.getIntoGiving();
				assertNotNull(intoGiving.getGivings());
				assertEquals(2, intoGiving.getGivings().size());

				{
					final Giving giving = intoGiving.getGivings().get(0);
					assertTrue(giving.isRounded());
					assertEquals(Call.CallType.UNDEFINED_CALL, giving.getCall().getCallType());
				}

				{
					final Giving giving = intoGiving.getGivings().get(1);
					assertFalse(giving.isRounded());
					assertEquals(Call.CallType.UNDEFINED_CALL, giving.getCall().getCallType());
				}
			}

			{
				final Remainder remainder = divideStatement.getRemainder();
				assertNotNull(remainder);
				assertNotNull(remainder.getRemainderCall());
				assertEquals(Call.CallType.UNDEFINED_CALL, remainder.getRemainderCall().getCallType());
			}
		}

		{
			final DivideStatement divideStatement = (DivideStatement) procedureDivision.getStatements().get(2);
			assertNotNull(divideStatement);
			assertEquals(StatementTypeEnum.DIVIDE, divideStatement.getStatementType());
			assertEquals(DivideStatement.Type.INTO_BY_GIVING, divideStatement.getType());

			{
				assertNotNull(divideStatement.getDivisorValueStmt());
				final CallValueStmt divisorCallValueStmt = (CallValueStmt) divideStatement.getDivisorValueStmt();
				assertEquals(Call.CallType.UNDEFINED_CALL, divisorCallValueStmt.getCall().getCallType());
			}

			{
				final IntoByGiving intoByGiving = divideStatement.getIntoByGiving();
				assertNotNull(intoByGiving);
				assertNotNull(intoByGiving.getIntoValueStmt());

				final CallValueStmt intoByGivingCallValueStmt = (CallValueStmt) intoByGiving.getIntoValueStmt();
				assertEquals(Call.CallType.UNDEFINED_CALL, intoByGivingCallValueStmt.getCall().getCallType());

				assertNotNull(intoByGiving.getGivings());
				assertEquals(1, intoByGiving.getGivings().getGivings().size());

				{
					final Giving giving = intoByGiving.getGivings().getGivings().get(0);
					assertTrue(giving.isRounded());
					assertEquals(Call.CallType.UNDEFINED_CALL, giving.getCall().getCallType());
				}
			}

			{
				final Remainder remainder = divideStatement.getRemainder();
				assertNotNull(remainder);
				assertNotNull(remainder.getRemainderCall());
				assertEquals(Call.CallType.UNDEFINED_CALL, remainder.getRemainderCall().getCallType());
			}
		}
	}
}