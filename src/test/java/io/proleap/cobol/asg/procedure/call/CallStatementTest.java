package io.proleap.cobol.asg.procedure.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import io.proleap.cobol.asg.metamodel.procedure.call.ByContent;
import io.proleap.cobol.asg.metamodel.procedure.call.ByReference;
import io.proleap.cobol.asg.metamodel.procedure.call.CallByContentStatement;
import io.proleap.cobol.asg.metamodel.procedure.call.CallByReferenceStatement;
import io.proleap.cobol.asg.metamodel.procedure.call.CallByValueStatement;
import io.proleap.cobol.asg.metamodel.procedure.call.CallStatement;
import io.proleap.cobol.asg.metamodel.procedure.call.Giving;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CallStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/call/CallStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("CallStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final CallStatement callStatement = (CallStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.CALL, callStatement.getStatementType());

			{
				final Giving giving = callStatement.getGiving();
				assertNotNull(giving.getGivingCall());
				assertEquals(Call.CallType.UNDEFINED_CALL, giving.getGivingCall().getCallType());
			}

			{
				final CallByReferenceStatement callByReferenceStatement = callStatement.getCallByReferenceStatements()
						.get(0);
				assertEquals(2, callByReferenceStatement.getByReferences().size());

				{
					final ByReference byReference = callByReferenceStatement.getByReferences().get(0);
					assertEquals(ByReference.Type.INTEGER, byReference.getType());
					assertEquals(Call.CallType.UNDEFINED_CALL, byReference.getCall().getCallType());
				}

				{
					final ByReference byReference = callByReferenceStatement.getByReferences().get(1);
					assertNull(byReference.getType());
					assertEquals(Call.CallType.UNDEFINED_CALL, byReference.getCall().getCallType());
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
					assertEquals(ByContent.Type.ADDRESS_OF, byContent.getType());
					assertEquals(Call.CallType.UNDEFINED_CALL, byContent.getCall().getCallType());
				}

				{
					final ByContent byContent = callByContentStatement.getByContents().get(1);
					assertEquals(ByContent.Type.LENGTH_OF, byContent.getType());
					assertEquals(Call.CallType.UNDEFINED_CALL, byContent.getCall().getCallType());
				}

				{
					final ByContent byContent = callByContentStatement.getByContents().get(2);
					assertNull(byContent.getType());
					assertEquals(Call.CallType.UNDEFINED_CALL, byContent.getCall().getCallType());
				}
			}
		}
	}
}
