package io.proleap.cobol.asg.procedure.execsql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.execsql.ExecSqlStatement;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ExecSqlStatementBeforeParagraphTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/execsql/ExecSqlBeforeParagraph.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.VARIABLE);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ExecSqlBeforeParagraph");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		assertEquals(1, procedureDivision.getParagraphs().size());
		assertEquals(2, procedureDivision.getStatements().size());

		{
			final MoveStatement moveStatement = (MoveStatement) procedureDivision.getStatements().get(0);
			assertNotNull(moveStatement);
			assertEquals(StatementTypeEnum.MOVE, moveStatement.getStatementType());
		}

		{
			final ExecSqlStatement execSqlStatement = (ExecSqlStatement) procedureDivision.getStatements().get(1);
			assertNotNull(execSqlStatement);
			assertEquals(StatementTypeEnum.EXEC_SQL, execSqlStatement.getStatementType());

			{
				assertNotNull(execSqlStatement.getExecSqlText());
				assertEquals("EXEC SQL WHENEVER SQLERROR CONTINUE END-EXEC", execSqlStatement.getExecSqlText());
			}
		}
	}
}