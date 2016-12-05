package io.proleap.cobol.asg.procedure.entry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.entry.EntryStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class EntryStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/entry/EntryStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("EntryStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final EntryStatement entryStatement = (EntryStatement) procedureDivision.getStatements().get(0);
			assertNotNull(entryStatement);
			assertEquals(StatementTypeEnum.Entry, entryStatement.getStatementType());
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