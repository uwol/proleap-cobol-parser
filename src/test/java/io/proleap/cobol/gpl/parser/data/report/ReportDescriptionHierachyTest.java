package io.proleap.cobol.gpl.parser.data.report;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CompilationUnit;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.report.Report;
import io.proleap.cobol.parser.metamodel.data.report.ReportGroupDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.report.ReportSection;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ReportDescriptionHierachyTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/data/report/ReportGroupDescriptionHierarchy.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ReportGroupDescriptionHierarchy");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ReportSection reportSection = dataDivision.getReportSection();
		final Report report = reportSection.getReports().get(0);

		final List<ReportGroupDescriptionEntry> rootReportGroupDescriptionEntries = report
				.getRootReportGroupDescriptionEntries();
		assertEquals(2, rootReportGroupDescriptionEntries.size());

		{
			final ReportGroupDescriptionEntry reportGroupDescriptionEntry1 = rootReportGroupDescriptionEntries.get(0);
			assertEquals(new Integer(1), reportGroupDescriptionEntry1.getLevelNumber());

			assertEquals(2, reportGroupDescriptionEntry1.getReportGroupDescriptionEntries().size());

			{
				final ReportGroupDescriptionEntry subReportGroupDescriptionEntry1 = reportGroupDescriptionEntry1
						.getReportGroupDescriptionEntries().get(0);
				assertEquals(new Integer(4), subReportGroupDescriptionEntry1.getLevelNumber());
			}

			{
				final ReportGroupDescriptionEntry subReportGroupDescriptionEntry2 = reportGroupDescriptionEntry1
						.getReportGroupDescriptionEntries().get(1);
				assertEquals(new Integer(2), subReportGroupDescriptionEntry2.getLevelNumber());
			}
		}

		{
			final ReportGroupDescriptionEntry reportGroupDescriptionEntry2 = rootReportGroupDescriptionEntries.get(1);
			assertEquals(new Integer(1), reportGroupDescriptionEntry2.getLevelNumber());
		}
	}
}