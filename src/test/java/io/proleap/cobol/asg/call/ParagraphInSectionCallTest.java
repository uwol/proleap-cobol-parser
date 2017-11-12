package io.proleap.cobol.asg.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.Section;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ParagraphInSectionCallTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/call/ParagraphInSectionCall.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ParagraphInSectionCall");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(2, procedureDivision.getParagraphs("SOME-PARAGRAPH").size());

		{
			final Paragraph paragraph = procedureDivision.getParagraph("SOME-PARAGRAPH");
			assertNotNull(paragraph);
			assertEquals(0, paragraph.getCalls().size());
		}

		{
			final Section section = procedureDivision.getSection("SOME-SECTION");
			assertNotNull(section);

			{
				final Paragraph paragraph = section.getParagraph("SOME-PARAGRAPH");
				assertNotNull(paragraph);
				assertEquals(1, paragraph.getCalls().size());
			}
		}
	}
}