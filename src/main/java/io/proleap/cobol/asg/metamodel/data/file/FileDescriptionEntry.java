/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file;

import io.proleap.cobol.CobolParser.BlockContainsClauseContext;
import io.proleap.cobol.CobolParser.CodeSetClauseContext;
import io.proleap.cobol.CobolParser.DataRecordsClauseContext;
import io.proleap.cobol.CobolParser.ExternalClauseContext;
import io.proleap.cobol.CobolParser.GlobalClauseContext;
import io.proleap.cobol.CobolParser.LabelRecordsClauseContext;
import io.proleap.cobol.CobolParser.LinageClauseContext;
import io.proleap.cobol.CobolParser.RecordContainsClauseContext;
import io.proleap.cobol.CobolParser.ReportClauseContext;
import io.proleap.cobol.CobolParser.ValueOfClauseContext;
import io.proleap.cobol.asg.metamodel.Declaration;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryContainer;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;

public interface FileDescriptionEntry extends DataDescriptionEntryContainer, Declaration {

	BlockContainsClause addBlockContainsClause(BlockContainsClauseContext ctx);

	CodeSetClause addCodeSetClause(CodeSetClauseContext ctx);

	DataRecordsClause addDataRecordsClause(DataRecordsClauseContext ctx);

	ExternalClause addExternalClause(ExternalClauseContext ctx);

	GlobalClause addGlobalClause(GlobalClauseContext ctx);

	LabelRecordsClause addLabelRecordsClause(LabelRecordsClauseContext ctx);

	LinageClause addLinageClause(LinageClauseContext ctx);

	RecordContainsClause addRecordContainsClause(RecordContainsClauseContext ctx);

	ReportClause addReportClause(ReportClauseContext ctx);

	ValueOfClause addValueOfClause(ValueOfClauseContext ctx);

	BlockContainsClause getBlockContainsClause();

	CodeSetClause getCodeSetClause();

	DataRecordsClause getDataRecordsClause();

	ExternalClause getExternalClause();

	Call getFileCall();

	FileControlEntry getFileControlEntry();

	GlobalClause getGlobalClause();

	LabelRecordsClause getLabelRecordsClause();

	LinageClause getLinageClause();

	RecordContainsClause getRecordContainsClause();

	ReportClause getReportClause();

	ValueOfClause getValueOfClause();

	void setFileCall(Call fileCall);

	void setFileControlEntry(FileControlEntry fileControlEntry);

}
