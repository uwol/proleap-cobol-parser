/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file;

import io.proleap.cobol.Cobol85Parser.BlockContainsClauseContext;
import io.proleap.cobol.Cobol85Parser.CodeSetClauseContext;
import io.proleap.cobol.Cobol85Parser.DataRecordsClauseContext;
import io.proleap.cobol.Cobol85Parser.ExternalClauseContext;
import io.proleap.cobol.Cobol85Parser.GlobalClauseContext;
import io.proleap.cobol.Cobol85Parser.LabelRecordsClauseContext;
import io.proleap.cobol.Cobol85Parser.LinageClauseContext;
import io.proleap.cobol.Cobol85Parser.RecordContainsClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportClauseContext;
import io.proleap.cobol.Cobol85Parser.ValueOfClauseContext;
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
