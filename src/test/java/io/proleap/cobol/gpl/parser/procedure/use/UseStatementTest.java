package io.proleap.cobol.gpl.parser.procedure.use;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import io.proleap.cobol.parser.metamodel.procedure.declaratives.Declarative;
import io.proleap.cobol.parser.metamodel.procedure.declaratives.Declaratives;
import io.proleap.cobol.parser.metamodel.procedure.use.Debug;
import io.proleap.cobol.parser.metamodel.procedure.use.DebugOn;
import io.proleap.cobol.parser.metamodel.procedure.use.UseStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class UseStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/use/UseStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("UseStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		final Declaratives declaratives = procedureDivision.getDeclaratives();

		assertEquals(2, declaratives.getDeclaratives().size());

		{
			final Declarative declarative = declaratives.getDeclaratives().get(0);
			final UseStatement useStatement = declarative.getUseStament();
			assertNotNull(useStatement);
			assertEquals(UseStatement.Type.Debug, useStatement.getType());

			final Debug debug = useStatement.getDebug();
			assertEquals(3, debug.getDebugOns().size());

			{
				final DebugOn debugOn = debug.getDebugOns().get(0);
				assertEquals(DebugOn.Type.AllReferences, debugOn.getType());

				final Call onCall = debugOn.getOnCall();
				assertEquals(Call.CallType.UndefinedCall, onCall.getCallType());
			}

			{
				final DebugOn debugOn = debug.getDebugOns().get(1);
				assertEquals(DebugOn.Type.AllProcedures, debugOn.getType());
				assertNull(debugOn.getOnCall());
			}

			{
				final DebugOn debugOn = debug.getDebugOns().get(2);
				assertEquals(DebugOn.Type.Procedure, debugOn.getType());

				final Call onCall = debugOn.getOnCall();
				assertEquals(Call.CallType.UndefinedCall, onCall.getCallType());
			}
		}

		{
			final Declarative declarative = declaratives.getDeclaratives().get(1);
			final UseStatement useStatement = declarative.getUseStament();
			assertNotNull(useStatement);

			assertEquals(UseStatement.Type.After, useStatement.getType());
			assertTrue(useStatement.getAfter().isGlobal());
		}
	}
}