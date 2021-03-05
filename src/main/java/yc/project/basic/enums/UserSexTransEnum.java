package yc.project.basic.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户性别翻译
 *
 * @author: yagena
 * @date: 2021-03-05
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserSexTransEnum {

    /**
     *  1   男
     *  2   女
     */
    SEX_MALE(1, "男"),
    SEX_FEMALE(2, "女"),

    ;

    /**
     * 工作流状态
     */
    private Integer sex;

    /**
     * 翻译
     */
    private String trans;

    /**
     * 根据性别获取
     *
     * @param sex   性别
     * @return  UserSexTransEnum
     */
    public static UserSexTransEnum of(Integer sex) {
        for (UserSexTransEnum value : UserSexTransEnum.values()) {
            if (value.sex.equals(sex)) {
                return value;
            }
        }
        return null;
    }
}
