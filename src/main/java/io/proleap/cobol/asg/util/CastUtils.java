package io.proleap.cobol.asg.util;

import java.math.BigDecimal;

public class CastUtils {

	public static BigDecimal castBigDecimal(final Object obj) {
		return obj != null && obj instanceof BigDecimal ? (BigDecimal) obj : null;
	}

	public static String castString(final Object obj) {
		return obj != null && obj instanceof String ? (String) obj : null;
	}
}
