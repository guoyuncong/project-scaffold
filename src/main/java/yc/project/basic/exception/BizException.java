package yc.project.basic.exception;

import lombok.Getter;
import yc.project.basic.enums.ResultCode;

/**
 * 业务处理异常类
 *
 * @author: yagena
 * @date: 2021-03-05
 */
@Getter
public class BizException extends RuntimeException {

    private ResultCode code;

    private Object detail;

    private BizException(ResultCode code) {
        super(code.getMessage());
        this.code = code;
    }

    private BizException(ResultCode code, Object detail) {
        super(code.getMessage());
        this.code = code;
        this.detail = detail;
    }

    /**
     * 抛出异常
     */
    public static BizException of(ResultCode code) {
        return new BizException(code);
    }

    /**
     * 抛出异常
     */
    public static BizException of(ResultCode code, Object detail) {
        return new BizException(code, detail);
    }
}
