package io.proleap.cobol.gpl.parser.procedure.start;

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
import io.proleap.cobol.parser.metamodel.procedure.InvalidKey;
import io.proleap.cobol.parser.metamodel.procedure.NotInvalidKey;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.parser.metamodel.procedure.start.Key;
import io.proleap.cobol.parser.metamodel.procedure.start.StartStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class StartStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/start/StartStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("StartStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final StartStatement startStatement = (StartStatement) procedureDivision.getStatements().get(0);
			assertNotNull(startStatement);

			{
				final Call fileCall = startStatement.getFileCall();
				assertNotNull(fileCall);
				assertEquals(Call.CallType.UndefinedCall, fileCall.getCallType());
			}

			{
				final Key key = startStatement.getKey();
				assertNotNull(key);
				assertEquals(Key.Type.Equal, key.getType());

				{
					final Call comparisonCall = key.getComparisonCall();
					assertNotNull(comparisonCall);
					assertEquals(Call.CallType.UndefinedCall, comparisonCall.getCallType());
				}
			}

			{
				final InvalidKey invalidKey = startStatement.getInvalidKey();
				assertNotNull(invalidKey);
				assertEquals(1, invalidKey.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) invalidKey.getStatements().get(0);
				assertNotNull(displayStatement);
			}

			{
				final NotInvalidKey notInvalidKey = startStatement.getNotInvalidKey();
				assertNotNull(notInvalidKey);
				assertEquals(1, notInvalidKey.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) notInvalidKey.getStatements().get(0);
				assertNotNull(displayStatement);
			}
		}
	}
}