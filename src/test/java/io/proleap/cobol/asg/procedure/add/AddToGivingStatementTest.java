package io.proleap.cobol.asg.procedure.add;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.AddToGivingStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.From;
import io.proleap.cobol.asg.metamodel.procedure.add.Giving;
import io.proleap.cobol.asg.metamodel.procedure.add.To;
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
		assertEquals(2, procedureDivision.getStatements().size());

		{
			final AddStatement addStatement = (AddStatement) procedureDivision.getStatements().get(0);
			assertEquals(AddStatement.AddType.TO_GIVING, addStatement.getAddType());
			assertNotNull(addStatement.getAddToGivingStatement());

			{
				final AddToGivingStatement addToGivingStatement = addStatement.getAddToGivingStatement();
				assertEquals(1, addToGivingStatement.getFroms().size());

				{
					final From from = addToGivingStatement.getFroms().get(0);
					assertNotNull(from.getFrom());
					assertEquals(1, addToGivingStatement.getTos().size());
				}

				{
					final To to = addToGivingStatement.getTos().get(0);
					assertNotNull(to.getTo());
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, to.getTo().getCallType());
					assertEquals(1, addToGivingStatement.getGivings().size());
				}

				{
					final Giving giving = addToGivingStatement.getGivings().get(0);
					assertNotNull(giving.getGiving());
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, giving.getGiving().getCallType());
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
					assertNotNull(from1.getFrom());
				}

				{
					final From from2 = addToGivingStatement.getFroms().get(1);
					assertNotNull(from2.getFrom());
				}

				assertEquals(2, addToGivingStatement.getTos().size());

				{
					final To to1 = addToGivingStatement.getTos().get(0);
					assertNotNull(to1.getTo());
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, to1.getTo().getCallType());
				}

				{
					final To to2 = addToGivingStatement.getTos().get(1);
					assertNotNull(to2.getTo());
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, to2.getTo().getCallType());
				}

				assertEquals(2, addToGivingStatement.getGivings().size());

				{
					final Giving giving1 = addToGivingStatement.getGivings().get(0);
					assertNotNull(giving1.getGiving());
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, giving1.getGiving().getCallType());
				}

				{
					final Giving giving2 = addToGivingStatement.getGivings().get(1);
					assertNotNull(giving2.getGiving());
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, giving2.getGiving().getCallType());
				}
			}
		}
	}
}
