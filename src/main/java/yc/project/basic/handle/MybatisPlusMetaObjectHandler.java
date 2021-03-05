package yc.project.basic.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import yc.project.basic.constants.MPDefaultConstants;

import java.time.LocalDateTime;

/**
 * MyBatis-Plus 自动填充字段
 *
 * @author: yagena
 * @date: 2021-03-05
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入方法填充值
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        // 填充创建时间
        if (metaObject.hasSetter(MPDefaultConstants.CREATED_TIME)) {
            this.setFieldValByName(MPDefaultConstants.CREATED_TIME, now, metaObject);
        }
        // 填充更新时间
        if (metaObject.hasSetter(MPDefaultConstants.UPDATED_TIME)) {
            this.setFieldValByName(MPDefaultConstants.UPDATED_TIME, now, metaObject);
        }
        // @todo 填充创建人
    }

    /**
     * 更新方法填充值
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 填充更新时间
        if (metaObject.hasSetter(MPDefaultConstants.UPDATED_TIME)) {
            this.setFieldValByName(MPDefaultConstants.UPDATED_TIME, LocalDateTime.now(), metaObject);
        }
        // @todo 填充更新人
    }
}
