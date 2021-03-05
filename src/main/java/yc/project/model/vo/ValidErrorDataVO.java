package yc.project.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 参数核验异常信息类
 *      主要用于 @Validated 参数核验时，未通过核验返回前端
 *
 * @author: yagena
 * @date: 2021-03-05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidErrorDataVO {

    /**
     * 属性值
     */
    private String field;

    /**
     * 请求参数值
     */
    private Object rejectValue;

    /**
     * 信息
     */
    private String message;
}
