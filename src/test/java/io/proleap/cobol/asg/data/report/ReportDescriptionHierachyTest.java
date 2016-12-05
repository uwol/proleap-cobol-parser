package io.proleap.cobol.asg.data.report;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.report.Report;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.report.ReportSection;
import io.proleap.cobol.asg.metamodel.data.report.UsageClause;
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
				"src/test/resources/io/proleap/cobol/asg/data/report/ReportGroupDescriptionHierarchy.cbl");
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
			final ReportGroupDescriptionEntry reportGroupDescriptionEntry = rootReportGroupDescriptionEntries.get(0);
			assertEquals(new Integer(1), reportGroupDescriptionEntry.getLevelNumber());
			assertEquals(2, reportGroupDescriptionEntry.getReportGroupDescriptionEntries().size());

			{
				final UsageClause usageClause = reportGroupDescriptionEntry.getUsageClause();
				assertNotNull(usageClause);
				assertEquals(UsageClause.Type.Display, usageClause.getType());
			}

			{
				final ReportGroupDescriptionEntry subReportGroupDescriptionEntry = reportGroupDescriptionEntry
						.getReportGroupDescriptionEntries().get(0);
				assertEquals(new Integer(4), subReportGroupDescriptionEntry.getLevelNumber());

				{
					final UsageClause usageClause = subReportGroupDescriptionEntry.getUsageClause();
					assertNotNull(usageClause);
					assertEquals(UsageClause.Type.Display, usageClause.getType());
				}
			}

			{
				final ReportGroupDescriptionEntry subReportGroupDescriptionEntry = reportGroupDescriptionEntry
						.getReportGroupDescriptionEntries().get(1);
				assertEquals(new Integer(2), subReportGroupDescriptionEntry.getLevelNumber());

				{
					final UsageClause usageClause = subReportGroupDescriptionEntry.getUsageClause();
					assertNotNull(usageClause);
					assertEquals(UsageClause.Type.Display, usageClause.getType());
				}
			}
		}

		{
			final ReportGroupDescriptionEntry reportGroupDescriptionEntry = rootReportGroupDescriptionEntries.get(1);
			assertEquals(new Integer(1), reportGroupDescriptionEntry.getLevelNumber());

			{
				final UsageClause usageClause = reportGroupDescriptionEntry.getUsageClause();
				assertNotNull(usageClause);
				assertEquals(UsageClause.Type.Display, usageClause.getType());
			}
		}
	}
}