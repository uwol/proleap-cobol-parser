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

	AlternateRecordKeyClause getAlternateRecordKeyClause();

	AssignClause getAssignClause();

	FileStatusClause getFileStatusClause();

	OrganizationClause getOrganizationClause();

	PaddingCharacterClause getPaddingCharacterClause();

	PasswordClause getPasswordClause();

	RecordDelimiterClause getRecordDelimiterClause();

	RecordKeyClause getRecordKeyClause();

	ReserveClause getReserveClause();

	SelectClause getSelectClause();

	void setAccessModeClause(AccessModeClause accessModeClause);

	void setAlternateRecordKeyClause(AlternateRecordKeyClause alternateRecordKeyClause);

	void setAssignClause(AssignClause assignClause);

	void setFileStatusClause(FileStatusClause fileStatusClause);

	void setOrganizationClause(OrganizationClause organizationClause);

	void setPaddingCharacterClause(PaddingCharacterClause paddingCharacterClause);

	void setPasswordClause(PasswordClause passwordClause);

	void setRecordDelimiterClause(RecordDelimiterClause recordDelimiterClause);

	void setRecordKeyClause(RecordKeyClause recordKeyClause);

	void setReserveClause(ReserveClause reserveClause);

	void setSelectClause(SelectClause selectClause);

}
