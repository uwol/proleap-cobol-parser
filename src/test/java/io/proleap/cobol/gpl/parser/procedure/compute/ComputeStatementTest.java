package io.proleap.cobol.gpl.parser.procedure.compute;

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
import io.proleap.cobol.parser.metamodel.procedure.compute.ComputeStatement;
import io.proleap.cobol.parser.metamodel.procedure.compute.Store;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ComputeStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/compute/ComputeStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("ComputeStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final ComputeStatement computeStatement = (ComputeStatement) procedureDivision.getStatements().get(0);

		{
			final Store store = computeStatement.getStores().get(0);
			assertNotNull(store.getStoreCall());
			assertEquals(Call.CallType.UndefinedCall, store.getStoreCall().getCallType());
		}

		{
			final Store store = computeStatement.getStores().get(1);
			assertNotNull(store.getStoreCall());
			assertEquals(Call.CallType.UndefinedCall, store.getStoreCall().getCallType());
		}
	}
}