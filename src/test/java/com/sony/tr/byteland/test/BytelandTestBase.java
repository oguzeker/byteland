package com.sony.tr.byteland.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sony.tr.byteland.common.BytelandUtil;
import com.sony.tr.byteland.dto.Byteland;
import com.sony.tr.byteland.exception.BytelandException;

/**
 * Byteland test class base.
 * @author Oguz Erhan Eker
 */
public abstract class BytelandTestBase  {
	
	private static final int CASE1_SINGULAR_STATE_COUNT = 4;
	private static final int CASE2_SINGULAR_STATE_COUNT = 8;
	private static final int CASE3_SINGULAR_STATE_COUNT = 9;
	
	private static final List<Integer> CASE1_PATHS = Arrays.asList(0, 1, 2);
	private static final List<Integer> CASE2_PATHS = Arrays.asList(0, 1, 2, 0, 0, 3, 3);
	private static final List<Integer> CASE3_PATHS = Arrays.asList(0, 1, 1, 1, 1, 0, 2, 2);

	protected static final int MAX_TESTCASE_COUNT = 1000;

	protected static Byteland byteland1;
	protected static Byteland byteland2;
	protected static Byteland byteland3;
	
	protected static int testCaseCount = 0;

	public abstract void test_Case1();
	public abstract void test_Case2();
	public abstract void test_ExceptionMin();
	public abstract void test_ExceptionMax();
	public abstract void test_ExceptionPath();
	
	protected static void populateWorld() throws BytelandException {
		populateByteland(CaseType.CASE1);
		populateByteland(CaseType.CASE2);
		populateByteland(CaseType.CASE3);
	}

	protected static void populateByteland(CaseType caseType) throws BytelandException {
		switch (caseType) {
		case CASE1:
			populateCase1();
			break;
		case CASE2:
			populateCase2();
			break;
		case CASE3:
			populateCase3();
			break;
		case EXCEPTION_MIN:
			populateExceptionMinCase();
			break;
		case EXCEPTION_MAX:
			populateExceptionMaxCase();
			break;
		case EXCEPTION_PATH:
			populateCaseWithInvalidPathCount();
			break;
		default:
			break;
		}
	}
	
	private static void populateCase1() throws BytelandException {
		byteland1 = new Byteland(BytelandUtil.generateSingularStates(
				CASE1_SINGULAR_STATE_COUNT, CASE1_PATHS), null, false);
	}
	
	private static void populateCase2() throws BytelandException {
		byteland2 = new Byteland(BytelandUtil.generateSingularStates(
				CASE2_SINGULAR_STATE_COUNT, CASE2_PATHS), null, false);
	}
	
	private static void populateCase3() throws BytelandException {
		byteland3 = new Byteland(BytelandUtil.generateSingularStates(
				CASE3_SINGULAR_STATE_COUNT, CASE3_PATHS), null, false);
	}
	
	private static void populateExceptionMinCase() throws BytelandException {
		BytelandUtil.generateSingularStates(BytelandUtil.MIN_SINGULAR_STATE_COUNT, 
				new ArrayList<Integer>(BytelandUtil.MIN_SINGULAR_STATE_COUNT - 1));
	}
	
	private static void populateExceptionMaxCase() throws BytelandException {
		BytelandUtil.generateSingularStates(BytelandUtil.MAX_SINGULAR_STATE_COUNT + 1, 
				new ArrayList<Integer>(BytelandUtil.MAX_SINGULAR_STATE_COUNT - 1));
	}
	
	private static void populateCaseWithInvalidPathCount() throws BytelandException {
		BytelandUtil.generateSingularStates(BytelandUtil.MAX_SINGULAR_STATE_COUNT, 
				new ArrayList<Integer>(BytelandUtil.MAX_SINGULAR_STATE_COUNT + 1));
	}
	
}
