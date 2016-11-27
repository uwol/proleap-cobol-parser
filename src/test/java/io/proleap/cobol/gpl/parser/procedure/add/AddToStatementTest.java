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
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.parser.metamodel.procedure.add.AddTo;
import io.proleap.cobol.parser.metamodel.procedure.add.From;
import io.proleap.cobol.parser.metamodel.procedure.add.To;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class AddToStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/add/AddToStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("AddToStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		assertEquals(2, procedureDivision.getStatements().size());

		{
			final AddStatement addStatement = (AddStatement) procedureDivision.getStatements().get(0);
			assertEquals(AddStatement.Type.To, addStatement.getType());
			assertNotNull(addStatement.getAddTo());

			final AddTo addTo = addStatement.getAddTo();
			assertEquals(1, addTo.getFroms().size());

			final From from = addTo.getFroms().get(0);

			assertNotNull(from.getFrom());
			assertEquals(1, addTo.getTos().size());

			final To to = addTo.getTos().get(0);

			assertNotNull(to.getTo());
			assertEquals(Call.CallType.DataDescriptionEntryCall, to.getTo().getCallType());
		}

		{
			final AddStatement addStatement = (AddStatement) procedureDivision.getStatements().get(1);
			assertEquals(AddStatement.Type.To, addStatement.getType());
			assertNotNull(addStatement.getAddTo());

			final AddTo addTo = addStatement.getAddTo();
			assertEquals(2, addTo.getFroms().size());

			final From from1 = addTo.getFroms().get(0);
			final From from2 = addTo.getFroms().get(1);

			assertNotNull(from1.getFrom());
			assertNotNull(from2.getFrom());
			assertEquals(2, addTo.getTos().size());

			final To to1 = addTo.getTos().get(0);
			final To to2 = addTo.getTos().get(1);

			assertNotNull(to1.getTo());
			assertEquals(Call.CallType.DataDescriptionEntryCall, to1.getTo().getCallType());
			assertNotNull(to2.getTo());
			assertEquals(Call.CallType.DataDescriptionEntryCall, to2.getTo().getCallType());
		}
	}
}
