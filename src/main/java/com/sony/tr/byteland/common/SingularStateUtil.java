package com.sony.tr.byteland.common;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.sony.tr.byteland.dto.SingularState;

public final class SingularStateUtil {
	
	/*
	 * Utility classes should not have a public constructor.
	 * Added a private static constructor in order to hide the implicit public one.
	 * Therefore, preventing instantiation.
	 */
	private SingularStateUtil() {
	}
	
	public static int processSingularStates(List<SingularState> singularStateList) {
		return processSingularStates(singularStateList, BytelandUtil.INITIAL_TURN_NUMBER);
	}
	
	// Recursive method to process singular-states.
    private static int processSingularStates(List<SingularState> singularStateList, 
    		int turnNumber) {
    	List<SingularState> nextTurnList = new ArrayList<>();

        for (SingularState subject : singularStateList) {
            if (!subject.isUnited()) {

                SingularState merger = detectMergerState(subject);

                if (merger != null) {
                    mergeStates(subject, merger);
                    merger.setUnited(true);
                }
                nextTurnList.add(subject);             
                subject.setUnited(true);
            }
        }
        
        // Unity status is applicable for the current turn only.
        // Has to be reset every time.
        return nextTurnList.size() >= BytelandUtil.MIN_SINGULAR_STATE_COUNT ? 
        		processSingularStates(resetUnityStatus(nextTurnList), ++turnNumber)
        		: turnNumber;
        		
    }
    
	private static List<SingularState> resetUnityStatus(List<SingularState> list) {
		Consumer<SingularState> consumer = (param) -> param.setUnited(false);
		
		list.forEach(consumer);
        return list;
    }

    private static void mergeStates(SingularState subject, SingularState merger) {
    	// Break the link between subject and merger.
    	merger.getNeighborStates().remove(subject);
    	subject.getNeighborStates().remove(merger);

    	// Subject acquires neighbors of merger.
        subject.getNeighborStates().addAll(merger.getNeighborStates());
        
        // Isolate merger.
        merger.getNeighborStates().clear();
    }

    private static SingularState detectMergerState(SingularState state) {
    	// Equals to the maximum value except the source state itself.
        int minimumNeighborCount = BytelandUtil.MAX_SINGULAR_STATE_COUNT - 1;

        SingularState mergerState = null;
        for (SingularState tempState : state.getNeighborStates()) {
            if (!tempState.isUnited() 
            		&& tempState.getNeighborStates().size() < minimumNeighborCount) {
                minimumNeighborCount = tempState.getNeighborStates().size();
                mergerState = tempState;
            }
        }

        return mergerState;
    }

}
