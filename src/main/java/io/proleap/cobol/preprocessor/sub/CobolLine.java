/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub;

import com.google.common.base.Strings;

import io.proleap.cobol.asg.params.CobolDialect;
import io.proleap.cobol.preprocessor.CobolPreprocessor;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

/**
 * Representation of a Cobol line.
 */
public class CobolLine {

	public static String blankSequenceArea(final CobolSourceFormatEnum format) {
		return CobolSourceFormatEnum.TANDEM.equals(format) ? "" : Strings.repeat(CobolPreprocessor.WS, 6);
	}

	private static String contentAreaA(final String contentArea) {
		return contentArea.length() > 4 ? contentArea.substring(0, 4) : contentArea;
	}

	private static String contentAreaB(final String contentArea) {
		return contentArea.length() > 4 ? contentArea.substring(4) : "";
	}

	public static CobolLine with(final CobolLine line, final String indicatorArea, final String contentArea) {
		return new CobolLine(line.sequenceArea, indicatorArea, contentAreaA(contentArea), contentAreaB(contentArea),
				line.comment, line.format, line.dialect, line.number, line.type, line.predecessor, line.successor);
	}

	public static CobolLine withContentArea(final CobolLine line, final String contentArea) {
		return new CobolLine(line.sequenceArea, line.indicatorArea, contentAreaA(contentArea),
				contentAreaB(contentArea), line.comment, line.format, line.dialect, line.number, line.type,
				line.predecessor, line.successor);
	}

	public String comment;

	public String contentAreaA;

	public String contentAreaB;

	public CobolDialect dialect;

	public CobolSourceFormatEnum format;

	public String indicatorArea;

	public int number;

	public CobolLine predecessor;

	public String sequenceArea;

	public CobolLine successor;

	public CobolLineTypeEnum type;

	public CobolLine(final String sequenceArea, final String indicatorArea, final String contentAreaA,
			final String contentAreaB, final String comment, final CobolSourceFormatEnum format,
			final CobolDialect dialect, final int number, final CobolLineTypeEnum type, final CobolLine predecessor,
			final CobolLine successor) {
		this.sequenceArea = sequenceArea;
		this.indicatorArea = indicatorArea;
		this.contentAreaA = contentAreaA;
		this.contentAreaB = contentAreaB;
		this.comment = comment;

		this.format = format;
		this.dialect = dialect;
		this.number = number;
		this.type = type;

		setPredecessor(predecessor);
		setSuccessor(successor);
	}

	public String blankSequenceArea() {
		return blankSequenceArea(format);
	}

	public String getContentArea() {
		return contentAreaA + contentAreaB;
	}

	public String serialize() {
		return sequenceArea + indicatorArea + contentAreaA + contentAreaB + comment;
	}

	public void setPredecessor(final CobolLine predecessor) {
		this.predecessor = predecessor;

		if (predecessor != null) {
			predecessor.successor = this;
		}
	}

	public void setSuccessor(final CobolLine successor) {
		this.successor = successor;

		if (successor != null) {
			successor.predecessor = this;
		}
	}

	@Override
	public String toString() {
		return serialize();
	}
}
