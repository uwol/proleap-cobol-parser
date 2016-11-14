/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.impl;

import io.proleap.cobol.Cobol85Parser.FileControlEntryContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.AccessModeClause;
import io.proleap.cobol.parser.metamodel.environment.AlternateRecordKeyClause;
import io.proleap.cobol.parser.metamodel.environment.AssignClause;
import io.proleap.cobol.parser.metamodel.environment.FileControlEntry;
import io.proleap.cobol.parser.metamodel.environment.FileStatusClause;
import io.proleap.cobol.parser.metamodel.environment.OrganizationClause;
import io.proleap.cobol.parser.metamodel.environment.PaddingCharacterClause;
import io.proleap.cobol.parser.metamodel.environment.PasswordClause;
import io.proleap.cobol.parser.metamodel.environment.RecordDelimiterClause;
import io.proleap.cobol.parser.metamodel.environment.RecordKeyClause;
import io.proleap.cobol.parser.metamodel.environment.RelativeKeyClause;
import io.proleap.cobol.parser.metamodel.environment.ReserveClause;
import io.proleap.cobol.parser.metamodel.environment.SelectClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

//FIXME: add fileControlClauses
public class FileControlEntryImpl extends CobolDivisionElementImpl implements FileControlEntry {

	protected AccessModeClause accessModeClause;

	protected AlternateRecordKeyClause alternateRecordKeyClause;

	protected AssignClause assignClause;

	protected final FileControlEntryContext ctx;

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

	public FileControlEntryImpl(final String name, final ProgramUnit programUnit, final CobolDivision scope,
			final FileControlEntryContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
		this.name = name;
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
	public void setAccessModeClause(final AccessModeClause accessModeClause) {
		this.accessModeClause = accessModeClause;
	}

	@Override
	public void setAlternateRecordKeyClause(final AlternateRecordKeyClause alternateRecordKeyClause) {
		this.alternateRecordKeyClause = alternateRecordKeyClause;
	}

	@Override
	public void setAssignClause(final AssignClause assignClause) {
		this.assignClause = assignClause;
	}

	@Override
	public void setFileStatusClause(final FileStatusClause fileStatusClause) {
		this.fileStatusClause = fileStatusClause;
	}

	@Override
	public void setOrganizationClause(final OrganizationClause organizationClause) {
		this.organizationClause = organizationClause;
	}

	@Override
	public void setPaddingCharacterClause(final PaddingCharacterClause paddingCharacterClause) {
		this.paddingCharacterClause = paddingCharacterClause;
	}

	@Override
	public void setPasswordClause(final PasswordClause passwordClause) {
		this.passwordClause = passwordClause;
	}

	@Override
	public void setRecordDelimiterClause(final RecordDelimiterClause recordDelimiterClause) {
		this.recordDelimiterClause = recordDelimiterClause;
	}

	@Override
	public void setRecordKeyClause(final RecordKeyClause recordKeyClause) {
		this.recordKeyClause = recordKeyClause;
	}

	@Override
	public void setRelativeKeyClause(final RelativeKeyClause relativeKeyClause) {
		this.relativeKeyClause = relativeKeyClause;
	}

	@Override
	public void setReserveClause(final ReserveClause reserveClause) {
		this.reserveClause = reserveClause;
	}

	@Override
	public void setSelectClause(final SelectClause selectClause) {
		this.selectClause = selectClause;
	}

}
