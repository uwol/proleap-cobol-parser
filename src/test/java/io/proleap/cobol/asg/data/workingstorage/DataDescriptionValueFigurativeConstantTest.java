package io.proleap.cobol.asg.data.workingstorage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.FigurativeConstant;
import io.proleap.cobol.asg.metamodel.FigurativeConstant.FigurativeConstantType;
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

public class DataDescriptionValueFigurativeConstantTest extends CobolTestBase {

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
				assertEquals(Literal.LiteralType.FIGURATIVE_CONSTANT, literal.getLiteralType());

				{
					final FigurativeConstant figurativeConstant = literal.getFigurativeConstant();
					assertNotNull(figurativeConstant);
					assertEquals(FigurativeConstantType.ALL, figurativeConstant.getFigurativeConstantType());

					final Literal allLiteral = figurativeConstant.getLiteral();
					assertNotNull(allLiteral);
					assertEquals(Literal.LiteralType.NUMERIC, allLiteral.getLiteralType());
					assertEquals(new BigDecimal(2), allLiteral.getNumericLiteral().getValue());
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
				assertEquals(Literal.LiteralType.FIGURATIVE_CONSTANT, literal.getLiteralType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(FigurativeConstantType.HIGH_VALUE,
						literal.getFigurativeConstant().getFigurativeConstantType());
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
				assertEquals(Literal.LiteralType.FIGURATIVE_CONSTANT, literal.getLiteralType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(FigurativeConstantType.HIGH_VALUES,
						literal.getFigurativeConstant().getFigurativeConstantType());
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
				assertEquals(Literal.LiteralType.FIGURATIVE_CONSTANT, literal.getLiteralType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(FigurativeConstantType.LOW_VALUE,
						literal.getFigurativeConstant().getFigurativeConstantType());
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
				assertEquals(Literal.LiteralType.FIGURATIVE_CONSTANT, literal.getLiteralType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(FigurativeConstantType.LOW_VALUES,
						literal.getFigurativeConstant().getFigurativeConstantType());
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
				assertEquals(Literal.LiteralType.FIGURATIVE_CONSTANT, literal.getLiteralType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(FigurativeConstantType.NULL, literal.getFigurativeConstant().getFigurativeConstantType());
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
				assertEquals(Literal.LiteralType.FIGURATIVE_CONSTANT, literal.getLiteralType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(FigurativeConstantType.NULLS, literal.getFigurativeConstant().getFigurativeConstantType());
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
				assertEquals(Literal.LiteralType.FIGURATIVE_CONSTANT, literal.getLiteralType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(FigurativeConstantType.QUOTE, literal.getFigurativeConstant().getFigurativeConstantType());
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
				assertEquals(Literal.LiteralType.FIGURATIVE_CONSTANT, literal.getLiteralType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(FigurativeConstantType.QUOTES,
						literal.getFigurativeConstant().getFigurativeConstantType());
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
				assertEquals(Literal.LiteralType.FIGURATIVE_CONSTANT, literal.getLiteralType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(FigurativeConstantType.SPACE, literal.getFigurativeConstant().getFigurativeConstantType());
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
					assertEquals(Literal.LiteralType.FIGURATIVE_CONSTANT, literal.getLiteralType());

					assertNotNull(literal.getFigurativeConstant());
					assertEquals(FigurativeConstantType.SPACES,
							literal.getFigurativeConstant().getFigurativeConstantType());
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
					assertEquals(Literal.LiteralType.FIGURATIVE_CONSTANT, literal.getLiteralType());

					assertNotNull(literal.getFigurativeConstant());
					assertEquals(FigurativeConstantType.ZERO,
							literal.getFigurativeConstant().getFigurativeConstantType());
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
					assertEquals(Literal.LiteralType.FIGURATIVE_CONSTANT, literal.getLiteralType());

					assertNotNull(literal.getFigurativeConstant());
					assertEquals(FigurativeConstantType.ZEROS,
							literal.getFigurativeConstant().getFigurativeConstantType());
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
				assertEquals(Literal.LiteralType.FIGURATIVE_CONSTANT, literal.getLiteralType());

				assertNotNull(literal.getFigurativeConstant());
				assertEquals(FigurativeConstantType.ZEROES,
						literal.getFigurativeConstant().getFigurativeConstantType());
			}
		}
	}
}
