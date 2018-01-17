package io.proleap.cobol.asg.procedure.perform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformInlineStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformType;
import io.proleap.cobol.asg.metamodel.procedure.perform.Times;
import io.proleap.cobol.asg.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class PerformInlineTimesTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/perform/PerformInlineTimes.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("PerformInlineTimes");
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
					assertEquals(PerformType.PerformTypeType.TIMES, performType.getPerformTypeType());

					{
						final Times times = performType.getTimes();
						assertNotNull(times.getTimesValueStmt());

						final IntegerLiteralValueStmt timesValueStmt = (IntegerLiteralValueStmt) times
								.getTimesValueStmt();
						final IntegerLiteral literal = timesValueStmt.getLiteral();
						assertEquals(new BigDecimal(2), literal.getValue());
					}
				}

				{
					assertEquals(1, performInlineStatement.getStatements().size());

					{
						final DisplayStatement displayStatement = (DisplayStatement) performInlineStatement
								.getStatements().get(0);
						assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
					}
				}
			}
		}
	}
}
