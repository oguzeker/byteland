package com.sony.tr.byteland.exception;

public abstract class BytelandException extends Exception {

	private static final long serialVersionUID = 1L;

	protected ErrorCode errorCode;

	protected String[] args;

	public abstract int getHttpStatusCode();

	/**
	 * Constructs a new exception with the specified cause and a detail message of
	 * <tt>(cause==null ? null : cause.toString())</tt> (which typically contains
	 * the class and detail message of <tt>cause</tt>). This constructor is useful
	 * for exceptions that are little more than wrappers for other throwables (for
	 * example, {@link java.security.PrivilegedActionException}).
	 * 
	 * @param errorCode the error code
	 * @param args      list of arguments are used in error message
	 */
	public BytelandException(ErrorCode errorCode, String... args) {
		super();
		this.errorCode = errorCode;
		this.args = args;
	}

	/**
	 * Constructs a new exception with the specified cause and a detail message of
	 * <tt>(cause==null ? null : cause.toString())</tt> (which typically contains
	 * the class and detail message of <tt>cause</tt>). This constructor is useful
	 * for exceptions that are little more than wrappers for other throwables (for
	 * example, {@link java.security.PrivilegedActionException}).
	 * 
	 * @param cause     the cause (which is saved for later retrieval by the
	 *                  {@link #getCause()} method). (A <tt>null</tt> value is
	 *                  permitted, and indicates that the cause is nonexistent or
	 *                  unknown.)
	 * @param errorCode the error code
	 * @param args      list of arguments are used in error message
	 */
	public BytelandException(Throwable cause, ErrorCode errorCode, String... args) {
		super(cause);
		this.errorCode = errorCode;
		this.args = args;
	}

	public BytelandException() {
		super();
		errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
	}

	public BytelandException(Throwable cause) {
		super(cause);
		errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
	}

	public BytelandException(String message) {
		super(message);
		errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
	}

	public BytelandException(String message, Throwable cause) {
		super(message, cause);
		errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public String[] getArgs() {
		return args;
	}

}
