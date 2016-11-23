package io.proleap.cobol.gpl.parser.procedure.close;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.close.CloseFile;
import io.proleap.cobol.parser.metamodel.procedure.close.ClosePortFileIoStatement;
import io.proleap.cobol.parser.metamodel.procedure.close.CloseReelUnitStatement;
import io.proleap.cobol.parser.metamodel.procedure.close.CloseRelativeStatement;
import io.proleap.cobol.parser.metamodel.procedure.close.CloseStatement;
import io.proleap.cobol.parser.metamodel.procedure.close.Using;
import io.proleap.cobol.parser.metamodel.procedure.close.UsingAssociatedData;
import io.proleap.cobol.parser.metamodel.procedure.close.UsingAssociatedDataLength;
import io.proleap.cobol.parser.metamodel.procedure.close.UsingCloseDisposition;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CloseStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/close/CloseStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("CloseStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final CloseStatement closeStatement = (CloseStatement) procedureDivision.getStatements().get(0);

			{
				final CloseFile closeFile = closeStatement.getCloseFiles().get(0);
				final CloseReelUnitStatement closeReelUnitStatement = closeFile.getCloseReelUnitStatement();
				assertEquals(CloseReelUnitStatement.Type.Unit, closeReelUnitStatement.getType());
				assertTrue(closeReelUnitStatement.isForRemovel());
				assertEquals(CloseReelUnitStatement.WithType.Lock, closeReelUnitStatement.getWithType());
			}

			{
				final CloseFile closeFile = closeStatement.getCloseFiles().get(1);
				final CloseReelUnitStatement closeReelUnitStatement = closeFile.getCloseReelUnitStatement();
				assertEquals(CloseReelUnitStatement.Type.Reel, closeReelUnitStatement.getType());
				assertFalse(closeReelUnitStatement.isForRemovel());
			}
		}

		{
			final CloseStatement closeStatement = (CloseStatement) procedureDivision.getStatements().get(1);
			final CloseFile closeFile = closeStatement.getCloseFiles().get(0);
			final CloseRelativeStatement closeRelativeStatement = closeFile.getCloseRelativeStatement();
			assertEquals(CloseRelativeStatement.WithType.Lock, closeRelativeStatement.getWithType());
		}

		{
			final CloseStatement closeStatement = (CloseStatement) procedureDivision.getStatements().get(2);
			final CloseFile closeFile = closeStatement.getCloseFiles().get(0);
			final ClosePortFileIoStatement closePortFileIOStatement = closeFile.getClosePortFileIOStatement();
			assertEquals(ClosePortFileIoStatement.WithType.Wait, closePortFileIOStatement.getWithType());

			final List<Using> usings = closePortFileIOStatement.getUsings();
			final Using using = usings.get(0);

			assertEquals(Using.Type.CloseDisposition, using.getType());

			final UsingCloseDisposition usingCloseDisposition = using.getUsingCloseDisposition();
			assertEquals(UsingCloseDisposition.Type.Orderly, usingCloseDisposition.getType());
		}

		{
			final CloseStatement closeStatement = (CloseStatement) procedureDivision.getStatements().get(3);
			final CloseFile closeFile = closeStatement.getCloseFiles().get(0);
			final ClosePortFileIoStatement closePortFileIOStatement = closeFile.getClosePortFileIOStatement();
			assertEquals(ClosePortFileIoStatement.WithType.NoWait, closePortFileIOStatement.getWithType());

			final List<Using> usings = closePortFileIOStatement.getUsings();
			final Using using = usings.get(0);

			assertEquals(Using.Type.AssociatedData, using.getType());

			final UsingAssociatedData usingAssociatedData = using.getUsingAssociatedData();
			assertNotNull(usingAssociatedData.getDataCall());
		}

		{
			final CloseStatement closeStatement = (CloseStatement) procedureDivision.getStatements().get(4);
			final CloseFile closeFile = closeStatement.getCloseFiles().get(0);
			final ClosePortFileIoStatement closePortFileIOStatement = closeFile.getClosePortFileIOStatement();
			assertEquals(ClosePortFileIoStatement.WithType.NoWait, closePortFileIOStatement.getWithType());

			final List<Using> usings = closePortFileIOStatement.getUsings();
			final Using using = usings.get(0);

			assertEquals(Using.Type.AssociatedDataLength, using.getType());

			final UsingAssociatedDataLength usingAssociatedDataLength = using.getUsingAssociatedDataLength();
			assertNotNull(usingAssociatedDataLength.getDataLengthCall());
		}
	}
}