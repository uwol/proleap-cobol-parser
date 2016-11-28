package io.proleap.cobol.gpl.parser.procedure.receive;

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
import io.proleap.cobol.parser.metamodel.procedure.receive.NoData;
import io.proleap.cobol.parser.metamodel.procedure.receive.ReceiveIntoStatement;
import io.proleap.cobol.parser.metamodel.procedure.receive.ReceiveStatement;
import io.proleap.cobol.parser.metamodel.procedure.receive.WithData;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ReceiveIntoStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/receive/ReceiveIntoStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ReceiveIntoStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final ReceiveStatement receiveStatement = (ReceiveStatement) procedureDivision.getStatements().get(0);
			assertNotNull(receiveStatement);

			assertEquals(ReceiveStatement.Type.Into, receiveStatement.getType());
			assertNotNull(receiveStatement.getReceiveIntoStatement());

			final ReceiveIntoStatement receiveIntoStatement = receiveStatement.getReceiveIntoStatement();

			{
				final Call communicationDescriptionCall = receiveIntoStatement.getCommunicationDescriptionCall();
				assertNotNull(communicationDescriptionCall);
				assertEquals(Call.CallType.UndefinedCall, communicationDescriptionCall.getCallType());
			}

			{
				assertEquals(ReceiveIntoStatement.Type.Message, receiveIntoStatement.getType());
			}

			{
				final Call intoCall = receiveIntoStatement.getIntoCall();
				assertNotNull(intoCall);
				assertEquals(Call.CallType.UndefinedCall, intoCall.getCallType());
			}

			{
				final NoData noData = receiveIntoStatement.getNoData();
				assertNotNull(noData);
			}

			{
				final WithData withData = receiveIntoStatement.getWithData();
				assertNotNull(withData);
			}
		}
	}
}