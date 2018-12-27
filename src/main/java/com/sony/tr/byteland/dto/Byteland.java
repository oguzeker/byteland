package com.sony.tr.byteland.dto;

import java.util.ArrayList;
import java.util.List;

public class Byteland {

	private Integer unificationTurnCount;
	private List<SingularState> singularStates = new ArrayList<>();
	private boolean isUnified;

	public Byteland(List<SingularState> singularStates, 
			Integer unificationTurnCount, boolean isUnified) {
		this.singularStates = singularStates;
		this.unificationTurnCount = unificationTurnCount;
		this.isUnified = isUnified; 
	}
	
	public Byteland() {	}
	
	public Integer getUnificationTurnCount() {
		return unificationTurnCount;
	}

	public void setUnificationTurnCount(Integer unificationTurnCount) {
		this.unificationTurnCount = unificationTurnCount;
	}

	public List<SingularState> getSingularStates() {
		return singularStates;
	}

	public void setSingularStates(List<SingularState> singularStates) {
		this.singularStates = singularStates;
	}

	public boolean isUnified() {
		return isUnified;
	}

	public void setUnified(boolean isUnified) {
		this.isUnified = isUnified;
	}

}
