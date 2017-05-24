package io.proleap.cobol.asg.data.workingstorage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.datadescription.ValueInterval;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DataDescriptionValueBooleanTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/workingstorage/DataDescriptionValueBoolean.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DataDescriptionValueBoolean");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.getDataDescriptionEntry("ITEMTRUE");

			assertNotNull(dataDescriptionEntryItem);
			assertNotNull(dataDescriptionEntryItem.getValueClause());

			final ValueInterval valueInterval = dataDescriptionEntryItem.getValueClause().getValueIntervals().get(0);
			assertNotNull(valueInterval);

			{
				final ValueStmt fromValueStmt = valueInterval.getFromValueStmt();
				final LiteralValueStmt literalFromValueStmt = (LiteralValueStmt) fromValueStmt;
				final Literal literal = literalFromValueStmt.getLiteral();
				assertEquals(Literal.LiteralType.BOOLEAN, literal.getLiteralType());
				assertTrue(literal.getBooleanLiteral().getValue());
			}
		}

		{
			final DataDescriptionEntryGroup dataDescriptionEntryItem = (DataDescriptionEntryGroup) workingStorageSection
					.getDataDescriptionEntry("ITEMFALSE");

			assertNotNull(dataDescriptionEntryItem);
			assertNotNull(dataDescriptionEntryItem.getValueClause());

			final ValueInterval valueInterval = dataDescriptionEntryItem.getValueClause().getValueIntervals().get(0);
			assertNotNull(valueInterval);

			{
				final ValueStmt fromValueStmt = valueInterval.getFromValueStmt();
				final LiteralValueStmt literalFromValueStmt = (LiteralValueStmt) fromValueStmt;
				final Literal literal = literalFromValueStmt.getLiteral();
				assertEquals(Literal.LiteralType.BOOLEAN, literal.getLiteralType());
				assertFalse(literal.getBooleanLiteral().getValue());
			}
		}
	}
}
