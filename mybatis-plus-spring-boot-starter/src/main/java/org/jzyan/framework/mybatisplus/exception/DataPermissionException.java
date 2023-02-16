package org.jzyan.framework.mybatisplus.exception;

/**
 * <p>
 *
 * </p>
 *
 * @author jzyan
 * @since 2022-03-15
 */
public class DataPermissionException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public DataPermissionException(String message) {
        super(message);
    }

}
