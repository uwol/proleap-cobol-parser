package io.proleap.cobol.asg.data.workingstorage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.FigurativeConstant.Type;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.datadescription.ValueInterval;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DataDescriptionValueTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/workingstorage/DataDescriptionValue.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DataDescriptionValue");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.findDataDescriptionEntry("ITEMSPAC");

			assertNotNull(dataDescriptionEntryItem);
			assertNotNull(dataDescriptionEntryItem.getPictureClause());
			assertEquals("X(10)", dataDescriptionEntryItem.getPictureClause().getPictureString());

			assertNotNull(dataDescriptionEntryItem.getValueClause());

			{
				final ValueInterval valueInterval = dataDescriptionEntryItem.getValueClause().getValueIntervals()
						.get(0);
				assertNotNull(valueInterval);

				{
					final ValueStmt fromValueStmt = valueInterval.getFromValueStmt();
					final LiteralValueStmt literalFromValueStmt = (LiteralValueStmt) fromValueStmt;
					final Literal literal = literalFromValueStmt.getLiteral();
					assertEquals("SPACES", literal.getValue());

					assertNotNull(literal.getFigurativeConstant());
					assertEquals(Type.SPACES, literal.getFigurativeConstant().getType());
				}
			}
		}

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.findDataDescriptionEntry("ITEMZER");

			assertNotNull(dataDescriptionEntryItem);
			assertNotNull(dataDescriptionEntryItem.getPictureClause());
			assertEquals("9(10)", dataDescriptionEntryItem.getPictureClause().getPictureString());

			assertNotNull(dataDescriptionEntryItem.getValueClause());

			{
				final ValueInterval valueInterval = dataDescriptionEntryItem.getValueClause().getValueIntervals()
						.get(0);
				assertNotNull(valueInterval);

				{
					final ValueStmt fromValueStmt = valueInterval.getFromValueStmt();
					final LiteralValueStmt literalFromValueStmt = (LiteralValueStmt) fromValueStmt;
					final Literal literal = literalFromValueStmt.getLiteral();
					assertEquals("ZEROS", literal.getValue());

					assertNotNull(literal.getFigurativeConstant());
					assertEquals(Type.ZEROS, literal.getFigurativeConstant().getType());
				}
			}
		}
	}
}
