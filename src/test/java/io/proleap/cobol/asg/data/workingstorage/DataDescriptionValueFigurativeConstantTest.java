package io.proleap.cobol.asg.data.workingstorage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.FigurativeConstant;
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
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DataDescriptionValueFigurativeConstantTest extends CobolTestSupport {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/workingstorage/DataDescriptionValueFigurativeConstant.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DataDescriptionValueFigurativeConstant");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.getDataDescriptionEntry("ITEMALL");

			assertNotNull(dataDescriptionEntryItem);
			assertNotNull(dataDescriptionEntryItem.getValueClause());

			final ValueInterval valueInterval = dataDescriptionEntryItem.getValueClause().getValueIntervals().get(0);
			assertNotNull(valueInterval);

			{
				final ValueStmt fromValueStmt = valueInterval.getFromValueStmt();
				final LiteralValueStmt literalFromValueStmt = (LiteralValueStmt) fromValueStmt;
				final Literal literal = literalFromValueStmt.getLiteral();
				assertEquals(Literal.Type.FIGURATIVE_CONSTANT, literal.getType());

				{
					final FigurativeConstant figurativeConstant = literal.getFigurativeConstant();
					assertNotNull(figurativeConstant);
					assertEquals(Type.ALL, figurativeConstant.getType());

					final Literal allLiteral = figurativeConstant.getLiteral();
					assertNotNull(allLiteral);
					assertEquals(Literal.Type.NUMERIC, allLiteral.getType());
					assertEquals(2, allLiteral.getNumericLiteral().getValue());
				}
			}
		}

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.getDataDescriptionEntry("ITEMHIGHVALUE");

			assertNotNull(dataDescriptionEntryItem);
			assertNotNull(dataDescriptionEntryItem.getValueClause());

			final ValueInterval valueInterval = dataDescriptionEntryItem.getValueClause().getValueIntervals().get(0);
			assertNotNull(valueInterval);

			{
				final ValueStmt fromValueStmt = valueInterval.getFromValueStmt();
				final LiteralValueStmt literalFromValueStmt = (LiteralValueStmt) fromValueStmt;
				final Literal literal = literalFromValueStmt.getLiteral();
				assertEquals(Literal.Type.FIGURATIVE_CONSTANT, literal.getType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(Type.HIGH_VALUE, literal.getFigurativeConstant().getType());
			}
		}

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.getDataDescriptionEntry("ITEMHIGHVALUES");

			assertNotNull(dataDescriptionEntryItem);
			assertNotNull(dataDescriptionEntryItem.getValueClause());

			final ValueInterval valueInterval = dataDescriptionEntryItem.getValueClause().getValueIntervals().get(0);
			assertNotNull(valueInterval);

			{
				final ValueStmt fromValueStmt = valueInterval.getFromValueStmt();
				final LiteralValueStmt literalFromValueStmt = (LiteralValueStmt) fromValueStmt;
				final Literal literal = literalFromValueStmt.getLiteral();
				assertEquals(Literal.Type.FIGURATIVE_CONSTANT, literal.getType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(Type.HIGH_VALUES, literal.getFigurativeConstant().getType());
			}
		}

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.getDataDescriptionEntry("ITEMLOWVALUE");

			assertNotNull(dataDescriptionEntryItem);
			assertNotNull(dataDescriptionEntryItem.getValueClause());

			final ValueInterval valueInterval = dataDescriptionEntryItem.getValueClause().getValueIntervals().get(0);
			assertNotNull(valueInterval);

			{
				final ValueStmt fromValueStmt = valueInterval.getFromValueStmt();
				final LiteralValueStmt literalFromValueStmt = (LiteralValueStmt) fromValueStmt;
				final Literal literal = literalFromValueStmt.getLiteral();
				assertEquals(Literal.Type.FIGURATIVE_CONSTANT, literal.getType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(Type.LOW_VALUE, literal.getFigurativeConstant().getType());
			}
		}

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.getDataDescriptionEntry("ITEMLOWVALUES");

			assertNotNull(dataDescriptionEntryItem);
			assertNotNull(dataDescriptionEntryItem.getValueClause());

			final ValueInterval valueInterval = dataDescriptionEntryItem.getValueClause().getValueIntervals().get(0);
			assertNotNull(valueInterval);

			{
				final ValueStmt fromValueStmt = valueInterval.getFromValueStmt();
				final LiteralValueStmt literalFromValueStmt = (LiteralValueStmt) fromValueStmt;
				final Literal literal = literalFromValueStmt.getLiteral();
				assertEquals(Literal.Type.FIGURATIVE_CONSTANT, literal.getType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(Type.LOW_VALUES, literal.getFigurativeConstant().getType());
			}
		}

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.getDataDescriptionEntry("ITEMNULL");

			assertNotNull(dataDescriptionEntryItem);
			assertNotNull(dataDescriptionEntryItem.getValueClause());

			final ValueInterval valueInterval = dataDescriptionEntryItem.getValueClause().getValueIntervals().get(0);
			assertNotNull(valueInterval);

			{
				final ValueStmt fromValueStmt = valueInterval.getFromValueStmt();
				final LiteralValueStmt literalFromValueStmt = (LiteralValueStmt) fromValueStmt;
				final Literal literal = literalFromValueStmt.getLiteral();
				assertEquals(Literal.Type.FIGURATIVE_CONSTANT, literal.getType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(Type.NULL, literal.getFigurativeConstant().getType());
			}
		}

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.getDataDescriptionEntry("ITEMNULLS");

			assertNotNull(dataDescriptionEntryItem);
			assertNotNull(dataDescriptionEntryItem.getValueClause());

			final ValueInterval valueInterval = dataDescriptionEntryItem.getValueClause().getValueIntervals().get(0);
			assertNotNull(valueInterval);

			{
				final ValueStmt fromValueStmt = valueInterval.getFromValueStmt();
				final LiteralValueStmt literalFromValueStmt = (LiteralValueStmt) fromValueStmt;
				final Literal literal = literalFromValueStmt.getLiteral();
				assertEquals(Literal.Type.FIGURATIVE_CONSTANT, literal.getType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(Type.NULLS, literal.getFigurativeConstant().getType());
			}
		}

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.getDataDescriptionEntry("ITEMQUOTE");

			assertNotNull(dataDescriptionEntryItem);
			assertNotNull(dataDescriptionEntryItem.getValueClause());

			final ValueInterval valueInterval = dataDescriptionEntryItem.getValueClause().getValueIntervals().get(0);
			assertNotNull(valueInterval);

			{
				final ValueStmt fromValueStmt = valueInterval.getFromValueStmt();
				final LiteralValueStmt literalFromValueStmt = (LiteralValueStmt) fromValueStmt;
				final Literal literal = literalFromValueStmt.getLiteral();
				assertEquals(Literal.Type.FIGURATIVE_CONSTANT, literal.getType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(Type.QUOTE, literal.getFigurativeConstant().getType());
			}
		}

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.getDataDescriptionEntry("ITEMQUOTES");

			assertNotNull(dataDescriptionEntryItem);
			assertNotNull(dataDescriptionEntryItem.getValueClause());

			final ValueInterval valueInterval = dataDescriptionEntryItem.getValueClause().getValueIntervals().get(0);
			assertNotNull(valueInterval);

			{
				final ValueStmt fromValueStmt = valueInterval.getFromValueStmt();
				final LiteralValueStmt literalFromValueStmt = (LiteralValueStmt) fromValueStmt;
				final Literal literal = literalFromValueStmt.getLiteral();
				assertEquals(Literal.Type.FIGURATIVE_CONSTANT, literal.getType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(Type.QUOTES, literal.getFigurativeConstant().getType());
			}
		}

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.getDataDescriptionEntry("ITEMSPACE");

			assertNotNull(dataDescriptionEntryItem);
			assertNotNull(dataDescriptionEntryItem.getValueClause());

			final ValueInterval valueInterval = dataDescriptionEntryItem.getValueClause().getValueIntervals().get(0);
			assertNotNull(valueInterval);

			{
				final ValueStmt fromValueStmt = valueInterval.getFromValueStmt();
				final LiteralValueStmt literalFromValueStmt = (LiteralValueStmt) fromValueStmt;
				final Literal literal = literalFromValueStmt.getLiteral();
				assertEquals(Literal.Type.FIGURATIVE_CONSTANT, literal.getType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(Type.SPACE, literal.getFigurativeConstant().getType());
			}
		}

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.getDataDescriptionEntry("ITEMSPACES");

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
					assertEquals(Literal.Type.FIGURATIVE_CONSTANT, literal.getType());

					assertNotNull(literal.getFigurativeConstant());
					assertEquals(Type.SPACES, literal.getFigurativeConstant().getType());
				}
			}
		}

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.getDataDescriptionEntry("ITEMZERO");

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
					assertEquals(Literal.Type.FIGURATIVE_CONSTANT, literal.getType());

					assertNotNull(literal.getFigurativeConstant());
					assertEquals(Type.ZERO, literal.getFigurativeConstant().getType());
				}
			}
		}

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.getDataDescriptionEntry("ITEMZEROS");

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
					assertEquals(Literal.Type.FIGURATIVE_CONSTANT, literal.getType());

					assertNotNull(literal.getFigurativeConstant());
					assertEquals(Type.ZEROS, literal.getFigurativeConstant().getType());
				}
			}
		}

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.getDataDescriptionEntry("ITEMZEROES");

			assertNotNull(dataDescriptionEntryItem);
			assertNotNull(dataDescriptionEntryItem.getValueClause());

			final ValueInterval valueInterval = dataDescriptionEntryItem.getValueClause().getValueIntervals().get(0);
			assertNotNull(valueInterval);

			{
				final ValueStmt fromValueStmt = valueInterval.getFromValueStmt();
				final LiteralValueStmt literalFromValueStmt = (LiteralValueStmt) fromValueStmt;
				final Literal literal = literalFromValueStmt.getLiteral();
				assertEquals(Literal.Type.FIGURATIVE_CONSTANT, literal.getType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(Type.ZEROES, literal.getFigurativeConstant().getType());
			}
		}
	}
}
