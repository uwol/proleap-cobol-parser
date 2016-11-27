package io.proleap.cobol.gpl.parser.procedure.set;

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
import io.proleap.cobol.parser.metamodel.procedure.set.SetStatement;
import io.proleap.cobol.parser.metamodel.procedure.set.SetTo;
import io.proleap.cobol.parser.metamodel.procedure.set.To;
import io.proleap.cobol.parser.metamodel.procedure.set.Value;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SetToStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/set/SetToStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("SetToStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final SetStatement setStatement = (SetStatement) procedureDivision.getStatements().get(0);
		assertNotNull(setStatement);
		assertEquals(SetStatement.Type.To, setStatement.getType());
		assertEquals(2, setStatement.getSetTos().size());

		{
			final SetTo setTo = setStatement.getSetTos().get(0);
			assertEquals(2, setTo.getTos().size());
			assertEquals(2, setTo.getValues().size());

			{
				final To to = setTo.getTos().get(0);
				final Call toCall = to.getToCall();
				assertNotNull(toCall);
				assertEquals(Call.CallType.UndefinedCall, toCall.getCallType());
			}

			{
				final To to = setTo.getTos().get(1);
				final Call toCall = to.getToCall();
				assertNotNull(toCall);
				assertEquals(Call.CallType.UndefinedCall, toCall.getCallType());
			}

			{
				final Value value = setTo.getValues().get(0);
				assertEquals(Value.Type.Call, value.getType());

				final Call valueCall = value.getValueCall();
				assertNotNull(valueCall);
				assertEquals(Call.CallType.UndefinedCall, valueCall.getCallType());
			}

			{
				final Value value = setTo.getValues().get(1);
				assertEquals(Value.Type.Call, value.getType());

				final Call valueCall = value.getValueCall();
				assertNotNull(valueCall);
				assertEquals(Call.CallType.UndefinedCall, valueCall.getCallType());
			}
		}

		{
			final SetTo setTo = setStatement.getSetTos().get(1);
			assertEquals(1, setTo.getTos().size());
			assertEquals(2, setTo.getValues().size());

			{
				final To to = setTo.getTos().get(0);
				final Call toCall = to.getToCall();
				assertNotNull(toCall);
				assertEquals(Call.CallType.UndefinedCall, toCall.getCallType());
			}

			{
				final Value value = setTo.getValues().get(0);
				assertEquals(Value.Type.Call, value.getType());

				final Call valueCall = value.getValueCall();
				assertNotNull(valueCall);
				assertEquals(Call.CallType.UndefinedCall, valueCall.getCallType());
			}

			{
				final Value value = setTo.getValues().get(1);
				assertEquals(Value.Type.On, value.getType());
				assertNull(value.getValueCall());
			}
		}
	}
}