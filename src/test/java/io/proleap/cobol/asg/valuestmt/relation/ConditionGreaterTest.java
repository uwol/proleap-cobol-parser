package io.proleap.cobol.asg.valuestmt.relation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.ifstmt.IfStatement;
import io.proleap.cobol.asg.metamodel.procedure.ifstmt.Then;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.RelationConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.Basis;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDivs;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.Powers;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.CombinableCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.SimpleCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.ArithmeticComparison;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.RelationalOperator;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ConditionGreaterTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/valuestmt/relation/ConditionGreater.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ConditionGreater");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final IfStatement ifStatement = (IfStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.IF, ifStatement.getStatementType());

			{
				final Then then = ifStatement.getThen();
				assertTrue(then.getStatements().isEmpty());
			}

			{
				final ConditionValueStmt condition = ifStatement.getCondition();

				{
					final CombinableCondition combinableCondition = condition.getCombinableCondition();
					assertFalse(combinableCondition.isNot());

					{
						final SimpleCondition simpleCondition = combinableCondition.getSimpleCondition();
						assertNotNull(simpleCondition);
						assertEquals(SimpleCondition.SimpleConditionType.RELATION_CONDITION,
								simpleCondition.getSimpleConditionType());

						{
							final RelationConditionValueStmt relationCondition = simpleCondition.getRelationCondition();
							assertNotNull(relationCondition);
							assertEquals(RelationConditionValueStmt.RelationConditionType.ARITHMETIC,
									relationCondition.getRelationConditionType());

							{
								final ArithmeticComparison arithmeticComparison = relationCondition
										.getArithmeticComparison();
								assertNotNull(arithmeticComparison);

								{
									final RelationalOperator operator = arithmeticComparison.getOperator();
									assertNotNull(operator);
									assertEquals(RelationalOperator.RelationalOperatorType.GREATER,
											operator.getRelationalOperatorType());
								}

								{
									final ArithmeticValueStmt arithmeticExpressionLeft = arithmeticComparison
											.getArithmeticExpressionLeft();
									assertNotNull(arithmeticExpressionLeft);

									{
										final MultDivs multDivs = arithmeticExpressionLeft.getMultDivs();
										assertNotNull(multDivs);

										{
											final Powers powers = multDivs.getPowers();
											assertNotNull(powers);

											{
												final Basis basis = powers.getBasis();
												assertNotNull(basis);

												{
													final ValueStmt basisValueStmt = basis.getBasisValueStmt();
													assertNotNull(basisValueStmt);

													{
														final LiteralValueStmt literalValueStmt = (LiteralValueStmt) basisValueStmt;
														final Literal literal = literalValueStmt.getLiteral();
														assertEquals(BigDecimal.ONE, literal.getValue());
													}
												}
											}
										}
									}
								}

								{
									final ArithmeticValueStmt arithmeticExpressionRight = arithmeticComparison
											.getArithmeticExpressionRight();
									assertNotNull(arithmeticExpressionRight);

									{
										final MultDivs multDivs = arithmeticExpressionRight.getMultDivs();
										assertNotNull(multDivs);

										{
											final Powers powers = multDivs.getPowers();
											assertNotNull(powers);

											{
												final Basis basis = powers.getBasis();
												assertNotNull(basis);

												{
													final ValueStmt basisValueStmt = basis.getBasisValueStmt();
													assertNotNull(basisValueStmt);

													{
														final LiteralValueStmt literalValueStmt = (LiteralValueStmt) basisValueStmt;
														final Literal literal = literalValueStmt.getLiteral();
														assertEquals(new BigDecimal(2), literal.getValue());
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}