package io.proleap.cobol.asg.procedure.execcics;

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
import io.proleap.cobol.asg.metamodel.procedure.execcics.ExecCicsStatement;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ExecCicsStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/execcics/ExecCics.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ExecCics");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(5, procedureDivision.getStatements().size());

		{
			final MoveStatement moveStatement = (MoveStatement) procedureDivision.getStatements().get(0);
			assertNotNull(moveStatement);
			assertEquals(StatementTypeEnum.MOVE, moveStatement.getStatementType());
		}

		{
			final ExecCicsStatement execCicsStatement = (ExecCicsStatement) procedureDivision.getStatements().get(1);
			assertNotNull(execCicsStatement);
			assertEquals(StatementTypeEnum.EXEC_CICS, execCicsStatement.getStatementType());

			{
				assertNotNull(execCicsStatement.getExecCicsText());
				assertEquals("EXEC CICS RECEIVE INTO(ws-input) LENGTH(ws-length) END-EXEC",
						execCicsStatement.getExecCicsText());
			}
		}

		{
			final MoveStatement moveStatement = (MoveStatement) procedureDivision.getStatements().get(2);
			assertNotNull(moveStatement);
			assertEquals(StatementTypeEnum.MOVE, moveStatement.getStatementType());
		}

		{
			final ExecCicsStatement execCicsStatement = (ExecCicsStatement) procedureDivision.getStatements().get(3);
			assertNotNull(execCicsStatement);
			assertEquals(StatementTypeEnum.EXEC_CICS, execCicsStatement.getStatementType());

			{
				assertNotNull(execCicsStatement.getExecCicsText());
				assertEquals("EXEC CICS SEND FROM(ws-output) LENGTH(ws-length) END-EXEC",
						execCicsStatement.getExecCicsText());
			}
		}

		{
			final ExecCicsStatement execCicsStatement = (ExecCicsStatement) procedureDivision.getStatements().get(4);
			assertNotNull(execCicsStatement);
			assertEquals(StatementTypeEnum.EXEC_CICS, execCicsStatement.getStatementType());

			{
				assertNotNull(execCicsStatement.getExecCicsText());
				assertEquals("EXEC CICS RETURN END-EXEC", execCicsStatement.getExecCicsText());
			}
		}
	}
}