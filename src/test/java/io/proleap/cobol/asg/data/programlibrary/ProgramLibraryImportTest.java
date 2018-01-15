package io.proleap.cobol.asg.data.programlibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.programlibrary.CommonClause;
import io.proleap.cobol.asg.metamodel.data.programlibrary.GlobalClause;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ImportAttribute;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ImportAttribute.ImportAttributeType;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ImportEntryProcedure;
import io.proleap.cobol.asg.metamodel.data.programlibrary.LibraryDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.programlibrary.LibraryDescriptionEntryImport;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ProgramLibrarySection;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ProgramLibraryImportTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/programlibrary/ProgramLibraryImport.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ProgramLibraryImport");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ProgramLibrarySection programLibrarySection = dataDivision.getProgramLibrarySection();

		{
			final LibraryDescriptionEntry libraryDescriptionEntry = programLibrarySection
					.getLibraryDescriptionEntry("SOMELIB");
			assertNotNull(libraryDescriptionEntry);
			assertEquals(LibraryDescriptionEntry.LibraryDescriptionEntryType.IMPORT,
					libraryDescriptionEntry.getLibraryDescriptionEntryType());

			final LibraryDescriptionEntryImport libraryDescriptionEntryImport = (LibraryDescriptionEntryImport) libraryDescriptionEntry;

			{
				final GlobalClause globalClause = libraryDescriptionEntryImport.getGlobalClause();
				assertNotNull(globalClause);
				assertTrue(globalClause.isGlobal());
			}

			{
				final CommonClause commonClause = libraryDescriptionEntryImport.getCommonClause();
				assertNotNull(commonClause);
				assertTrue(commonClause.isCommon());
			}

			{
				final List<ImportAttribute> importAttributes = libraryDescriptionEntryImport.getImportAttributes();
				assertEquals(1, importAttributes.size());

				final ImportAttribute importAttribute = importAttributes.get(0);
				assertEquals(ImportAttributeType.BY_TITLE, importAttribute.getImportAttributeType());
				assertEquals(new BigDecimal(123), importAttribute.getFunctionLiteral().getNumericLiteral().getValue());
				assertEquals(new BigDecimal(234), importAttribute.getParameterLiteral().getNumericLiteral().getValue());
				assertEquals("SOMETITLE", importAttribute.getTitleLiteral().getNonNumericLiteral());
			}

			{
				final List<ImportEntryProcedure> importEntryProcedures = libraryDescriptionEntryImport
						.getImportEntryProcedures();
				assertEquals(1, importEntryProcedures.size());

				final ImportEntryProcedure importEntryProcedure = importEntryProcedures.get(0);
				assertEquals(new BigDecimal(123),
						importEntryProcedure.getForClause().getForLiteral().getNumericLiteral().getValue());

				assertNotNull(importEntryProcedure.getUsingClause());
				assertEquals(2, importEntryProcedure.getUsingClause().getUsingValueStmts().size());

				assertNotNull(importEntryProcedure.getWithClause());
				assertEquals(2, importEntryProcedure.getWithClause().getWithValueStmts().size());

				assertNotNull(importEntryProcedure.getGivingClause().getGivingCall());
			}
		}
	}
}