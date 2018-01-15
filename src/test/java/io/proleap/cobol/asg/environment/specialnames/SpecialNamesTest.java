package io.proleap.cobol.asg.environment.specialnames;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.specialnames.AlphabetClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.AlphabetClauseAlphanumeric;
import io.proleap.cobol.asg.metamodel.environment.specialnames.AlphabetClauseAlphanumeric.AlphabetClauseAlphanumericType;
import io.proleap.cobol.asg.metamodel.environment.specialnames.AlphabetClauseNational;
import io.proleap.cobol.asg.metamodel.environment.specialnames.AlphabetClauseNational.AlphabetClauseNationalType;
import io.proleap.cobol.asg.metamodel.environment.specialnames.ChannelClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.ClassClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.ClassThrough;
import io.proleap.cobol.asg.metamodel.environment.specialnames.CurrencySignClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.DecimalPointClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.DefaultDisplaySignClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.OdtClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.ReserveNetworkClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.SpecialNamesParagraph;
import io.proleap.cobol.asg.metamodel.environment.specialnames.SymbolicCharactersClause;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SpecialNamesTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/environment/specialnames/SpecialNames.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SpecialNames");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();
		final SpecialNamesParagraph specialNamesParagraph = environmentDivision.getSpecialNamesParagraph();

		{
			final ChannelClause channelClause = specialNamesParagraph.getChannelClause();
			assertNotNull(channelClause);
			assertEquals(new BigDecimal(2), channelClause.getIntegerLiteral().getValue());
			assertEquals("SOMEMNEMONIC", channelClause.getMnemonicName().getValue());
		}

		{
			final ClassClause classClause = specialNamesParagraph.getClassClause();
			assertNotNull(classClause);
			assertNotNull(classClause.getClassCall());
			assertEquals(ClassClause.ClassClauseType.NATIONAL, classClause.getClassClauseType());

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
			assertEquals("E", currencySignClause.getCurrencyLiteral().getNonNumericLiteral());
			assertEquals("-", currencySignClause.getPictureSymbolLiteral().getNonNumericLiteral());
		}

		{
			final DecimalPointClause decimalPointClause = specialNamesParagraph.getDecimalPointClause();
			assertNotNull(decimalPointClause);
		}

		{
			final DefaultDisplaySignClause defaultDisplaySignClause = specialNamesParagraph
					.getDefaultDisplaySignClause();
			assertNotNull(defaultDisplaySignClause);
			assertEquals(DefaultDisplaySignClause.DefaultDisplaySignClauseType.TRAILING,
					defaultDisplaySignClause.getDefaultDisplaySignClauseType());
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
			assertEquals(SymbolicCharactersClause.SymbolicCharactersClauseType.NATIONAL,
					symbolicCharactersClause.getSymbolicCharactersClauseType());
		}

		{
			assertNotNull(specialNamesParagraph.getAlphabetClauses());
			assertEquals(5, specialNamesParagraph.getAlphabetClauses().size());

			{
				final AlphabetClause alphabetClause = specialNamesParagraph.getAlphabetClauses().get(0);
				assertEquals(AlphabetClause.AlphabetClauseType.NATIONAL, alphabetClause.getAlphabetClauseType());
				assertNotNull(alphabetClause.getAlphabetCall());

				final AlphabetClauseNational alphabetClauseNational = (AlphabetClauseNational) alphabetClause;

				assertEquals(AlphabetClauseNationalType.NATIVE, alphabetClauseNational.getAlphabetClauseNationalType());
			}

			{
				final AlphabetClause alphabetClause = specialNamesParagraph.getAlphabetClauses().get(1);
				assertEquals(AlphabetClause.AlphabetClauseType.NATIONAL, alphabetClause.getAlphabetClauseType());
				assertNotNull(alphabetClause.getAlphabetCall());

				final AlphabetClauseNational alphabetClauseNational = (AlphabetClauseNational) alphabetClause;

				assertEquals(AlphabetClauseNationalType.CCS_VERSION,
						alphabetClauseNational.getAlphabetClauseNationalType());
				assertEquals(new BigDecimal(123),
						alphabetClauseNational.getCcsVersion().getNumericLiteral().getValue());
			}

			{
				final AlphabetClause alphabetClause = specialNamesParagraph.getAlphabetClauses().get(2);
				assertEquals(AlphabetClause.AlphabetClauseType.ALPHANUMERIC, alphabetClause.getAlphabetClauseType());
				assertNotNull(alphabetClause.getAlphabetCall());

				final AlphabetClauseAlphanumeric alphabetClauseAlphanumeric = (AlphabetClauseAlphanumeric) alphabetClause;

				assertEquals(AlphabetClauseAlphanumericType.EBCDIC,
						alphabetClauseAlphanumeric.getAlphabetClauseAlphanumericType());
			}

			{
				final AlphabetClause alphabetClause = specialNamesParagraph.getAlphabetClauses().get(3);
				assertEquals(AlphabetClause.AlphabetClauseType.ALPHANUMERIC, alphabetClause.getAlphabetClauseType());
				assertNotNull(alphabetClause.getAlphabetCall());

				final AlphabetClauseAlphanumeric alphabetClauseAlphanumeric = (AlphabetClauseAlphanumeric) alphabetClause;

				assertEquals(1, alphabetClauseAlphanumeric.getCharacterSetValueStmts().size());
			}

			{
				final AlphabetClause alphabetClause = specialNamesParagraph.getAlphabetClauses().get(4);
				assertEquals(AlphabetClause.AlphabetClauseType.ALPHANUMERIC, alphabetClause.getAlphabetClauseType());
				assertNotNull(alphabetClause.getAlphabetCall());

				final AlphabetClauseAlphanumeric alphabetClauseAlphanumeric = (AlphabetClauseAlphanumeric) alphabetClause;

				assertEquals(4, alphabetClauseAlphanumeric.getCharacterSetValueStmts().size());
			}
		}
	}
}
