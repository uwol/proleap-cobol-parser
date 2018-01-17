package io.proleap.cobol.asg.environment.inputoutput.filecontrol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.AccessModeClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.AlternateRecordKeyClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.AssignClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileStatusClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.OrganizationClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.PaddingCharacterClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.PasswordClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.RecordDelimiterClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.RecordKeyClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.RelativeKeyClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.ReserveClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.SelectClause;
import io.proleap.cobol.asg.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class FileControlTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/environment/inputoutput/filecontrol/FileControl.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("FileControl");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();

		final FileControlEntry fileControlEntry = environmentDivision.getInputOutputSection().getFileControlParagraph()
				.getFileControlEntry("TEACHER");
		assertNotNull(fileControlEntry);

		final SelectClause selectClause = fileControlEntry.getSelectClause();
		assertNotNull(selectClause);
		assertEquals("TEACHER", selectClause.getName());

		{
			final AssignClause assignClause = fileControlEntry.getAssignClause();
			assertNotNull(assignClause);
			assertEquals(AssignClause.AssignClauseType.CALL, assignClause.getAssignClauseType());

			final LiteralValueStmt literalValueStmt = (LiteralValueStmt) assignClause.getToValueStmt();
			final Literal literal = literalValueStmt.getLiteral();
			assertEquals("teacher.txt", literal.getValue());
		}

		{
			final ReserveClause reserveClause = fileControlEntry.getReserveClause();
			assertNotNull(reserveClause);

			final IntegerLiteralValueStmt valueStmt = reserveClause.getValueStmt();
			final IntegerLiteral literal = valueStmt.getLiteral();
			assertEquals(new BigDecimal(10), literal.getValue());
		}

		{
			final OrganizationClause organizationClause = fileControlEntry.getOrganizationClause();
			assertNotNull(organizationClause);
			assertEquals(OrganizationClause.OrganizationClauseType.RECORD,
					organizationClause.getOrganizationClauseType());
			assertEquals(OrganizationClause.Mode.INDEXED, organizationClause.getMode());
		}

		{
			final PaddingCharacterClause paddingCharacterClause = fileControlEntry.getPaddingCharacterClause();
			assertNotNull(paddingCharacterClause);

			final LiteralValueStmt literalValueStmt = (LiteralValueStmt) paddingCharacterClause.getValueStmt();
			final Literal literal = literalValueStmt.getLiteral();
			assertEquals("-", literal.getValue());
		}

		{
			final RecordDelimiterClause recordDelimiterClause = fileControlEntry.getRecordDelimiterClause();
			assertNotNull(recordDelimiterClause);
			assertEquals(RecordDelimiterClause.RecordDelimiterClauseType.IMPLICIT,
					recordDelimiterClause.getRecordDelimiterClauseType());
		}

		{
			final AccessModeClause accessModeClause = fileControlEntry.getAccessModeClause();
			assertNotNull(accessModeClause);
			assertEquals(AccessModeClause.Mode.DYNAMIC, accessModeClause.getMode());
		}

		{
			final RecordKeyClause recordKeyClause = fileControlEntry.getRecordKeyClause();
			assertNotNull(recordKeyClause);
			assertNotNull(recordKeyClause.getRecordKeyCall());

			final PasswordClause passwordClause = recordKeyClause.getPasswordClause();
			assertNotNull(passwordClause);
			assertNotNull(passwordClause.getDataCall());
		}

		{
			final AlternateRecordKeyClause alternateRecordKeyClause = fileControlEntry.getAlternateRecordKeyClause();
			assertNotNull(alternateRecordKeyClause);
			assertNotNull(alternateRecordKeyClause.getDataCall());

			final PasswordClause passwordClause = alternateRecordKeyClause.getPasswordClause();
			assertNotNull(passwordClause);
			assertNotNull(passwordClause.getDataCall());
		}

		{
			final PasswordClause passwordClause = fileControlEntry.getPasswordClause();
			assertNotNull(passwordClause);
			assertNotNull(passwordClause.getDataCall());
		}

		{
			final FileStatusClause fileStatusClause = fileControlEntry.getFileStatusClause();
			assertNotNull(fileStatusClause);
			assertNotNull(fileStatusClause.getDataCall());
			assertNotNull(fileStatusClause.getDataCall2());
		}

		{
			final RelativeKeyClause relativeKeyClause = fileControlEntry.getRelativeKeyClause();
			assertNotNull(relativeKeyClause);
			assertNotNull(relativeKeyClause.getRelativeKeyCall());
		}
	}
}
