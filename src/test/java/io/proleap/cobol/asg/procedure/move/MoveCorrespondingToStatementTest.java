package io.proleap.cobol.asg.procedure.move;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveCorrespondingTo;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class MoveCorrespondingToStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/procedure/move/MoveCorrespondingToStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("MoveCorrespondingToStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

		{
			final DataDescriptionEntry dataDescriptionEntry = workingStorageSection
					.findDataDescriptionEntry("SOME-TEXT");

			assertNotNull(dataDescriptionEntry);
			assertFalse(dataDescriptionEntry.getCalls().isEmpty());
			assertEquals(1, dataDescriptionEntry.getCalls().size());
		}

		{
			final DataDescriptionEntry dataDescriptionEntry = workingStorageSection
					.findDataDescriptionEntry("SOME-TEXT2");

			assertNotNull(dataDescriptionEntry);
			assertFalse(dataDescriptionEntry.getCalls().isEmpty());
			assertEquals(1, dataDescriptionEntry.getCalls().size());
		}

		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(1, procedureDivision.getStatements().size());

		{
			final MoveStatement moveStatement = (MoveStatement) procedureDivision.getStatements().get(0);
			assertNotNull(moveStatement);
			assertEquals(StatementTypeEnum.MOVE, moveStatement.getStatementType());
			assertEquals(MoveStatement.Type.MOVE_CORRESPONDING_TO, moveStatement.getType());

			{
				final MoveCorrespondingTo moveCorrespondingTo = moveStatement.getMoveCorrespondingTo();

				{
					final Call sendingCall = moveCorrespondingTo.getSendingCall();
					assertNotNull(sendingCall);
					assertEquals(Call.CallType.DATA_DESCRIPTION_ENTRY_CALL, sendingCall.getCallType());
				}

				{
					final List<Call> receivingAreaCalls = moveCorrespondingTo.getReceivingAreaCalls();
					assertEquals(1, receivingAreaCalls.size());

					{
						final Call receivingAreaCall = receivingAreaCalls.get(0);
						assertNotNull(receivingAreaCall);
						assertEquals(Call.CallType.DATA_DESCRIPTION_ENTRY_CALL, receivingAreaCall.getCallType());
					}
				}
			}
		}
	}
}
