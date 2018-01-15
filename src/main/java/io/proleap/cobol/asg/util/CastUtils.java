package io.proleap.cobol.asg.util;

public class CastUtils {

	public static Double castDouble(final Object obj) {
		return obj != null && obj instanceof Double ? (Double) obj : null;
	}

	public static Long castLong(final Object obj) {
		return obj != null && obj instanceof Long ? (Long) obj : null;
	}

	public static String castString(final Object obj) {
		return obj != null && obj instanceof String ? (String) obj : null;
	}
}
