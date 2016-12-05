package io.proleap.cobol.asg.procedure.read;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.AtEnd;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKey;
import io.proleap.cobol.asg.metamodel.procedure.NotAtEnd;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKey;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.read.Into;
import io.proleap.cobol.asg.metamodel.procedure.read.Key;
import io.proleap.cobol.asg.metamodel.procedure.read.ReadStatement;
import io.proleap.cobol.asg.metamodel.procedure.read.With;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ReadStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/read/ReadStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ReadStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final ReadStatement readStatement = (ReadStatement) procedureDivision.getStatements().get(0);
			assertNotNull(readStatement);
			assertEquals(StatementTypeEnum.Read, readStatement.getStatementType());

			{
				assertNotNull(readStatement.getFileCall());
				assertEquals(Call.CallType.UndefinedCall, readStatement.getFileCall().getCallType());
			}

			assertTrue(readStatement.isNextRecord());

			{
				final Into into = readStatement.getInto();
				assertNotNull(into);
				assertNotNull(into.getIntoCall());
				assertEquals(Call.CallType.UndefinedCall, into.getIntoCall().getCallType());
			}

			{
				final With with = readStatement.getWith();
				assertNotNull(with);
				assertNotNull(with.getType());
				assertEquals(With.Type.Wait, with.getType());
			}

			{
				final Key key = readStatement.getKey();
				assertNotNull(key.getKeyCall());
				assertEquals(Call.CallType.UndefinedCall, key.getKeyCall().getCallType());
			}

			{
				final InvalidKey invalidKey = readStatement.getInvalidKey();
				assertNotNull(invalidKey);
				assertEquals(1, invalidKey.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) invalidKey.getStatements().get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.Display, displayStatement.getStatementType());
				}
			}

			{
				final NotInvalidKey notInvalidKey = readStatement.getNotInvalidKey();
				assertNotNull(notInvalidKey);
				assertEquals(1, notInvalidKey.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) notInvalidKey.getStatements().get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.Display, displayStatement.getStatementType());
				}
			}

			{
				final AtEnd atEnd = readStatement.getAtEnd();
				assertNotNull(atEnd);
				assertEquals(1, atEnd.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) atEnd.getStatements().get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.Display, displayStatement.getStatementType());
				}
			}

			{
				final NotAtEnd notAtEnd = readStatement.getNotAtEnd();
				assertNotNull(notAtEnd);
				assertEquals(1, notAtEnd.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) notAtEnd.getStatements().get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.Display, displayStatement.getStatementType());
				}
			}
		}
	}
}