package io.proleap.cobol.asg.procedure.string;

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
import io.proleap.cobol.asg.metamodel.procedure.string.DelimitedBy;
import io.proleap.cobol.asg.metamodel.procedure.string.For;
import io.proleap.cobol.asg.metamodel.procedure.string.Into;
import io.proleap.cobol.asg.metamodel.procedure.string.Sendings;
import io.proleap.cobol.asg.metamodel.procedure.string.StringStatement;
import io.proleap.cobol.asg.metamodel.procedure.string.WithPointer;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class StringStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/string/StringStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

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
				assertEquals(Sendings.Type.DELIMITED_BY, sendings.getType());

				final DelimitedBy delimitedBy = sendings.getDelimitedBy();
				assertEquals(DelimitedBy.Type.CHARACTERS, delimitedBy.getType());
				assertEquals("1", delimitedBy.getCharactersValueStmt().getValue());
			}

			{
				final Sendings sendings = stringStatement.getSendings().get(1);
				assertEquals(Sendings.Type.FOR, sendings.getType());

				final For sendingFor = sendings.getFor();
				final CallValueStmt forCallValueStmt = (CallValueStmt) sendingFor.getForValueStmt();
				assertEquals(Call.CallType.UNDEFINED_CALL, forCallValueStmt.getCall().getCallType());
			}

			{
				final Into into = stringStatement.getInto();
				assertNotNull(into);

				final Call intoCall = into.getIntoCall();
				assertEquals(Call.CallType.UNDEFINED_CALL, intoCall.getCallType());
			}

			{
				final WithPointer withPointer = stringStatement.getWithPointer();
				assertNotNull(withPointer);

				final Call pointerCall = withPointer.getPointerCall();
				assertEquals(Call.CallType.UNDEFINED_CALL, pointerCall.getCallType());
			}

			{
				final OnOverflow onOverflow = stringStatement.getOnOverflow();
				assertNotNull(onOverflow);
				assertEquals(1, onOverflow.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) onOverflow.getStatements().get(0);
				assertNotNull(displayStatement);
			}

			{
				final NotOnOverflow notOnOverflow = stringStatement.getNotOnOverflow();
				assertNotNull(notOnOverflow);
				assertEquals(1, notOnOverflow.getStatements().size());

				final DisplayStatement displayStatement = (DisplayStatement) notOnOverflow.getStatements().get(0);
				assertNotNull(displayStatement);
			}
		}
	}
}