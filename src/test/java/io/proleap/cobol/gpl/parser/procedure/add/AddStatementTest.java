package io.proleap.cobol.gpl.parser.procedure.add;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.add.AddCorresponding;
import io.proleap.cobol.parser.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.parser.metamodel.procedure.add.AddTo;
import io.proleap.cobol.parser.metamodel.procedure.add.AddToGiving;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class AddStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/add/AddStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("AddStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		assertEquals(5, procedureDivision.getStatements().size());

		{
			final AddStatement addStatement = (AddStatement) procedureDivision.getStatements().get(0);
			assertEquals(AddStatement.Type.To, addStatement.getType());
			assertNotNull(addStatement.getAddTo());

			final AddTo addTo = addStatement.getAddTo();
			assertEquals(1, addTo.getFroms().size());
			assertNotNull(addTo.getFroms().get(0).getFromValueStmt());

			assertEquals(1, addTo.getTos().size());
			assertNotNull(addTo.getTos().get(0).getToValueStmt());
		}

		{
			final AddStatement addStatement = (AddStatement) procedureDivision.getStatements().get(1);
			assertEquals(AddStatement.Type.To, addStatement.getType());
			assertNotNull(addStatement.getAddTo());

			final AddTo addTo = addStatement.getAddTo();
			assertEquals(2, addTo.getFroms().size());
			assertNotNull(addTo.getFroms().get(0).getFromValueStmt());
			assertNotNull(addTo.getFroms().get(1).getFromValueStmt());

			assertEquals(2, addTo.getTos().size());
			assertNotNull(addTo.getTos().get(0).getToValueStmt());
			assertNotNull(addTo.getTos().get(1).getToValueStmt());
		}

		{
			final AddStatement addStatement = (AddStatement) procedureDivision.getStatements().get(2);
			assertEquals(AddStatement.Type.Giving, addStatement.getType());
			assertNotNull(addStatement.getAddToGiving());

			final AddToGiving addToGiving = addStatement.getAddToGiving();
			assertEquals(1, addToGiving.getFroms().size());
			assertNotNull(addToGiving.getFroms().get(0).getFromValueStmt());

			assertEquals(1, addToGiving.getTos().size());
			assertNotNull(addToGiving.getTos().get(0).getToValueStmt());

			assertEquals(1, addToGiving.getGivings().size());
			assertNotNull(addToGiving.getGivings().get(0).getGivingValueStmt());
		}

		{
			final AddStatement addStatement = (AddStatement) procedureDivision.getStatements().get(3);
			assertEquals(AddStatement.Type.Giving, addStatement.getType());
			assertNotNull(addStatement.getAddToGiving());

			final AddToGiving addToGiving = addStatement.getAddToGiving();
			assertEquals(2, addToGiving.getFroms().size());
			assertNotNull(addToGiving.getFroms().get(0).getFromValueStmt());
			assertNotNull(addToGiving.getFroms().get(1).getFromValueStmt());

			assertEquals(2, addToGiving.getTos().size());
			assertNotNull(addToGiving.getTos().get(0).getToValueStmt());
			assertNotNull(addToGiving.getTos().get(1).getToValueStmt());

			assertEquals(2, addToGiving.getGivings().size());
			assertNotNull(addToGiving.getGivings().get(0).getGivingValueStmt());
			assertNotNull(addToGiving.getGivings().get(1).getGivingValueStmt());
		}

		{
			final AddStatement addStatement = (AddStatement) procedureDivision.getStatements().get(4);
			assertEquals(AddStatement.Type.Corresponding, addStatement.getType());
			assertNotNull(addStatement.getAddCorresponding());

			final AddCorresponding addCorresponding = addStatement.getAddCorresponding();
			assertNotNull(addCorresponding.getFromValueStmt());
			assertNotNull(addCorresponding.getTo());
			assertNotNull(addCorresponding.getTo().getToValueStmt());
		}
	}
}
