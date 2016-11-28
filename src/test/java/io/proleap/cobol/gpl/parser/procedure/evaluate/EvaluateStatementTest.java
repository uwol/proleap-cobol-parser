package io.proleap.cobol.gpl.parser.procedure.evaluate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.AlsoCondition;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.AlsoSelect;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.Condition;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.EvaluateStatement;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.Select;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.Through;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.Value;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.When;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.WhenOther;
import io.proleap.cobol.parser.metamodel.procedure.evaluate.WhenPhrase;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class EvaluateStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/evaluate/EvaluateStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("EvaluateStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final EvaluateStatement evaluateStatement = (EvaluateStatement) procedureDivision.getStatements().get(0);
		assertNotNull(evaluateStatement);

		{
			final Select select = evaluateStatement.getSelect();
			assertNotNull(select);

			final ValueStmt selectValueStmt = select.getSelectValueStmt();
			assertNotNull(selectValueStmt);
		}

		{
			assertEquals(2, evaluateStatement.getAlsoSelects().size());

			{
				final AlsoSelect alsoSelect = evaluateStatement.getAlsoSelects().get(0);
				assertNotNull(alsoSelect);

				final Select select = alsoSelect.getSelect();
				assertNotNull(select);

				assertNotNull(select.getSelectValueStmt());
			}

			{
				final AlsoSelect alsoSelect = evaluateStatement.getAlsoSelects().get(1);
				assertNotNull(alsoSelect);

				final Select select = alsoSelect.getSelect();
				assertNotNull(select);

				assertNotNull(select.getSelectValueStmt());
			}
		}

		{
			assertEquals(3, evaluateStatement.getWhenPhrases().size());

			{
				final WhenPhrase whenPhrase = evaluateStatement.getWhenPhrases().get(0);
				assertEquals(1, whenPhrase.getWhens().size());

				{
					final When when = whenPhrase.getWhens().get(0);
					final Condition condition = when.getCondition();
					assertNotNull(condition);
					assertEquals(Condition.Type.ValueThrough, condition.getType());

					{
						final Value value = condition.getValue();
						assertNotNull(value);
						assertNotNull(value.getValueStmt());
					}

					assertEquals(0, when.getAlsoConditions().size());

					{
						final Through through = condition.getThrough();
						assertNotNull(through);

						{
							final Value value = through.getValue();
							assertNotNull(value);
							assertNotNull(value.getValueStmt());
						}
					}
				}
			}

			{
				final WhenPhrase whenPhrase = evaluateStatement.getWhenPhrases().get(1);
				assertEquals(1, whenPhrase.getWhens().size());

				{
					final When when = whenPhrase.getWhens().get(0);
					final Condition condition = when.getCondition();
					assertNotNull(condition);
					assertEquals(Condition.Type.ValueThrough, condition.getType());

					{
						final Value value = condition.getValue();
						assertNotNull(value);
						assertNotNull(value.getValueStmt());
					}

					assertEquals(0, when.getAlsoConditions().size());

					{
						final Through through = condition.getThrough();
						assertNotNull(through);

						{
							final Value value = through.getValue();
							assertNotNull(value);
							assertNotNull(value.getValueStmt());
						}
					}
				}
			}

			{
				final WhenPhrase whenPhrase = evaluateStatement.getWhenPhrases().get(2);
				assertEquals(1, whenPhrase.getWhens().size());

				{
					final When when = whenPhrase.getWhens().get(0);
					final Condition condition = when.getCondition();
					assertNotNull(condition);
					assertEquals(Condition.Type.ValueThrough, condition.getType());

					{
						final Value value = condition.getValue();
						assertNotNull(value);
						assertNotNull(value.getValueStmt());
					}

					assertEquals(1, when.getAlsoConditions().size());

					{
						final AlsoCondition alsoCondition = when.getAlsoConditions().get(0);
						assertNotNull(alsoCondition);

						{
							final Value value = alsoCondition.getValue();
							assertNotNull(value);
							assertNotNull(value.getValueStmt());
						}
					}

					{
						final Through through = condition.getThrough();
						assertNotNull(through);

						{
							final Value value = through.getValue();
							assertNotNull(value);
							assertNotNull(value.getValueStmt());
						}
					}
				}
			}
		}

		{
			final WhenOther whenOther = evaluateStatement.getWhenOther();
			assertNotNull(whenOther);
		}
	}
}