package io.proleap.cobol.asg.procedure.set;

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
import io.proleap.cobol.asg.metamodel.procedure.set.By;
import io.proleap.cobol.asg.metamodel.procedure.set.SetBy;
import io.proleap.cobol.asg.metamodel.procedure.set.SetStatement;
import io.proleap.cobol.asg.metamodel.procedure.set.To;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SetByStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/set/SetByStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SetByStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final SetStatement setStatement = (SetStatement) procedureDivision.getStatements().get(0);
			assertNotNull(setStatement);
			assertEquals(StatementTypeEnum.SET, setStatement.getStatementType());
			assertEquals(SetStatement.SetType.BY, setStatement.getSetType());

			{
				final SetBy setBy = setStatement.getSetBy();

				{
					assertEquals(2, setBy.getTos().size());

					{
						final To to = setBy.getTos().get(0);
						final Call toCall = to.getToCall();
						assertNotNull(toCall);
						assertEquals(CallType.UNDEFINED_CALL, toCall.getCallType());
					}

					{
						final To to = setBy.getTos().get(1);
						final Call toCall = to.getToCall();
						assertNotNull(toCall);
						assertEquals(CallType.UNDEFINED_CALL, toCall.getCallType());
					}

					{
						final By by = setBy.getBy();
						assertNotNull(by);

						final ValueStmt valueValueStmt = by.getByValueStmt();
						assertNotNull(valueValueStmt);

						final CallValueStmt callValueStmt = (CallValueStmt) valueValueStmt;
						assertEquals(CallType.UNDEFINED_CALL, callValueStmt.getCall().getCallType());
					}
				}
			}
		}
	}
}
