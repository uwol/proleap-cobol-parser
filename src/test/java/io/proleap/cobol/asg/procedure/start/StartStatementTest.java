package io.proleap.cobol.asg.procedure.start;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.start.Key;
import io.proleap.cobol.asg.metamodel.procedure.start.StartStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class StartStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/start/StartStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("StartStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final StartStatement startStatement = (StartStatement) procedureDivision.getStatements().get(0);
			assertNotNull(startStatement);
			assertEquals(StatementTypeEnum.START, startStatement.getStatementType());

			{
				final Call fileCall = startStatement.getFileCall();
				assertNotNull(fileCall);
				assertEquals(CallType.UNDEFINED_CALL, fileCall.getCallType());
			}

			{
				final Key key = startStatement.getKey();
				assertNotNull(key);
				assertEquals(Key.KeyType.EQUAL, key.getKeyType());

				{
					final Call comparisonCall = key.getComparisonCall();
					assertNotNull(comparisonCall);
					assertEquals(CallType.UNDEFINED_CALL, comparisonCall.getCallType());
				}
			}

			{
				final InvalidKeyPhrase invalidKeyPhrase = startStatement.getInvalidKeyPhrase();
				assertNotNull(invalidKeyPhrase);
				assertEquals(1, invalidKeyPhrase.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) invalidKeyPhrase.getStatements().get(0);
				assertNotNull(displayStatement);
				assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
			}

			{
				final NotInvalidKeyPhrase notInvalidKeyPhrase = startStatement.getNotInvalidKeyPhrase();
				assertNotNull(notInvalidKeyPhrase);
				assertEquals(1, notInvalidKeyPhrase.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) notInvalidKeyPhrase.getStatements()
							.get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
				}
			}
		}
	}
}
