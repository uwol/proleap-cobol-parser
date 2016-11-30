package io.proleap.cobol.gpl.parser.data.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CompilationUnit;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.file.BlockContainsClause;
import io.proleap.cobol.parser.metamodel.data.file.CodeSetClause;
import io.proleap.cobol.parser.metamodel.data.file.DataRecordsClause;
import io.proleap.cobol.parser.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.file.FileSection;
import io.proleap.cobol.parser.metamodel.data.file.GlobalClause;
import io.proleap.cobol.parser.metamodel.data.file.LabelRecordsClause;
import io.proleap.cobol.parser.metamodel.data.file.LinageClause;
import io.proleap.cobol.parser.metamodel.data.file.RecordContainsClause;
import io.proleap.cobol.parser.metamodel.data.file.ReportClause;
import io.proleap.cobol.parser.metamodel.data.file.ValueOfClause;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class FileDescriptionEntryTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/data/file/FileDescriptionEntry.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("FileDescriptionEntry");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final FileSection fileSection = dataDivision.getFileSection();
		final FileDescriptionEntry fileDescriptionEntry = fileSection.getFileDescriptionEntry("PERSON");
		assertNotNull(fileDescriptionEntry);

		{
			final BlockContainsClause blockContainsClause = fileDescriptionEntry.getBlockContainsClause();
			assertNotNull(blockContainsClause);
			assertEquals(new Integer(1), blockContainsClause.getFrom().getValue());
			assertEquals(new Integer(5), blockContainsClause.getTo().getValue());
		}

		{
			final GlobalClause globalClause = fileDescriptionEntry.getGlobalClause();
			assertNotNull(globalClause);
			assertTrue(globalClause.isGlobal());
		}

		{
			final RecordContainsClause recordContainsClause = fileDescriptionEntry.getRecordContainsClause();
			assertNotNull(recordContainsClause);
			assertTrue(recordContainsClause.isVarying());
			assertEquals(new Integer(1), recordContainsClause.getFrom().getValue());
			assertEquals(new Integer(5), recordContainsClause.getTo().getValue());
			assertNotNull(recordContainsClause.getDependingOnCall());
		}

		{
			final LabelRecordsClause labelRecordsClause = fileDescriptionEntry.getLabelRecordsClause();
			assertNotNull(labelRecordsClause);
			assertEquals(LabelRecordsClause.Type.DataNames, labelRecordsClause.getType());
			assertEquals(2, labelRecordsClause.getDataCalls().size());
			assertNotNull(labelRecordsClause.getDataCalls().get(0));
			assertNotNull(labelRecordsClause.getDataCalls().get(1));
		}

		{
			final DataRecordsClause dataRecordsClause = fileDescriptionEntry.getDataRecordsClause();
			assertNotNull(dataRecordsClause);
			assertEquals(2, dataRecordsClause.getDataCalls().size());
			assertNotNull(dataRecordsClause.getDataCalls().get(0));
			assertNotNull(dataRecordsClause.getDataCalls().get(1));
		}

		{
			final ValueOfClause valueOfClause = fileDescriptionEntry.getValueOfClause();
			assertNotNull(valueOfClause);
			assertEquals(1, valueOfClause.getValuePairs().size());
			assertNotNull(valueOfClause.getValuePairs().get(0));
		}

		{
			final LinageClause linageClause = fileDescriptionEntry.getLinageClause();
			assertNotNull(linageClause);

			assertNotNull(linageClause.getNumberOfLinesValueStmt());
			assertEquals(new Integer(30), linageClause.getNumberOfLinesValueStmt().getValue());

			assertNotNull(linageClause.getFootingAtValueStmt());
			assertEquals(new Integer(5), linageClause.getFootingAtValueStmt().getValue());

			assertNotNull(linageClause.getLinesAtTopValueStmt());
			assertEquals(new Integer(2), linageClause.getLinesAtTopValueStmt().getValue());

			assertNotNull(linageClause.getLinesAtBottomValueStmt());
		}

		{
			final CodeSetClause codeSetClause = fileDescriptionEntry.getCodeSetClause();
			assertNotNull(codeSetClause);
			assertEquals("SomeAlphabet", codeSetClause.getAlphabetName());
		}

		{
			final ReportClause reportClause = fileDescriptionEntry.getReportClause();
			assertNotNull(reportClause);
			assertEquals(2, reportClause.getReportCalls().size());
		}
	}
}