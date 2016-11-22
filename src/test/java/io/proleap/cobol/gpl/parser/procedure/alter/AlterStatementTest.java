package io.proleap.cobol.gpl.parser.procedure.alter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.call.ProcedureCall;
import io.proleap.cobol.parser.metamodel.procedure.Paragraph;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.alter.AlterProceedTo;
import io.proleap.cobol.parser.metamodel.procedure.alter.AlterStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class AlterStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/alter/AlterStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("AlterStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final Paragraph paragraph1 = procedureDivision.getParagraph("Paragraph-1");
		final Paragraph paragraph2 = procedureDivision.getParagraph("Paragraph-2");

		final AlterStatement alterStatement = (AlterStatement) procedureDivision.getStatements().get(0);
		final List<AlterProceedTo> alterProceedTos = alterStatement.getAlterProceedTos();

		assertEquals(1, alterProceedTos.size());

		final AlterProceedTo alterProceedTo = alterProceedTos.get(0);
		final Call sourceCall = alterProceedTo.getSourceCall();
		assertNotNull(sourceCall);
		assertEquals(Call.CallType.ProcedureCall, sourceCall.getCallType());

		final ProcedureCall sourceProcedureCall = (ProcedureCall) sourceCall;
		assertEquals(paragraph1, sourceProcedureCall.getParagraph());

		final Call targetCall = alterProceedTo.getTargetCall();
		assertNotNull(targetCall);
		assertEquals(Call.CallType.ProcedureCall, targetCall.getCallType());

		final ProcedureCall targetProcedureCall = (ProcedureCall) targetCall;
		assertEquals(paragraph2, targetProcedureCall.getParagraph());
	}
}
