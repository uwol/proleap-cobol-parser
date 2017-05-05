package io.proleap.cobol.asg.procedure.add;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.AddTo;
import io.proleap.cobol.asg.metamodel.procedure.add.From;
import io.proleap.cobol.asg.metamodel.procedure.add.To;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class AddToStatementTest extends CobolTestSupport {

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/add/AddToStatement.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("AddToStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(0, procedureDivision.getParagraphs().size());
		assertEquals(2, procedureDivision.getStatements().size());

		{
			final AddStatement addStatement = (AddStatement) procedureDivision.getStatements().get(0);
			assertEquals(StatementTypeEnum.ADD, addStatement.getStatementType());
			assertEquals(AddStatement.Type.TO, addStatement.getType());
			assertNotNull(addStatement.getAddTo());

			{
				final AddTo addTo = addStatement.getAddTo();
				assertEquals(1, addTo.getFroms().size());

				{
					final From from = addTo.getFroms().get(0);
					assertNotNull(from.getFrom());
					assertEquals(1, addTo.getTos().size());
				}

				{
					final To to = addTo.getTos().get(0);
					assertNotNull(to.getTo());
					assertEquals(Call.CallType.DATA_DESCRIPTION_ENTRY_CALL, to.getTo().getCallType());
				}
			}
		}

		{
			final AddStatement addStatement = (AddStatement) procedureDivision.getStatements().get(1);
			assertEquals(StatementTypeEnum.ADD, addStatement.getStatementType());
			assertEquals(AddStatement.Type.TO, addStatement.getType());
			assertNotNull(addStatement.getAddTo());

			{
				final AddTo addTo = addStatement.getAddTo();
				assertEquals(2, addTo.getFroms().size());

				{
					final From from1 = addTo.getFroms().get(0);
					assertNotNull(from1.getFrom());
				}

				{
					final From from2 = addTo.getFroms().get(1);
					assertNotNull(from2.getFrom());
				}

				assertEquals(2, addTo.getTos().size());

				{
					final To to1 = addTo.getTos().get(0);
					assertNotNull(to1.getTo());
					assertEquals(Call.CallType.DATA_DESCRIPTION_ENTRY_CALL, to1.getTo().getCallType());
				}

				{
					final To to2 = addTo.getTos().get(1);
					assertNotNull(to2.getTo());
					assertEquals(Call.CallType.DATA_DESCRIPTION_ENTRY_CALL, to2.getTo().getCallType());
				}
			}
		}
	}
}
