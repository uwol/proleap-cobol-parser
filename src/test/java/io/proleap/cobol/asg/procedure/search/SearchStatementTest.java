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
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.search.SearchStatement;
import io.proleap.cobol.asg.metamodel.procedure.search.Varying;
import io.proleap.cobol.asg.metamodel.procedure.search.When;
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
				assertEquals(Call.CallType.UNDEFINED_CALL, dataCall.getCallType());
			}

			{
				final Varying varying = searchStatement.getVarying();
				assertNotNull(varying);
				assertNotNull(varying.getDataCall());
				assertEquals(Call.CallType.UNDEFINED_CALL, varying.getDataCall().getCallType());
			}

			assertNotNull(searchStatement.getAtEndPhrase());

			{
				assertEquals(2, searchStatement.getWhens().size());

				{
					final When when = searchStatement.getWhens().get(0);
					assertEquals(When.WhenType.NEXT_SENTENCE, when.getWhenType());
				}

				{
					final When when = searchStatement.getWhens().get(1);
					assertEquals(When.WhenType.STATEMENTS, when.getWhenType());
				}
			}
		}
	}
}