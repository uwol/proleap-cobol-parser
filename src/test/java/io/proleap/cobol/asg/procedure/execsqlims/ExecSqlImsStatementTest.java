package io.proleap.cobol.asg.procedure.execsqlims;

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
import io.proleap.cobol.asg.metamodel.procedure.execsqlims.ExecSqlImsStatement;
import io.proleap.cobol.asg.metamodel.procedure.ifstmt.IfStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ExecSqlImsStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/execsqlims/ExecSqlIms.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ExecSqlIms");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(5, procedureDivision.getStatements().size());

		{
			final ExecSqlImsStatement execSqlImsStatement = (ExecSqlImsStatement) procedureDivision.getStatements()
					.get(0);
			assertNotNull(execSqlImsStatement);
			assertEquals(StatementTypeEnum.EXEC_SQLIMS, execSqlImsStatement.getStatementType());

			{
				assertNotNull(execSqlImsStatement.getExecSqlImsText());
				assertEquals("EXEC SQLIMS DECLARE SOMECUR CURSOR FOR DYSQL END-EXEC",
						execSqlImsStatement.getExecSqlImsText());
			}
		}

		{
			final ExecSqlImsStatement execSqlImsStatement = (ExecSqlImsStatement) procedureDivision.getStatements()
					.get(1);
			assertNotNull(execSqlImsStatement);
			assertEquals(StatementTypeEnum.EXEC_SQLIMS, execSqlImsStatement.getStatementType());

			{
				assertNotNull(execSqlImsStatement.getExecSqlImsText());
				assertEquals("EXEC SQLIMS OPEN SOMECUR END-EXEC", execSqlImsStatement.getExecSqlImsText());
			}
		}

		{
			final ExecSqlImsStatement execSqlImsStatement = (ExecSqlImsStatement) procedureDivision.getStatements()
					.get(2);
			assertNotNull(execSqlImsStatement);
			assertEquals(StatementTypeEnum.EXEC_SQLIMS, execSqlImsStatement.getStatementType());

			{
				assertNotNull(execSqlImsStatement.getExecSqlImsText());
				assertEquals("EXEC SQLIMS FETCH SOMECUR INTO :SOMECOL1, :SOMECOL2 END-EXEC",
						execSqlImsStatement.getExecSqlImsText());
			}
		}

		{
			final IfStatement ifStatement = (IfStatement) procedureDivision.getStatements().get(3);
			assertNotNull(ifStatement);
			assertEquals(StatementTypeEnum.IF, ifStatement.getStatementType());
		}

		{
			final ExecSqlImsStatement execSqlImsStatement = (ExecSqlImsStatement) procedureDivision.getStatements()
					.get(4);
			assertNotNull(execSqlImsStatement);
			assertEquals(StatementTypeEnum.EXEC_SQLIMS, execSqlImsStatement.getStatementType());

			{
				assertNotNull(execSqlImsStatement.getExecSqlImsText());
				assertEquals("EXEC SQLIMS CLOSE SOMECUR END-EXEC", execSqlImsStatement.getExecSqlImsText());
			}
		}
	}
}