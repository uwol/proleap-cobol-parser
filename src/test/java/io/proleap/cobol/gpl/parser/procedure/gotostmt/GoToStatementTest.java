package io.proleap.cobol.gpl.parser.procedure.gotostmt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.call.ProcedureCall;
import io.proleap.cobol.parser.metamodel.procedure.Paragraph;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.gotostmt.DependingOn;
import io.proleap.cobol.parser.metamodel.procedure.gotostmt.GoToStatement;
import io.proleap.cobol.parser.metamodel.procedure.gotostmt.Simple;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class GoToStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/gotostmt/GoToStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("GoToStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final Paragraph paragraph1 = procedureDivision.getParagraph("SOMEPROC1");
		assertNotNull(paragraph1);

		final Paragraph paragraph2 = procedureDivision.getParagraph("SOMEPROC2");
		assertNotNull(paragraph2);

		final Paragraph paragraph3 = procedureDivision.getParagraph("SOMEPROC3");
		assertNotNull(paragraph3);

		{
			final GoToStatement goToStatement = (GoToStatement) procedureDivision.getStatements().get(0);
			assertNotNull(goToStatement);
			assertEquals(GoToStatement.Type.Simple, goToStatement.getType());

			final Simple simple = goToStatement.getSimple();
			assertEquals(Call.CallType.ProcedureCall, simple.getProcedureCall().getCallType());

			final ProcedureCall procedureCall = (ProcedureCall) simple.getProcedureCall();
			assertEquals(paragraph1, procedureCall.getParagraph());
		}

		{
			final GoToStatement goToStatement = (GoToStatement) procedureDivision.getStatements().get(1);
			assertNotNull(goToStatement);
			assertEquals(GoToStatement.Type.DependingOn, goToStatement.getType());

			final DependingOn dependingOn = goToStatement.getDependingOn();
			assertEquals(2, dependingOn.getProcedureCalls().size());

			{
				final Call call = dependingOn.getProcedureCalls().get(0);
				assertEquals(Call.CallType.ProcedureCall, call.getCallType());

				final ProcedureCall procedureCall = (ProcedureCall) call;
				assertEquals(paragraph2, procedureCall.getParagraph());
			}

			{
				final Call call = dependingOn.getProcedureCalls().get(1);
				assertEquals(Call.CallType.ProcedureCall, call.getCallType());

				final ProcedureCall procedureCall = (ProcedureCall) call;
				assertEquals(paragraph3, procedureCall.getParagraph());
			}

			{
				final Call dependingOnCall = dependingOn.getDependingOnCall();
				assertEquals(Call.CallType.UndefinedCall, dependingOnCall.getCallType());
			}
		}

		{
			final GoToStatement goToStatement = (GoToStatement) procedureDivision.getStatements().get(2);
			assertNotNull(goToStatement);
			assertEquals(GoToStatement.Type.DependingOn, goToStatement.getType());

			final DependingOn dependingOn = goToStatement.getDependingOn();
			assertTrue(dependingOn.isMoreLabels());
		}
	}
}