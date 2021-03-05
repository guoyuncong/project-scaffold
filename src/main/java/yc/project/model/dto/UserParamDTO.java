package yc.project.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import yc.project.basic.group.AddGroup;
import yc.project.basic.group.DeleteGroup;
import yc.project.basic.group.UpdateGroup;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 用户请求参数
 *
 * @author: yagena
 * @date: 2021-03-05
 */
@Data
public class UserParamDTO {

    /**
     * 用户ID
     */
    @NotBlank(message = "主键ID不能为空", groups = {DeleteGroup.class, UpdateGroup.class})
    private String id;

    /**
     * 用户姓名
     */
    @NotBlank(message = "用户姓名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;

    /**
     * 用户年龄
     */
    @Max(value = 200, message = "年龄不能超过200岁", groups = {AddGroup.class, UpdateGroup.class})
    @Min(value = 1, message = "年龄不能少于12岁", groups = {AddGroup.class, UpdateGroup.class})
    private Integer age;

    /**
     * 性别
     */
    @Range(max = 2, min = 1, message = "性别数值错误", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sex;
}
