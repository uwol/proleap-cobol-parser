package io.proleap.cobol.asg.procedure.close;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseFile;
import io.proleap.cobol.asg.metamodel.procedure.close.ClosePortFileIoStatement;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseReelUnitStatement;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseRelativeStatement;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseStatement;
import io.proleap.cobol.asg.metamodel.procedure.close.Using;
import io.proleap.cobol.asg.metamodel.procedure.close.UsingAssociatedData;
import io.proleap.cobol.asg.metamodel.procedure.close.UsingAssociatedDataLength;
import io.proleap.cobol.asg.metamodel.procedure.close.UsingCloseDisposition;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class CloseStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/close/CloseStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("CloseStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(5, procedureDivision.getStatements().size());

		{
			final CloseStatement closeStatement = (CloseStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.CLOSE, closeStatement.getStatementType());

			{
				final CloseFile closeFile = closeStatement.getCloseFiles().get(0);
				final CloseReelUnitStatement closeReelUnitStatement = closeFile.getCloseReelUnitStatement();
				assertEquals(CloseReelUnitStatement.Type.UNIT, closeReelUnitStatement.getType());
				assertTrue(closeReelUnitStatement.isForRemovel());
				assertEquals(CloseReelUnitStatement.WithType.LOCK, closeReelUnitStatement.getWithType());
			}

			{
				final CloseFile closeFile = closeStatement.getCloseFiles().get(1);
				final CloseReelUnitStatement closeReelUnitStatement = closeFile.getCloseReelUnitStatement();
				assertEquals(CloseReelUnitStatement.Type.REEL, closeReelUnitStatement.getType());
				assertFalse(closeReelUnitStatement.isForRemovel());
			}
		}

		{
			final CloseStatement closeStatement = (CloseStatement) procedureDivision.getStatements().get(1);
			assertEquals(StatementTypeEnum.CLOSE, closeStatement.getStatementType());

			{
				final CloseFile closeFile = closeStatement.getCloseFiles().get(0);
				final CloseRelativeStatement closeRelativeStatement = closeFile.getCloseRelativeStatement();
				assertEquals(CloseRelativeStatement.WithType.LOCK, closeRelativeStatement.getWithType());
			}
		}

		{
			final CloseStatement closeStatement = (CloseStatement) procedureDivision.getStatements().get(2);
			assertEquals(StatementTypeEnum.CLOSE, closeStatement.getStatementType());

			{
				final CloseFile closeFile = closeStatement.getCloseFiles().get(0);
				final ClosePortFileIoStatement closePortFileIOStatement = closeFile.getClosePortFileIOStatement();
				assertEquals(ClosePortFileIoStatement.WithType.WAIT, closePortFileIOStatement.getWithType());

				{
					final List<Using> usings = closePortFileIOStatement.getUsings();
					final Using using = usings.get(0);
					assertEquals(Using.Type.CLOSE_DISPOSITION, using.getType());

					{
						final UsingCloseDisposition usingCloseDisposition = using.getUsingCloseDisposition();
						assertEquals(UsingCloseDisposition.Type.ORDERLY, usingCloseDisposition.getType());
					}
				}
			}
		}

		{
			final CloseStatement closeStatement = (CloseStatement) procedureDivision.getStatements().get(3);
			assertEquals(StatementTypeEnum.CLOSE, closeStatement.getStatementType());

			{
				final CloseFile closeFile = closeStatement.getCloseFiles().get(0);
				final ClosePortFileIoStatement closePortFileIOStatement = closeFile.getClosePortFileIOStatement();
				assertEquals(ClosePortFileIoStatement.WithType.NO_WAIT, closePortFileIOStatement.getWithType());

				{
					final List<Using> usings = closePortFileIOStatement.getUsings();
					final Using using = usings.get(0);
					assertEquals(Using.Type.ASSOCIATED_DATA, using.getType());

					{
						final UsingAssociatedData usingAssociatedData = using.getUsingAssociatedData();
						assertNotNull(usingAssociatedData.getDataCall());
					}
				}
			}
		}

		{
			final CloseStatement closeStatement = (CloseStatement) procedureDivision.getStatements().get(4);
			assertEquals(StatementTypeEnum.CLOSE, closeStatement.getStatementType());

			{
				final CloseFile closeFile = closeStatement.getCloseFiles().get(0);
				final ClosePortFileIoStatement closePortFileIOStatement = closeFile.getClosePortFileIOStatement();
				assertEquals(ClosePortFileIoStatement.WithType.NO_WAIT, closePortFileIOStatement.getWithType());

				{
					final List<Using> usings = closePortFileIOStatement.getUsings();
					final Using using = usings.get(0);
					assertEquals(Using.Type.ASSOCIATED_DATA_LENGTH, using.getType());

					{
						final UsingAssociatedDataLength usingAssociatedDataLength = using
								.getUsingAssociatedDataLength();
						assertNotNull(usingAssociatedDataLength.getDataLengthCall());
					}
				}
			}
		}
	}
}