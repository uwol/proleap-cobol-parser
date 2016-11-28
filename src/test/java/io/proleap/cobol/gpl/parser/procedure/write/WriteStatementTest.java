package io.proleap.cobol.gpl.parser.procedure.write;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CompilationUnit;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.write.Advancing;
import io.proleap.cobol.parser.metamodel.procedure.write.AdvancingLines;
import io.proleap.cobol.parser.metamodel.procedure.write.From;
import io.proleap.cobol.parser.metamodel.procedure.write.WriteStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class WriteStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/write/WriteStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("WriteStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final WriteStatement writeStatement = (WriteStatement) procedureDivision.getStatements().get(0);
		assertNotNull(writeStatement);

		{
			final Call recordCall = writeStatement.getRecordCall();
			assertNotNull(recordCall);
			assertEquals(Call.CallType.UndefinedCall, recordCall.getCallType());
		}

		{
			final From from = writeStatement.getFrom();
			assertNotNull(from);
			assertEquals(Call.CallType.UndefinedCall, from.getFromCall().getCallType());
		}

		{
			final Advancing advancing = writeStatement.getAdvancing();
			assertNotNull(advancing);
			assertEquals(Advancing.PositionType.Before, advancing.getPositionType());
			assertEquals(Advancing.Type.Lines, advancing.getType());

			{
				final AdvancingLines advancingLines = advancing.getAdvancingLines();
				assertNotNull(advancingLines);
				assertNotNull(advancingLines.getLinesCall());
				assertEquals(Call.CallType.UndefinedCall, advancingLines.getLinesCall().getCallType());
			}
		}
	}
}