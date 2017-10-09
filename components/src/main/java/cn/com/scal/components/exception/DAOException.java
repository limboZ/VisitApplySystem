package cn.com.scal.components.exception;

public class DAOException extends BasicException {

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, String errorCode) {
        super(message, errorCode);
    }

    public DAOException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }
}
