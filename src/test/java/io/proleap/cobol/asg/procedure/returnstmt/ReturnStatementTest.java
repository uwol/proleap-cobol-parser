package io.proleap.cobol.asg.procedure.returnstmt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.AtEnd;
import io.proleap.cobol.asg.metamodel.procedure.NotAtEnd;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.returnstmt.Into;
import io.proleap.cobol.asg.metamodel.procedure.returnstmt.ReturnStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ReturnStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/returnstmt/ReturnStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ReturnStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final ReturnStatement returnStatement = (ReturnStatement) procedureDivision.getStatements().get(0);
			assertNotNull(returnStatement);
			assertEquals(StatementTypeEnum.RETURN, returnStatement.getStatementType());

			{
				final Call fileCall = returnStatement.getFileCall();
				assertNotNull(fileCall);
				assertEquals(Call.CallType.UNDEFINED_CALL, fileCall.getCallType());
			}

			{
				final Into into = returnStatement.getInto();
				assertNotNull(into);

				final Call intoCall = into.getIntoCall();
				assertNotNull(intoCall);
				assertEquals(Call.CallType.UNDEFINED_CALL, intoCall.getCallType());
			}

			{
				final AtEnd atEnd = returnStatement.getAtEnd();
				assertNotNull(atEnd);
				assertEquals(1, atEnd.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) atEnd.getStatements().get(0);
				assertNotNull(displayStatement);
			}

			{
				final NotAtEnd notAtEnd = returnStatement.getNotAtEnd();
				assertNotNull(notAtEnd);
				assertEquals(1, notAtEnd.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) notAtEnd.getStatements().get(0);
				assertNotNull(displayStatement);
			}
		}
	}
}