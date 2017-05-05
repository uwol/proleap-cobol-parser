package io.proleap.cobol.asg.procedure.unstring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.NotOnOverflow;
import io.proleap.cobol.asg.metamodel.procedure.OnOverflow;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.unstring.CountIn;
import io.proleap.cobol.asg.metamodel.procedure.unstring.DelimitedBy;
import io.proleap.cobol.asg.metamodel.procedure.unstring.DelimiterIn;
import io.proleap.cobol.asg.metamodel.procedure.unstring.Intos;
import io.proleap.cobol.asg.metamodel.procedure.unstring.OrAll;
import io.proleap.cobol.asg.metamodel.procedure.unstring.Sending;
import io.proleap.cobol.asg.metamodel.procedure.unstring.UnstringStatement;
import io.proleap.cobol.asg.metamodel.procedure.unstring.WithPointer;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
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
					assertEquals(Call.CallType.UNDEFINED_CALL, sendingCall.getCallType());

					{
						final DelimitedBy delimitedBy = sending.getDelimitedBy();
						final ValueStmt delimitedByValueStmt = delimitedBy.getDelimitedByValueStmt();
						assertEquals("1", delimitedByValueStmt.getValue());
					}

					{
						assertEquals(2, sending.getOrAlls().size());

						{
							final OrAll orAll = sending.getOrAlls().get(0);
							final ValueStmt orAllValueStmt = orAll.getOrAllValueStmt();

							final CallValueStmt orAllCallValueStmt = (CallValueStmt) orAllValueStmt;
							assertEquals(Call.CallType.UNDEFINED_CALL, orAllCallValueStmt.getCall().getCallType());
						}

						{
							final OrAll orAll = sending.getOrAlls().get(1);
							final ValueStmt orAllValueStmt = orAll.getOrAllValueStmt();

							final CallValueStmt orAllCallValueStmt = (CallValueStmt) orAllValueStmt;
							assertEquals(Call.CallType.UNDEFINED_CALL, orAllCallValueStmt.getCall().getCallType());
						}
					}
				}
			}

			{
				final Intos intos = unstringStatement.getIntos();
				assertNotNull(intos);
				assertEquals(2, intos.getIntos().size());

				{
					final io.proleap.cobol.asg.metamodel.procedure.unstring.Into into = intos.getIntos().get(0);
					final Call intoCall = into.getIntoCall();
					assertEquals(Call.CallType.UNDEFINED_CALL, intoCall.getCallType());

					{
						final DelimiterIn delimiterIn = into.getDelimiterIn();
						final Call delimiterInCall = delimiterIn.getDelimiterInCall();
						assertEquals(Call.CallType.UNDEFINED_CALL, delimiterInCall.getCallType());
					}
				}

				{
					final io.proleap.cobol.asg.metamodel.procedure.unstring.Into into = intos.getIntos().get(1);
					final Call intoCall = into.getIntoCall();
					assertEquals(Call.CallType.UNDEFINED_CALL, intoCall.getCallType());

					{
						final CountIn countIn = into.getCountIn();
						final Call countInCall = countIn.getCountInCall();
						assertEquals(Call.CallType.UNDEFINED_CALL, countInCall.getCallType());
					}
				}
			}

			{
				final WithPointer withPointer = unstringStatement.getWithPointer();
				final Call pointerCall = withPointer.getPointerCall();
				assertEquals(Call.CallType.UNDEFINED_CALL, pointerCall.getCallType());
			}

			{
				final OnOverflow onOverflow = unstringStatement.getOnOverflow();
				assertNotNull(onOverflow);
				assertEquals(1, onOverflow.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) onOverflow.getStatements().get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
				}
			}

			{
				final NotOnOverflow notOnOverflow = unstringStatement.getNotOnOverflow();
				assertNotNull(notOnOverflow);
				assertEquals(1, notOnOverflow.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) notOnOverflow.getStatements().get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
				}
			}
		}
	}
}