package io.proleap.cobol.asg.procedure.alter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.call.ProcedureCall;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.alter.AlterStatement;
import io.proleap.cobol.asg.metamodel.procedure.alter.ProceedTo;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.GoToStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class AlterStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/alter/AlterStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("AlterStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(3, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		final Paragraph paragraph1 = procedureDivision.getParagraph("Paragraph-1");
		final Paragraph paragraph2 = procedureDivision.getParagraph("Paragraph-2");
		final Paragraph paragraph3 = procedureDivision.getParagraph("Paragraph-3");

		{
			final AlterStatement alterStatement = (AlterStatement) procedureDivision.getStatements().get(0);
			assertNotNull(alterStatement);
			assertEquals(StatementTypeEnum.ALTER, alterStatement.getStatementType());

			final List<ProceedTo> proceedTos = alterStatement.getProceedTos();
			assertEquals(1, proceedTos.size());

			{
				final ProceedTo proceedTo = proceedTos.get(0);
				final Call sourceCall = proceedTo.getSourceCall();
				assertNotNull(sourceCall);
				assertEquals(CallType.PROCEDURE_CALL, sourceCall.getCallType());

				final ProcedureCall sourceProcedureCall = (ProcedureCall) sourceCall;
				assertEquals(paragraph1, sourceProcedureCall.getParagraph());

				final Call targetCall = proceedTo.getTargetCall();
				assertNotNull(targetCall);
				assertEquals(CallType.PROCEDURE_CALL, targetCall.getCallType());

				final ProcedureCall targetProcedureCall = (ProcedureCall) targetCall;
				assertEquals(paragraph2, targetProcedureCall.getParagraph());
			}
		}

		{
			assertEquals(1, paragraph1.getStatements().size());

			{
				final GoToStatement statement = (GoToStatement) paragraph1.getStatements().get(0);
				assertNotNull(statement);
				assertEquals(StatementTypeEnum.GO_TO, statement.getStatementType());
			}
		}

		{
			assertEquals(0, paragraph2.getStatements().size());
		}

		{
			assertEquals(0, paragraph3.getStatements().size());
		}
	}
}
