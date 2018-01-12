package io.proleap.cobol.asg.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.type.CobolBaseType;

public class PictureUtilsTest extends CobolTestBase {

	@Test
	public void testDetermineType9() throws Exception {
		assertEquals(CobolBaseType.INTEGER, PictureTypeUtils.determineType("9"));
	}

	@Test
	public void testDetermineType99() throws Exception {
		assertEquals(CobolBaseType.INTEGER, PictureTypeUtils.determineType("99"));
	}

	@Test
	public void testDetermineType9Length() throws Exception {
		assertEquals(CobolBaseType.INTEGER, PictureTypeUtils.determineType("9(2)"));
	}

	@Test
	public void testDetermineType9V9() throws Exception {
		assertEquals(CobolBaseType.FLOAT, PictureTypeUtils.determineType("9V9"));
	}

	@Test
	public void testDetermineTypeS9() throws Exception {
		assertEquals(CobolBaseType.INTEGER, PictureTypeUtils.determineType("S9"));
	}

	@Test
	public void testDetermineTypeS9Length() throws Exception {
		assertEquals(CobolBaseType.INTEGER, PictureTypeUtils.determineType("S9(2)"));
	}

	@Test
	public void testDetermineTypeX() throws Exception {
		assertEquals(CobolBaseType.STRING, PictureTypeUtils.determineType("X"));
	}

	@Test
	public void testDetermineTypeXLength() throws Exception {
		assertEquals(CobolBaseType.STRING, PictureTypeUtils.determineType("X(2)"));
	}

	@Test
	public void testDetermineTypeXX() throws Exception {
		assertEquals(CobolBaseType.STRING, PictureTypeUtils.determineType("XX"));
	}
}