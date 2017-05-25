package io.proleap.cobol.asg.procedure.rewrite;

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
import io.proleap.cobol.asg.metamodel.procedure.rewrite.From;
import io.proleap.cobol.asg.metamodel.procedure.rewrite.RewriteStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class RewriteStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/rewrite/RewriteStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("RewriteStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final RewriteStatement rewriteStatement = (RewriteStatement) procedureDivision.getStatements().get(0);
			assertNotNull(rewriteStatement);
			assertEquals(StatementTypeEnum.REWRITE, rewriteStatement.getStatementType());

			{
				final Call recordCall = rewriteStatement.getRecordCall();
				assertNotNull(recordCall);
				assertEquals(CallType.UNDEFINED_CALL, recordCall.getCallType());
			}

			{
				final From from = rewriteStatement.getFrom();
				assertNotNull(from);

				final Call fromCall = from.getFromCall();
				assertNotNull(fromCall);
				assertEquals(CallType.UNDEFINED_CALL, fromCall.getCallType());
			}

			{
				final InvalidKeyPhrase invalidKeyPhrase = rewriteStatement.getInvalidKeyPhrase();
				assertNotNull(invalidKeyPhrase);
				assertEquals(1, invalidKeyPhrase.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) invalidKeyPhrase.getStatements().get(0);
				assertNotNull(displayStatement);
			}

			{
				final NotInvalidKeyPhrase notInvalidKeyPhrase = rewriteStatement.getNotInvalidKeyPhrase();
				assertNotNull(notInvalidKeyPhrase);
				assertEquals(1, notInvalidKeyPhrase.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) notInvalidKeyPhrase.getStatements().get(0);
				assertNotNull(displayStatement);
			}
		}
	}
}
