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
import io.proleap.cobol.parser.metamodel.procedure.inspect.InspectStatement;
import io.proleap.cobol.parser.metamodel.procedure.inspect.Replacing;
import io.proleap.cobol.parser.metamodel.procedure.inspect.ReplacingAllLeading;
import io.proleap.cobol.parser.metamodel.procedure.inspect.ReplacingAllLeadings;
import io.proleap.cobol.parser.metamodel.procedure.inspect.ReplacingCharacters;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class InspectReplacingStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/inspect/InspectReplacingStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("InspectReplacingStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final InspectStatement inspectStatement = (InspectStatement) procedureDivision.getStatements().get(0);
			assertNotNull(inspectStatement);
			assertEquals(InspectStatement.Type.Replacing, inspectStatement.getType());

			final Replacing replacing = inspectStatement.getReplacing();
			assertEquals(1, replacing.getCharacters().size());
			assertEquals(1, replacing.getAllLeadings().size());

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