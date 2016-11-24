package io.proleap.cobol.gpl.parser.procedure.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import io.proleap.cobol.parser.metamodel.procedure.call.ByContent;
import io.proleap.cobol.parser.metamodel.procedure.call.ByReference;
import io.proleap.cobol.parser.metamodel.procedure.call.CallByContentStatement;
import io.proleap.cobol.parser.metamodel.procedure.call.CallByReferenceStatement;
import io.proleap.cobol.parser.metamodel.procedure.call.CallByValueStatement;
import io.proleap.cobol.parser.metamodel.procedure.call.CallStatement;
import io.proleap.cobol.parser.metamodel.procedure.call.Giving;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CallStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/call/CallStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("CallStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final CallStatement callStatement = (CallStatement) procedureDivision.getStatements().get(0);

		{
			final Giving giving = callStatement.getGiving();
			assertNotNull(giving.getGivingCall());
			assertEquals(Call.CallType.UndefinedCall, giving.getGivingCall().getCallType());
		}

		{
			final CallByReferenceStatement callByReferenceStatement = callStatement.getCallByReferenceStatements()
					.get(0);
			assertEquals(2, callByReferenceStatement.getByReferences().size());

			{
				final ByReference byReference = callByReferenceStatement.getByReferences().get(0);
				assertEquals(ByReference.Type.Integer, byReference.getType());
				assertEquals(Call.CallType.UndefinedCall, byReference.getCall().getCallType());
			}

			{
				final ByReference byReference = callByReferenceStatement.getByReferences().get(1);
				assertNull(byReference.getType());
				assertEquals(Call.CallType.UndefinedCall, byReference.getCall().getCallType());
			}
		}

		{
			final CallByValueStatement callByValueStatement = callStatement.getCallByValueStatements().get(0);
			assertEquals(3, callByValueStatement.getValueStmts().size());

			{
				final ValueStmt valueStmt = callByValueStatement.getValueStmts().get(0);
				assertEquals("1", valueStmt.getValue());
			}

			{
				final ValueStmt valueStmt = callByValueStatement.getValueStmts().get(1);
				assertEquals("2", valueStmt.getValue());
			}

			{
				final ValueStmt valueStmt = callByValueStatement.getValueStmts().get(2);
				assertNotNull(valueStmt);
			}
		}

		{
			final CallByContentStatement callByContentStatement = callStatement.getCallByContentStatements().get(0);
			assertEquals(3, callByContentStatement.getByContents().size());

			{
				final ByContent byContent = callByContentStatement.getByContents().get(0);
				assertEquals(ByContent.Type.AddressOf, byContent.getType());
				assertEquals(Call.CallType.UndefinedCall, byContent.getCall().getCallType());
			}

			{
				final ByContent byContent = callByContentStatement.getByContents().get(1);
				assertEquals(ByContent.Type.LengthOf, byContent.getType());
				assertEquals(Call.CallType.UndefinedCall, byContent.getCall().getCallType());
			}

			{
				final ByContent byContent = callByContentStatement.getByContents().get(2);
				assertNull(byContent.getType());
				assertEquals(Call.CallType.UndefinedCall, byContent.getCall().getCallType());
			}
		}
	}
}
