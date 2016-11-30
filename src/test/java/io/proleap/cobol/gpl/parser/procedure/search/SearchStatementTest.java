package io.proleap.cobol.gpl.parser.procedure.search;

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
import io.proleap.cobol.parser.metamodel.procedure.search.SearchStatement;
import io.proleap.cobol.parser.metamodel.procedure.search.Varying;
import io.proleap.cobol.parser.metamodel.procedure.search.When;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SearchStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/search/SearchStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SearchStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final SearchStatement searchStatement = (SearchStatement) procedureDivision.getStatements().get(0);
			assertNotNull(searchStatement);

			{
				final Call dataCall = searchStatement.getDataCall();
				assertNotNull(dataCall);
				assertEquals(Call.CallType.UndefinedCall, dataCall.getCallType());
			}

			{
				final Varying varying = searchStatement.getVarying();
				assertNotNull(varying);
				assertNotNull(varying.getDataCall());
				assertEquals(Call.CallType.UndefinedCall, varying.getDataCall().getCallType());
			}

			assertNotNull(searchStatement.getAtEnd());

			{
				assertEquals(2, searchStatement.getWhens().size());

				{
					final When when = searchStatement.getWhens().get(0);
					assertEquals(When.Type.NextSentence, when.getType());
				}

				{
					final When when = searchStatement.getWhens().get(1);
					assertEquals(When.Type.Statements, when.getType());
				}
			}
		}
	}
}