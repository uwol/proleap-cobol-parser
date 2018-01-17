package io.proleap.cobol.asg.data.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.file.BlockContainsClause;
import io.proleap.cobol.asg.metamodel.data.file.CodeSetClause;
import io.proleap.cobol.asg.metamodel.data.file.DataRecordsClause;
import io.proleap.cobol.asg.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.file.FileSection;
import io.proleap.cobol.asg.metamodel.data.file.GlobalClause;
import io.proleap.cobol.asg.metamodel.data.file.LabelRecordsClause;
import io.proleap.cobol.asg.metamodel.data.file.LinageClause;
import io.proleap.cobol.asg.metamodel.data.file.RecordContainsClause;
import io.proleap.cobol.asg.metamodel.data.file.ReportClause;
import io.proleap.cobol.asg.metamodel.data.file.ValueOfClause;
import io.proleap.cobol.asg.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class FileDescriptionEntryTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/data/file/FileDescriptionEntry.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("FileDescriptionEntry");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final FileSection fileSection = dataDivision.getFileSection();

		{
			final FileDescriptionEntry fileDescriptionEntry = fileSection.getFileDescriptionEntry("PERSON");
			assertNotNull(fileDescriptionEntry);
			assertNotNull(fileDescriptionEntry.getFileCall());
			assertEquals("PERSON", fileDescriptionEntry.getFileCall().getName());

			{
				final BlockContainsClause blockContainsClause = fileDescriptionEntry.getBlockContainsClause();
				assertNotNull(blockContainsClause);
				assertEquals(BigDecimal.ONE, blockContainsClause.getFrom().getValue());
				assertEquals(new BigDecimal(5), blockContainsClause.getTo().getValue());
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
				assertEquals(BigDecimal.ONE, recordContainsClause.getFrom().getValue());
				assertEquals(new BigDecimal(5), recordContainsClause.getTo().getValue());
				assertNotNull(recordContainsClause.getDependingOnCall());
			}

			{
				final LabelRecordsClause labelRecordsClause = fileDescriptionEntry.getLabelRecordsClause();
				assertNotNull(labelRecordsClause);
				assertEquals(LabelRecordsClause.LabelRecordsClauseType.DATA_NAMES,
						labelRecordsClause.getLabelRecordsClauseType());
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
				assertNotNull(linageClause.getFootingAtValueStmt());
				assertNotNull(linageClause.getLinesAtTopValueStmt());

				{
					final IntegerLiteralValueStmt numberOfLinesValueStmt = (IntegerLiteralValueStmt) linageClause
							.getNumberOfLinesValueStmt();
					final IntegerLiteral literal = numberOfLinesValueStmt.getLiteral();
					assertEquals(new BigDecimal(30), literal.getValue());
				}

				{
					final IntegerLiteralValueStmt footingAtValueStmt = (IntegerLiteralValueStmt) linageClause
							.getFootingAtValueStmt();
					final IntegerLiteral literal = footingAtValueStmt.getLiteral();
					assertEquals(new BigDecimal(5), literal.getValue());
				}

				{
					final IntegerLiteralValueStmt linesAtTopValueStmt = (IntegerLiteralValueStmt) linageClause
							.getLinesAtTopValueStmt();
					final IntegerLiteral literal = linesAtTopValueStmt.getLiteral();
					assertEquals(new BigDecimal(2), literal.getValue());
				}

				{
					assertNotNull(linageClause.getLinesAtBottomValueStmt());
				}
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
}