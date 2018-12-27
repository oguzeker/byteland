package com.sony.tr.byteland.exception;

public enum ErrorCode {

	SUCCESS("200"), 
	BAD_REQUEST("400"),

	UNPROCESSABLE_ENTITY("422"),
	INPUT_EXCEEDS_MIN_STATE_COUNT("422.001"), 
	INPUT_EXCEEDS_MAX_STATE_COUNT("422.002"),
	INPUT_PATH_COUNT_INVALID("422.003"),

	INTERNAL_SERVER_ERROR("500");

	private String code;

	private ErrorCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static ErrorCode fromValue(String code) {
		for (ErrorCode t : values()) {
			if (t.getCode().equals(code)) {
				return t;
			}
		}
		return INTERNAL_SERVER_ERROR;
	}
}
