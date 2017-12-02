package io.proleap.cobol.asg.valuestmt.relation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.ifstmt.IfStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.RelationConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.CombinableCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.SimpleCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.ArithmeticComparison;
import io.proleap.cobol.asg.metamodel.valuestmt.relation.RelationalOperator;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ConditionNotEqualCharTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/valuestmt/relation/ConditionNotEqualChar.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ConditionNotEqualChar");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final IfStatement ifStatement = (IfStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.IF, ifStatement.getStatementType());

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
									assertEquals(RelationalOperator.RelationalOperatorType.NOT_EQUAL,
											operator.getRelationalOperatorType());
								}
							}
						}
					}
				}
			}
		}
	}
}
