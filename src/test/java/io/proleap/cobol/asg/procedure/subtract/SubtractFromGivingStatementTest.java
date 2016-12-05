package io.proleap.cobol.asg.procedure.subtract;

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
import io.proleap.cobol.asg.metamodel.procedure.subtract.Giving;
import io.proleap.cobol.asg.metamodel.procedure.subtract.MinuendGiving;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractFromGiving;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractStatement;
import io.proleap.cobol.asg.metamodel.procedure.subtract.Subtrahend;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SubtractFromGivingStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/subtract/SubtractFromGivingStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SubtractFromGivingStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final SubtractStatement subtractStatement = (SubtractStatement) procedureDivision.getStatements().get(0);
			assertNotNull(subtractStatement);
			assertEquals(StatementTypeEnum.Subtract, subtractStatement.getStatementType());
			assertEquals(SubtractStatement.Type.FromGiving, subtractStatement.getType());
			assertNotNull(subtractStatement.getSubtractFromGiving());

			{
				final SubtractFromGiving subtractFromGiving = subtractStatement.getSubtractFromGiving();
				assertEquals(2, subtractFromGiving.getSubtrahends().size());

				{
					final Subtrahend subtrahend = subtractFromGiving.getSubtrahends().get(0);
					final Call subtrahendCall = subtrahend.getSubtrahendCall();
					assertEquals(Call.CallType.UndefinedCall, subtrahendCall.getCallType());
				}

				{
					final Subtrahend subtrahend = subtractFromGiving.getSubtrahends().get(1);
					final Call subtrahendCall = subtrahend.getSubtrahendCall();
					assertEquals(Call.CallType.UndefinedCall, subtrahendCall.getCallType());
				}

				{
					final MinuendGiving minuend = subtractFromGiving.getMinuend();
					final Call minuendCall = minuend.getMinuendCall();
					assertEquals(Call.CallType.UndefinedCall, minuendCall.getCallType());
				}

				assertEquals(2, subtractFromGiving.getGivings().size());

				{
					final Giving giving = subtractFromGiving.getGivings().get(0);
					assertFalse(giving.isRounded());

					final Call givingCall = giving.getGivingCall();
					assertEquals(Call.CallType.UndefinedCall, givingCall.getCallType());
				}

				{
					final Giving giving = subtractFromGiving.getGivings().get(1);
					assertTrue(giving.isRounded());

					final Call givingCall = giving.getGivingCall();
					assertEquals(Call.CallType.UndefinedCall, givingCall.getCallType());
				}
			}
		}
	}
}
