package io.proleap.cobol.asg.valuestmt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.compute.ComputeStatement;
import io.proleap.cobol.asg.metamodel.procedure.compute.Store;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.BasisValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDivValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDivsValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.PowersValueStmt;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ArithmeticExpressionTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/valuestmt/ArithmeticExpression.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ArithmeticExpression");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final ComputeStatement computeStatement = (ComputeStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.Compute, computeStatement.getStatementType());

			{
				final Store store = computeStatement.getStores().get(0);
				assertNotNull(store.getStoreCall());
				assertEquals(Call.CallType.UndefinedCall, store.getStoreCall().getCallType());
			}

			{
				final ArithmeticValueStmt arithmeticExpression = computeStatement.getArithmeticExpression();
				assertEquals(1, arithmeticExpression.getSubValueStmts().size());

				{
					final MultDivsValueStmt multDivsValueStmt = (MultDivsValueStmt) arithmeticExpression
							.getSubValueStmts().get(0);
					assertEquals(2, multDivsValueStmt.getSubValueStmts().size());

					{
						final PowersValueStmt powersValueStmt = (PowersValueStmt) multDivsValueStmt.getSubValueStmts()
								.get(0);
						assertEquals(1, powersValueStmt.getSubValueStmts().size());

						{
							final BasisValueStmt basisValueStmt = (BasisValueStmt) powersValueStmt.getSubValueStmts()
									.get(0);
							assertNotNull(basisValueStmt.getBasisValueStmt());

							{
								final LiteralValueStmt literalValueStmt = (LiteralValueStmt) basisValueStmt
										.getBasisValueStmt();
								assertEquals("2", literalValueStmt.getValue());
							}
						}
					}

					{
						final MultDivValueStmt multDivValueStmt = (MultDivValueStmt) multDivsValueStmt
								.getSubValueStmts().get(1);
						assertEquals(MultDivValueStmt.Type.Mult, multDivValueStmt.getType());
						assertEquals(1, multDivValueStmt.getSubValueStmts().size());

						{
							final PowersValueStmt powersValueStmt = (PowersValueStmt) multDivValueStmt
									.getSubValueStmts().get(0);

							{
								final BasisValueStmt basisValueStmt = (BasisValueStmt) powersValueStmt
										.getSubValueStmts().get(0);
								assertNotNull(basisValueStmt.getBasisValueStmt());

								{
									final LiteralValueStmt literalValueStmt = (LiteralValueStmt) basisValueStmt
											.getBasisValueStmt();
									assertEquals("2", literalValueStmt.getValue());
								}
							}
						}
					}
				}
			}
		}
	}
}