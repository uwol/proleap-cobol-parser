package io.proleap.cobol.asg.procedure.merge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.merge.Alphanumeric;
import io.proleap.cobol.asg.metamodel.procedure.merge.CollatingSequence;
import io.proleap.cobol.asg.metamodel.procedure.merge.Giving;
import io.proleap.cobol.asg.metamodel.procedure.merge.Givings;
import io.proleap.cobol.asg.metamodel.procedure.merge.MergeStatement;
import io.proleap.cobol.asg.metamodel.procedure.merge.National;
import io.proleap.cobol.asg.metamodel.procedure.merge.OnKey;
import io.proleap.cobol.asg.metamodel.procedure.merge.OutputProcedure;
import io.proleap.cobol.asg.metamodel.procedure.merge.OutputThrough;
import io.proleap.cobol.asg.metamodel.procedure.merge.Using;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class MergeStatementTest extends CobolTestSupport {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/merge/MergeStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("MergeStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final MergeStatement mergeStatement = (MergeStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.MERGE, mergeStatement.getStatementType());
			assertNotNull(mergeStatement);

			{
				final Call fileCall = mergeStatement.getFileCall();
				assertNotNull(fileCall);
				assertEquals(Call.CallType.UNDEFINED_CALL, fileCall.getCallType());
			}

			{
				assertEquals(2, mergeStatement.getOnKeys().size());

				{
					final OnKey onKey = mergeStatement.getOnKeys().get(0);
					assertEquals(OnKey.Type.DESCENDING, onKey.getType());
					assertEquals(1, onKey.getKeyCalls().size());

					{
						final Call keyCall = onKey.getKeyCalls().get(0);
						assertEquals(Call.CallType.UNDEFINED_CALL, keyCall.getCallType());
					}
				}

				{
					final OnKey onKey = mergeStatement.getOnKeys().get(1);
					assertEquals(OnKey.Type.ASCENDING, onKey.getType());
					assertEquals(2, onKey.getKeyCalls().size());

					{
						final Call keyCall = onKey.getKeyCalls().get(0);
						assertEquals(Call.CallType.UNDEFINED_CALL, keyCall.getCallType());
					}

					{
						final Call keyCall = onKey.getKeyCalls().get(1);
						assertEquals(Call.CallType.UNDEFINED_CALL, keyCall.getCallType());
					}
				}
			}

			{
				final CollatingSequence collatingSequence = mergeStatement.getCollatingSequence();
				assertEquals(2, collatingSequence.getAlphabetCalls().size());

				{
					final Alphanumeric alphaNumeric = collatingSequence.getAlphaNumeric();
					assertNotNull(alphaNumeric);

					final Call alphabetCall = alphaNumeric.getAlphabetCall();
					assertEquals(Call.CallType.UNDEFINED_CALL, alphabetCall.getCallType());
				}

				{
					final National national = collatingSequence.getNational();
					assertNotNull(national);

					final Call alphabetCall = national.getAlphabetCall();
					assertEquals(Call.CallType.UNDEFINED_CALL, alphabetCall.getCallType());
				}
			}

			{
				assertEquals(1, mergeStatement.getUsings().size());

				final Using using = mergeStatement.getUsings().get(0);
				assertEquals(1, using.getFileCalls().size());

				final Call fileCall = using.getFileCalls().get(0);
				assertEquals(Call.CallType.UNDEFINED_CALL, fileCall.getCallType());
			}

			{
				final OutputProcedure outputProcedure = mergeStatement.getOutputProcedure();
				assertNotNull(outputProcedure);

				{
					final Call procedureCall = outputProcedure.getProcedureCall();
					assertNotNull(procedureCall);
					assertEquals(Call.CallType.UNDEFINED_CALL, procedureCall.getCallType());
				}

				{
					final OutputThrough outputThrough = outputProcedure.getOutputThrough();
					assertNotNull(outputThrough);

					final Call procedureCall = outputThrough.getProcedureCall();
					assertNotNull(procedureCall);
					assertEquals(Call.CallType.UNDEFINED_CALL, procedureCall.getCallType());
				}
			}

			{
				assertEquals(1, mergeStatement.getGivings().size());

				final Givings givings = mergeStatement.getGivings().get(0);
				assertNotNull(givings);
				assertEquals(1, givings.getGivings().size());

				{
					final Giving giving = givings.getGivings().get(0);
					assertNotNull(giving);
					assertEquals(Giving.CloseProcedure.NO_REWIND, giving.getCloseProcedure());

					final Call fileCall = giving.getFileCall();
					assertEquals(Call.CallType.UNDEFINED_CALL, fileCall.getCallType());
				}
			}
		}
	}
}