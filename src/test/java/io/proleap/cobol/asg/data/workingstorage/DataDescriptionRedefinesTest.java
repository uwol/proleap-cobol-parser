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
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry.DataDescriptionEntryType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryContainer.DataDescriptionEntryContainerType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.datadescription.RedefinesClause;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DataDescriptionRedefinesTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/workingstorage/DataDescriptionRedefines.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("DataDescriptionRedefines");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		assertEquals(6, workingStorageSection.getDataDescriptionEntries().size());
		assertEquals(1, workingStorageSection.getRootDataDescriptionEntries().size());

		assertEquals(DataDescriptionEntryContainerType.WORKING_STORAGE_SECTION,
				workingStorageSection.getContainerType());

		final DataDescriptionEntry dataDescriptionEntryPerson = workingStorageSection.getDataDescriptionEntry("PERSON");

		final DataDescriptionEntry dataDescriptionEntryBirthdate = workingStorageSection
				.getDataDescriptionEntry("BIRTHDATE");
		final DataDescriptionEntry dataDescriptionEntryBirthdateParts = workingStorageSection
				.getDataDescriptionEntry("BIRTHDATE-PARTS");

		final DataDescriptionEntry dataDescriptionEntryBirthYear = workingStorageSection
				.getDataDescriptionEntry("BIRTH-YEAR");
		final DataDescriptionEntry dataDescriptionEntryBirthMonth = workingStorageSection
				.getDataDescriptionEntry("BIRTH-MONTH");
		final DataDescriptionEntry dataDescriptionEntryBirthDay = workingStorageSection
				.getDataDescriptionEntry("BIRTH-DAY");

		{
			assertNotNull(dataDescriptionEntryPerson);
			assertEquals("PERSON", dataDescriptionEntryPerson.getName());
			assertEquals(Integer.valueOf(1), dataDescriptionEntryPerson.getLevelNumber());
			assertNull(dataDescriptionEntryPerson.getParentDataDescriptionEntryGroup());

			{
				assertNotNull(dataDescriptionEntryBirthdate);
				assertEquals("BIRTHDATE", dataDescriptionEntryBirthdate.getName());
				assertEquals(Integer.valueOf(5), dataDescriptionEntryBirthdate.getLevelNumber());
				assertEquals(dataDescriptionEntryPerson,
						dataDescriptionEntryBirthdate.getParentDataDescriptionEntryGroup());
				assertEquals(DataDescriptionEntryType.GROUP,
						dataDescriptionEntryBirthdate.getDataDescriptionEntryType());
			}

			{
				assertNotNull(dataDescriptionEntryBirthdateParts);
				assertEquals("BIRTHDATE-PARTS", dataDescriptionEntryBirthdateParts.getName());
				assertEquals(Integer.valueOf(5), dataDescriptionEntryBirthdateParts.getLevelNumber());
				assertEquals(dataDescriptionEntryPerson,
						dataDescriptionEntryBirthdateParts.getParentDataDescriptionEntryGroup());
				assertEquals(DataDescriptionEntryType.GROUP,
						dataDescriptionEntryBirthdateParts.getDataDescriptionEntryType());

				final DataDescriptionEntryGroup dataDescriptionEntryBirthdatePartsGroup = (DataDescriptionEntryGroup) dataDescriptionEntryBirthdateParts;
				final RedefinesClause redefinesClause = dataDescriptionEntryBirthdatePartsGroup.getRedefinesClause();
				assertNotNull(redefinesClause);

				{
					final Call redefinesCall = redefinesClause.getRedefinesCall();
					assertEquals(CallType.DATA_DESCRIPTION_ENTRY_CALL, redefinesCall.getCallType());

					final DataDescriptionEntryCall dataDescriptionEntryCall = (DataDescriptionEntryCall) redefinesCall
							.unwrap();
					assertEquals(dataDescriptionEntryBirthdate, dataDescriptionEntryCall.getDataDescriptionEntry());
				}

				{
					assertNotNull(dataDescriptionEntryBirthYear);
					assertEquals("BIRTH-YEAR", dataDescriptionEntryBirthYear.getName());
					assertEquals(Integer.valueOf(7), dataDescriptionEntryBirthYear.getLevelNumber());
					assertEquals(dataDescriptionEntryBirthdateParts,
							dataDescriptionEntryBirthYear.getParentDataDescriptionEntryGroup());
					assertEquals(DataDescriptionEntryType.GROUP,
							dataDescriptionEntryBirthYear.getDataDescriptionEntryType());
				}

				{
					assertNotNull(dataDescriptionEntryBirthMonth);
					assertEquals("BIRTH-MONTH", dataDescriptionEntryBirthMonth.getName());
					assertEquals(Integer.valueOf(7), dataDescriptionEntryBirthMonth.getLevelNumber());
					assertEquals(dataDescriptionEntryBirthdateParts,
							dataDescriptionEntryBirthMonth.getParentDataDescriptionEntryGroup());
					assertEquals(DataDescriptionEntryType.GROUP,
							dataDescriptionEntryBirthMonth.getDataDescriptionEntryType());
				}

				{
					assertNotNull(dataDescriptionEntryBirthDay);
					assertEquals("BIRTH-DAY", dataDescriptionEntryBirthDay.getName());
					assertEquals(Integer.valueOf(7), dataDescriptionEntryBirthDay.getLevelNumber());
					assertEquals(dataDescriptionEntryBirthdateParts,
							dataDescriptionEntryBirthDay.getParentDataDescriptionEntryGroup());
					assertEquals(DataDescriptionEntryType.GROUP,
							dataDescriptionEntryBirthDay.getDataDescriptionEntryType());
				}
			}
		}
	}
}
