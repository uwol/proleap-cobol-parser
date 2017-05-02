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
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.Basis;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDiv;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.MultDivs;
import io.proleap.cobol.asg.metamodel.valuestmt.arithmetic.Powers;
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
			assertEquals(StatementTypeEnum.COMPUTE, computeStatement.getStatementType());

			{
				final Store store = computeStatement.getStores().get(0);
				assertNotNull(store.getStoreCall());
				assertEquals(Call.CallType.UNDEFINED_CALL, store.getStoreCall().getCallType());
			}

			{
				final ArithmeticValueStmt arithmeticExpression = computeStatement.getArithmeticExpression();
				assertEquals(1, arithmeticExpression.getSubValueStmts().size());

				{
					final MultDivs multDivsValueStmt = (MultDivs) arithmeticExpression.getSubValueStmts().get(0);
					assertEquals(2, multDivsValueStmt.getSubValueStmts().size());

					{
						final Powers powersValueStmt = (Powers) multDivsValueStmt.getSubValueStmts().get(0);
						assertEquals(1, powersValueStmt.getSubValueStmts().size());

						{
							final Basis basisValueStmt = (Basis) powersValueStmt.getSubValueStmts().get(0);
							assertNotNull(basisValueStmt.getBasisValueStmt());

							{
								final LiteralValueStmt literalValueStmt = (LiteralValueStmt) basisValueStmt
										.getBasisValueStmt();
								assertEquals(2, literalValueStmt.getValue());
							}
						}
					}

					{
						final MultDiv multDivValueStmt = (MultDiv) multDivsValueStmt.getSubValueStmts().get(1);
						assertEquals(MultDiv.Type.MULT, multDivValueStmt.getType());
						assertEquals(1, multDivValueStmt.getSubValueStmts().size());

						{
							final Powers powersValueStmt = (Powers) multDivValueStmt.getSubValueStmts().get(0);

							{
								final Basis basisValueStmt = (Basis) powersValueStmt.getSubValueStmts().get(0);
								assertNotNull(basisValueStmt.getBasisValueStmt());

								{
									final LiteralValueStmt literalValueStmt = (LiteralValueStmt) basisValueStmt
											.getBasisValueStmt();
									assertEquals(2, literalValueStmt.getValue());
								}
							}
						}
					}
				}
			}
		}
	}
}