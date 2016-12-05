package io.proleap.cobol.asg.data.programlibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ExportAttribute;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ExportEntryProcedure;
import io.proleap.cobol.asg.metamodel.data.programlibrary.LibraryDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.programlibrary.LibraryDescriptionEntryExport;
import io.proleap.cobol.asg.metamodel.data.programlibrary.ProgramLibrarySection;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ProgramLibraryExportTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/programlibrary/ProgramLibraryExport.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ProgramLibraryExport");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ProgramLibrarySection programLibrarySection = dataDivision.getProgramLibrarySection();

		{
			final LibraryDescriptionEntry libraryDescriptionEntry = programLibrarySection
					.getLibraryDescriptionEntry("SOMELIB");
			assertNotNull(libraryDescriptionEntry);
			assertEquals(LibraryDescriptionEntry.Type.Export, libraryDescriptionEntry.getType());

			final LibraryDescriptionEntryExport libraryDescriptionEntryExport = (LibraryDescriptionEntryExport) libraryDescriptionEntry;

			{
				final ExportAttribute exportAttribute = libraryDescriptionEntryExport.getExportAttribute();
				assertNotNull(exportAttribute);
				assertEquals(ExportAttribute.Sharing.Private, exportAttribute.getSharing());
			}

			{
				final ExportEntryProcedure exportEntryProcedure = libraryDescriptionEntryExport
						.getExportEntryProcedure();
				assertNotNull(exportEntryProcedure);
				assertNotNull(exportEntryProcedure.getProgramCall());
				assertNotNull(exportEntryProcedure.getForClause());
				assertEquals("123", exportEntryProcedure.getForClause().getForLiteral().getValue());
			}
		}
	}
}