package io.proleap.cobol.gpl.parser.data.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.file.BlockContainsClause;
import io.proleap.cobol.parser.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.file.FileSection;
import io.proleap.cobol.parser.metamodel.data.file.LabelRecordsClause;
import io.proleap.cobol.parser.metamodel.data.file.RecordContainsClause;
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
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("FileDescriptionEntry");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
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

		assertTrue(fileDescriptionEntry.isExternal());
		assertTrue(fileDescriptionEntry.isGlobal());

		{
			final RecordContainsClause recordContainsClause = fileDescriptionEntry.getRecordContainsClause();
			assertNotNull(recordContainsClause);
			assertTrue(recordContainsClause.isVarying());
			assertEquals(new Integer(1), recordContainsClause.getFrom().getValue());
			assertEquals(new Integer(5), recordContainsClause.getTo().getValue());
			assertNotNull(recordContainsClause.getDependingOnValueStmt());
		}

		{
			final LabelRecordsClause labelRecordsClause = fileDescriptionEntry.getLabelRecordsClause();
			assertNotNull(labelRecordsClause);
			assertEquals(LabelRecordsClause.Type.DataNames, labelRecordsClause.getType());
			assertEquals(2, labelRecordsClause.getDataNames().size());
			assertNotNull(labelRecordsClause.getDataNames().get(0));
			assertNotNull(labelRecordsClause.getDataNames().get(1));
		}
	}
}