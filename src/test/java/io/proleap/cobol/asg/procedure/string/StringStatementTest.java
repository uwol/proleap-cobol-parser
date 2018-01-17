package io.proleap.cobol.asg.procedure.string;

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
import io.proleap.cobol.asg.metamodel.procedure.string.DelimitedByPhrase;
import io.proleap.cobol.asg.metamodel.procedure.string.ForPhrase;
import io.proleap.cobol.asg.metamodel.procedure.string.IntoPhrase;
import io.proleap.cobol.asg.metamodel.procedure.string.Sendings;
import io.proleap.cobol.asg.metamodel.procedure.string.StringStatement;
import io.proleap.cobol.asg.metamodel.procedure.string.WithPointerPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class StringStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/string/StringStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("StringStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final StringStatement stringStatement = (StringStatement) procedureDivision.getStatements().get(0);
			assertNotNull(stringStatement);
			assertEquals(StatementTypeEnum.STRING, stringStatement.getStatementType());
			assertEquals(2, stringStatement.getSendings().size());

			{
				final Sendings sendings = stringStatement.getSendings().get(0);
				assertEquals(Sendings.SendingsType.DELIMITED_BY, sendings.getSendingsType());

				final DelimitedByPhrase delimitedByPhrase = sendings.getDelimitedByPhrase();
				assertEquals(DelimitedByPhrase.DelimitedByType.CHARACTERS, delimitedByPhrase.getDelimitedByType());

				final LiteralValueStmt charactersValueStmt = (LiteralValueStmt) delimitedByPhrase
						.getCharactersValueStmt();
				final Literal literal = charactersValueStmt.getLiteral();
				assertEquals("1", literal.getValue());
			}

			{
				final Sendings sendings = stringStatement.getSendings().get(1);
				assertEquals(Sendings.SendingsType.FOR, sendings.getSendingsType());

				final ForPhrase forPhrase = sendings.getForPhrase();
				final CallValueStmt forCallValueStmt = (CallValueStmt) forPhrase.getForValueStmt();
				assertEquals(CallType.UNDEFINED_CALL, forCallValueStmt.getCall().getCallType());
			}

			{
				final IntoPhrase intoPhrase = stringStatement.getIntoPhrase();
				assertNotNull(intoPhrase);

				final Call intoCall = intoPhrase.getIntoCall();
				assertEquals(CallType.UNDEFINED_CALL, intoCall.getCallType());
			}

			{
				final WithPointerPhrase withPointerPhrase = stringStatement.getWithPointerPhrase();
				assertNotNull(withPointerPhrase);

				final Call pointerCall = withPointerPhrase.getPointerCall();
				assertEquals(CallType.UNDEFINED_CALL, pointerCall.getCallType());
			}

			{
				final OnOverflowPhrase onOverflowPhrase = stringStatement.getOnOverflowPhrase();
				assertNotNull(onOverflowPhrase);
				assertEquals(1, onOverflowPhrase.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) onOverflowPhrase.getStatements().get(0);
				assertNotNull(displayStatement);
			}

			{
				final NotOnOverflowPhrase notOnOverflowPhrase = stringStatement.getNotOnOverflowPhrase();
				assertNotNull(notOnOverflowPhrase);
				assertEquals(1, notOnOverflowPhrase.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) notOnOverflowPhrase.getStatements().get(0);
				assertNotNull(displayStatement);
			}
		}
	}
}