package io.proleap.cobol.asg.procedure.stop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class StopStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/stop/StopStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("StopStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(2, procedureDivision.getStatements().size());

		{
			final StopStatement stopStatement = (StopStatement) procedureDivision.getStatements().get(0);
			assertNotNull(stopStatement);
			assertEquals(StatementTypeEnum.STOP, stopStatement.getStatementType());
			assertEquals(StopStatement.Type.STOP_RUN, stopStatement.getType());
		}

		{
			final StopStatement stopStatement = (StopStatement) procedureDivision.getStatements().get(1);
			assertNotNull(stopStatement);
			assertEquals(StatementTypeEnum.STOP, stopStatement.getStatementType());
			assertEquals(StopStatement.Type.STOP_RUN_AND_DISPLAY, stopStatement.getType());
			assertNotNull(stopStatement.getDisplayValueStmt());
			assertEquals("someliteral", stopStatement.getDisplayValueStmt().getValue());
		}
	}
}