package io.proleap.cobol.asg.procedure.subtract;

import static org.junit.Assert.assertEquals;
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
import io.proleap.cobol.asg.metamodel.procedure.subtract.MinuendCorresponding;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractCorresponding;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SubtractCorrespondingStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/subtract/SubtractCorrespondingStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SubtractCorrespondingStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final SubtractStatement subtractStatement = (SubtractStatement) procedureDivision.getStatements().get(0);
			assertNotNull(subtractStatement);
			assertEquals(StatementTypeEnum.Subtract, subtractStatement.getStatementType());
			assertEquals(SubtractStatement.Type.Corresponding, subtractStatement.getType());
			assertNotNull(subtractStatement.getSubtractCorresponding());

			{
				final SubtractCorresponding subtractCorresponding = subtractStatement.getSubtractCorresponding();

				{
					final Call subtrahendCall = subtractCorresponding.getSubtrahendCall();
					assertEquals(Call.CallType.UndefinedCall, subtrahendCall.getCallType());
				}

				{
					final MinuendCorresponding minuend = subtractCorresponding.getMinuend();
					assertTrue(minuend.isRounded());

					final Call minuendCall = minuend.getMinuendCall();
					assertEquals(Call.CallType.UndefinedCall, minuendCall.getCallType());
				}
			}
		}
	}
}
