package io.proleap.cobol.gpl.parser.data.report;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CompilationUnit;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.report.BlankWhenZeroClause;
import io.proleap.cobol.parser.metamodel.data.report.ColumnNumberClause;
import io.proleap.cobol.parser.metamodel.data.report.JustifiedClause;
import io.proleap.cobol.parser.metamodel.data.report.LineNumberClause;
import io.proleap.cobol.parser.metamodel.data.report.PictureClause;
import io.proleap.cobol.parser.metamodel.data.report.Report;
import io.proleap.cobol.parser.metamodel.data.report.ReportGroupDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.report.ReportGroupDescriptionEntryPrintable;
import io.proleap.cobol.parser.metamodel.data.report.ReportSection;
import io.proleap.cobol.parser.metamodel.data.report.SignClause;
import io.proleap.cobol.parser.metamodel.data.report.SumClause;
import io.proleap.cobol.parser.metamodel.data.report.UsageClause;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ReportGroupDescriptionResetTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/data/report/ReportGroupDescriptionPrintable.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ReportGroupDescriptionPrintable");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ReportSection reportSection = dataDivision.getReportSection();

		assertEquals(1, reportSection.getReports().size());

		final Report report1 = reportSection.getReport("REPORT1");
		assertNotNull(report1);
		assertEquals(1, report1.getReportGroupDescriptionEntries().size());

		final ReportGroupDescriptionEntry reportGroupDescriptionEntry = report1.getReportGroupDescriptionEntries()
				.get(0);
		assertEquals(ReportGroupDescriptionEntry.Type.Printable, reportGroupDescriptionEntry.getType());

		final ReportGroupDescriptionEntryPrintable reportGroupDescriptionEntryPrintable = (ReportGroupDescriptionEntryPrintable) reportGroupDescriptionEntry;
		assertEquals(new Integer(1), reportGroupDescriptionEntryPrintable.getLevelNumber());

		{
			final PictureClause pictureClause = reportGroupDescriptionEntryPrintable.getPictureClause();
			assertEquals("9(10)", pictureClause.getPictureString());
		}

		{
			final SignClause signClause = reportGroupDescriptionEntryPrintable.getSignClause();
			assertEquals(SignClause.Type.Trailing, signClause.getType());
		}

		{
			final JustifiedClause justifiedClause = reportGroupDescriptionEntryPrintable.getJustifiedClause();
			assertEquals(JustifiedClause.Justified.JustifiedRight, justifiedClause.getJustified());
		}

		{
			final BlankWhenZeroClause blankWhenZeroClause = reportGroupDescriptionEntryPrintable
					.getBlankWhenZeroClause();
			assertTrue(blankWhenZeroClause.isBlankWhenZero());
		}

		{
			final LineNumberClause lineNumberClause = reportGroupDescriptionEntryPrintable.getLineNumberClause();
			assertEquals(new Integer(2), lineNumberClause.getIntegerLiteral().getValue());
		}

		{
			final ColumnNumberClause columnNumberClause = reportGroupDescriptionEntryPrintable.getColumnNumberClause();
			assertEquals(new Integer(42), columnNumberClause.getIntegerLiteral().getValue());
		}

		{
			final SumClause sumClause = reportGroupDescriptionEntryPrintable.getSumClause();
			assertEquals(3, sumClause.getSumCalls().size());
			assertEquals(2, sumClause.getUponCalls().size());
		}

		{
			final UsageClause usageClause = reportGroupDescriptionEntryPrintable.getUsageClause();
			assertEquals(UsageClause.Type.Display1, usageClause.getType());
		}
	}
}