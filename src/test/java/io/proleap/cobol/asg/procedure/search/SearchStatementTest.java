package io.proleap.cobol.asg.procedure.search;

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
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.search.SearchStatement;
import io.proleap.cobol.asg.metamodel.procedure.search.VaryingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.search.WhenPhrase;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SearchStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/search/SearchStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SearchStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final SearchStatement searchStatement = (SearchStatement) procedureDivision.getStatements().get(0);
			assertNotNull(searchStatement);
			assertEquals(StatementTypeEnum.SEARCH, searchStatement.getStatementType());

			{
				final Call dataCall = searchStatement.getDataCall();
				assertNotNull(dataCall);
				assertEquals(CallType.UNDEFINED_CALL, dataCall.getCallType());
			}

			{
				final VaryingPhrase varying = searchStatement.getVaryingPhrase();
				assertNotNull(varying);
				assertNotNull(varying.getDataCall());
				assertEquals(CallType.UNDEFINED_CALL, varying.getDataCall().getCallType());
			}

			assertNotNull(searchStatement.getAtEndPhrase());

			{
				assertEquals(2, searchStatement.getWhenPhrases().size());

				{
					final WhenPhrase whenPhrase = searchStatement.getWhenPhrases().get(0);
					assertEquals(WhenPhrase.WhenType.NEXT_SENTENCE, whenPhrase.getWhenType());
				}

				{
					final WhenPhrase whenPhrase = searchStatement.getWhenPhrases().get(1);
					assertEquals(WhenPhrase.WhenType.STATEMENTS, whenPhrase.getWhenType());
				}
			}
		}
	}
}
