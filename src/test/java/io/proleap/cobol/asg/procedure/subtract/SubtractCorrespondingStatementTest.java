package io.proleap.cobol.asg.procedure.subtract;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.subtract.MinuendCorresponding;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractCorrespondingStatement;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SubtractCorrespondingStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/subtract/SubtractCorrespondingStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SubtractCorrespondingStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final SubtractStatement subtractStatement = (SubtractStatement) procedureDivision.getStatements().get(0);
			assertNotNull(subtractStatement);
			assertEquals(StatementTypeEnum.SUBTRACT, subtractStatement.getStatementType());
			assertEquals(SubtractStatement.SubtractType.CORRESPONDING, subtractStatement.getSubtractType());
			assertNotNull(subtractStatement.getSubtractCorrespondingStatement());

			{
				final SubtractCorrespondingStatement subtractCorrespondingStatement = subtractStatement
						.getSubtractCorrespondingStatement();

				{
					final Call subtrahendCall = subtractCorrespondingStatement.getSubtrahendCall();
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, subtrahendCall.getCallType());
				}

				{
					final MinuendCorresponding minuend = subtractCorrespondingStatement.getMinuend();
					assertTrue(minuend.isRounded());

					final Call minuendCall = minuend.getMinuendCall();
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, minuendCall.getCallType());
				}
			}
		}
	}
}
