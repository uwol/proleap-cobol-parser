package io.proleap.cobol.asg.data.workingstorage;

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
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryContainer.DataDescriptionEntryContainerType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.datadescription.UsageClause;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class BinaryDataDescriptionTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/workingstorage/BinaryDataDescriptionEntry.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("BinaryDataDescriptionEntry");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(7, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(3, workingStorageSection.getRootDataDescriptionEntries().size());

		assertEquals(DataDescriptionEntryContainerType.WORKING_STORAGE_SECTION,
				workingStorageSection.getContainerType());

		final DataDescriptionEntry dataDescriptionEntryBinary = workingStorageSection.getDataDescriptionEntry("BINARY");

		{
			assertNotNull(dataDescriptionEntryBinary);
			assertEquals("BINARY", dataDescriptionEntryBinary.getName());
			assertEquals(Integer.valueOf(1), dataDescriptionEntryBinary.getLevelNumber());
			assertNull(dataDescriptionEntryBinary.getParentDataDescriptionEntryGroup());
			assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryBinary.getDataDescriptionEntryType());
		}

		final DataDescriptionEntry dataDescriptionEntryBinaryAmbiguous = workingStorageSection
				.getDataDescriptionEntry("SOME-DATA-BINARY");
		final DataDescriptionEntry dataDescriptionEntryBinaryUsage = workingStorageSection
				.getDataDescriptionEntry("SOME-DATA-BINARY-USAGE");

		{
			assertNotNull(dataDescriptionEntryBinaryAmbiguous);
			assertEquals("SOME-DATA-BINARY", dataDescriptionEntryBinaryAmbiguous.getName());
			assertEquals(DataDescriptionEntryType.GROUP,
					dataDescriptionEntryBinaryAmbiguous.getDataDescriptionEntryType());

			final DataDescriptionEntryGroup dataDescriptionEntryGroupBinaryAmbiguous = (DataDescriptionEntryGroup) dataDescriptionEntryBinaryAmbiguous;

			assertNotNull(dataDescriptionEntryGroupBinaryAmbiguous.getUsageClause());

			{
				final UsageClause usageClause = dataDescriptionEntryGroupBinaryAmbiguous.getUsageClause();
				assertNotNull(usageClause);
				assertEquals(UsageClause.UsageClauseType.BINARY, usageClause.getUsageClauseType());
			}
		}

		{
			assertNotNull(dataDescriptionEntryBinaryUsage);
			assertEquals("SOME-DATA-BINARY-USAGE", dataDescriptionEntryBinaryUsage.getName());
			assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryBinaryUsage.getDataDescriptionEntryType());

			final DataDescriptionEntryGroup dataDescriptionEntryGroupBinaryUsage = (DataDescriptionEntryGroup) dataDescriptionEntryBinaryUsage;

			assertNotNull(dataDescriptionEntryGroupBinaryUsage.getUsageClause());

			{
				final UsageClause usageClause = dataDescriptionEntryGroupBinaryUsage.getUsageClause();
				assertNotNull(usageClause);
				assertEquals(UsageClause.UsageClauseType.BINARY, usageClause.getUsageClauseType());
			}
		}
	}
}
