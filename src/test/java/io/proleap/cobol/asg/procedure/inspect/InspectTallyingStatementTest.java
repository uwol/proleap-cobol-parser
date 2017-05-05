package io.proleap.cobol.asg.procedure.inspect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.inspect.AllLeading;
import io.proleap.cobol.asg.metamodel.procedure.inspect.AllLeadings;
import io.proleap.cobol.asg.metamodel.procedure.inspect.BeforeAfter;
import io.proleap.cobol.asg.metamodel.procedure.inspect.Characters;
import io.proleap.cobol.asg.metamodel.procedure.inspect.For;
import io.proleap.cobol.asg.metamodel.procedure.inspect.InspectStatement;
import io.proleap.cobol.asg.metamodel.procedure.inspect.Tallying;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class InspectTallyingStatementTest extends CobolTestSupport {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/inspect/InspectTallyingStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("InspectTallyingStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final InspectStatement inspectStatement = (InspectStatement) procedureDivision.getStatements().get(0);
			assertNotNull(inspectStatement);
			assertEquals(StatementTypeEnum.INSPECT, inspectStatement.getStatementType());
			assertEquals(InspectStatement.Type.TALLYING, inspectStatement.getType());

			final Tallying tallying = inspectStatement.getTallying();
			assertEquals(1, tallying.getFors().size());

			{
				final For for1 = tallying.getFors().get(0);

				assertNotNull(for1.getTallyCountDataItemCall());
				assertEquals(Call.CallType.UNDEFINED_CALL, for1.getTallyCountDataItemCall().getCallType());
				assertEquals(1, for1.getCharacters().size());
				assertEquals(1, for1.getAllLeadings().size());

				{
					final Characters characters = for1.getCharacters().get(0);
					assertEquals(1, characters.getBeforeAfters().size());

					{
						final BeforeAfter beforeAfter = characters.getBeforeAfters().get(0);
						assertEquals(BeforeAfter.Type.AFTER, beforeAfter.getType());
					}
				}

				{
					final AllLeadings allLeadings = for1.getAllLeadings().get(0);
					assertEquals(AllLeadings.Type.ALL, allLeadings.getType());
					assertEquals(1, allLeadings.getAllLeadings().size());

					{
						final AllLeading allLeading = allLeadings.getAllLeadings().get(0);

						assertNotNull(allLeading.getPatternDataItemValueStmt());
						assertEquals("B", allLeading.getPatternDataItemValueStmt().getValue());
						assertEquals(1, allLeading.getBeforeAfters().size());

						{
							final BeforeAfter beforeAfter = allLeading.getBeforeAfters().get(0);
							assertEquals(BeforeAfter.Type.BEFORE, beforeAfter.getType());
						}
					}
				}
			}
		}
	}
}