package io.proleap.cobol.asg.procedure.merge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.merge.Alphanumeric;
import io.proleap.cobol.asg.metamodel.procedure.merge.CollatingSequencePhrase;
import io.proleap.cobol.asg.metamodel.procedure.merge.Giving;
import io.proleap.cobol.asg.metamodel.procedure.merge.GivingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.merge.MergeStatement;
import io.proleap.cobol.asg.metamodel.procedure.merge.National;
import io.proleap.cobol.asg.metamodel.procedure.merge.OnKey;
import io.proleap.cobol.asg.metamodel.procedure.merge.OutputProcedurePhrase;
import io.proleap.cobol.asg.metamodel.procedure.merge.OutputThrough;
import io.proleap.cobol.asg.metamodel.procedure.merge.UsingPhrase;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class MergeStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/merge/MergeStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("MergeStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(3, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final MergeStatement mergeStatement = (MergeStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.MERGE, mergeStatement.getStatementType());
			assertNotNull(mergeStatement);

			{
				final Call fileCall = mergeStatement.getFileCall();
				assertNotNull(fileCall);
				assertEquals(CallType.UNDEFINED_CALL, fileCall.getCallType());
			}

			{
				assertEquals(2, mergeStatement.getOnKeys().size());

				{
					final OnKey onKey = mergeStatement.getOnKeys().get(0);
					assertEquals(OnKey.OnKeyType.DESCENDING, onKey.getOnKeyType());
					assertEquals(1, onKey.getKeyCalls().size());

					{
						final Call keyCall = onKey.getKeyCalls().get(0);
						assertEquals(CallType.UNDEFINED_CALL, keyCall.getCallType());
					}
				}

				{
					final OnKey onKey = mergeStatement.getOnKeys().get(1);
					assertEquals(OnKey.OnKeyType.ASCENDING, onKey.getOnKeyType());
					assertEquals(2, onKey.getKeyCalls().size());

					{
						final Call keyCall = onKey.getKeyCalls().get(0);
						assertEquals(CallType.UNDEFINED_CALL, keyCall.getCallType());
					}

					{
						final Call keyCall = onKey.getKeyCalls().get(1);
						assertEquals(CallType.UNDEFINED_CALL, keyCall.getCallType());
					}
				}
			}

			{
				final CollatingSequencePhrase collatingSequencePhrase = mergeStatement.getCollatingSequencePhrase();
				assertEquals(2, collatingSequencePhrase.getAlphabetCalls().size());

				{
					final Alphanumeric alphaNumeric = collatingSequencePhrase.getAlphaNumeric();
					assertNotNull(alphaNumeric);

					final Call alphabetCall = alphaNumeric.getAlphabetCall();
					assertEquals(CallType.UNDEFINED_CALL, alphabetCall.getCallType());
				}

				{
					final National national = collatingSequencePhrase.getNational();
					assertNotNull(national);

					final Call alphabetCall = national.getAlphabetCall();
					assertEquals(CallType.UNDEFINED_CALL, alphabetCall.getCallType());
				}
			}

			{
				assertEquals(1, mergeStatement.getUsingPhrases().size());

				final UsingPhrase usingPhrase = mergeStatement.getUsingPhrases().get(0);
				assertEquals(1, usingPhrase.getFileCalls().size());

				final Call fileCall = usingPhrase.getFileCalls().get(0);
				assertEquals(CallType.UNDEFINED_CALL, fileCall.getCallType());
			}

			{
				final OutputProcedurePhrase outputProcedure = mergeStatement.getOutputProcedurePhrase();
				assertNotNull(outputProcedure);

				{
					final Call procedureCall = outputProcedure.getProcedureCall();
					assertNotNull(procedureCall);
					assertEquals(CallType.PROCEDURE_CALL, procedureCall.getCallType());
				}

				{
					final OutputThrough outputThrough = outputProcedure.getOutputThrough();
					assertNotNull(outputThrough);

					final Call procedureCall = outputThrough.getProcedureCall();
					assertNotNull(procedureCall);
					assertEquals(CallType.PROCEDURE_CALL, procedureCall.getCallType());
				}

				{
					final List<Call> calls = outputProcedure.getCalls();
					assertEquals(3, calls.size());

					{
						assertEquals(CallType.PROCEDURE_CALL, calls.get(0).getCallType());
					}

					{
						assertEquals(CallType.PROCEDURE_CALL, calls.get(1).getCallType());
					}

					{
						assertEquals(CallType.PROCEDURE_CALL, calls.get(2).getCallType());
					}
				}
			}

			{
				assertEquals(1, mergeStatement.getGivingPhrases().size());

				final GivingPhrase givingPhrases = mergeStatement.getGivingPhrases().get(0);
				assertNotNull(givingPhrases);
				assertEquals(1, givingPhrases.getGivings().size());

				{
					final Giving giving = givingPhrases.getGivings().get(0);
					assertNotNull(giving);
					assertEquals(Giving.CloseProcedure.NO_REWIND, giving.getCloseProcedure());

					final Call fileCall = giving.getFileCall();
					assertEquals(CallType.UNDEFINED_CALL, fileCall.getCallType());
				}
			}
		}
	}
}
