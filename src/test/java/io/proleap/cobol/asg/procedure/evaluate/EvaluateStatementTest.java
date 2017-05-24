package io.proleap.cobol.asg.procedure.evaluate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.AlsoCondition;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.AlsoSelect;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.Condition;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.EvaluateStatement;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.Select;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.Through;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.Value;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.When;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.WhenOther;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.WhenPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class EvaluateStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/evaluate/EvaluateStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("EvaluateStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final EvaluateStatement evaluateStatement = (EvaluateStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.EVALUATE, evaluateStatement.getStatementType());
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
						assertEquals(Condition.ConditionType.VALUE_THROUGH, condition.getConditionType());

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
						assertEquals(Condition.ConditionType.VALUE_THROUGH, condition.getConditionType());

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

						{
							final Condition condition = when.getCondition();
							assertNotNull(condition);
							assertEquals(Condition.ConditionType.VALUE_THROUGH, condition.getConditionType());

							{
								final Value value = condition.getValue();
								assertNotNull(value);
								assertNotNull(value.getValueStmt());
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

						assertEquals(1, when.getAlsoConditions().size());

						{
							final AlsoCondition alsoCondition = when.getAlsoConditions().get(0);
							assertNotNull(alsoCondition);

							{
								final Condition condition = alsoCondition.getCondition();
								assertNotNull(condition);
								assertEquals(Condition.ConditionType.VALUE, condition.getConditionType());

								{
									final Value value = condition.getValue();
									assertNotNull(value);
									assertNotNull(value.getValueStmt());
								}
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
}