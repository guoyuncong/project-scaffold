package yc.project.model.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import yc.project.basic.enums.ResultCode;

/**
 * 返回结构体类
 *
 * @author: yagena
 * @date: 2021-03-05
 */
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Result<T> {

    /**
     * 返回码
     */
    private final String code;

    /**
     * 返回提示
     */
    private final String message;

    /**
     * 返回详情
     */
    private final T detail;

    /**
     * 返回：
     *      ❶ code：SUCCESS
     *      ❷ message：SUCCESS.getMessage
     *      ❸ detail：detail
     */
    public static <T> Result<T> ofSuccess(T detail) {
        return of(ResultCode.SUCCESS, detail);
    }

    /**
     * 返回：
     *      ❶ code：SUCCESS
     *      ❷ message：SUCCESS.getMessage
     *      ❸ detail：null
     */
    public static <T> Result<T> ofSuccess() {
        return of(ResultCode.SUCCESS);
    }

    /**
     * 返回：
     *      ❶ code：ResultCode
     *      ❷ message：ResultCode.getMessage
     *      ❸ detail：T
     */
    public static <T> Result<T> of(ResultCode resultCode, T detail) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), detail);
    }

    /**
     * 返回：
     *      ❶ code：ResultCode
     *      ❷ message：ResultCode.getMessage
     *      ❸ detail：null
     */
    public static <T> Result<T> of(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), null);
    }
}
