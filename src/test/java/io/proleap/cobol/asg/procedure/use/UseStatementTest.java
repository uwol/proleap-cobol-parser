package io.proleap.cobol.asg.procedure.use;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
import io.proleap.cobol.asg.metamodel.procedure.declaratives.Declarative;
import io.proleap.cobol.asg.metamodel.procedure.declaratives.Declaratives;
import io.proleap.cobol.asg.metamodel.procedure.use.DebugOn;
import io.proleap.cobol.asg.metamodel.procedure.use.UseDebugStatement;
import io.proleap.cobol.asg.metamodel.procedure.use.UseStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class UseStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/use/UseStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("UseStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(2, procedureDivision.getParagraphs().size());

		final Declaratives declaratives = procedureDivision.getDeclaratives();
		assertEquals(2, declaratives.getDeclaratives().size());

		{
			final Declarative declarative = declaratives.getDeclaratives().get(0);
			final UseStatement useStatement = declarative.getUseStament();
			assertNotNull(useStatement);
			assertEquals(UseStatement.UseType.DEBUG, useStatement.getUseType());

			{
				final UseDebugStatement useDebugStatement = useStatement.getUseDebugStatement();
				assertEquals(3, useDebugStatement.getDebugOns().size());

				{
					final DebugOn debugOn = useDebugStatement.getDebugOns().get(0);
					assertEquals(DebugOn.DebugOnType.ALL_REFERENCES, debugOn.getDebugOnType());

					final Call onCall = debugOn.getOnCall();
					assertEquals(CallType.UNDEFINED_CALL, onCall.getCallType());
				}

				{
					final DebugOn debugOn = useDebugStatement.getDebugOns().get(1);
					assertEquals(DebugOn.DebugOnType.ALL_PROCEDURES, debugOn.getDebugOnType());
					assertNull(debugOn.getOnCall());
				}

				{
					final DebugOn debugOn = useDebugStatement.getDebugOns().get(2);
					assertEquals(DebugOn.DebugOnType.PROCEDURE, debugOn.getDebugOnType());

					final Call onCall = debugOn.getOnCall();
					assertEquals(CallType.UNDEFINED_CALL, onCall.getCallType());
				}
			}
		}

		{
			final Declarative declarative = declaratives.getDeclaratives().get(1);
			final UseStatement useStatement = declarative.getUseStament();
			assertNotNull(useStatement);
			assertEquals(UseStatement.UseType.AFTER, useStatement.getUseType());
			assertTrue(useStatement.getUseAfterStatement().isGlobal());
		}
	}
}