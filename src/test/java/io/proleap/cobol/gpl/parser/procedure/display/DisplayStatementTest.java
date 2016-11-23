package io.proleap.cobol.gpl.parser.procedure.display;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.parser.metamodel.procedure.display.Operand;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DisplayStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/display/DisplayStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("DisplayStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final DisplayStatement displayStatement = (DisplayStatement) procedureDivision.getStatements().get(0);
			assertNotNull(displayStatement);

			assertEquals(3, displayStatement.getOperands().size());

			{
				final Operand operand = displayStatement.getOperands().get(0);
				assertNotNull(operand.getOperandCall());
				assertEquals(Call.CallType.UndefinedCall, operand.getOperandCall().getCallType());
			}

			{
				final Operand operand = displayStatement.getOperands().get(1);
				assertNotNull(operand.getOperandCall());
				assertEquals(Call.CallType.UndefinedCall, operand.getOperandCall().getCallType());
			}

			{
				final Operand operand = displayStatement.getOperands().get(2);
				assertNotNull(operand.getOperandCall());
				assertEquals(Call.CallType.UndefinedCall, operand.getOperandCall().getCallType());
			}

			{
				assertNotNull(displayStatement.getUpon());

				final Call uponCall = displayStatement.getUpon().getUponCall();

				assertNotNull(uponCall);
				assertEquals(Call.CallType.UndefinedCall, uponCall.getCallType());
			}

			{
				assertNotNull(displayStatement.getWith());
				assertTrue(displayStatement.getWith().isNoAdvancing());
			}
		}
	}
}