package io.proleap.cobol.asg.data.workingstorage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.datadescription.CommonOwnLocalClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry.DataDescriptionEntryType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.datadescription.Index;
import io.proleap.cobol.asg.metamodel.data.datadescription.IntegerStringClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.JustifiedClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.OccursClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.OccursIndexed;
import io.proleap.cobol.asg.metamodel.data.datadescription.OccursSort;
import io.proleap.cobol.asg.metamodel.data.datadescription.ReceivedByClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.SignClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.SynchronizedClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.TypeClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.UsageClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.UsingClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.ValueInterval;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class DataDescriptionClausesTest extends CobolTestBase {

	protected void assertProperties(final DataDescriptionEntry dataDescriptionEntryItem) {
		assertNotNull(dataDescriptionEntryItem);
		assertEquals(DataDescriptionEntryType.GROUP, dataDescriptionEntryItem.getDataDescriptionEntryType());
		assertEquals(Integer.valueOf(2), dataDescriptionEntryItem.getLevelNumber());
	}

	protected DataDescriptionEntryGroup getDataDescriptionEntryGroup(final String name) throws Exception {
		final WorkingStorageSection workingStorageSection = getWorkingStorageSection();
		final DataDescriptionEntryGroup result = (DataDescriptionEntryGroup) workingStorageSection
				.getDataDescriptionEntry(name);
		return result;
	}

	protected WorkingStorageSection getWorkingStorageSection() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/asg/data/workingstorage/DataDescriptionClauses.cbl");
		final Program program = new CobolParserRunnerImpl().analyzeFile(inputFile, CobolSourceFormatEnum.TANDEM);
		final CompilationUnit compilationUnit = program.getCompilationUnit("DataDescriptionClauses");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection result = dataDivision.getWorkingStorageSection();
		return result;
	}

	@Test
	public void testItem() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEM");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEM", dataDescriptionEntryGroup.getName());
		assertEquals("X(10)", dataDescriptionEntryGroup.getPictureClause().getPictureString());
	}

	@Test
	public void testItemAli() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMALI");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMALI", dataDescriptionEntryGroup.getName());
		assertEquals("X(10)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getAlignedClause());
		assertTrue(dataDescriptionEntryGroup.getAlignedClause().isAligned());
	}

	@Test
	public void testItemBln() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMBLN");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMBLN", dataDescriptionEntryGroup.getName());
		assertEquals("9(10)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getBlankWhenZeroClause());
		assertTrue(dataDescriptionEntryGroup.getBlankWhenZeroClause().isBlankWhenZero());
	}

	@Test
	public void testItemCom() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMCOM");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMCOM", dataDescriptionEntryGroup.getName());
		assertEquals("9(1)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getCommonOwnLocalClause());
		assertEquals(CommonOwnLocalClause.Invariance.COMMON,
				dataDescriptionEntryGroup.getCommonOwnLocalClause().getInvariance());
	}

	@Test
	public void testItemCon() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMCON");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMCON", dataDescriptionEntryGroup.getName());
		assertEquals("9(1)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getReceivedByClause());
		assertEquals(ReceivedByClause.ReceivedBy.CONTENT,
				dataDescriptionEntryGroup.getReceivedByClause().getReceivedBy());
	}

	@Test
	public void testItemExt() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMEXT");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMEXT", dataDescriptionEntryGroup.getName());
		assertEquals("X(10)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getExternalClause());
		assertTrue(dataDescriptionEntryGroup.getExternalClause().isExternal());
	}

	@Test
	public void testItemGlbt() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMGLB");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMGLB", dataDescriptionEntryGroup.getName());
		assertEquals("X(10)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getGlobalClause());
		assertTrue(dataDescriptionEntryGroup.getGlobalClause().isGlobal());
	}

	@Test
	public void testItemInt() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMINT");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMINT", dataDescriptionEntryGroup.getName());
		assertEquals("9(10)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getIntegerStringClause());
		assertEquals(IntegerStringClause.PrimitiveType.INTEGER,
				dataDescriptionEntryGroup.getIntegerStringClause().getPrimitiveType());
	}

	@Test
	public void testItemJur() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMJUR");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMJUR", dataDescriptionEntryGroup.getName());
		assertEquals("9(1)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getJustifiedClause());
		assertEquals(JustifiedClause.Justified.JUSTIFIED_RIGHT,
				dataDescriptionEntryGroup.getJustifiedClause().getJustified());
	}

	@Test
	public void testItemJus() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMJUS");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMJUS", dataDescriptionEntryGroup.getName());
		assertEquals("9(1)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getJustifiedClause());
		assertEquals(JustifiedClause.Justified.JUSTIFIED,
				dataDescriptionEntryGroup.getJustifiedClause().getJustified());
	}

	@Test
	public void testItemLbd() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMLBD");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMLBD", dataDescriptionEntryGroup.getName());
		assertEquals("X(10)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getWithLowerBoundsClause());
		assertTrue(dataDescriptionEntryGroup.getWithLowerBoundsClause().isWithLowerBounds());
	}

	@Test
	public void testItemLoc() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMLOC");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMLOC", dataDescriptionEntryGroup.getName());
		assertEquals("9(1)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getCommonOwnLocalClause());
		assertEquals(CommonOwnLocalClause.Invariance.LOCAL,
				dataDescriptionEntryGroup.getCommonOwnLocalClause().getInvariance());
	}

	@Test
	public void testItemOcc() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMOCC");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMOCC", dataDescriptionEntryGroup.getName());
		assertEquals("X(10)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		final List<OccursClause> occursClauses = dataDescriptionEntryGroup.getOccursClauses();
		assertEquals(1, occursClauses.size());

		final OccursClause occursClause = occursClauses.get(0);
		assertNotNull(occursClause.getFrom());

		final IntegerLiteralValueStmt from = (IntegerLiteralValueStmt) occursClause.getFrom();
		assertEquals(BigDecimal.ONE, from.getLiteral().getValue());
		assertNotNull(occursClause.getTo());
		assertEquals(new BigDecimal(5), occursClause.getTo().getValue());
		assertNotNull(occursClause.getOccursDepending());

		final List<OccursSort> occursSorts = occursClause.getOccursSorts();
		assertEquals(2, occursSorts.size());

		final OccursSort occursSort1 = occursSorts.get(0);
		assertEquals(OccursSort.Order.DESCENDING, occursSort1.getOrder());
		assertEquals(2, occursSort1.getKeyCalls().size());

		final OccursSort occursSort2 = occursSorts.get(1);
		assertEquals(OccursSort.Order.ASCENDING, occursSort2.getOrder());
		assertEquals(1, occursSort2.getKeyCalls().size());

		final OccursIndexed occursIndexed = occursClause.getOccursIndexed();
		final List<Index> indices = occursIndexed.getIndices();
		assertEquals(2, indices.size());
	}

	@Test
	public void testItemOcc2() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMOCC2");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMOCC2", dataDescriptionEntryGroup.getName());
		assertEquals("X(10)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		final List<OccursClause> occursClauses = dataDescriptionEntryGroup.getOccursClauses();
		assertEquals(1, occursClauses.size());

		final OccursClause occursClause = occursClauses.get(0);
		assertNotNull(occursClause.getFrom());
		assertNull(occursClause.getTo());
		assertNull(occursClause.getOccursDepending());

		final OccursIndexed occursIndexed = occursClause.getOccursIndexed();
		final List<Index> indices = occursIndexed.getIndices();
		assertEquals(1, indices.size());

		final List<OccursSort> occursSorts = occursClause.getOccursSorts();
		assertEquals(1, occursSorts.size());

		final OccursSort occursSort = occursSorts.get(0);
		assertEquals(OccursSort.Order.ASCENDING, occursSort.getOrder());
		assertEquals(1, occursSort.getKeyCalls().size());
	}

	@Test
	public void testItemOwn() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMOWN");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMOWN", dataDescriptionEntryGroup.getName());
		assertEquals("9(1)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getCommonOwnLocalClause());
		assertEquals(CommonOwnLocalClause.Invariance.OWN,
				dataDescriptionEntryGroup.getCommonOwnLocalClause().getInvariance());
	}

	@Test
	public void testItemPic() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMPIC");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMPIC", dataDescriptionEntryGroup.getName());
		assertEquals("999.99", dataDescriptionEntryGroup.getPictureClause().getPictureString());
	}

	@Test
	public void testItemRec() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMREC");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMREC", dataDescriptionEntryGroup.getName());

		assertNotNull(dataDescriptionEntryGroup.getRecordAreaClause());
		assertTrue(dataDescriptionEntryGroup.getRecordAreaClause().isRecordArea());
	}

	@Test
	public void testItemRed() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMRED");

		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMRED", dataDescriptionEntryGroup.getName());
		assertEquals("X(5)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getRedefinesClause());
		assertNotNull(dataDescriptionEntryGroup.getRedefinesClause().getRedefinesCall());
	}

	@Test
	public void testItemRef() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMREF");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMREF", dataDescriptionEntryGroup.getName());
		assertEquals("9(1)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getReceivedByClause());
		assertEquals(ReceivedByClause.ReceivedBy.REFERENCE,
				dataDescriptionEntryGroup.getReceivedByClause().getReceivedBy());
	}

	@Test
	public void testItemSgn() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMSGN");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMSGN", dataDescriptionEntryGroup.getName());
		assertEquals("9(1)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getSignClause());
		assertTrue(dataDescriptionEntryGroup.getSignClause().isSeparate());
		assertEquals(SignClause.SignClauseType.TRAILING, dataDescriptionEntryGroup.getSignClause().getSignClauseType());
	}

	@Test
	public void testItemStr() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMSTR");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMSTR", dataDescriptionEntryGroup.getName());
		assertEquals("X(10)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getIntegerStringClause());
		assertEquals(IntegerStringClause.PrimitiveType.STRING,
				dataDescriptionEntryGroup.getIntegerStringClause().getPrimitiveType());
	}

	@Test
	public void testItemSyn() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMSYN");

		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMSYN", dataDescriptionEntryGroup.getName());
		assertEquals("9(1)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getSynchronizedClause());
		assertEquals(SynchronizedClause.Synchronized.RIGHT,
				dataDescriptionEntryGroup.getSynchronizedClause().getSynchronized());
	}

	@Test
	public void testItemThr() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMTHR");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMTHR", dataDescriptionEntryGroup.getName());
		assertEquals("X(10)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getThreadLocalClause());
		assertTrue(dataDescriptionEntryGroup.getThreadLocalClause().isThreadLocal());
	}

	@Test
	public void testItemTpd() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMTPD");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMTPD", dataDescriptionEntryGroup.getName());
		assertEquals("X(10)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getTypeDefClause());
		assertTrue(dataDescriptionEntryGroup.getTypeDefClause().isTypeDef());
	}

	@Test
	public void testItemTyp() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMTYP");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMTYP", dataDescriptionEntryGroup.getName());
		assertEquals("X(10)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getTypeClause());
		assertEquals(TypeClause.TimeType.LONG_TIME, dataDescriptionEntryGroup.getTypeClause().getTimeType());
	}

	@Test
	public void testItemUsa() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMUSA");

		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMUSA", dataDescriptionEntryGroup.getName());
		assertEquals("X(10)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getUsageClause());
		assertEquals(UsageClause.UsageClauseType.DATE, dataDescriptionEntryGroup.getUsageClause().getUsageClauseType());
	}

	@Test
	public void testItemUsg() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMUSG");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMUSG", dataDescriptionEntryGroup.getName());
		assertEquals("9(1)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getUsingClause());
		assertEquals(UsingClause.UsingClauseType.LANGUAGE,
				dataDescriptionEntryGroup.getUsingClause().getUsingClauseType());
		assertNotNull(dataDescriptionEntryGroup.getUsingClause().getOfValueStmt());
	}

	@Test
	public void testItemUsn() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMUSN");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMUSN", dataDescriptionEntryGroup.getName());
		assertEquals("9(1)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getUsingClause());
		assertEquals(UsingClause.UsingClauseType.CONVENTION,
				dataDescriptionEntryGroup.getUsingClause().getUsingClauseType());
		assertNotNull(dataDescriptionEntryGroup.getUsingClause().getOfValueStmt());
	}

	@Test
	public void testItemVal() throws Exception {
		final DataDescriptionEntryGroup dataDescriptionEntryGroup = getDataDescriptionEntryGroup("ITEMVAL");
		assertProperties(dataDescriptionEntryGroup);
		assertEquals("ITEMVAL", dataDescriptionEntryGroup.getName());
		assertEquals("9(1)", dataDescriptionEntryGroup.getPictureClause().getPictureString());

		assertNotNull(dataDescriptionEntryGroup.getValueClause());
		assertEquals(1, dataDescriptionEntryGroup.getValueClause().getValueIntervals().size());

		final List<ValueInterval> valueIntervals = dataDescriptionEntryGroup.getValueClause().getValueIntervals();
		final ValueInterval valueInterval = valueIntervals.get(0);
		assertNotNull(valueInterval.getFromValueStmt());
		assertNotNull(valueInterval.getToValueStmt());
	}
}
