package io.proleap.cobol.gpl.parser.environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.AccessModeClause;
import io.proleap.cobol.parser.metamodel.environment.AlternateRecordKeyClause;
import io.proleap.cobol.parser.metamodel.environment.AssignClause;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.environment.FileControlEntry;
import io.proleap.cobol.parser.metamodel.environment.OrganizationClause;
import io.proleap.cobol.parser.metamodel.environment.PaddingCharacterClause;
import io.proleap.cobol.parser.metamodel.environment.PasswordClause;
import io.proleap.cobol.parser.metamodel.environment.RecordDelimiterClause;
import io.proleap.cobol.parser.metamodel.environment.RecordKeyClause;
import io.proleap.cobol.parser.metamodel.environment.ReserveClause;
import io.proleap.cobol.parser.metamodel.environment.SelectClause;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class FileControlTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/gpl/parser/environment/FileControl.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("FileControl");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();

		final FileControlEntry fileControlEntry = environmentDivision.getFileControlEntry("TEACHER");
		assertNotNull(fileControlEntry);

		final SelectClause selectClause = fileControlEntry.getSelectClause();
		assertNotNull(selectClause);
		assertEquals("TEACHER", selectClause.getName());

		{
			final AssignClause assignClause = fileControlEntry.getAssignClause();
			assertNotNull(assignClause);
			assertEquals("'teacher.txt'", assignClause.getValueStmt().getValue());
		}

		{
			final ReserveClause reserveClause = fileControlEntry.getReserveClause();
			assertNotNull(reserveClause);
			assertEquals(new Integer(10), reserveClause.getValueStmt().getValue());
		}

		{
			final OrganizationClause organizationClause = fileControlEntry.getOrganizationClause();
			assertNotNull(organizationClause);
			assertEquals(OrganizationClause.Type.Record, organizationClause.getType());
			assertEquals(OrganizationClause.Mode.Indexed, organizationClause.getMode());
		}

		{
			final PaddingCharacterClause paddingCharacterClause = fileControlEntry.getPaddingCharacterClause();
			assertNotNull(paddingCharacterClause);
			assertEquals("'-'", paddingCharacterClause.getValueStmt().getValue());
		}

		{
			final RecordDelimiterClause recordDelimiterClause = fileControlEntry.getRecordDelimiterClause();
			assertNotNull(recordDelimiterClause);
			assertEquals("IMPLICIT", recordDelimiterClause.getValueStmt().getValue());
		}

		{
			final AccessModeClause accessModeClause = fileControlEntry.getAccessModeClause();
			assertNotNull(accessModeClause);
			assertEquals(AccessModeClause.Mode.Dynamic, accessModeClause.getMode());
		}

		{
			final RecordKeyClause recordKeyClause = fileControlEntry.getRecordKeyClause();
			assertNotNull(recordKeyClause);
			assertNotNull(recordKeyClause.getValueStmt());

			final PasswordClause passwordClause = recordKeyClause.getPasswordClause();
			assertNotNull(passwordClause);
			assertNotNull(passwordClause.getValueStmt());
		}

		{
			final AlternateRecordKeyClause alternateRecordKeyClause = fileControlEntry.getAlternateRecordKeyClause();
			assertNotNull(alternateRecordKeyClause);
			assertNotNull(alternateRecordKeyClause.getValueStmt());

			final PasswordClause passwordClause = alternateRecordKeyClause.getPasswordClause();
			assertNotNull(passwordClause);
			assertNotNull(passwordClause.getValueStmt());
		}

		{
			final PasswordClause passwordClause = fileControlEntry.getPasswordClause();
			assertNotNull(passwordClause);
			assertNotNull(passwordClause.getValueStmt());
		}
	}
}
