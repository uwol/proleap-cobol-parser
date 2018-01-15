package io.proleap.cobol.asg.data.report;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
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
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ReportGroupDescriptionVerticalTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/report/ReportGroupDescriptionVertical.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

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
				assertEquals(ReportGroupDescriptionEntry.ReportGroupDescriptionEntryType.VERTICAL,
						reportGroupDescriptionEntry.getReportGroupDescriptionEntryType());

				final ReportGroupDescriptionEntryVertical reportGroupDescriptionEntryVertical = (ReportGroupDescriptionEntryVertical) reportGroupDescriptionEntry;
				assertEquals(Integer.valueOf(1), reportGroupDescriptionEntryVertical.getLevelNumber());

				{
					final LineNumberClause lineNumberClause = reportGroupDescriptionEntryVertical.getLineNumberClause();
					assertEquals(new BigDecimal(2), lineNumberClause.getIntegerLiteral().getValue());
				}

				{
					final NextGroupClause nextGroupClause = reportGroupDescriptionEntryVertical.getNextGroupClause();
					assertEquals(NextGroupClause.NextGroupClauseType.NEXT_PAGE,
							nextGroupClause.getNextGroupClauseType());
					assertNull(nextGroupClause.getIntegerLiteral());
				}

				{
					final TypeClause typeClause = reportGroupDescriptionEntryVertical.getTypeClause();
					assertEquals(TypeClause.TypeClauseType.CONTROL_HEADING, typeClause.getTypeClauseType());
					assertNotNull(typeClause.getDataCall());
				}

				{
					final UsageClause usageClause = reportGroupDescriptionEntryVertical.getUsageClause();
					assertEquals(UsageClause.UsageClauseType.DISPLAY_1, usageClause.getUsageClauseType());
				}
			}
		}
	}
}