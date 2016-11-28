package io.proleap.cobol.gpl.parser.procedure.inspect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CompilationUnit;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.inspect.BeforeAfter;
import io.proleap.cobol.parser.metamodel.procedure.inspect.Converting;
import io.proleap.cobol.parser.metamodel.procedure.inspect.InspectStatement;
import io.proleap.cobol.parser.metamodel.procedure.inspect.To;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class InspectConvertingStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/inspect/InspectConvertingStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("InspectConvertingStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final InspectStatement inspectStatement = (InspectStatement) procedureDivision.getStatements().get(0);
			assertNotNull(inspectStatement);
			assertEquals(InspectStatement.Type.Converting, inspectStatement.getType());

			final Converting converting = inspectStatement.getConverting();

			{
				assertNotNull(converting.getFromCall());
				assertEquals(Call.CallType.UndefinedCall, converting.getFromCall().getCallType());
			}

			{
				final To to = converting.getTo();
				assertNotNull(to);
				assertNotNull(to.getToCall());
				assertEquals(Call.CallType.UndefinedCall, to.getToCall().getCallType());
			}

			assertEquals(1, converting.getBeforeAfters().size());

			{
				{
					final BeforeAfter beforeAfter = converting.getBeforeAfters().get(0);
					assertEquals(BeforeAfter.Type.After, beforeAfter.getType());
				}
			}
		}
	}
}