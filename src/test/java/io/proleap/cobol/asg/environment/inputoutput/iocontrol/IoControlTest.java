package io.proleap.cobol.asg.environment.inputoutput.iocontrol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.IoControlParagraph;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.MultipleFileClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.MultipleFilePosition;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.RerunClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.RerunEveryRecords;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol.SameClause;
import io.proleap.cobol.asg.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class IoControlTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/environment/inputoutput/iocontrol/IoControl.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("IoControl");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();

		final IoControlParagraph ioControlParagraph = environmentDivision.getInputOutputSection()
				.getIoControlParagraph();
		assertNotNull(ioControlParagraph);

		final Call fileCall = ioControlParagraph.getFileCall();
		assertNotNull(fileCall);

		{
			assertFalse(ioControlParagraph.getSameClauses().isEmpty());

			final SameClause sameClause = ioControlParagraph.getSameClauses().get(0);
			assertEquals(2, sameClause.getFileCalls().size());
		}

		{
			assertNotNull(ioControlParagraph.getMultipleFileClause());

			final MultipleFileClause multipleFileClause = ioControlParagraph.getMultipleFileClause();
			assertEquals(1, multipleFileClause.getMultipleFilePositions().size());

			final MultipleFilePosition filePosition = multipleFileClause.getMultipleFilePositions().get(0);
			final IntegerLiteralValueStmt integerLiteralValueStmt = filePosition.getIntegerLiteralValueStmt();
			final IntegerLiteral literal = integerLiteralValueStmt.getLiteral();
			assertEquals(BigDecimal.ONE, literal.getValue());
		}

		{
			assertNotNull(ioControlParagraph.getCommitmentControlClause());
		}

		{
			assertNotNull(ioControlParagraph.getRerunClause());

			final RerunClause rerunClause = ioControlParagraph.getRerunClause();
			final RerunEveryRecords rerunEveryRecords = rerunClause.getRerunEveryRecords();
			final IntegerLiteral records = rerunEveryRecords.getRecords();
			assertEquals(BigDecimal.ONE, records.getValue());
		}
	}
}
