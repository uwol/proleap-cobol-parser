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
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformProcedureStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformType;
import io.proleap.cobol.asg.metamodel.procedure.perform.Times;
import io.proleap.cobol.asg.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class PerformProcedureTimesTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/perform/PerformProcedureTimes.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("PERFORMPROCEDURETIMES");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(1, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final Statement statement = procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.PERFORM, statement.getStatementType());

			final PerformStatement performStatement = (PerformStatement) statement;
			assertEquals(PerformStatement.PerformStatementType.PROCEDURE, performStatement.getPerformStatementType());

			{
				final PerformProcedureStatement performProcedureStatement = performStatement
						.getPerformProcedureStatement();
				assertNotNull(performProcedureStatement.getPerformType());

				{
					final PerformType performType = performProcedureStatement.getPerformType();
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
			}
		}
	}
}
