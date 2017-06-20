package io.proleap.cobol.asg.procedure.execsql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.asg.metamodel.identification.ProgramIdParagraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.execsql.ExecSqlStatement;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ExecSqlStatementMultilineWithDotTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/execsql/ExecSqlMultilineWithDot.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.VARIABLE);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ExecSqlMultilineWithDot");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final IdentificationDivision identificationDivision = programUnit.getIdentificationDivision();

		{
			final ProgramIdParagraph programIdParagraph = identificationDivision.getProgramIdParagraph();
			assertEquals("EXECSQL", programIdParagraph.getName());
		}

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(2, procedureDivision.getStatements().size());

		{
			final ExecSqlStatement execSqlStatement = (ExecSqlStatement) procedureDivision.getStatements().get(0);
			assertNotNull(execSqlStatement);
			assertEquals(StatementTypeEnum.EXEC_SQL, execSqlStatement.getStatementType());

			{
				assertNotNull(execSqlStatement.getExecSqlText());
				assertEquals("EXEC SQL SELECT TEACHER-ID FROM TBL.TEACHR END-EXEC", execSqlStatement.getExecSqlText());
			}
		}

		{
			final StopStatement stopStatement = (StopStatement) procedureDivision.getStatements().get(1);
			assertNotNull(stopStatement);
			assertEquals(StatementTypeEnum.STOP, stopStatement.getStatementType());
		}
	}
}