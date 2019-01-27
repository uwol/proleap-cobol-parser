package io.proleap.cobol.asg.procedure.sort;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import io.proleap.cobol.asg.metamodel.procedure.sort.Alphanumeric;
import io.proleap.cobol.asg.metamodel.procedure.sort.CollatingSequence;
import io.proleap.cobol.asg.metamodel.procedure.sort.Duplicates;
import io.proleap.cobol.asg.metamodel.procedure.sort.Giving;
import io.proleap.cobol.asg.metamodel.procedure.sort.GivingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.sort.InputProcedure;
import io.proleap.cobol.asg.metamodel.procedure.sort.InputThrough;
import io.proleap.cobol.asg.metamodel.procedure.sort.National;
import io.proleap.cobol.asg.metamodel.procedure.sort.OnKey;
import io.proleap.cobol.asg.metamodel.procedure.sort.OutputProcedure;
import io.proleap.cobol.asg.metamodel.procedure.sort.OutputThrough;
import io.proleap.cobol.asg.metamodel.procedure.sort.SortStatement;
import io.proleap.cobol.asg.metamodel.procedure.sort.UsingPhrase;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SortStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/sort/SortStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SortStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(3, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final SortStatement sortStatement = (SortStatement) procedureDivision.getStatements().get(0);
			assertNotNull(sortStatement);
			assertEquals(StatementTypeEnum.SORT, sortStatement.getStatementType());

			{
				final Call fileCall = sortStatement.getFileCall();
				assertNotNull(fileCall);
				assertEquals(CallType.UNDEFINED_CALL, fileCall.getCallType());
			}

			{
				assertEquals(2, sortStatement.getOnKeys().size());

				{
					final OnKey onKey = sortStatement.getOnKeys().get(0);
					assertEquals(OnKey.OnKeyType.DESCENDING, onKey.getOnKeyType());
					assertEquals(1, onKey.getKeyCalls().size());

					{
						final Call keyCall = onKey.getKeyCalls().get(0);
						assertEquals(CallType.UNDEFINED_CALL, keyCall.getCallType());
					}
				}

				{
					final OnKey onKey = sortStatement.getOnKeys().get(1);
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
				final InputProcedure inputProcedure = sortStatement.getInputProcedure();
				assertNotNull(inputProcedure);

				{
					final Call procedureCall = inputProcedure.getProcedureCall();
					assertNotNull(procedureCall);
					assertEquals(CallType.PROCEDURE_CALL, procedureCall.getCallType());
				}

				{
					final InputThrough inputThrough = inputProcedure.getInputThrough();
					assertNotNull(inputThrough);

					final Call procedureCall = inputThrough.getProcedureCall();
					assertNotNull(procedureCall);
					assertEquals(CallType.PROCEDURE_CALL, procedureCall.getCallType());
				}

				{
					final List<Call> calls = inputProcedure.getCalls();
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
				final CollatingSequence collatingSequence = sortStatement.getCollatingSequence();
				assertEquals(2, collatingSequence.getAlphabetCalls().size());

				{
					final Alphanumeric alphaNumeric = collatingSequence.getAlphaNumeric();
					assertNotNull(alphaNumeric);

					final Call alphabetCall = alphaNumeric.getAlphabetCall();
					assertEquals(CallType.UNDEFINED_CALL, alphabetCall.getCallType());
				}

				{
					final National national = collatingSequence.getNational();
					assertNotNull(national);

					final Call alphabetCall = national.getAlphabetCall();
					assertEquals(CallType.UNDEFINED_CALL, alphabetCall.getCallType());
				}
			}

			{
				final Duplicates duplicates = sortStatement.getDuplicates();
				assertNotNull(duplicates);
				assertTrue(duplicates.isDuplicatesInOrder());
			}

			{
				assertEquals(1, sortStatement.getUsingPhrases().size());

				final UsingPhrase usingPhrase = sortStatement.getUsingPhrases().get(0);
				assertEquals(1, usingPhrase.getFileCalls().size());

				final Call fileCall = usingPhrase.getFileCalls().get(0);
				assertEquals(CallType.UNDEFINED_CALL, fileCall.getCallType());
			}

			{
				final OutputProcedure outputProcedure = sortStatement.getOutputProcedure();
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
				assertEquals(1, sortStatement.getGivingPhrases().size());

				final GivingPhrase givingPhrase = sortStatement.getGivingPhrases().get(0);
				assertNotNull(givingPhrase);
				assertEquals(1, givingPhrase.getGivings().size());

				{
					final Giving giving = givingPhrase.getGivings().get(0);
					assertNotNull(giving);
					assertEquals(Giving.CloseProcedure.NO_REWIND, giving.getCloseProcedure());

					final Call fileCall = giving.getFileCall();
					assertEquals(CallType.UNDEFINED_CALL, fileCall.getCallType());
				}
			}
		}
	}
}
