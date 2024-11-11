package org.jzyanframework.core.exception;

import org.jzyanframework.core.response.ResCode;

/**
 * <p>
 * 系统异常顶层父类
 * </p>
 *
 * @author jzyan
 * @since 2022-03-15
 */
public class SystemException extends RuntimeException {

    /**
     * 错误码
     */
    private String code;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public SystemException(String code) {
        this.code = code;
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public SystemException(String code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(ResCode resCode) {
        super(resCode.getMessage());
        this.code = resCode.getCode();
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public SystemException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /**
     * Constructs a new runtime exception with the specified cause and a
     * detail message of <tt>(cause==null ? null : cause.toString())</tt>
     * (which typically contains the class and detail message of
     * <tt>cause</tt>).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.4
     */
    public SystemException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
