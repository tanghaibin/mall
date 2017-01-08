package top.tanghaibin.common.exception;

/**
 * Created by tanghaibin on 2016/12/27.
 */
public class BizRuntimeException extends RuntimeException {

    public BizRuntimeException() {
        super();
    }

    public BizRuntimeException(String message) {
        super(message);
    }

    public BizRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
