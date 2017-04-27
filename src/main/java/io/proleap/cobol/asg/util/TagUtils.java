/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.util;

import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import io.proleap.cobol.preprocessor.CobolPreprocessor;

public class TagUtils {

	public static String getUntaggedText(final String tag, final List<TerminalNode> terminalNodes) {
		final StringBuffer sb = new StringBuffer();

		for (final TerminalNode terminalNode : terminalNodes) {
			final String rawLineText = terminalNode.getText();
			final String execLineText = rawLineText.replace(tag, "");
			final String execLineTextCleaned = execLineText.trim() + CobolPreprocessor.WS;

			sb.append(execLineTextCleaned);
		}

		return sb.toString().trim();
	}

}
