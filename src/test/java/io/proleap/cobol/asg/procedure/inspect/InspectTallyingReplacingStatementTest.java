package io.proleap.cobol.asg.procedure.inspect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
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
import io.proleap.cobol.asg.metamodel.procedure.inspect.Replacing;
import io.proleap.cobol.asg.metamodel.procedure.inspect.ReplacingAllLeading;
import io.proleap.cobol.asg.metamodel.procedure.inspect.ReplacingAllLeadings;
import io.proleap.cobol.asg.metamodel.procedure.inspect.ReplacingCharacters;
import io.proleap.cobol.asg.metamodel.procedure.inspect.TallyingReplacing;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class InspectTallyingReplacingStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/inspect/InspectTallyingReplacingStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("InspectTallyingReplacingStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final InspectStatement inspectStatement = (InspectStatement) procedureDivision.getStatements().get(0);
			assertNotNull(inspectStatement);
			assertEquals(StatementTypeEnum.INSPECT, inspectStatement.getStatementType());
			assertEquals(InspectStatement.Type.TALLYING_REPLACING, inspectStatement.getType());

			{
				final TallyingReplacing tallyingReplacing = inspectStatement.getTallyingReplacing();
				assertEquals(1, tallyingReplacing.getFors().size());
				assertEquals(1, tallyingReplacing.getReplacings().size());

				{
					final For for1 = tallyingReplacing.getFors().get(0);
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

				{
					final Replacing replacing = tallyingReplacing.getReplacings().get(0);

					{
						final ReplacingCharacters characters = replacing.getCharacters().get(0);
						assertEquals(1, characters.getBeforeAfters().size());

						{
							final BeforeAfter beforeAfter = characters.getBeforeAfters().get(0);
							assertEquals(BeforeAfter.Type.AFTER, beforeAfter.getType());
						}
					}

					{
						final ReplacingAllLeadings allLeadings = replacing.getAllLeadings().get(0);
						assertEquals(ReplacingAllLeadings.Type.FIRST, allLeadings.getType());
						assertEquals(1, allLeadings.getAllLeadings().size());

						{
							final ReplacingAllLeading allLeading = allLeadings.getAllLeadings().get(0);

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
}