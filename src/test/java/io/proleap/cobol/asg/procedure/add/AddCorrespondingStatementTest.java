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
import io.proleap.cobol.asg.metamodel.procedure.add.AddCorresponding;
import io.proleap.cobol.asg.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class AddCorrespondingStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/add/AddCorrespondingStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("AddCorrespondingStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final AddStatement addStatement = (AddStatement) procedureDivision.getStatements().get(0);
			assertEquals(AddStatement.Type.CORRESPONDING, addStatement.getType());
			assertNotNull(addStatement.getAddCorresponding());

			{
				final AddCorresponding addCorresponding = addStatement.getAddCorresponding();
				assertNotNull(addCorresponding.getFrom());
				assertNotNull(addCorresponding.getTo());
				assertNotNull(addCorresponding.getTo().getTo());
				assertEquals(Call.CallType.DATA_DESCRIPTION_ENTRY_CALL, addCorresponding.getTo().getTo().getCallType());
			}
		}
	}
}
