package io.proleap.cobol.asg.procedure.perform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformInlineStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformType;
import io.proleap.cobol.asg.metamodel.procedure.perform.TestClause;
import io.proleap.cobol.asg.metamodel.procedure.perform.Until;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class PerformInlineUntilTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/perform/PerformInlineUntil.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("PerformInlineUntil");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final Statement statement = procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.PERFORM, statement.getStatementType());

			final PerformStatement performStatement = (PerformStatement) statement;
			assertEquals(PerformStatement.PerformStatementType.INLINE, performStatement.getPerformStatementType());

			{
				final PerformInlineStatement performInlineStatement = performStatement.getPerformInlineStatement();
				assertNotNull(performInlineStatement.getPerformType());

				{
					final PerformType performType = performInlineStatement.getPerformType();
					assertEquals(PerformType.PerformTypeType.UNTIL, performType.getPerformTypeType());

					{
						final Until until = performType.getUntil();
						assertNotNull(until.getTestClause());
						assertEquals(TestClause.TestClauseType.AFTER, until.getTestClause().getTestClauseType());

						final ConditionValueStmt condition = until.getCondition();
						assertNotNull(condition);
					}
				}

				{
					assertEquals(2, performInlineStatement.getStatements().size());

					{
						final DisplayStatement displayStatement = (DisplayStatement) performInlineStatement
								.getStatements().get(0);
						assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
					}

					{
						final StopStatement stopStatement = (StopStatement) performInlineStatement.getStatements()
								.get(1);
						assertEquals(StatementTypeEnum.STOP, stopStatement.getStatementType());
					}
				}
			}
		}
	}
}
