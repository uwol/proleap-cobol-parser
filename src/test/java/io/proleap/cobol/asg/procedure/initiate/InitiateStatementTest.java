package io.proleap.cobol.asg.procedure.initiate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.call.ReportCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.report.ReportDescription;
import io.proleap.cobol.asg.metamodel.data.report.ReportSection;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.initiate.InitiateStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class InitiateStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/initiate/InitiateStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("InitiateStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();

		final ReportDescription reportDescription1;
		final ReportDescription reportDescription2;

		{
			final DataDivision dataDivision = programUnit.getDataDivision();
			assertNotNull(dataDivision);

			{
				final ReportSection reportSection = dataDivision.getReportSection();
				assertNotNull(reportSection);

				{
					reportDescription1 = reportSection.getReportDescription("SOMEREPORT1");
					assertNotNull(reportDescription1);
					assertEquals(1, reportDescription1.getCalls().size());
				}

				{
					reportDescription2 = reportSection.getReportDescription("SOMEREPORT2");
					assertNotNull(reportDescription2);
					assertEquals(1, reportDescription2.getCalls().size());
				}
			}
		}

		{
			final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
			assertEquals(0, procedureDivision.getParagraphs().size());
			assertEquals(1, procedureDivision.getStatements().size());

			{
				final InitiateStatement initiateStatement = (InitiateStatement) procedureDivision.getStatements()
						.get(0);
				assertNotNull(initiateStatement);
				assertEquals(StatementTypeEnum.INITIATE, initiateStatement.getStatementType());
				assertEquals(2, initiateStatement.getReportCalls().size());

				{
					final Call call = initiateStatement.getReportCalls().get(0);
					assertNotNull(call);

					{
						final ReportCall reportCall = (ReportCall) call;
						assertEquals(CallType.REPORT_DESCRIPTION_CALL, call.getCallType());
						assertEquals(reportDescription1, reportCall.getReport());
					}
				}

				{
					final Call call = initiateStatement.getReportCalls().get(1);
					assertNotNull(call);

					{
						final ReportCall reportCall = (ReportCall) call;
						assertEquals(CallType.REPORT_DESCRIPTION_CALL, call.getCallType());
						assertEquals(reportDescription2, reportCall.getReport());
					}
				}
			}
		}
	}
}
