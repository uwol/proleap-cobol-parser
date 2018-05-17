/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription;

import java.util.List;

import io.proleap.cobol.CobolParser.DataAlignedClauseContext;
import io.proleap.cobol.CobolParser.DataBlankWhenZeroClauseContext;
import io.proleap.cobol.CobolParser.DataCommonOwnLocalClauseContext;
import io.proleap.cobol.CobolParser.DataExternalClauseContext;
import io.proleap.cobol.CobolParser.DataGlobalClauseContext;
import io.proleap.cobol.CobolParser.DataIntegerStringClauseContext;
import io.proleap.cobol.CobolParser.DataJustifiedClauseContext;
import io.proleap.cobol.CobolParser.DataOccursClauseContext;
import io.proleap.cobol.CobolParser.DataPictureClauseContext;
import io.proleap.cobol.CobolParser.DataReceivedByClauseContext;
import io.proleap.cobol.CobolParser.DataRecordAreaClauseContext;
import io.proleap.cobol.CobolParser.DataRedefinesClauseContext;
import io.proleap.cobol.CobolParser.DataSignClauseContext;
import io.proleap.cobol.CobolParser.DataSynchronizedClauseContext;
import io.proleap.cobol.CobolParser.DataThreadLocalClauseContext;
import io.proleap.cobol.CobolParser.DataTypeClauseContext;
import io.proleap.cobol.CobolParser.DataTypeDefClauseContext;
import io.proleap.cobol.CobolParser.DataUsageClauseContext;
import io.proleap.cobol.CobolParser.DataUsingClauseContext;
import io.proleap.cobol.CobolParser.DataValueClauseContext;
import io.proleap.cobol.CobolParser.DataWithLowerBoundsClauseContext;

public interface DataDescriptionEntryGroup extends DataDescriptionEntry {

	AlignedClause addAlignedClause(DataAlignedClauseContext ctx);

	BlankWhenZeroClause addBlankWhenZeroClause(DataBlankWhenZeroClauseContext ctx);

	CommonOwnLocalClause addCommonOwnLocalClause(DataCommonOwnLocalClauseContext ctx);

	void addDataDescriptionEntry(DataDescriptionEntry dataDescriptionEntry);

	ExternalClause addExternalClause(DataExternalClauseContext ctx);

	GlobalClause addGlobalClause(DataGlobalClauseContext ctx);

	IntegerStringClause addIntegerStringClause(DataIntegerStringClauseContext ctx);

	JustifiedClause addJustifiedClause(DataJustifiedClauseContext ctx);

	OccursClause addOccursClause(DataOccursClauseContext ctx);

	PictureClause addPictureClause(DataPictureClauseContext ctx);

	ReceivedByClause addReceivedByClause(DataReceivedByClauseContext ctx);

	RecordAreaClause addRecordAreaClause(DataRecordAreaClauseContext ctx);

	RedefinesClause addRedefinesClause(DataRedefinesClauseContext ctx);

	SignClause addSignClause(DataSignClauseContext dataSignClauseContext);

	SynchronizedClause addSynchronizedClause(DataSynchronizedClauseContext ctx);

	ThreadLocalClause addThreadLocalClause(DataThreadLocalClauseContext ctx);

	TypeClause addTypeClause(DataTypeClauseContext ctx);

	TypeDefClause addTypeDefClause(DataTypeDefClauseContext ctx);

	UsageClause addUsageClause(DataUsageClauseContext ctx);

	UsingClause addUsingClause(DataUsingClauseContext ctx);

	ValueClause addValueClause(DataValueClauseContext ctx);

	WithLowerBoundsClause addWithLowerBoundClause(DataWithLowerBoundsClauseContext ctx);

	AlignedClause getAlignedClause();

	BlankWhenZeroClause getBlankWhenZeroClause();

	CommonOwnLocalClause getCommonOwnLocalClause();

	List<DataDescriptionEntry> getDataDescriptionEntries();

	List<DataDescriptionEntry> getDataDescriptionEntries(String name);

	DataDescriptionEntry getDataDescriptionEntry(String name);

	ExternalClause getExternalClause();

	Boolean getFiller();

	Integer getFillerNumber();

	GlobalClause getGlobalClause();

	IntegerStringClause getIntegerStringClause();

	JustifiedClause getJustifiedClause();

	List<OccursClause> getOccursClauses();

	PictureClause getPictureClause();

	ReceivedByClause getReceivedByClause();

	RecordAreaClause getRecordAreaClause();

	RedefinesClause getRedefinesClause();

	SignClause getSignClause();

	SynchronizedClause getSynchronizedClause();

	ThreadLocalClause getThreadLocalClause();

	TypeClause getTypeClause();

	TypeDefClause getTypeDefClause();

	UsageClause getUsageClause();

	UsingClause getUsingClause();

	ValueClause getValueClause();

	WithLowerBoundsClause getWithLowerBoundsClause();

	void setFiller(Boolean filler);

	void setFillerNumber(Integer fillerNumber);
}
