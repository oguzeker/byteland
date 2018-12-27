package com.sony.tr.byteland.common;

import java.util.ArrayList;
import java.util.List;

import com.sony.tr.byteland.dto.Byteland;
import com.sony.tr.byteland.dto.SingularState;
import com.sony.tr.byteland.exception.BytelandException;
import com.sony.tr.byteland.exception.BytelandLogicalException;
import com.sony.tr.byteland.exception.ErrorCode;

/**
 * The BytelandUtil class implements a byteland generation-utility. It can
 * populate a byteland and calculate its unification step count.
 *
 * @author Oguz Erhan Eker
 * @version 1.0.0-SNAPSHOT
 * @since 2018-12-27
 */
public final class BytelandUtil {
	
	public static final int MAX_SINGULAR_STATE_COUNT = 600;
	public static final int MIN_SINGULAR_STATE_COUNT = 2;
	public static final int INITIAL_TURN_NUMBER = 1;
	
	/*
	 * Utility classes should not have a public constructor.
	 * Added a private constructor in order to hide the implicit public one.
	 * Therefore, preventing instantiation.
	 */
	private BytelandUtil() {
	}
	
	public static void unifyByteland(Byteland byteland) {
		byteland.setUnificationTurnCount(
				SingularStateUtil.processSingularStates(byteland.getSingularStates()));
		byteland.setUnified(true);
	}
	
	public static List<SingularState> generateSingularStates(int stateCount, List<Integer> pathList) 
			throws BytelandException {
		verifyInputs(stateCount, pathList.size());
		List<SingularState> singularStateList = generateSingularStatesDisconnected(stateCount); 

		return connectSingularStates(singularStateList, pathList);
	}

	private static void verifyInputs(int stateCount, int pathCount) throws BytelandException {
		if (stateCount > MAX_SINGULAR_STATE_COUNT) {
			throw new BytelandLogicalException(ErrorCode.INPUT_EXCEEDS_MAX_STATE_COUNT, 
					String.valueOf(stateCount));
		}
		if (stateCount <= MIN_SINGULAR_STATE_COUNT) {
			throw new BytelandLogicalException(ErrorCode.INPUT_EXCEEDS_MIN_STATE_COUNT, 
					String.valueOf(stateCount));
		}
		if (pathCount != stateCount -1) {
			throw new BytelandLogicalException(ErrorCode.INPUT_PATH_COUNT_INVALID, 
					String.valueOf(pathCount));
		}
	}
	
    private static List<SingularState> connectSingularStates(List<SingularState> singularStateList, 
    		List<Integer> pathList) {
        for (int i = 0; i < pathList.size(); i++) {
            singularStateList.get(pathList.get(i)).getNeighborStates().add(singularStateList.get(i + 1));
            singularStateList.get(i + 1).getNeighborStates().add(singularStateList.get(pathList.get(i)));
        }
        return singularStateList;
    }

    private static List<SingularState> generateSingularStatesDisconnected(int count) {
        List<SingularState> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new SingularState());
        }
        return list;
    }

}
