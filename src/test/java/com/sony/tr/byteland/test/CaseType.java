package com.sony.tr.byteland.test;

public enum CaseType {

	CASE1("Case1"), 
	CASE2("Case2"), 
	CASE3("Case3"), 
	EXCEPTION_MIN("ExceptionMin"), 
	EXCEPTION_MAX("ExceptionMax"),
	EXCEPTION_PATH("ExceptionPath");
	
	private final String value;

	CaseType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static CaseType fromValue(String v) {
		for (CaseType c : CaseType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
