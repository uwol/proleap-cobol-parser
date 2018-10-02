/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.CobolParser.AccessModeClauseContext;
import io.proleap.cobol.CobolParser.AlternateRecordKeyClauseContext;
import io.proleap.cobol.CobolParser.AssignClauseContext;
import io.proleap.cobol.CobolParser.FileControlEntryContext;
import io.proleap.cobol.CobolParser.FileStatusClauseContext;
import io.proleap.cobol.CobolParser.OrganizationClauseContext;
import io.proleap.cobol.CobolParser.PaddingCharacterClauseContext;
import io.proleap.cobol.CobolParser.PasswordClauseContext;
import io.proleap.cobol.CobolParser.RecordDelimiterClauseContext;
import io.proleap.cobol.CobolParser.RecordKeyClauseContext;
import io.proleap.cobol.CobolParser.RelativeKeyClauseContext;
import io.proleap.cobol.CobolParser.ReserveClauseContext;
import io.proleap.cobol.CobolParser.SelectClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.FileControlEntryCall;
import io.proleap.cobol.asg.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.AccessModeClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.AlternateRecordKeyClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.AssignClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileStatusClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.OrganizationClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.PaddingCharacterClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.PasswordClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.RecordDelimiterClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.RecordKeyClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.RelativeKeyClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.ReserveClause;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.SelectClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class FileControlEntryImpl extends CobolDivisionElementImpl implements FileControlEntry {

	private final static Logger LOG = LoggerFactory.getLogger(FileControlEntryImpl.class);

	protected AccessModeClause accessModeClause;

	protected AlternateRecordKeyClause alternateRecordKeyClause;

	protected AssignClause assignClause;

	protected List<FileControlEntryCall> calls = new ArrayList<FileControlEntryCall>();

	protected final FileControlEntryContext ctx;

	protected FileDescriptionEntry fileDescriptionEntry;

	protected FileStatusClause fileStatusClause;

	protected final String name;

	protected OrganizationClause organizationClause;

	protected PaddingCharacterClause paddingCharacterClause;

	protected PasswordClause passwordClause;

	protected RecordDelimiterClause recordDelimiterClause;

	protected RecordKeyClause recordKeyClause;

	protected RelativeKeyClause relativeKeyClause;

	protected ReserveClause reserveClause;

	protected SelectClause selectClause;

	public FileControlEntryImpl(final String name, final ProgramUnit programUnit, final FileControlEntryContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public AccessModeClause addAccessModeClause(final AccessModeClauseContext ctx) {
		AccessModeClause result = (AccessModeClause) getASGElement(ctx);

		if (result == null) {
			result = new AccessModeClauseImpl(programUnit, ctx);

			final AccessModeClause.Mode mode;

			if (ctx.SEQUENTIAL() != null) {
				mode = AccessModeClause.Mode.SEQUENTIAL;
			} else if (ctx.RANDOM() != null) {
				mode = AccessModeClause.Mode.RANDOM;
			} else if (ctx.DYNAMIC() != null) {
				mode = AccessModeClause.Mode.DYNAMIC;
			} else if (ctx.EXCLUSIVE() != null) {
				mode = AccessModeClause.Mode.EXCLUSIVE;
			} else {
				LOG.warn("unknown mode {}.", ctx);
				mode = null;
			}

			result.setMode(mode);

			accessModeClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AlternateRecordKeyClause addAlternateRecordKeyClause(final AlternateRecordKeyClauseContext ctx) {
		AlternateRecordKeyClause result = (AlternateRecordKeyClause) getASGElement(ctx);

		if (result == null) {
			result = new AlternateRecordKeyClauseImpl(programUnit, ctx);

			final Call dataCall = createCall(ctx.qualifiedDataName());
			result.setDataCall(dataCall);

			if (ctx.passwordClause() != null) {
				final PasswordClause passwordClause = addPasswordClause(ctx.passwordClause());
				result.setPasswordClause(passwordClause);
			}

			alternateRecordKeyClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AssignClause addAssignClause(final AssignClauseContext ctx) {
		AssignClause result = (AssignClause) getASGElement(ctx);

		if (result == null) {
			result = new AssignClauseImpl(programUnit, ctx);

			// to call
			final ValueStmt toValueStmt = createValueStmt(ctx.assignmentName(), ctx.literal());
			result.setToValueStmt(toValueStmt);

			// type
			final AssignClause.AssignClauseType type;

			if (ctx.DISK() != null) {
				type = AssignClause.AssignClauseType.DISK;
			} else if (ctx.DISPLAY() != null) {
				type = AssignClause.AssignClauseType.DISPLAY;
			} else if (ctx.KEYBOARD() != null) {
				type = AssignClause.AssignClauseType.KEYBOARD;
			} else if (ctx.PORT() != null) {
				type = AssignClause.AssignClauseType.PORT;
			} else if (ctx.PRINTER() != null) {
				type = AssignClause.AssignClauseType.PRINTER;
			} else if (ctx.READER() != null) {
				type = AssignClause.AssignClauseType.READER;
			} else if (ctx.REMOTE() != null) {
				type = AssignClause.AssignClauseType.REMOTE;
			} else if (ctx.TAPE() != null) {
				type = AssignClause.AssignClauseType.TAPE;
			} else if (ctx.VIRTUAL() != null) {
				type = AssignClause.AssignClauseType.VIRTUAL;
			} else if (ctx.assignmentName() != null) {
				type = AssignClause.AssignClauseType.CALL;
			} else if (ctx.literal() != null) {
				type = AssignClause.AssignClauseType.CALL;
			} else {
				type = null;
			}

			result.setAssignClauseType(type);

			assignClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public void addCall(final FileControlEntryCall call) {
		calls.add(call);
	}

	@Override
	public FileStatusClause addFileStatusClause(final FileStatusClauseContext ctx) {
		FileStatusClause result = (FileStatusClause) getASGElement(ctx);

		if (result == null) {
			result = new FileStatusClauseImpl(programUnit, ctx);

			final Call dataCall = createCall(ctx.qualifiedDataName(0));
			result.setDataCall(dataCall);

			if (ctx.qualifiedDataName().size() > 1 && ctx.qualifiedDataName(1) != null) {
				final Call dataCall2 = createCall(ctx.qualifiedDataName(1));
				result.setDataCall2(dataCall2);
			}

			fileStatusClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public OrganizationClause addOrganizationClause(final OrganizationClauseContext ctx) {
		OrganizationClause result = (OrganizationClause) getASGElement(ctx);

		if (result == null) {
			result = new OrganizationClauseImpl(programUnit, ctx);

			/*
			 * type
			 */
			final OrganizationClause.OrganizationClauseType type;

			if (ctx.LINE() != null) {
				type = OrganizationClause.OrganizationClauseType.LINE;
			} else if (ctx.RECORD() != null && ctx.BINARY() != null) {
				type = OrganizationClause.OrganizationClauseType.RECORD_BINARY;
			} else if (ctx.BINARY() != null) {
				type = OrganizationClause.OrganizationClauseType.BINARY;
			} else if (ctx.RECORD() != null) {
				type = OrganizationClause.OrganizationClauseType.RECORD;
			} else {
				type = null;
			}

			result.setOrganizationClauseType(type);

			/*
			 * mode
			 */
			final OrganizationClause.Mode mode;

			if (ctx.SEQUENTIAL() != null) {
				mode = OrganizationClause.Mode.SEQUENTIAL;
			} else if (ctx.RELATIVE() != null) {
				mode = OrganizationClause.Mode.RELATIVE;
			} else if (ctx.INDEXED() != null) {
				mode = OrganizationClause.Mode.INDEXED;
			} else {
				mode = null;
			}

			result.setMode(mode);

			organizationClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public PaddingCharacterClause addPaddingCharacterClause(final PaddingCharacterClauseContext ctx) {
		PaddingCharacterClause result = (PaddingCharacterClause) getASGElement(ctx);

		if (result == null) {
			result = new PaddingCharacterClauseImpl(programUnit, ctx);

			final ValueStmt valueStmt = createValueStmt(ctx.qualifiedDataName(), ctx.literal());
			result.setValueStmt(valueStmt);

			paddingCharacterClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public PasswordClause addPasswordClause(final PasswordClauseContext ctx) {
		PasswordClause result = (PasswordClause) getASGElement(ctx);

		if (result == null) {
			result = new PasswordClauseImpl(programUnit, ctx);

			final Call dataCall = createCall(ctx.dataName());
			result.setDataCall(dataCall);

			passwordClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public RecordDelimiterClause addRecordDelimiterClause(final RecordDelimiterClauseContext ctx) {
		RecordDelimiterClause result = (RecordDelimiterClause) getASGElement(ctx);

		if (result == null) {
			result = new RecordDelimiterClauseImpl(programUnit, ctx);

			final RecordDelimiterClause.RecordDelimiterClauseType type;

			if (ctx.STANDARD_1() != null) {
				type = RecordDelimiterClause.RecordDelimiterClauseType.STANDARD_1;
			} else if (ctx.IMPLICIT() != null) {
				type = RecordDelimiterClause.RecordDelimiterClauseType.IMPLICIT;
			} else if (ctx.assignmentName() != null) {
				type = RecordDelimiterClause.RecordDelimiterClauseType.ASSIGNMENT;

				final ValueStmt valueStmt = createValueStmt(ctx.assignmentName());
				result.setValueStmt(valueStmt);
			} else {
				type = null;
			}

			result.setRecordDelimiterClauseType(type);

			recordDelimiterClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public RecordKeyClause addRecordKeyClause(final RecordKeyClauseContext ctx) {
		RecordKeyClause result = (RecordKeyClause) getASGElement(ctx);

		if (result == null) {
			result = new RecordKeyClauseImpl(programUnit, ctx);

			final Call recordKeyCall = createCall(ctx.qualifiedDataName());
			result.setRecordKeyCall(recordKeyCall);

			if (ctx.passwordClause() != null) {
				final PasswordClause passwordClause = addPasswordClause(ctx.passwordClause());
				result.setPasswordClause(passwordClause);
			}

			recordKeyClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public RelativeKeyClause addRelativeKeyClause(final RelativeKeyClauseContext ctx) {
		RelativeKeyClause result = (RelativeKeyClause) getASGElement(ctx);

		if (result == null) {
			result = new RelativeKeyClauseImpl(programUnit, ctx);

			final Call relativeKeyCall = createCall(ctx.qualifiedDataName());
			result.setRelativeKeyCall(relativeKeyCall);

			relativeKeyClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ReserveClause addReserveClause(final ReserveClauseContext ctx) {
		ReserveClause result = (ReserveClause) getASGElement(ctx);

		if (result == null) {
			result = new ReserveClauseImpl(programUnit, ctx);

			if (ctx.integerLiteral() != null) {
				final IntegerLiteralValueStmt integerLiteralValueStmt = createIntegerLiteralValueStmt(
						ctx.integerLiteral());
				result.setValueStmt(integerLiteralValueStmt);
			}

			reserveClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SelectClause addSelectClause(final SelectClauseContext ctx) {
		SelectClause result = (SelectClause) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new SelectClauseImpl(name, programUnit, ctx);

			final Call fileCall = createCall(ctx.fileName());
			result.addFileCall(fileCall);

			if (ctx.OPTIONAL() != null) {
				result.setOptional(true);
			}

			selectClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AccessModeClause getAccessModeClause() {
		return accessModeClause;
	}

	@Override
	public AlternateRecordKeyClause getAlternateRecordKeyClause() {
		return alternateRecordKeyClause;
	}

	@Override
	public AssignClause getAssignClause() {
		return assignClause;
	}

	@Override
	public List<FileControlEntryCall> getCalls() {
		return calls;
	}

	@Override
	public FileDescriptionEntry getFileDescriptionEntry() {
		return fileDescriptionEntry;
	}

	@Override
	public FileStatusClause getFileStatusClause() {
		return fileStatusClause;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public OrganizationClause getOrganizationClause() {
		return organizationClause;
	}

	@Override
	public PaddingCharacterClause getPaddingCharacterClause() {
		return paddingCharacterClause;
	}

	@Override
	public PasswordClause getPasswordClause() {
		return passwordClause;
	}

	@Override
	public RecordDelimiterClause getRecordDelimiterClause() {
		return recordDelimiterClause;
	}

	@Override
	public RecordKeyClause getRecordKeyClause() {
		return recordKeyClause;
	}

	@Override
	public RelativeKeyClause getRelativeKeyClause() {
		return relativeKeyClause;
	}

	@Override
	public ReserveClause getReserveClause() {
		return reserveClause;
	}

	@Override
	public SelectClause getSelectClause() {
		return selectClause;
	}

	@Override
	public void setFileDescriptionEntry(final FileDescriptionEntry fileDescriptionEntry) {
		this.fileDescriptionEntry = fileDescriptionEntry;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
