package io.proleap.cobol.asg.valuestmt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
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
import io.proleap.cobol.asg.metamodel.valuestmt.condition.AndOrCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.CombinableCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.ConditionNameReference;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.SimpleCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.ArithmeticComparison;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.RelationalOperator;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ConditionTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/valuestmt/Condition.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("Condition");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final IfStatement ifStatement = (IfStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.IF, ifStatement.getStatementType());

			{
				final Then then = ifStatement.getThen();
				assertEquals(1, then.getStatements().size());
			}

			{
				final ConditionValueStmt condition = ifStatement.getCondition();

				{
					final CombinableCondition combinableCondition = condition.getCombinableCondition();
					assertFalse(combinableCondition.isNot());

					{
						final SimpleCondition simpleCondition = combinableCondition.getSimpleCondition();
						assertNotNull(simpleCondition);
						assertEquals(SimpleCondition.Type.RELATION_CONDITION, simpleCondition.getType());

						{
							final RelationConditionValueStmt relationCondition = simpleCondition.getRelationCondition();
							assertNotNull(relationCondition);
							assertEquals(RelationConditionValueStmt.Type.ARITHMETIC, relationCondition.getType());

							{
								final ArithmeticComparison arithmeticComparison = relationCondition
										.getArithmeticComparison();
								assertNotNull(arithmeticComparison);

								{
									final RelationalOperator operator = arithmeticComparison.getOperator();
									assertNotNull(operator);
									assertEquals(RelationalOperator.Type.GREATER, operator.getType());
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
														assertEquals(1.0, (Double) literalValueStmt.getValue(),
																EPSILON);
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
														assertEquals(2, (Double) literalValueStmt.getValue(), EPSILON);
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

				assertEquals(1, condition.getAndOrConditions().size());

				{
					final AndOrCondition andOrCondition = condition.getAndOrConditions().get(0);
					assertNotNull(andOrCondition);

					{
						final CombinableCondition combinableCondition = andOrCondition.getCombinableCondition();
						assertTrue(combinableCondition.isNot());
						assertNotNull(combinableCondition);

						{
							final SimpleCondition simpleCondition = combinableCondition.getSimpleCondition();
							assertNotNull(simpleCondition);
							assertEquals(SimpleCondition.Type.CONDITION_NAME_REFERENCE, simpleCondition.getType());

							{
								final ConditionNameReference conditionNameReference = simpleCondition
										.getConditionNameReference();
								assertNotNull(conditionNameReference);

								{
									final Call conditionCall = conditionNameReference.getConditionCall();
									assertNotNull(conditionCall);
									assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, conditionCall.getCallType());
								}
							}
						}
					}
				}
			}
		}
	}
}