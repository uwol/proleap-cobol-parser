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
			assertEquals(SpecialRegisterCall.SpecialRegisterType.ADDRESS_OF,
					specialRegisterCall.getSpecialRegisterType());
			assertEquals(Call.CallType.UNDEFINED_CALL, specialRegisterCall.getIdentifierCall().getCallType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(1);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.DATE, specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(2);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.DAY, specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(3);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.DAY_OF_WEEK,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(4);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.DEBUG_CONTENTS,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(5);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.DEBUG_ITEM,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(6);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.DEBUG_LINE,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(7);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.DEBUG_NAME,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(8);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.DEBUG_SUB_1,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(9);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.DEBUG_SUB_2,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(10);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.DEBUG_SUB_3,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(11);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.LENGTH_OF,
					specialRegisterCall.getSpecialRegisterType());
			assertEquals(Call.CallType.UNDEFINED_CALL, specialRegisterCall.getIdentifierCall().getCallType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(12);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.LINAGE_COUNTER,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(13);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.LINE_COUNTER,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(14);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.PAGE_COUNTER,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(15);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.RETURN_CODE,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(16);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.SHIFT_IN,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(17);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.SHIFT_OUT,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(18);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.SORT_CONTROL,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(19);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.SORT_CORE_SIZE,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(20);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.SORT_FILE_SIZE,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(21);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.SORT_MESSAGE,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(22);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.SORT_MODE_SIZE,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(23);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.SORT_RETURN,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(24);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.TALLY, specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(25);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.TIME, specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(26);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.WHEN_COMPILED,
					specialRegisterCall.getSpecialRegisterType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(27);
			assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());

			final Call call = acceptStatement.getAcceptCall();
			assertEquals(Call.CallType.SPECIAL_REGISTER_CALL, call.getCallType());

			final SpecialRegisterCall specialRegisterCall = (SpecialRegisterCall) call.unwrap();
			assertEquals(SpecialRegisterCall.SpecialRegisterType.LENGTH_OF,
					specialRegisterCall.getSpecialRegisterType());
			assertEquals(Call.CallType.UNDEFINED_CALL, specialRegisterCall.getIdentifierCall().getCallType());
		}
	}
}