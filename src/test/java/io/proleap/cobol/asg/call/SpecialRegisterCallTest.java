package io.proleap.cobol.asg.call;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.SpecialRegisterCall;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SpecialRegisterCallTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/call/SpecialRegisterCall.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SpecialRegisterCall");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.ADDRESS_OF, specialRegisterCall.getType());
			assertEquals(Call.CallType.UNDEFINED_CALL, specialRegisterCall.getIdentifierCall().getCallType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(1);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.DATE, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(2);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.DAY, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(3);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.DAY_OF_WEEK, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(4);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.DEBUG_CONTENTS, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(5);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.DEBUG_ITEM, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(6);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.DEBUG_LINE, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(7);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.DEBUG_NAME, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(8);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.DEBUG_SUB_1, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(9);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.DEBUG_SUB_2, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(10);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.DEBUG_SUB_3, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(11);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.LENGTH_OF, specialRegisterCall.getType());
			assertEquals(Call.CallType.UNDEFINED_CALL, specialRegisterCall.getIdentifierCall().getCallType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(12);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.LINAGE_COUNTER, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(13);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.LINE_COUNTER, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(14);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.PAGE_COUNTER, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(15);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.RETURN_CODE, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(16);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.SHIFT_IN, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(17);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.SHIFT_OUT, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(18);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.SORT_CONTROL, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(19);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.SORT_CORE_SIZE, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(20);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.SORT_FILE_SIZE, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(21);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.SORT_MESSAGE, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(22);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.SORT_MODE_SIZE, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(23);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.SORT_RETURN, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(24);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.TALLY, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(25);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.TIME, specialRegisterCall.getType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(26);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.Type.WHEN_COMPILED, specialRegisterCall.getType());
		}
	}
}