package io.proleap.cobol.gpl.parser.procedure.accept;

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
import io.proleap.cobol.parser.metamodel.procedure.accept.AcceptFromDate;
import io.proleap.cobol.parser.metamodel.procedure.accept.AcceptFromMnemonic;
import io.proleap.cobol.parser.metamodel.procedure.accept.AcceptMessageCount;
import io.proleap.cobol.parser.metamodel.procedure.accept.AcceptStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class AcceptStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/accept/AcceptStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("AcceptStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		assertEquals(3, procedureDivision.getStatements().size());

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(0);
			assertNotNull(acceptStatement.getAcceptValueStmt());
			assertNotNull(acceptStatement.getAcceptFromDate());

			final AcceptFromDate acceptFromDate = acceptStatement.getAcceptFromDate();
			assertEquals(AcceptFromDate.DateType.TodaysName, acceptFromDate.getDateType());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(1);
			assertNotNull(acceptStatement.getAcceptValueStmt());
			assertNotNull(acceptStatement.getAcceptFromMnemonic());

			final AcceptFromMnemonic acceptFromMnemonic = acceptStatement.getAcceptFromMnemonic();
			assertNotNull(acceptFromMnemonic.getMnemonicValueStmt());
		}

		{
			final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(2);
			assertNotNull(acceptStatement.getAcceptValueStmt());
			assertNotNull(acceptStatement.getAcceptMessageCount());

			final AcceptMessageCount acceptMessageCount = acceptStatement.getAcceptMessageCount();
			assertNotNull(acceptMessageCount);
		}
	}
}
