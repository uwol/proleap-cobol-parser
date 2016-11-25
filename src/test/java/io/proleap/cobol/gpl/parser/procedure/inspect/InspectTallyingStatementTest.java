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
import io.proleap.cobol.parser.metamodel.procedure.inspect.Tallying;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class InspectTallyingStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/inspect/InspectTallyingStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("InspectTallyingStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final InspectStatement inspectStatement = (InspectStatement) procedureDivision.getStatements().get(0);
			assertNotNull(inspectStatement);
			assertEquals(InspectStatement.Type.Tallying, inspectStatement.getType());

			final Tallying tallying = inspectStatement.getTallying();
			assertEquals(1, tallying.getFors().size());

			{
				final For for1 = tallying.getFors().get(0);

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
		}
	}
}