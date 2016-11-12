package io.proleap.cobol.gpl.parser.environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.ConfigurationSection;
import io.proleap.cobol.parser.metamodel.environment.DiskSizeClause;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.environment.MemorySizeClause;
import io.proleap.cobol.parser.metamodel.environment.ObjectComputerParagraph;
import io.proleap.cobol.parser.metamodel.environment.SegmentLimitClause;
import io.proleap.cobol.parser.metamodel.environment.SourceComputerParagraph;
import io.proleap.cobol.parser.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ConfigurationSectionTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/environment/ConfigurationSection.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("ConfigurationSection");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();
		final ConfigurationSection configurationSection = environmentDivision.getConfigurationSection();
		final SourceComputerParagraph sourceComputerParagraph = configurationSection.getSourceComputerParagraph();
		final ObjectComputerParagraph objectComputerParagraph = configurationSection.getObjectComputerParagraph();

		assertTrue(sourceComputerParagraph.isDebuggingMode());
		assertEquals("XYZ", sourceComputerParagraph.getName());

		final MemorySizeClause memorySizeClause = objectComputerParagraph.getMemorySizeClause();
		final IntegerLiteralValueStmt memorySizeValueStmt = (IntegerLiteralValueStmt) memorySizeClause.getValueStmt();
		assertEquals(new Integer(8192), memorySizeValueStmt.getValue().getValue());
		assertEquals(MemorySizeClause.Unit.Characters, memorySizeClause.getUnit());

		final DiskSizeClause diskSizeClause = objectComputerParagraph.getDiskSizeClause();
		final IntegerLiteralValueStmt diskSizeValueStmt = (IntegerLiteralValueStmt) diskSizeClause.getValueStmt();
		assertEquals(new Integer(4096), diskSizeValueStmt.getValue().getValue());
		assertEquals(DiskSizeClause.Unit.Words, diskSizeClause.getUnit());

		final SegmentLimitClause segmentLimitClause = objectComputerParagraph.getSegmentLimitClause();
		final IntegerLiteral segmentLimitIntegerLiteral = segmentLimitClause.getIntegerLiteral();
		assertEquals(new Integer(128), segmentLimitIntegerLiteral.getValue());
	}
}
