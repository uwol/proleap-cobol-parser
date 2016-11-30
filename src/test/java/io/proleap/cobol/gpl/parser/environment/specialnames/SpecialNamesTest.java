package io.proleap.cobol.gpl.parser.environment.specialnames;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CompilationUnit;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.environment.specialnames.AlphabetClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.AlphabetClauseAlphanumeric;
import io.proleap.cobol.parser.metamodel.environment.specialnames.AlphabetClauseAlphanumeric.AlphabetClauseAlphanumericType;
import io.proleap.cobol.parser.metamodel.environment.specialnames.AlphabetClauseNational;
import io.proleap.cobol.parser.metamodel.environment.specialnames.AlphabetClauseNational.AlphabetClauseNationalType;
import io.proleap.cobol.parser.metamodel.environment.specialnames.ChannelClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.ClassClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.ClassThrough;
import io.proleap.cobol.parser.metamodel.environment.specialnames.CurrencySignClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.DecimalPointClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.DefaultDisplaySignClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.OdtClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.ReserveNetworkClause;
import io.proleap.cobol.parser.metamodel.environment.specialnames.SpecialNamesParagraph;
import io.proleap.cobol.parser.metamodel.environment.specialnames.SymbolicCharactersClause;
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
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SpecialNames");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
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
			assertNotNull(classClause.getClassCall());
			assertEquals(ClassClause.Type.National, classClause.getType());

			assertEquals(2, classClause.getClassThroughs().size());

			{
				final ClassThrough clauseThrough1 = classClause.getClassThroughs().get(0);

				final ValueStmt from1 = clauseThrough1.getFrom();
				assertNotNull(from1);

				final ValueStmt to1 = clauseThrough1.getTo();
				assertNotNull(to1);
			}

			{
				final ClassThrough clauseThrough2 = classClause.getClassThroughs().get(1);

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
			final DefaultDisplaySignClause defaultDisplaySignClause = specialNamesParagraph
					.getDefaultDisplaySignClause();
			assertNotNull(defaultDisplaySignClause);
			assertEquals(DefaultDisplaySignClause.Type.Trailing, defaultDisplaySignClause.getType());
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

		{
			final SymbolicCharactersClause symbolicCharactersClause = specialNamesParagraph
					.getSymbolicCharactersClause();
			assertNotNull(symbolicCharactersClause);
			assertEquals(SymbolicCharactersClause.Type.National, symbolicCharactersClause.getType());
		}

		{
			assertNotNull(specialNamesParagraph.getAlphabetClauses());
			assertEquals(5, specialNamesParagraph.getAlphabetClauses().size());

			{
				final AlphabetClause alphabetClause = specialNamesParagraph.getAlphabetClauses().get(0);
				assertEquals(AlphabetClause.Type.National, alphabetClause.getType());
				assertNotNull(alphabetClause.getAlphabetCall());

				final AlphabetClauseNational alphabetClauseNational = (AlphabetClauseNational) alphabetClause;

				assertEquals(AlphabetClauseNationalType.Native, alphabetClauseNational.getAlphabetClauseType());
			}

			{
				final AlphabetClause alphabetClause = specialNamesParagraph.getAlphabetClauses().get(1);
				assertEquals(AlphabetClause.Type.National, alphabetClause.getType());
				assertNotNull(alphabetClause.getAlphabetCall());

				final AlphabetClauseNational alphabetClauseNational = (AlphabetClauseNational) alphabetClause;

				assertEquals(AlphabetClauseNationalType.CcsVersion, alphabetClauseNational.getAlphabetClauseType());
				assertEquals("123", alphabetClauseNational.getCcsVersion().getValue());
			}

			{
				final AlphabetClause alphabetClause = specialNamesParagraph.getAlphabetClauses().get(2);
				assertEquals(AlphabetClause.Type.Alphanumeric, alphabetClause.getType());
				assertNotNull(alphabetClause.getAlphabetCall());

				final AlphabetClauseAlphanumeric alphabetClauseAlphanumeric = (AlphabetClauseAlphanumeric) alphabetClause;

				assertEquals(AlphabetClauseAlphanumericType.Ebcdic, alphabetClauseAlphanumeric.getAlphabetClauseType());
			}

			{
				final AlphabetClause alphabetClause = specialNamesParagraph.getAlphabetClauses().get(3);
				assertEquals(AlphabetClause.Type.Alphanumeric, alphabetClause.getType());
				assertNotNull(alphabetClause.getAlphabetCall());

				final AlphabetClauseAlphanumeric alphabetClauseAlphanumeric = (AlphabetClauseAlphanumeric) alphabetClause;

				assertEquals(1, alphabetClauseAlphanumeric.getCharacterSetCalls().size());
			}

			{
				final AlphabetClause alphabetClause = specialNamesParagraph.getAlphabetClauses().get(4);
				assertEquals(AlphabetClause.Type.Alphanumeric, alphabetClause.getType());
				assertNotNull(alphabetClause.getAlphabetCall());

				final AlphabetClauseAlphanumeric alphabetClauseAlphanumeric = (AlphabetClauseAlphanumeric) alphabetClause;

				assertEquals(4, alphabetClauseAlphanumeric.getCharacterSetCalls().size());
			}
		}
	}
}
