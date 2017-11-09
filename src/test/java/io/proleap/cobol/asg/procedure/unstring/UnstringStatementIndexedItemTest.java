package io.proleap.cobol.asg.procedure.unstring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.EvaluateStatement;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformInlineStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformStatement.PerformStatementType;
import io.proleap.cobol.asg.metamodel.procedure.unstring.IntoPhrase;
import io.proleap.cobol.asg.metamodel.procedure.unstring.Sending;
import io.proleap.cobol.asg.metamodel.procedure.unstring.TallyingPhrase;
import io.proleap.cobol.asg.metamodel.procedure.unstring.UnstringStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class UnstringStatementIndexedItemTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/unstring/UnstringStatementIndexedItem.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.FIXED);

		final CompilationUnit compilationUnit = program.getCompilationUnit("UnstringStatementIndexedItem");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(2, procedureDivision.getParagraphs().size());
		assertEquals(2, procedureDivision.getStatements().size());

		{
			final Paragraph paragraphB70100 = procedureDivision.getParagraph("B70100");
			assertEquals(3, paragraphB70100.getStatements().size());

			{
				final MoveStatement moveStatement = (MoveStatement) paragraphB70100.getStatements().get(0);
				assertNotNull(moveStatement);
				assertEquals(StatementTypeEnum.MOVE, moveStatement.getStatementType());
			}

			{
				final UnstringStatement unstringStatement = (UnstringStatement) paragraphB70100.getStatements().get(1);
				assertNotNull(unstringStatement);
				assertEquals(StatementTypeEnum.UNSTRING, unstringStatement.getStatementType());

				{
					final Sending sending = unstringStatement.getSending();
					assertNotNull(sending);
				}

				{
					final IntoPhrase intoPhrase = unstringStatement.getIntoPhrase();
					assertNotNull(intoPhrase);
				}

				{
					final TallyingPhrase tallyingPhrase = unstringStatement.getTallyingPhrase();
					assertNotNull(tallyingPhrase);
				}
			}

			{
				final PerformStatement performStatement = (PerformStatement) paragraphB70100.getStatements().get(2);
				assertNotNull(performStatement);
				assertEquals(StatementTypeEnum.PERFORM, performStatement.getStatementType());

				assertEquals(PerformStatementType.INLINE, performStatement.getPerformStatementType());

				{
					final PerformInlineStatement performInlineStatement = performStatement.getPerformInlineStatement();
					assertEquals(3, performInlineStatement.getStatements().size());

					{
						final MoveStatement moveStatement = (MoveStatement) performInlineStatement.getStatements()
								.get(0);
						assertNotNull(moveStatement);
						assertEquals(StatementTypeEnum.MOVE, moveStatement.getStatementType());
					}

					{
						final UnstringStatement unstringStatement = (UnstringStatement) performInlineStatement
								.getStatements().get(1);
						assertNotNull(unstringStatement);
						assertEquals(StatementTypeEnum.UNSTRING, unstringStatement.getStatementType());

						{
							final Sending sending = unstringStatement.getSending();
							assertNotNull(sending);
						}

						{
							final IntoPhrase intoPhrase = unstringStatement.getIntoPhrase();
							assertNotNull(intoPhrase);
						}
					}

					{
						final EvaluateStatement evaluateStatement = (EvaluateStatement) performInlineStatement
								.getStatements().get(2);
						assertNotNull(evaluateStatement);
						assertEquals(StatementTypeEnum.EVALUATE, evaluateStatement.getStatementType());
					}
				}
			}
		}
	}
}
