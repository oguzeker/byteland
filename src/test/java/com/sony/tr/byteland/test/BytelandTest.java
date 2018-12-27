package com.sony.tr.byteland.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sony.tr.byteland.common.BytelandUtil;
import com.sony.tr.byteland.exception.BytelandException;
import com.sony.tr.byteland.exception.ErrorCode;

/**
 * Byteland test class.
 * 
 * @author Oguz Erhan Eker
 * @version 1.0.0-SNAPSHOT
 * @since 2018-12-27
 */
@DisplayName("Verify Byteland test cases")
public class BytelandTest extends BytelandTestBase {

	/*
	 * Initialize the universe of testing
	 */
	@BeforeAll
	public static void setup() {
		assertDoesNotThrow(() -> populateWorld());
	}

	/*
	 * Verify that the number of test cases is less than or equal to max value
	 */
	@AfterAll
	public static void end() {
		assertTrue(testCaseCount <= MAX_TESTCASE_COUNT);
		assertEquals(testCaseCount, 3);
	}

	@Test
	@DisplayName("Verify Case-1")
	public void test_Case1() {
		testCaseCount++;
		BytelandUtil.unifyByteland(byteland1);

		assertTrue(byteland1.isUnified());
		assertEquals(byteland1.getUnificationTurnCount().intValue(), 2);
	}

	@Test
	@DisplayName("Verify Case-2")
	public void test_Case2() {
		testCaseCount++;
		BytelandUtil.unifyByteland(byteland2);

		assertTrue(byteland2.isUnified());
		assertEquals(byteland2.getUnificationTurnCount().intValue(), 4);
	}

	@Test
	@DisplayName("Verify Case-3")
	public void test_Case3() {
		testCaseCount++;
		BytelandUtil.unifyByteland(byteland3);

		assertTrue(byteland3.isUnified());
		assertEquals(byteland3.getUnificationTurnCount().intValue(), 5);
	}

	@Test
	@DisplayName("Verify that exception is thrown when the initial number of "
			+ "singular states is less than or equal to min value")
	public void test_ExceptionMin() {
		BytelandException minException = assertThrows(BytelandException.class,
				() -> populateByteland(CaseType.EXCEPTION_MIN));
		assertEquals(minException.getErrorCode(), ErrorCode.INPUT_EXCEEDS_MIN_STATE_COUNT);
	}

	@Test
	@DisplayName("Verify that exception is thrown when the initial number of singular "
			+ "states is more than max value")
	public void test_ExceptionMax() {
		BytelandException maxException = assertThrows(BytelandException.class,
				() -> populateByteland(CaseType.EXCEPTION_MAX));
		assertEquals(maxException.getErrorCode(), ErrorCode.INPUT_EXCEEDS_MAX_STATE_COUNT);

	}

	@Test
	@DisplayName("Verify that exception is thrown when the number of paths does not " 
			+ "equal to stateCount-1")
	public void test_ExceptionPath() {
		BytelandException maxException = assertThrows(BytelandException.class,
				() -> populateByteland(CaseType.EXCEPTION_PATH));
		assertEquals(maxException.getErrorCode(), ErrorCode.INPUT_PATH_COUNT_INVALID);
	}

}
