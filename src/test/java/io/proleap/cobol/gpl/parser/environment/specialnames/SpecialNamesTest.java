package io.proleap.cobol.gpl.parser.environment.specialnames;

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
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.environment.specialnames.ChannelClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.ClassClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.ClassClauseThrough;
import io.proleap.cobol.parser.metamodel.environment.specialnames.CurrencySignClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.DecimalPointClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.OdtClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.ReserveNetworkClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.SpecialNamesParagraph;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SpecialNamesTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/environment/specialnames/SpecialNames.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("SpecialNames");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();
		final SpecialNamesParagraph specialNamesParagraph = environmentDivision.getSpecialNamesParagraph();

		{
			final ChannelClause channelClause = specialNamesParagraph.getChannelClause();
			assertNotNull(channelClause);
			assertEquals(new Integer(2), channelClause.getIntegerLiteral().getValue());
			assertEquals("SOMEMNEMONIC", channelClause.getMnemonicName().getValue());
		}

		{
			final ClassClause classClause = specialNamesParagraph.getClassClause();
			assertNotNull(classClause);
			assertNotNull(classClause.getClassNameValueStmt());
			assertEquals(ClassClause.Type.National, classClause.getType());

			assertEquals(2, classClause.getClassClauseThroughs().size());

			{
				final ClassClauseThrough clauseThrough1 = classClause.getClassClauseThroughs().get(0);

				final ValueStmt from1 = clauseThrough1.getFrom();
				assertNotNull(from1);

				final ValueStmt to1 = clauseThrough1.getTo();
				assertNotNull(to1);
			}

			{
				final ClassClauseThrough clauseThrough2 = classClause.getClassClauseThroughs().get(1);

				final ValueStmt from2 = clauseThrough2.getFrom();
				assertNotNull(from2);

				final ValueStmt to2 = clauseThrough2.getTo();
				assertNotNull(to2);
			}
		}

		{
			final CurrencySignClause currencySignClause = specialNamesParagraph.getCurrencySignClause();
			assertNotNull(currencySignClause);
			assertEquals("'E'", currencySignClause.getCurrencyLiteral().getValue());
			assertEquals("'-'", currencySignClause.getPictureSymbolLiteral().getValue());
		}

		{
			final DecimalPointClause decimalPointClause = specialNamesParagraph.getDecimalPointClause();
			assertNotNull(decimalPointClause);
		}

		{
			final OdtClause odtClause = specialNamesParagraph.getOdtClause();
			assertNotNull(odtClause);
			assertEquals("SOMEMNEMONIC", odtClause.getMnemonicName().getValue());
		}

		{
			final ReserveNetworkClause reserveNetworkClause = specialNamesParagraph.getReserveNetworkClause();
			assertNotNull(reserveNetworkClause);
		}
	}
}
