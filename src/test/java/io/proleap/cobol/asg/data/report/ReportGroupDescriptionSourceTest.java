package io.proleap.cobol.asg.data.report;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.report.ReportDescription;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntry.ReportGroupDescriptionEntryType;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntryPrintable;
import io.proleap.cobol.asg.metamodel.data.report.ReportSection;
import io.proleap.cobol.asg.metamodel.data.report.SourceClause;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ReportGroupDescriptionSourceTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/report/ReportGroupDescriptionSource.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ReportGroupDescriptionSource");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ReportSection reportSection = dataDivision.getReportSection();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertNotNull(reportSection);
		assertEquals(1, reportSection.getReportDescriptions().size());
		assertNotNull(workingStorageSection);

		{
			final ReportDescription reportDescription = reportSection.getReportDescription("SOME-REPORT");
			assertNotNull(reportDescription);

			{
				final ReportGroupDescriptionEntry reportGroupDescriptionEntry = reportDescription
						.getReportGroupDescriptionEntry("SOME-GROUP");
				assertNotNull(reportGroupDescriptionEntry);
				assertEquals(Integer.valueOf(1), reportGroupDescriptionEntry.getLevelNumber());
				assertEquals(ReportGroupDescriptionEntryType.PRINTABLE,
						reportGroupDescriptionEntry.getReportGroupDescriptionEntryType());

				final ReportGroupDescriptionEntryPrintable reportGroupDescriptionEntryPrintable = (ReportGroupDescriptionEntryPrintable) reportGroupDescriptionEntry;

				{
					final SourceClause sourceClause = reportGroupDescriptionEntryPrintable.getSourceClause();
					assertNotNull(sourceClause.getSourceCall());
				}
			}
		}

		final DataDescriptionEntry dataDescriptionEntry = workingStorageSection.getDataDescriptionEntry("SOME-DATA");

		{
			assertEquals(1, dataDescriptionEntry.getCalls().size());
		}
	}
}