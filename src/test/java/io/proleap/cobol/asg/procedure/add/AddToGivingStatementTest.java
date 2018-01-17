package io.proleap.cobol.asg.procedure.add;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.FigurativeConstant.FigurativeConstantType;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.AddToGivingStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.From;
import io.proleap.cobol.asg.metamodel.procedure.add.Giving;
import io.proleap.cobol.asg.metamodel.procedure.add.ToGiving;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class AddToGivingStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/add/AddToGivingStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("AddToGivingStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(3, procedureDivision.getStatements().size());

		{
			final AddStatement addStatement = (AddStatement) procedureDivision.getStatements().get(0);
			assertEquals(AddStatement.AddType.TO_GIVING, addStatement.getAddType());
			assertNotNull(addStatement.getAddToGivingStatement());

			{
				final AddToGivingStatement addToGivingStatement = addStatement.getAddToGivingStatement();
				assertEquals(1, addToGivingStatement.getFroms().size());

				{
					final From from = addToGivingStatement.getFroms().get(0);
					assertNotNull(from.getFromValueStmt());
					assertEquals(1, addToGivingStatement.getTos().size());
				}

				{
					final ToGiving to = addToGivingStatement.getTos().get(0);
					assertNotNull(to.getToValueStmt());

					final ValueStmt toValueStmt = to.getToValueStmt();
					final CallValueStmt toCallValueStmt = (CallValueStmt) toValueStmt;
					final Call toCall = toCallValueStmt.getCall();

					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, toCall.getCallType());
					assertEquals(1, addToGivingStatement.getGivings().size());
				}

				{
					final Giving giving = addToGivingStatement.getGivings().get(0);
					assertNotNull(giving.getGivingCall());
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, giving.getGivingCall().getCallType());
				}
			}
		}

		{
			final AddStatement addStatement = (AddStatement) procedureDivision.getStatements().get(1);
			assertEquals(AddStatement.AddType.TO_GIVING, addStatement.getAddType());
			assertNotNull(addStatement.getAddToGivingStatement());

			{
				final AddToGivingStatement addToGivingStatement = addStatement.getAddToGivingStatement();
				assertEquals(2, addToGivingStatement.getFroms().size());

				{
					final From from1 = addToGivingStatement.getFroms().get(0);
					assertNotNull(from1.getFromValueStmt());
				}

				{
					final From from2 = addToGivingStatement.getFroms().get(1);
					assertNotNull(from2.getFromValueStmt());
				}

				assertEquals(2, addToGivingStatement.getTos().size());

				{
					final ToGiving to1 = addToGivingStatement.getTos().get(0);
					assertNotNull(to1.getToValueStmt());

					final ValueStmt to1ValueStmt = to1.getToValueStmt();
					final CallValueStmt to1CallValueStmt = (CallValueStmt) to1ValueStmt;
					final Call to1Call = to1CallValueStmt.getCall();

					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, to1Call.getCallType());
				}

				{
					final ToGiving to2 = addToGivingStatement.getTos().get(1);
					assertNotNull(to2.getToValueStmt());

					final ValueStmt to2ValueStmt = to2.getToValueStmt();
					final CallValueStmt to2CallValueStmt = (CallValueStmt) to2ValueStmt;
					final Call to2Call = to2CallValueStmt.getCall();

					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, to2Call.getCallType());
				}

				assertEquals(2, addToGivingStatement.getGivings().size());

				{
					final Giving giving1 = addToGivingStatement.getGivings().get(0);
					assertNotNull(giving1.getGivingCall());
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, giving1.getGivingCall().getCallType());
				}

				{
					final Giving giving2 = addToGivingStatement.getGivings().get(1);
					assertNotNull(giving2.getGivingCall());
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, giving2.getGivingCall().getCallType());
				}
			}
		}

		{
			final AddStatement addStatement = (AddStatement) procedureDivision.getStatements().get(2);
			assertEquals(AddStatement.AddType.TO_GIVING, addStatement.getAddType());
			assertNotNull(addStatement.getAddToGivingStatement());

			{
				final AddToGivingStatement addToGivingStatement = addStatement.getAddToGivingStatement();
				assertEquals(1, addToGivingStatement.getFroms().size());

				{
					final From from1 = addToGivingStatement.getFroms().get(0);
					assertNotNull(from1.getFromValueStmt());

					final ValueStmt fromValueStmt = from1.getFromValueStmt();
					final LiteralValueStmt fromLiteralValueStmt = (LiteralValueStmt) fromValueStmt;
					final Literal literal = fromLiteralValueStmt.getLiteral();
					assertEquals(BigDecimal.ZERO, literal.getValue());
				}

				assertEquals(1, addToGivingStatement.getTos().size());

				{
					final ToGiving to = addToGivingStatement.getTos().get(0);
					assertNotNull(to.getToValueStmt());

					final ValueStmt toValueStmt = to.getToValueStmt();
					final LiteralValueStmt literalToValueStmt = (LiteralValueStmt) toValueStmt;
					final Literal literal = literalToValueStmt.getLiteral();
					assertEquals(Literal.LiteralType.FIGURATIVE_CONSTANT, literal.getLiteralType());

					assertNotNull(literal.getFigurativeConstant());
					assertEquals(FigurativeConstantType.ZERO,
							literal.getFigurativeConstant().getFigurativeConstantType());
				}

				assertEquals(1, addToGivingStatement.getGivings().size());

				{
					final Giving giving1 = addToGivingStatement.getGivings().get(0);
					assertNotNull(giving1.getGivingCall());
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, giving1.getGivingCall().getCallType());
				}
			}
		}
	}
}
