package io.proleap.cobol.asg.valuestmt.relation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.ifstmt.IfStatement;
import io.proleap.cobol.asg.metamodel.procedure.ifstmt.Then;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.AndOrCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.AndOrCondition.AndOrConditionType;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.CombinableCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.ConditionNameReference;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.SimpleCondition;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ConditionAndTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/valuestmt/relation/ConditionAnd.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ConditionAnd");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final IfStatement ifStatement = (IfStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.IF, ifStatement.getStatementType());

			{
				final Then then = ifStatement.getThen();
				assertEquals(0, then.getStatements().size());
			}

			{
				final ConditionValueStmt condition = ifStatement.getCondition();

				{
					final CombinableCondition combinableCondition = condition.getCombinableCondition();
					assertFalse(combinableCondition.isNot());

					{
						final SimpleCondition simpleCondition = combinableCondition.getSimpleCondition();
						assertNotNull(simpleCondition);
						assertEquals(SimpleCondition.SimpleConditionType.CONDITION_NAME_REFERENCE,
								simpleCondition.getSimpleConditionType());

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

				assertEquals(1, condition.getAndOrConditions().size());

				{
					final AndOrCondition andOrCondition = condition.getAndOrConditions().get(0);
					assertNotNull(andOrCondition);
					assertEquals(AndOrConditionType.AND, andOrCondition.getAndOrConditionType());

					{
						final CombinableCondition combinableCondition = andOrCondition.getCombinableCondition();
						assertTrue(combinableCondition.isNot());
						assertNotNull(combinableCondition);

						{
							final SimpleCondition simpleCondition = combinableCondition.getSimpleCondition();
							assertNotNull(simpleCondition);
							assertEquals(SimpleCondition.SimpleConditionType.CONDITION_NAME_REFERENCE,
									simpleCondition.getSimpleConditionType());

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