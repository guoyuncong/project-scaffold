package yc.project.basic.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yc.project.basic.enums.ResultCode;
import yc.project.basic.exception.BizException;
import yc.project.model.vo.Result;
import yc.project.model.vo.ValidErrorDataVO;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.validation.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常处理类
 *
 * @author: yagena
 * @date: 2021-03-05
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 业务异常
     *
     * @param ex 捕获异常
     * @return ResponseEntity<ResultDTO>
     */
    @ExceptionHandler({BizException.class})
    public ResponseEntity<Result> bizExceptionHandler(Exception ex) {
        LOGGER.error("bizException => ", ex);
        BizException biz = (BizException) ex;
        if (biz.getDetail() == null) {
            return new ResponseEntity<>(Result.of(biz.getCode()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Result.of(biz.getCode(), biz.getDetail()), HttpStatus.OK);
        }
    }

    /**
     * 参数核验异常
     */
    @ExceptionHandler(value = BindException.class)
    public Result bindException(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ValidErrorDataVO> validErrorDataVOS = parseBindingResult(bindingResult);
        return Result.of(ResultCode.USER_REQUEST_PARAM_ERROR, validErrorDataVOS);
    }

    /**
     * 核验参数异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ValidErrorDataVO> validErrorDataVOS = parseBindingResult(bindingResult);
        return Result.of(ResultCode.USER_REQUEST_PARAM_ERROR, validErrorDataVOS);
    }

    /**
     * 核验参数异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result constraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<ValidErrorDataVO> validErrorDataVOS = handleViolations4validErrorData(violations);
        return Result.of(ResultCode.USER_REQUEST_PARAM_ERROR, validErrorDataVOS);
    }

    /**
     * 服务器内部异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> exceptionHandler(Exception ex) {
        LOGGER.error("unknownException => ", ex);
        return new ResponseEntity<>(Result.of(ResultCode.BIZ_SYSTEM_EXECUTE_ERROR), HttpStatus.OK);
    }

    /**
     * 解析异常信息
     */
    private List<ValidErrorDataVO> parseBindingResult(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .map(error ->
                        ValidErrorDataVO.builder()
                                .field(error.getField())
                                .rejectValue(error.getRejectedValue())
                                .message(error.getDefaultMessage())
                                .build()
                )
                .collect(Collectors.toList());
    }

    /**
     * 解析异常信息
     */
    private List<ValidErrorDataVO> handleViolations4validErrorData(Set<ConstraintViolation<?>> violations) {
        return violations.stream()
                .map(violation -> {
                    ValidErrorDataVO validErrorDataVO = new ValidErrorDataVO();
                    Path propertyPath = violation.getPropertyPath();
                    propertyPath.forEach(node -> {
                        if (ElementKind.PARAMETER.equals(node.getKind()) || ElementKind.PROPERTY.equals(node.getKind())) {
                            validErrorDataVO.setField(node.getName());
                        }
                    });
                    validErrorDataVO.setRejectValue(violation.getInvalidValue());
                    validErrorDataVO.setMessage(violation.getMessage());
                    return validErrorDataVO;
                })
                .collect(Collectors.toList());

    }

}
