/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.NamedElement;

public interface FileControlEntry extends CobolDivisionElement, NamedElement {

	AccessModeClause getAccessModeClause();

	AssignClause getAssignClause();

	OrganizationClause getOrganizationClause();

	PaddingCharacterClause getPaddingCharacterClause();

	RecordDelimiterClause getRecordDelimiterClause();

	ReserveClause getReserveClause();

	SelectClause getSelectClause();

	void setAccessModeClause(AccessModeClause accessModeClause);

	void setAssignClause(AssignClause assignClause);

	void setOrganizationClause(OrganizationClause organizationClause);

	void setPaddingCharacterClause(PaddingCharacterClause paddingCharacterClause);

	void setRecordDelimiterClause(RecordDelimiterClause recordDelimiterClause);

	void setReserveClause(ReserveClause reserveClause);

	void setSelectClause(SelectClause selectClause);

}
