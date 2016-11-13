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
import io.proleap.cobol.parser.metamodel.environment.AssignClause;
import io.proleap.cobol.parser.metamodel.environment.FileControlEntry;
import io.proleap.cobol.parser.metamodel.environment.OrganizationClause;
import io.proleap.cobol.parser.metamodel.environment.ReserveClause;
import io.proleap.cobol.parser.metamodel.environment.SelectClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

//FIXME: add fileControlClauses
public class FileControlEntryImpl extends CobolDivisionElementImpl implements FileControlEntry {

	protected AssignClause assignClause;

	protected final FileControlEntryContext ctx;

	protected final String name;

	protected OrganizationClause organizationClause;

	protected ReserveClause reserveClause;

	protected SelectClause selectClause;

	public FileControlEntryImpl(final String name, final ProgramUnit programUnit, final CobolDivision scope,
			final FileControlEntryContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public AssignClause getAssignClause() {
		return assignClause;
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
	public ReserveClause getReserveClause() {
		return reserveClause;
	}

	@Override
	public SelectClause getSelectClause() {
		return selectClause;
	}

	@Override
	public void setAssignClause(final AssignClause assignClause) {
		this.assignClause = assignClause;
	}

	@Override
	public void setOrganizationClause(final OrganizationClause organizationClause) {
		this.organizationClause = organizationClause;
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
