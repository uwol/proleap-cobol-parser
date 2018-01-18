package io.proleap.cobol.asg.procedure.execsql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry.DataDescriptionEntryType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryExecSql;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.execsql.ExecSqlStatement;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ExecSqlStatementTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/execsql/ExecSql.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ExecSql");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(3, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(3, workingStorageSection.getRootDataDescriptionEntries().size());

		{
			final DataDescriptionEntryExecSql dataDescriptionEntry = (DataDescriptionEntryExecSql) workingStorageSection
					.getRootDataDescriptionEntries().get(0);

			assertNotNull(dataDescriptionEntry);
			assertEquals(DataDescriptionEntryType.EXEC_SQL, dataDescriptionEntry.getDataDescriptionEntryType());
			assertEquals("EXEC SQL BEGIN DECLARE SECTION END-EXEC", dataDescriptionEntry.getExecSqlText());
		}

		{
			final DataDescriptionEntry dataDescriptionEntryUserId = workingStorageSection
					.getDataDescriptionEntry("userid");

			assertNotNull(dataDescriptionEntryUserId);
			assertEquals("userid", dataDescriptionEntryUserId.getName());
			assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryUserId.getDataDescriptionEntryType());
			assertEquals(Integer.valueOf(1), dataDescriptionEntryUserId.getLevelNumber());
			assertNull(dataDescriptionEntryUserId.getParentDataDescriptionEntryGroup());
		}

		{
			final DataDescriptionEntryExecSql dataDescriptionEntry = (DataDescriptionEntryExecSql) workingStorageSection
					.getRootDataDescriptionEntries().get(2);

			assertNotNull(dataDescriptionEntry);
			assertEquals(DataDescriptionEntryType.EXEC_SQL, dataDescriptionEntry.getDataDescriptionEntryType());
			assertEquals("EXEC SQL END DECLARE SECTION END-EXEC", dataDescriptionEntry.getExecSqlText());
		}

		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(3, procedureDivision.getStatements().size());

		{
			final ExecSqlStatement execSqlStatement = (ExecSqlStatement) procedureDivision.getStatements().get(0);
			assertNotNull(execSqlStatement);
			assertEquals(StatementTypeEnum.EXEC_SQL, execSqlStatement.getStatementType());

			{
				assertNotNull(execSqlStatement.getExecSqlText());
				assertEquals("EXEC SQL CONNECT TO demo END-EXEC", execSqlStatement.getExecSqlText());
			}
		}

		{
			final DisplayStatement displayStatement = (DisplayStatement) procedureDivision.getStatements().get(1);
			assertNotNull(displayStatement);
			assertEquals(StatementTypeEnum.DISPLAY, displayStatement.getStatementType());
		}

		{
			final StopStatement stopStatement = (StopStatement) procedureDivision.getStatements().get(2);
			assertNotNull(stopStatement);
			assertEquals(StatementTypeEnum.STOP, stopStatement.getStatementType());
		}
	}
}