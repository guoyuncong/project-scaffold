package yc.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import yc.project.model.entity.User;

/**
 * user mapper
 *
 * @author: yagena
 * @date: 2021-03-05
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
