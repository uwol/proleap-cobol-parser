package io.proleap.cobol.asg.procedure.perform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.perform.ByPhrase;
import io.proleap.cobol.asg.metamodel.procedure.perform.FromPhrase;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformProcedureStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformType;
import io.proleap.cobol.asg.metamodel.procedure.perform.TestClause;
import io.proleap.cobol.asg.metamodel.procedure.perform.Until;
import io.proleap.cobol.asg.metamodel.procedure.perform.Varying;
import io.proleap.cobol.asg.metamodel.procedure.perform.VaryingClause;
import io.proleap.cobol.asg.metamodel.procedure.perform.VaryingPhrase;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class PerformProcedureVaryingTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/perform/PerformProcedureVarying.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("PERFORMPROCEDUREVARYING");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final Statement statement = procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.PERFORM, statement.getStatementType());

			final PerformStatement performStatement = (PerformStatement) statement;
			assertEquals(PerformStatement.PerformStatementType.PROCEDURE, performStatement.getPerformStatementType());

			{
				final PerformProcedureStatement performProcedureStatement = performStatement
						.getPerformProcedureStatement();
				assertNotNull(performProcedureStatement.getPerformType());

				{
					final PerformType performType = performProcedureStatement.getPerformType();
					assertEquals(PerformType.PerformTypeType.VARYING, performType.getPerformTypeType());

					{
						final Varying varying = performType.getVarying();

						{
							assertNotNull(varying.getTestClause());
							assertEquals(TestClause.TestClauseType.BEFORE, varying.getTestClause().getTestClauseType());
						}

						{
							final VaryingClause varyingClause = varying.getVaryingClause();

							{
								final VaryingPhrase varyingPhrase = varyingClause.getVaryingPhrase();

								{
									final FromPhrase from = varyingPhrase.getFrom();
									final LiteralValueStmt fromValueStmt = (LiteralValueStmt) from.getFromValueStmt();
									final Literal literal = fromValueStmt.getLiteral();
									assertEquals(BigDecimal.ONE, literal.getValue());
								}

								{
									final ByPhrase by = varyingPhrase.getBy();
									final LiteralValueStmt byValueStmt = (LiteralValueStmt) by.getByValueStmt();
									final Literal literal = byValueStmt.getLiteral();
									assertEquals(new BigDecimal(2), literal.getValue());
								}

								{
									final Until until = varyingPhrase.getUntil();
									assertNotNull(until);

									final ConditionValueStmt condition = until.getCondition();
									assertNotNull(condition);
								}
							}
						}
					}
				}
			}
		}
	}
}
