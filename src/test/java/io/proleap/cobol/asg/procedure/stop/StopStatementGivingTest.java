package io.proleap.cobol.asg.procedure.stop;

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
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement.StopType;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatementGiving;
import io.proleap.cobol.asg.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class StopStatementGivingTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/stop/StopStatementGiving.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("StopStatementGiving");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final StopStatement stopStatement = (StopStatement) procedureDivision.getStatements().get(0);
			assertNotNull(stopStatement);
			assertEquals(StatementTypeEnum.STOP, stopStatement.getStatementType());
			assertEquals(StopType.STOP_RUN_GIVING, stopStatement.getStopType());

			{
				final StopStatementGiving stopStatementGiving = stopStatement.getStopStatementGiving();
				assertNotNull(stopStatementGiving);

				final ValueStmt givingValueStmt = stopStatementGiving.getGivingValueStmt();
				assertNotNull(givingValueStmt);

				final IntegerLiteralValueStmt integerLiteralValueStmt = (IntegerLiteralValueStmt) givingValueStmt;
				final IntegerLiteral literal = integerLiteralValueStmt.getLiteral();
				assertEquals(BigDecimal.ZERO, literal.getValue());
			}
		}
	}
}