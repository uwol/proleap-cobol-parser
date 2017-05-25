package io.proleap.cobol.asg.procedure.generate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.call.ReportCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.report.ReportDescription;
import io.proleap.cobol.asg.metamodel.data.report.ReportSection;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.generate.GenerateStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class GenerateStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/generate/GenerateStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("GenerateStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();

		final ReportDescription reportDescription1;

		{
			final ReportSection reportSection = dataDivision.getReportSection();

			{
				reportDescription1 = reportSection.getReportDescription("REPORT1");
				assertNotNull(reportDescription1);
				assertEquals(1, reportDescription1.getCalls().size());
			}
		}

		{
			final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
			assertEquals(0, procedureDivision.getParagraphs().size());
			assertEquals(1, procedureDivision.getStatements().size());

			{
				final GenerateStatement generateStatement = (GenerateStatement) procedureDivision.getStatements()
						.get(0);
				assertNotNull(generateStatement);
				assertEquals(StatementTypeEnum.GENERATE, generateStatement.getStatementType());
				assertEquals(CallType.REPORT_DESCRIPTION_CALL,
						generateStatement.getReportDescriptionCall().getCallType());

				{
					final ReportCall reportDescriptionCall = (ReportCall) generateStatement.getReportDescriptionCall();
					assertEquals(reportDescription1, reportDescriptionCall.getReport());
				}
			}
		}
	}
}
