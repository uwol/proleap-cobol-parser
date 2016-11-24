package io.proleap.cobol.gpl.parser.procedure.entry;

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
import io.proleap.cobol.parser.metamodel.procedure.entry.EntryStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class EntryStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/entry/EntryStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("EntryStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final EntryStatement entryStatement = (EntryStatement) procedureDivision.getStatements().get(0);
			assertNotNull(entryStatement);
			assertNotNull(entryStatement.getEntryCall());
			assertEquals(Call.CallType.UndefinedCall, entryStatement.getEntryCall().getCallType());
			assertEquals(2, entryStatement.getUsingCalls().size());

			{
				final Call usingCall = entryStatement.getUsingCalls().get(0);
				assertNotNull(usingCall);
				assertEquals(Call.CallType.UndefinedCall, usingCall.getCallType());
			}

			{
				final Call usingCall = entryStatement.getUsingCalls().get(1);
				assertNotNull(usingCall);
				assertEquals(Call.CallType.UndefinedCall, usingCall.getCallType());
			}
		}
	}
}