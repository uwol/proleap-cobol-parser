package io.proleap.cobol.asg.procedure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.procedure.ByReference;
import io.proleap.cobol.asg.metamodel.procedure.ByReferencePhrase;
import io.proleap.cobol.asg.metamodel.procedure.ByValue;
import io.proleap.cobol.asg.metamodel.procedure.ByValuePhrase;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.UsingClause;
import io.proleap.cobol.asg.metamodel.procedure.UsingParameter;
import io.proleap.cobol.asg.metamodel.procedure.UsingParameter.UsingParameterType;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class ProcedureDivisionUsingClauseTest extends CobolTestBase {

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/ProcedureDivisionUsingClause.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("ProcedureDivisionUsingClause");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		final DataDescriptionEntry dataDescriptionEntry = workingStorageSection.getDataDescriptionEntry("SOME-DATA");

		{
			assertEquals(1, dataDescriptionEntry.getCalls().size());
		}

		{
			final UsingClause usingClause = procedureDivision.getUsingClause();
			assertNotNull(usingClause);

			assertEquals(2, usingClause.getUsingParameters().size());

			{
				final UsingParameter usingParameter = usingClause.getUsingParameters().get(0);
				assertEquals(UsingParameterType.REFERENCE, usingParameter.getUsingParameterType());

				{
					final ByReferencePhrase byReferencePhrase = usingParameter.getByReferencePhrase();
					assertNotNull(byReferencePhrase);

					assertEquals(2, byReferencePhrase.getByReferences().size());

					{
						final ByReference byReference = byReferencePhrase.getByReferences().get(0);
						assertNotNull(byReference);

						final Call referenceCall = byReference.getReferenceCall();
						assertNotNull(referenceCall);
					}

					{
						final ByReference byReference = byReferencePhrase.getByReferences().get(1);
						assertNotNull(byReference);

						assertTrue(byReference.isAny());
					}
				}
			}

			{
				final UsingParameter usingParameter = usingClause.getUsingParameters().get(1);
				assertEquals(UsingParameterType.VALUE, usingParameter.getUsingParameterType());

				{
					final ByValuePhrase byValuePhrase = usingParameter.getByValuePhrase();
					assertNotNull(byValuePhrase);

					assertEquals(2, byValuePhrase.getByValues().size());

					{
						final ByValue byValue = byValuePhrase.getByValues().get(0);
						assertNotNull(byValue);

						final ValueStmt valueValueStmt = byValue.getValueValueStmt();
						assertNotNull(valueValueStmt);
					}

					{
						final ByValue byValue = byValuePhrase.getByValues().get(1);
						assertNotNull(byValue);

						assertTrue(byValue.isAny());
					}
				}
			}
		}
	}
}
