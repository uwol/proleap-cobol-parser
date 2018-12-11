package io.proleap.cobol.asg.procedure.evaluate;

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
import io.proleap.cobol.asg.metamodel.procedure.evaluate.EvaluateStatement;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.Select;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.WhenOther;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class EvaluateStatementWhenOtherTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/evaluate/EvaluateStatementWhenOther.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("EvaluateStatementWhenOther");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final EvaluateStatement evaluateStatement = (EvaluateStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.EVALUATE, evaluateStatement.getStatementType());
			assertNotNull(evaluateStatement);

			{
				final Select select = evaluateStatement.getSelect();
				assertNotNull(select);

				final ValueStmt selectValueStmt = select.getSelectValueStmt();
				assertNotNull(selectValueStmt);
			}

			{
				final WhenOther whenOther = evaluateStatement.getWhenOther();
				assertNotNull(whenOther);
			}
		}
	}
}