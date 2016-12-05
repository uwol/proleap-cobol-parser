package io.proleap.cobol.asg.procedure.alter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.ProcedureCall;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.alter.AlterProceedTo;
import io.proleap.cobol.asg.metamodel.procedure.alter.AlterStatement;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.GoToStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class AlterStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/alter/AlterStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("AlterStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(3, procedureDivision.getParagraphs().size());
		assertEquals(2, procedureDivision.getStatements().size());

		final Paragraph paragraph1 = procedureDivision.getParagraph("Paragraph-1");
		final Paragraph paragraph2 = procedureDivision.getParagraph("Paragraph-2");

		{
			final AlterStatement alterStatement = (AlterStatement) procedureDivision.getStatements().get(0);
			assertNotNull(alterStatement);
			assertEquals(StatementTypeEnum.Alter, alterStatement.getStatementType());

			final List<AlterProceedTo> alterProceedTos = alterStatement.getAlterProceedTos();
			assertEquals(1, alterProceedTos.size());

			{
				final AlterProceedTo alterProceedTo = alterProceedTos.get(0);
				final Call sourceCall = alterProceedTo.getSourceCall();
				assertNotNull(sourceCall);
				assertEquals(Call.CallType.ProcedureCall, sourceCall.getCallType());

				final ProcedureCall sourceProcedureCall = (ProcedureCall) sourceCall;
				assertEquals(paragraph1, sourceProcedureCall.getParagraph());

				final Call targetCall = alterProceedTo.getTargetCall();
				assertNotNull(targetCall);
				assertEquals(Call.CallType.ProcedureCall, targetCall.getCallType());

				final ProcedureCall targetProcedureCall = (ProcedureCall) targetCall;
				assertEquals(paragraph2, targetProcedureCall.getParagraph());
			}
		}

		{
			final GoToStatement goToStatement = (GoToStatement) procedureDivision.getStatements().get(1);
			assertNotNull(goToStatement);
			assertEquals(StatementTypeEnum.GoTo, goToStatement.getStatementType());
		}
	}
}
