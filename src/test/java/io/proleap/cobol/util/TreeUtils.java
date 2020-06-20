package io.proleap.cobol.util;

import java.util.Arrays;
import java.util.List;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.misc.Utils;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.v4.runtime.tree.Trees;

public class TreeUtils {

	public static final String NEWLINE = "\n";

	public static final String TAB = "\t";

	public static String indent(final int indent) {
		return TAB.repeat(indent);
	}

	/**
	 * @see org.antlr.v4.runtime.tree.Trees.toStringTree(Tree, List<String>)
	 */
	public static String toStringTree(final Tree t, final List<String> ruleNames, final int depth) {
		String s = Utils.escapeWhitespace(Trees.getNodeText(t, ruleNames), false);

		if (t.getChildCount() == 0) {
			return s;
		}

		final StringBuilder buf = new StringBuilder();

		if (depth > 0) {
			buf.append(NEWLINE);
		}

		buf.append(indent(depth));
		buf.append("(");
		s = Utils.escapeWhitespace(Trees.getNodeText(t, ruleNames), false);
		buf.append(s);
		buf.append(' ');

		for (int i = 0; i < t.getChildCount(); i++) {
			if (i > 0) {
				buf.append(' ');
			}

			buf.append(toStringTree(t.getChild(i), ruleNames, depth + 1));
		}

		buf.append(")");

		return buf.toString();
	}

	/**
	 * @see org.antlr.v4.runtime.tree.Trees.toStringTree(Tree, Parser)
	 */
	public static String toStringTree(final Tree t, final Parser recog) {
		final String[] ruleNames = recog != null ? recog.getRuleNames() : null;
		final List<String> ruleNamesList = ruleNames != null ? Arrays.asList(ruleNames) : null;

		return toStringTree(t, ruleNamesList, 0);
	}
}
