package io.proleap.cobol.asg.procedure.delete;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.delete.DeleteStatement;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DeleteStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/delete/DeleteStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DeleteStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final DeleteStatement deleteStatement = (DeleteStatement) procedureDivision.getStatements().get(0);
			assertNotNull(deleteStatement);
			assertEquals(StatementTypeEnum.DELETE, deleteStatement.getStatementType());
			assertNotNull(deleteStatement.getFileCall());
			assertTrue(deleteStatement.isRecord());

			{
				final InvalidKeyPhrase invalidKey = deleteStatement.getInvalidKeyPhrase();
				assertNotNull(invalidKey);
				assertEquals(1, invalidKey.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) invalidKey.getStatements().get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
				}
			}

			{
				final NotInvalidKeyPhrase notInvalidKeyPhrase = deleteStatement.getNotInvalidKeyPhrase();
				assertNotNull(notInvalidKeyPhrase);
				assertEquals(1, notInvalidKeyPhrase.getStatements().size());

				{
					final DisplayStatement displayStatement = (DisplayStatement) notInvalidKeyPhrase.getStatements()
							.get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
				}
			}
		}
	}
}