package io.proleap.cobol.asg.data.programlibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ExportAttribute;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ExportEntryProcedure;
import io.proleap.cobol.asg.metamodel.data.programlibrary.LibraryDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.programlibrary.LibraryDescriptionEntryExport;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ProgramLibrarySection;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ProgramLibraryExportTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/programlibrary/ProgramLibraryExport.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ProgramLibraryExport");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ProgramLibrarySection programLibrarySection = dataDivision.getProgramLibrarySection();

		{
			final LibraryDescriptionEntry libraryDescriptionEntry = programLibrarySection
					.getLibraryDescriptionEntry("SOMELIB");
			assertNotNull(libraryDescriptionEntry);
			assertEquals(LibraryDescriptionEntry.LibraryDescriptionEntryType.EXPORT,
					libraryDescriptionEntry.getLibraryDescriptionEntryType());

			final LibraryDescriptionEntryExport libraryDescriptionEntryExport = (LibraryDescriptionEntryExport) libraryDescriptionEntry;

			{
				final ExportAttribute exportAttribute = libraryDescriptionEntryExport.getExportAttribute();
				assertNotNull(exportAttribute);
				assertEquals(ExportAttribute.Sharing.PRIVATE, exportAttribute.getSharing());
			}

			{
				final ExportEntryProcedure exportEntryProcedure = libraryDescriptionEntryExport
						.getExportEntryProcedure();
				assertNotNull(exportEntryProcedure);
				assertNotNull(exportEntryProcedure.getProgramCall());
				assertNotNull(exportEntryProcedure.getForClause());
				assertEquals(new BigDecimal(123),
						exportEntryProcedure.getForClause().getForLiteral().getNumericLiteral().getValue());
			}
		}
	}
}