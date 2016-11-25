package io.proleap.cobol.gpl.parser.procedure.inspect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.inspect.AllLeading;
import io.proleap.cobol.parser.metamodel.procedure.inspect.AllLeadings;
import io.proleap.cobol.parser.metamodel.procedure.inspect.BeforeAfter;
import io.proleap.cobol.parser.metamodel.procedure.inspect.Characters;
import io.proleap.cobol.parser.metamodel.procedure.inspect.For;
import io.proleap.cobol.parser.metamodel.procedure.inspect.InspectStatement;
import io.proleap.cobol.parser.metamodel.procedure.inspect.Replacing;
import io.proleap.cobol.parser.metamodel.procedure.inspect.ReplacingAllLeading;
import io.proleap.cobol.parser.metamodel.procedure.inspect.ReplacingAllLeadings;
import io.proleap.cobol.parser.metamodel.procedure.inspect.ReplacingCharacters;
import io.proleap.cobol.parser.metamodel.procedure.inspect.TallyingReplacing;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class InspectTallyingReplacingStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/inspect/InspectTallyingReplacingStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("InspectTallyingReplacingStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final InspectStatement inspectStatement = (InspectStatement) procedureDivision.getStatements().get(0);
			assertNotNull(inspectStatement);
			assertEquals(InspectStatement.Type.TallyingReplacing, inspectStatement.getType());

			final TallyingReplacing tallyingReplacing = inspectStatement.getTallyingReplacing();
			assertEquals(1, tallyingReplacing.getFors().size());
			assertEquals(1, tallyingReplacing.getReplacings().size());

			{
				final For for1 = tallyingReplacing.getFors().get(0);
				assertNotNull(for1.getTallyCountDataItemCall());
				assertEquals(Call.CallType.UndefinedCall, for1.getTallyCountDataItemCall().getCallType());
				assertEquals(1, for1.getCharacters().size());
				assertEquals(1, for1.getAllLeadings().size());

				{
					final Characters characters = for1.getCharacters().get(0);
					assertEquals(1, characters.getBeforeAfters().size());

					{
						final BeforeAfter beforeAfter = characters.getBeforeAfters().get(0);
						assertEquals(BeforeAfter.Type.After, beforeAfter.getType());
					}
				}

				{
					final AllLeadings allLeadings = for1.getAllLeadings().get(0);
					assertEquals(AllLeadings.Type.All, allLeadings.getType());
					assertEquals(1, allLeadings.getAllLeadings().size());

					{
						final AllLeading allLeading = allLeadings.getAllLeadings().get(0);

						assertNotNull(allLeading.getPatternDataItemCall());
						assertEquals(Call.CallType.UndefinedCall, allLeading.getPatternDataItemCall().getCallType());
						assertEquals(1, allLeading.getBeforeAfters().size());

						{
							final BeforeAfter beforeAfter = allLeading.getBeforeAfters().get(0);
							assertEquals(BeforeAfter.Type.Before, beforeAfter.getType());
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
						assertEquals(BeforeAfter.Type.After, beforeAfter.getType());
					}
				}

				{
					final ReplacingAllLeadings allLeadings = replacing.getAllLeadings().get(0);
					assertEquals(ReplacingAllLeadings.Type.First, allLeadings.getType());
					assertEquals(1, allLeadings.getAllLeadings().size());

					{
						final ReplacingAllLeading allLeading = allLeadings.getAllLeadings().get(0);

						assertNotNull(allLeading.getPatternDataItemCall());
						assertEquals(Call.CallType.UndefinedCall, allLeading.getPatternDataItemCall().getCallType());
						assertEquals(1, allLeading.getBeforeAfters().size());

						{
							final BeforeAfter beforeAfter = allLeading.getBeforeAfters().get(0);
							assertEquals(BeforeAfter.Type.Before, beforeAfter.getType());
						}
					}
				}
			}
		}
	}
}