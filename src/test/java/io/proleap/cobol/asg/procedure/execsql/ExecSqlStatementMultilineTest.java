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
import io.proleap.cobol.asg.metamodel.identification.IdentificationDivision;
import io.proleap.cobol.asg.metamodel.identification.ProgramIdParagraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.execsql.ExecSqlStatement;
import io.proleap.cobol.asg.metamodel.procedure.ifstmt.IfStatement;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ExecSqlStatementMultilineTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/execsql/ExecSqlMultiline.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.VARIABLE);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ExecSqlMultiline");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final IdentificationDivision identificationDivision = programUnit.getIdentificationDivision();

		{
			final ProgramIdParagraph programIdParagraph = identificationDivision.getProgramIdParagraph();
			assertEquals("EXECSQL", programIdParagraph.getName());
		}

		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(6, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(5, workingStorageSection.getRootDataDescriptionEntries().size());

		{
			final DataDescriptionEntryExecSql dataDescriptionEntry = (DataDescriptionEntryExecSql) workingStorageSection
					.getRootDataDescriptionEntries().get(0);

			assertNotNull(dataDescriptionEntry);
			assertEquals(DataDescriptionEntryType.EXEC_SQL, dataDescriptionEntry.getDataDescriptionEntryType());
			assertEquals("EXEC SQL INCLUDE SQLSCRIPT END-EXEC", dataDescriptionEntry.getExecSqlText());
		}

		{
			final DataDescriptionEntryExecSql dataDescriptionEntry = (DataDescriptionEntryExecSql) workingStorageSection
					.getRootDataDescriptionEntries().get(1);

			assertNotNull(dataDescriptionEntry);
			assertEquals(DataDescriptionEntryType.EXEC_SQL, dataDescriptionEntry.getDataDescriptionEntryType());
			assertEquals("EXEC SQL INCLUDE TEACHER END-EXEC", dataDescriptionEntry.getExecSqlText());
		}

		{
			final DataDescriptionEntryExecSql dataDescriptionEntry = (DataDescriptionEntryExecSql) workingStorageSection
					.getRootDataDescriptionEntries().get(2);

			assertNotNull(dataDescriptionEntry);
			assertEquals(DataDescriptionEntryType.EXEC_SQL, dataDescriptionEntry.getDataDescriptionEntryType());
			assertEquals("EXEC SQL BEGIN DECLARE SECTION END-EXEC", dataDescriptionEntry.getExecSqlText());
		}

		{
			final DataDescriptionEntry dataDescriptionEntry = workingStorageSection.getRootDataDescriptionEntries()
					.get(3);

			assertNotNull(dataDescriptionEntry);
			assertEquals("WS-TEACHER-REC", dataDescriptionEntry.getName());
			assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntry.getDataDescriptionEntryType());
			assertEquals(Integer.valueOf(1), dataDescriptionEntry.getLevelNumber());
			assertNull(dataDescriptionEntry.getParentDataDescriptionEntryGroup());
		}

		{
			final DataDescriptionEntryExecSql dataDescriptionEntry = (DataDescriptionEntryExecSql) workingStorageSection
					.getRootDataDescriptionEntries().get(4);

			assertNotNull(dataDescriptionEntry);
			assertEquals(DataDescriptionEntryType.EXEC_SQL, dataDescriptionEntry.getDataDescriptionEntryType());
			assertEquals("EXEC SQL END DECLARE SECTION END-EXEC", dataDescriptionEntry.getExecSqlText());
		}

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(3, procedureDivision.getStatements().size());

		{
			final ExecSqlStatement execSqlStatement = (ExecSqlStatement) procedureDivision.getStatements().get(0);
			assertNotNull(execSqlStatement);
			assertEquals(StatementTypeEnum.EXEC_SQL, execSqlStatement.getStatementType());

			{
				assertNotNull(execSqlStatement.getExecSqlText());
				assertEquals("EXEC SQL SELECT TEACHER-ID INTO :WS-TEACHER-ID FROM TEACHER WHERE TEACHER-ID=1 END-EXEC",
						execSqlStatement.getExecSqlText());
			}
		}

		{
			final IfStatement ifStatement = (IfStatement) procedureDivision.getStatements().get(1);
			assertNotNull(ifStatement);
			assertEquals(StatementTypeEnum.IF, ifStatement.getStatementType());
		}

		{
			final StopStatement stopStatement = (StopStatement) procedureDivision.getStatements().get(2);
			assertNotNull(stopStatement);
			assertEquals(StatementTypeEnum.STOP, stopStatement.getStatementType());
		}
	}
}