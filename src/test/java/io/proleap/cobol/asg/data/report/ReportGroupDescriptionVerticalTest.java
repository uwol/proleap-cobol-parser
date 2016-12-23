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
import io.proleap.cobol.asg.metamodel.data.report.ReportDescription;
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

		assertEquals(1, reportSection.getReportDescriptions().size());

		{
			final ReportDescription reportDescription = reportSection.getReportDescription("REPORT1");
			assertNotNull(reportDescription);
			assertEquals(1, reportDescription.getReportGroupDescriptionEntries().size());

			{
				final ReportGroupDescriptionEntry reportGroupDescriptionEntry = reportDescription
						.getReportGroupDescriptionEntries().get(0);
				assertEquals(ReportGroupDescriptionEntry.Type.VERTICAL, reportGroupDescriptionEntry.getType());

				final ReportGroupDescriptionEntryVertical reportGroupDescriptionEntryVertical = (ReportGroupDescriptionEntryVertical) reportGroupDescriptionEntry;
				assertEquals(new Integer(1), reportGroupDescriptionEntryVertical.getLevelNumber());

				{
					final LineNumberClause lineNumberClause = reportGroupDescriptionEntryVertical.getLineNumberClause();
					assertEquals(new Integer(2), lineNumberClause.getIntegerLiteral().getValue());
				}

				{
					final NextGroupClause nextGroupClause = reportGroupDescriptionEntryVertical.getNextGroupClause();
					assertEquals(NextGroupClause.Type.NEXT_PAGE, nextGroupClause.getType());
					assertNull(nextGroupClause.getIntegerLiteral());
				}

				{
					final TypeClause typeClause = reportGroupDescriptionEntryVertical.getTypeClause();
					assertEquals(TypeClause.Type.CONTROL_HEADING, typeClause.getType());
					assertNotNull(typeClause.getDataCall());
				}

				{
					final UsageClause usageClause = reportGroupDescriptionEntryVertical.getUsageClause();
					assertEquals(UsageClause.Type.DISPLAY_1, usageClause.getType());
				}
			}
		}
	}
}