package io.proleap.cobol.asg.procedure.inspect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.inspect.BeforeAfterPhrase;
import io.proleap.cobol.asg.metamodel.procedure.inspect.Converting;
import io.proleap.cobol.asg.metamodel.procedure.inspect.InspectStatement;
import io.proleap.cobol.asg.metamodel.procedure.inspect.To;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class InspectConvertingStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/inspect/InspectConvertingStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("InspectConvertingStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final InspectStatement inspectStatement = (InspectStatement) procedureDivision.getStatements().get(0);
			assertNotNull(inspectStatement);
			assertEquals(StatementTypeEnum.INSPECT, inspectStatement.getStatementType());
			assertEquals(InspectStatement.InspectType.CONVERTING, inspectStatement.getInspectType());

			{
				final Converting converting = inspectStatement.getConverting();

				{
					assertNotNull(converting.getFromValueStmt());

					final CallValueStmt fromCallValueStmt = (CallValueStmt) converting.getFromValueStmt();
					assertEquals(CallType.UNDEFINED_CALL, fromCallValueStmt.getCall().getCallType());
				}

				{
					final To to = converting.getTo();
					assertNotNull(to);
					assertNotNull(to.getToValueStmt());

					final CallValueStmt toCallValueStmt = (CallValueStmt) to.getToValueStmt();
					assertEquals(CallType.UNDEFINED_CALL, toCallValueStmt.getCall().getCallType());
				}

				assertEquals(1, converting.getBeforeAfterPhrases().size());

				{
					{
						final BeforeAfterPhrase beforeAfterPhrase = converting.getBeforeAfterPhrases().get(0);
						assertEquals(BeforeAfterPhrase.BeforeAfterType.AFTER, beforeAfterPhrase.getBeforeAfterType());
					}
				}
			}
		}
	}
}
