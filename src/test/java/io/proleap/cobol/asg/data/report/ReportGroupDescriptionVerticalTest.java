package io.proleap.cobol.asg.data.report;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.report.LineNumberClause;
import io.proleap.cobol.asg.metamodel.data.report.NextGroupClause;
import io.proleap.cobol.asg.metamodel.data.report.Report;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.report.ReportGroupDescriptionEntryVertical;
import io.proleap.cobol.asg.metamodel.data.report.ReportSection;
import io.proleap.cobol.asg.metamodel.data.report.TypeClause;
import io.proleap.cobol.asg.metamodel.data.report.UsageClause;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ReportGroupDescriptionVerticalTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/report/ReportGroupDescriptionVertical.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ReportGroupDescriptionVertical");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ReportSection reportSection = dataDivision.getReportSection();

		assertEquals(1, reportSection.getReports().size());

		final Report report1 = reportSection.getReport("REPORT1");
		assertNotNull(report1);
		assertEquals(1, report1.getReportGroupDescriptionEntries().size());

		final ReportGroupDescriptionEntry reportGroupDescriptionEntry = report1.getReportGroupDescriptionEntries()
				.get(0);
		assertEquals(ReportGroupDescriptionEntry.Type.Vertical, reportGroupDescriptionEntry.getType());

		final ReportGroupDescriptionEntryVertical reportGroupDescriptionEntryVertical = (ReportGroupDescriptionEntryVertical) reportGroupDescriptionEntry;
		assertEquals(new Integer(1), reportGroupDescriptionEntryVertical.getLevelNumber());

		{
			final LineNumberClause lineNumberClause = reportGroupDescriptionEntryVertical.getLineNumberClause();
			assertEquals(new Integer(2), lineNumberClause.getIntegerLiteral().getValue());
		}

		{
			final NextGroupClause nextGroupClause = reportGroupDescriptionEntryVertical.getNextGroupClause();
			assertEquals(NextGroupClause.Type.NextPage, nextGroupClause.getType());
			assertNull(nextGroupClause.getIntegerLiteral());
		}

		{
			final TypeClause typeClause = reportGroupDescriptionEntryVertical.getTypeClause();
			assertEquals(TypeClause.Type.ControlHeading, typeClause.getType());
			assertNotNull(typeClause.getDataCall());
		}

		{
			final UsageClause usageClause = reportGroupDescriptionEntryVertical.getUsageClause();
			assertEquals(UsageClause.Type.Display1, usageClause.getType());
		}
	}
}