package io.proleap.cobol.asg.data.report;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.report.FirstDetailClause;
import io.proleap.cobol.asg.metamodel.data.report.FootingClause;
import io.proleap.cobol.asg.metamodel.data.report.GlobalClause;
import io.proleap.cobol.asg.metamodel.data.report.HeadingClause;
import io.proleap.cobol.asg.metamodel.data.report.LastDetailClause;
import io.proleap.cobol.asg.metamodel.data.report.PageLimitClause;
import io.proleap.cobol.asg.metamodel.data.report.ReportDescription;
import io.proleap.cobol.asg.metamodel.data.report.ReportDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.report.ReportSection;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ReportDescriptionTest extends CobolTestSupport {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/data/report/ReportDescription.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ReportDescription");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ReportSection reportSection = dataDivision.getReportSection();

		assertEquals(2, reportSection.getReportDescriptions().size());

		{
			final ReportDescription reportDescription = reportSection.getReportDescription("REPORT1");
			assertNotNull(reportDescription);

			{
				final ReportDescriptionEntry reportDescriptionEntry = reportDescription.getReportDescriptionEntry();

				{
					final GlobalClause globalClause = reportDescriptionEntry.getGlobalClause();
					assertNotNull(globalClause);
					assertTrue(globalClause.isGlobal());
				}

				{
					final PageLimitClause pageLimitClause = reportDescriptionEntry.getPageLimitClause();
					assertNotNull(pageLimitClause);
					assertEquals(new Integer(5), pageLimitClause.getPageLimitIntegerLiteral().getValue());
				}

				{
					final HeadingClause headingClause = reportDescriptionEntry.getHeadingClause();
					assertNotNull(headingClause);
					assertEquals(new Integer(1), headingClause.getHeadingIntegerLiteral().getValue());
				}

				{
					final FirstDetailClause firstDetailClause = reportDescriptionEntry.getFirstDetailClause();
					assertNotNull(firstDetailClause);
					assertEquals(new Integer(2), firstDetailClause.getFirstDetailIntegerLiteral().getValue());
				}

				{
					final LastDetailClause lastDetailClause = reportDescriptionEntry.getLastDetailClause();
					assertNotNull(lastDetailClause);
					assertEquals(new Integer(3), lastDetailClause.getLastDetailIntegerLiteral().getValue());
				}

				{
					final FootingClause footingClause = reportDescriptionEntry.getFootingClause();
					assertNotNull(footingClause);
					assertEquals(new Integer(4), footingClause.getFootingIntegerLiteral().getValue());
				}

				{
					final ReportGroupDescriptionEntry reportGroupDescriptionEntry = reportDescription
							.getReportGroupDescriptionEntry("SOMEDATANAME");
					assertNotNull(reportGroupDescriptionEntry);
					assertEquals(new Integer(1), reportGroupDescriptionEntry.getLevelNumber());
				}
			}
		}

		{
			final ReportDescription report = reportSection.getReportDescription("REPORT2");
			assertNotNull(report);

			{
				final ReportDescriptionEntry reportDescriptionEntry = report.getReportDescriptionEntry();

				{
					final GlobalClause globalClause = reportDescriptionEntry.getGlobalClause();
					assertNotNull(globalClause);
					assertTrue(globalClause.isGlobal());
				}

				{
					final PageLimitClause pageLimitClause = reportDescriptionEntry.getPageLimitClause();
					assertNotNull(pageLimitClause);
					assertEquals(new Integer(10), pageLimitClause.getPageLimitIntegerLiteral().getValue());
				}

				{
					final HeadingClause headingClause = reportDescriptionEntry.getHeadingClause();
					assertNotNull(headingClause);
					assertEquals(new Integer(11), headingClause.getHeadingIntegerLiteral().getValue());
				}

				{
					final FirstDetailClause firstDetailClause = reportDescriptionEntry.getFirstDetailClause();
					assertNotNull(firstDetailClause);
					assertEquals(new Integer(12), firstDetailClause.getFirstDetailIntegerLiteral().getValue());
				}

				{
					final LastDetailClause lastDetailClause = reportDescriptionEntry.getLastDetailClause();
					assertNotNull(lastDetailClause);
					assertEquals(new Integer(13), lastDetailClause.getLastDetailIntegerLiteral().getValue());
				}

				{
					final FootingClause footingClause = reportDescriptionEntry.getFootingClause();
					assertNotNull(footingClause);
					assertEquals(new Integer(14), footingClause.getFootingIntegerLiteral().getValue());
				}

				{
					final ReportGroupDescriptionEntry reportGroupDescriptionEntry = report
							.getReportGroupDescriptionEntry("SOMEDATANAME");
					assertNotNull(reportGroupDescriptionEntry);
					assertEquals(new Integer(1), reportGroupDescriptionEntry.getLevelNumber());
				}
			}
		}
	}
}