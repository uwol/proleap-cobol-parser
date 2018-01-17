package io.proleap.cobol.asg.procedure.unstring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.NotOnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.unstring.CountIn;
import io.proleap.cobol.asg.metamodel.procedure.unstring.DelimitedByPhrase;
import io.proleap.cobol.asg.metamodel.procedure.unstring.DelimiterIn;
import io.proleap.cobol.asg.metamodel.procedure.unstring.IntoPhrase;
import io.proleap.cobol.asg.metamodel.procedure.unstring.OrAll;
import io.proleap.cobol.asg.metamodel.procedure.unstring.Sending;
import io.proleap.cobol.asg.metamodel.procedure.unstring.UnstringStatement;
import io.proleap.cobol.asg.metamodel.procedure.unstring.WithPointerPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class UnstringStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/unstring/UnstringStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("UnstringStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final UnstringStatement unstringStatement = (UnstringStatement) procedureDivision.getStatements().get(0);
			assertNotNull(unstringStatement);
			assertEquals(StatementTypeEnum.UNSTRING, unstringStatement.getStatementType());

			{
				final Sending sending = unstringStatement.getSending();
				assertNotNull(sending);

				{
					final Call sendingCall = sending.getSendingCall();
					assertEquals(CallType.UNDEFINED_CALL, sendingCall.getCallType());

					{
						final DelimitedByPhrase delimitedByPhrase = sending.getDelimitedByPhrase();
						final LiteralValueStmt delimitedByValueStmt = (LiteralValueStmt) delimitedByPhrase
								.getDelimitedByValueStmt();
						final Literal literal = delimitedByValueStmt.getLiteral();
						assertEquals("1", literal.getValue());
					}

					{
						assertEquals(2, sending.getOrAlls().size());

						{
							final OrAll orAll = sending.getOrAlls().get(0);
							final ValueStmt orAllValueStmt = orAll.getOrAllValueStmt();

							final CallValueStmt orAllCallValueStmt = (CallValueStmt) orAllValueStmt;
							assertEquals(CallType.UNDEFINED_CALL, orAllCallValueStmt.getCall().getCallType());
						}

						{
							final OrAll orAll = sending.getOrAlls().get(1);
							final ValueStmt orAllValueStmt = orAll.getOrAllValueStmt();

							final CallValueStmt orAllCallValueStmt = (CallValueStmt) orAllValueStmt;
							assertEquals(CallType.UNDEFINED_CALL, orAllCallValueStmt.getCall().getCallType());
						}
					}
				}
			}

			{
				final IntoPhrase intoPhrase = unstringStatement.getIntoPhrase();
				assertNotNull(intoPhrase);
				assertEquals(2, intoPhrase.getIntos().size());

				{
					final io.proleap.cobol.asg.metamodel.procedure.unstring.Into into = intoPhrase.getIntos().get(0);
					final Call intoCall = into.getIntoCall();
					assertEquals(CallType.UNDEFINED_CALL, intoCall.getCallType());

					{
						final DelimiterIn delimiterIn = into.getDelimiterIn();
						final Call delimiterInCall = delimiterIn.getDelimiterInCall();
						assertEquals(CallType.UNDEFINED_CALL, delimiterInCall.getCallType());
					}
				}

				{
					final io.proleap.cobol.asg.metamodel.procedure.unstring.Into into = intoPhrase.getIntos().get(1);
					final Call intoCall = into.getIntoCall();
					assertEquals(CallType.UNDEFINED_CALL, intoCall.getCallType());

					{
						final CountIn countIn = into.getCountIn();
						final Call countInCall = countIn.getCountInCall();
						assertEquals(CallType.UNDEFINED_CALL, countInCall.getCallType());
					}
				}
			}

			{
				final WithPointerPhrase withPointerPhrase = unstringStatement.getWithPointerPhrase();
				final Call pointerCall = withPointerPhrase.getPointerCall();
				assertEquals(CallType.UNDEFINED_CALL, pointerCall.getCallType());
			}

			{
				final OnOverflowPhrase onOverflowPhrase = unstringStatement.getOnOverflowPhrase();
				assertNotNull(onOverflowPhrase);
				assertEquals(1, onOverflowPhrase.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) onOverflowPhrase.getStatements()
							.get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
				}
			}

			{
				final NotOnOverflowPhrase notOnOverflowPhrase = unstringStatement.getNotOnOverflowPhrase();
				assertNotNull(notOnOverflowPhrase);
				assertEquals(1, notOnOverflowPhrase.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) notOnOverflowPhrase.getStatements()
							.get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
				}
			}
		}
	}
}
