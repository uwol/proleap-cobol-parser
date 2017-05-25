package io.proleap.cobol.asg.procedure.receive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.receive.NoData;
import io.proleap.cobol.asg.metamodel.procedure.receive.ReceiveIntoStatement;
import io.proleap.cobol.asg.metamodel.procedure.receive.ReceiveStatement;
import io.proleap.cobol.asg.metamodel.procedure.receive.WithData;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ReceiveIntoStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/receive/ReceiveIntoStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ReceiveIntoStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final ReceiveStatement receiveStatement = (ReceiveStatement) procedureDivision.getStatements().get(0);
			assertNotNull(receiveStatement);
			assertEquals(StatementTypeEnum.RECEIVE, receiveStatement.getStatementType());
			assertEquals(ReceiveStatement.ReceiveType.INTO, receiveStatement.getReceiveType());
			assertNotNull(receiveStatement.getReceiveIntoStatement());

			{
				final ReceiveIntoStatement receiveIntoStatement = receiveStatement.getReceiveIntoStatement();

				{
					final Call communicationDescriptionCall = receiveIntoStatement.getCommunicationDescriptionCall();
					assertNotNull(communicationDescriptionCall);
					assertEquals(CallType.UNDEFINED_CALL, communicationDescriptionCall.getCallType());
				}

				{
					assertEquals(ReceiveIntoStatement.ReceiveIntoType.MESSAGE,
							receiveIntoStatement.getReceiveIntoType());
				}

				{
					final Call intoCall = receiveIntoStatement.getIntoCall();
					assertNotNull(intoCall);
					assertEquals(CallType.UNDEFINED_CALL, intoCall.getCallType());
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
}
