package me.chaosdefinition.hyperspark.common;

/**
 * The default exception in hyperspark. This is just a simple wrapper of
 * {@link RuntimeException}.
 * 
 * @author Chaos Shen
 */
public class HypersparkException extends RuntimeException {

	private static final long serialVersionUID = 2495022590179009L;

	/**
	 * Constructs a new {@link HypersparkException} with no message.
	 */
	public HypersparkException() {
		super();
	}

	/**
	 * Constructs a new {@link HypersparkException} with the specified detail
	 * message.
	 * 
	 * @param message
	 *            the detail message.
	 */
	public HypersparkException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link HypersparkException} with only cause.
	 * 
	 * @param cause
	 *            the cause of this exception
	 */
	public HypersparkException(Throwable cause) {
		super("See below.", cause);
	}

	/**
	 * Constructs a new {@link HypersparkException} with the specified detail
	 * message and cause.
	 * 
	 * @param message
	 *            the detail message.
	 * @param cause
	 *            the cause of this exception
	 */
	public HypersparkException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Overrides the default {@link Throwable#printStackTrace()} and prints a
	 * shorter backtrace constisting only of causes and their messages (if
	 * exist) like the following.
	 * 
	 * <pre>
	 * hyperspark: Message of this exception.
	 * Caused by: java.lang.NullPointerException
	 * Caused by: java.lang.IOException: File not found.
	 * ...
	 * </pre>
	 */
	@Override
	public void printStackTrace() {
		System.err.println("hyperspark: " + getMessage());
		Throwable cause = getCause();
		while (cause != null) {
			System.err.println("Caused by: " + cause);
			cause = cause.getCause();
		}
	}
}
