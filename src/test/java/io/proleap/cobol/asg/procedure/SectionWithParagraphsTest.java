package io.proleap.cobol.asg.procedure;

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
import io.proleap.cobol.asg.metamodel.call.ProcedureCall;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Section;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformProcedureStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SectionWithParagraphsTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/SectionWithParagraphs.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("SectionWithParagraphs");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getRootParagraphs().size());
		assertEquals(1, procedureDivision.getSections().size());
		assertEquals(0, procedureDivision.getStatements().size());

		{
			final Section section = procedureDivision.getSection("SOME-SECTION");
			assertNotNull(section);

			{
				final Paragraph paragraph = section.getParagraph("INIT");
				assertNotNull(paragraph);
				assertEquals(1, paragraph.getCalls().size());

				{
					final StopStatement stopStatement = (StopStatement) paragraph.getStatements().get(0);
					assertNotNull(stopStatement);
					assertEquals(StatementTypeEnum.STOP, stopStatement.getStatementType());
				}

				{
					final PerformStatement performStatement = (PerformStatement) paragraph.getStatements().get(1);
					assertNotNull(performStatement);
					assertEquals(StatementTypeEnum.PERFORM, performStatement.getStatementType());

					{
						final PerformProcedureStatement performProcedureStatement = performStatement
								.getPerformProcedureStatement();
						assertEquals(1, performProcedureStatement.getCalls().size());

						final Call call = performProcedureStatement.getCalls().get(0);
						assertEquals(CallType.PROCEDURE_CALL, call.getCallType());
						final ProcedureCall procedureCall = (ProcedureCall) call;

						{
							final Paragraph calledParagraph = procedureCall.getParagraph();
							assertEquals(paragraph, calledParagraph);
						}
					}
				}
			}

			{
				final Paragraph paragraph = section.getParagraph("AFTER-INIT");
				assertNotNull(paragraph);

				{
					final DisplayStatement displayStatement = (DisplayStatement) paragraph.getStatements().get(0);
					assertNotNull(displayStatement);
					assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
				}

				{
					final StopStatement stopStatement = (StopStatement) paragraph.getStatements().get(1);
					assertNotNull(stopStatement);
					assertEquals(StatementTypeEnum.STOP, stopStatement.getStatementType());
				}
			}
		}
	}
}
