package io.proleap.cobol.asg.procedure.unstring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
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
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class UnstringStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/unstring/UnstringStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("UnstringStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final UnstringStatement unstringStatement = (UnstringStatement) procedureDivision.getStatements().get(0);
			assertNotNull(unstringStatement);
			assertEquals(StatementTypeEnum.Unstring, unstringStatement.getStatementType());

			{
				final Sending sending = unstringStatement.getSending();
				assertNotNull(sending);

				{
					final Call sendingCall = sending.getSendingCall();
					assertEquals(Call.CallType.UndefinedCall, sendingCall.getCallType());

					{
						final DelimitedBy delimitedBy = sending.getDelimitedBy();
						final Call delimitedByCall = delimitedBy.getDelimitedByCall();
						assertEquals(Call.CallType.UndefinedCall, delimitedByCall.getCallType());
					}

					{
						assertEquals(2, sending.getOrAlls().size());

						{
							final OrAll orAll = sending.getOrAlls().get(0);
							final Call orAllCall = orAll.getOrAllCall();
							assertEquals(Call.CallType.UndefinedCall, orAllCall.getCallType());
						}

						{
							final OrAll orAll = sending.getOrAlls().get(1);
							final Call orAllCall = orAll.getOrAllCall();
							assertEquals(Call.CallType.UndefinedCall, orAllCall.getCallType());
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
					assertEquals(Call.CallType.UndefinedCall, intoCall.getCallType());

					{
						final DelimiterIn delimiterIn = into.getDelimiterIn();
						final Call delimiterInCall = delimiterIn.getDelimiterInCall();
						assertEquals(Call.CallType.UndefinedCall, delimiterInCall.getCallType());
					}
				}

				{
					final io.proleap.cobol.asg.metamodel.procedure.unstring.Into into = intos.getIntos().get(1);
					final Call intoCall = into.getIntoCall();
					assertEquals(Call.CallType.UndefinedCall, intoCall.getCallType());

					{
						final CountIn countIn = into.getCountIn();
						final Call countInCall = countIn.getCountInCall();
						assertEquals(Call.CallType.UndefinedCall, countInCall.getCallType());
					}
				}
			}

			{
				final WithPointer withPointer = unstringStatement.getWithPointer();
				final Call pointerCall = withPointer.getPointerCall();
				assertEquals(Call.CallType.UndefinedCall, pointerCall.getCallType());
			}

			{
				final OnOverflow onOverflow = unstringStatement.getOnOverflow();
				assertNotNull(onOverflow);
				assertEquals(1, onOverflow.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) onOverflow.getStatements().get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.Display, displayStatement.getStatementType());
				}
			}

			{
				final NotOnOverflow notOnOverflow = unstringStatement.getNotOnOverflow();
				assertNotNull(notOnOverflow);
				assertEquals(1, notOnOverflow.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) notOnOverflow.getStatements().get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.Display, displayStatement.getStatementType());
				}
			}
		}
	}
}