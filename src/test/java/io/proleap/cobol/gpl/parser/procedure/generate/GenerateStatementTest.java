package io.proleap.cobol.gpl.parser.procedure.generate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CompilationUnit;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.call.ReportDescriptionEntryCall;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.report.Report;
import io.proleap.cobol.parser.metamodel.data.report.ReportDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.report.ReportSection;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.generate.GenerateStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class GenerateStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/generate/GenerateStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("GenerateStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ReportSection reportSection = dataDivision.getReportSection();
		final Report report1 = reportSection.getReport("REPORT1");
		assertNotNull(report1);

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final GenerateStatement generateStatement = (GenerateStatement) procedureDivision.getStatements().get(0);
		assertNotNull(generateStatement);

		assertEquals(Call.CallType.ReportDescriptionEntryCall,
				generateStatement.getReportDescriptionCall().getCallType());

		final ReportDescriptionEntryCall reportDescriptionEntryCall = (ReportDescriptionEntryCall) generateStatement
				.getReportDescriptionCall();
		final ReportDescriptionEntry reportDescriptionEntry = reportDescriptionEntryCall.getReportDescriptionEntry();

		assertEquals(report1, reportDescriptionEntry.getReport());
	}
}
