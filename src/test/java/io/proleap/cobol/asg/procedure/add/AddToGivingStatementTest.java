package io.proleap.cobol.asg.procedure.add;

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
import io.proleap.cobol.asg.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.AddToGiving;
import io.proleap.cobol.asg.metamodel.procedure.add.From;
import io.proleap.cobol.asg.metamodel.procedure.add.Giving;
import io.proleap.cobol.asg.metamodel.procedure.add.To;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class AddToGivingStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/add/AddToGivingStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("AddToGivingStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(2, procedureDivision.getStatements().size());

		{
			final AddStatement addStatement = (AddStatement) procedureDivision.getStatements().get(0);
			assertEquals(AddStatement.Type.Giving, addStatement.getType());
			assertNotNull(addStatement.getAddToGiving());

			final AddToGiving addToGiving = addStatement.getAddToGiving();
			assertEquals(1, addToGiving.getFroms().size());

			final From from = addToGiving.getFroms().get(0);
			assertNotNull(from.getFrom());
			assertEquals(1, addToGiving.getTos().size());

			final To to = addToGiving.getTos().get(0);

			assertNotNull(to.getTo());
			assertEquals(Call.CallType.DataDescriptionEntryCall, to.getTo().getCallType());

			assertEquals(1, addToGiving.getGivings().size());

			final Giving giving = addToGiving.getGivings().get(0);

			assertNotNull(giving.getGiving());
			assertEquals(Call.CallType.DataDescriptionEntryCall, giving.getGiving().getCallType());
		}

		{
			final AddStatement addStatement = (AddStatement) procedureDivision.getStatements().get(1);
			assertEquals(AddStatement.Type.Giving, addStatement.getType());
			assertNotNull(addStatement.getAddToGiving());

			final AddToGiving addToGiving = addStatement.getAddToGiving();
			assertEquals(2, addToGiving.getFroms().size());

			final From from1 = addToGiving.getFroms().get(0);
			final From from2 = addToGiving.getFroms().get(1);

			assertNotNull(from1.getFrom());
			assertNotNull(from2.getFrom());
			assertEquals(2, addToGiving.getTos().size());

			final To to1 = addToGiving.getTos().get(0);
			final To to2 = addToGiving.getTos().get(1);

			assertNotNull(to1.getTo());
			assertEquals(Call.CallType.DataDescriptionEntryCall, to1.getTo().getCallType());
			assertNotNull(to2.getTo());
			assertEquals(Call.CallType.DataDescriptionEntryCall, to2.getTo().getCallType());

			assertEquals(2, addToGiving.getGivings().size());

			final Giving giving1 = addToGiving.getGivings().get(0);
			final Giving giving2 = addToGiving.getGivings().get(1);

			assertNotNull(giving1.getGiving());
			assertEquals(Call.CallType.DataDescriptionEntryCall, giving1.getGiving().getCallType());
			assertNotNull(giving2.getGiving());
			assertEquals(Call.CallType.DataDescriptionEntryCall, giving2.getGiving().getCallType());
		}
	}
}
