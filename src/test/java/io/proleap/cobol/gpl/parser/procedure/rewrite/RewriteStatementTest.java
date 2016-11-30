package io.proleap.cobol.gpl.parser.procedure.rewrite;

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
import io.proleap.cobol.parser.metamodel.procedure.rewrite.From;
import io.proleap.cobol.parser.metamodel.procedure.rewrite.RewriteStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class RewriteStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/rewrite/RewriteStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("RewriteStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final RewriteStatement rewriteStatement = (RewriteStatement) procedureDivision.getStatements().get(0);
			assertNotNull(rewriteStatement);

			{
				final Call recordCall = rewriteStatement.getRecordCall();
				assertNotNull(recordCall);
				assertEquals(Call.CallType.UndefinedCall, recordCall.getCallType());
			}

			{
				final From from = rewriteStatement.getFrom();
				assertNotNull(from);

				final Call fromCall = from.getFromCall();
				assertNotNull(fromCall);
				assertEquals(Call.CallType.UndefinedCall, fromCall.getCallType());
			}

			{
				final InvalidKey invalidKey = rewriteStatement.getInvalidKey();
				assertNotNull(invalidKey);
				assertEquals(1, invalidKey.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) invalidKey.getStatements().get(0);
				assertNotNull(displayStatement);
			}

			{
				final NotInvalidKey notInvalidKey = rewriteStatement.getNotInvalidKey();
				assertNotNull(notInvalidKey);
				assertEquals(1, notInvalidKey.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) notInvalidKey.getStatements().get(0);
				assertNotNull(displayStatement);
			}
		}
	}
}