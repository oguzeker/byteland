package com.sony.tr.byteland.dto;

import java.util.ArrayList;
import java.util.List;

public class SingularState {

	private boolean isUnited;
	private List<SingularState> neighborStates = new ArrayList<>();

	public SingularState(List<SingularState> neighborStates, 
			boolean isUnited) { 
		this.neighborStates = neighborStates;
		this.isUnited = isUnited;
	}
	
	public SingularState() { }
	
	public boolean isUnited() {
		return isUnited;
	}

	public void setUnited(boolean united) {
		isUnited = united;
	}

	public List<SingularState> getNeighborStates() {
		return neighborStates;
	}

	public void setNeighborStates(List<SingularState> neighborStates) {
		this.neighborStates = neighborStates;
	}

}
