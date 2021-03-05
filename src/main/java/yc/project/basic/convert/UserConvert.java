package yc.project.basic.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import yc.project.model.dto.UserParamDTO;
import yc.project.model.entity.User;
import yc.project.model.vo.UserVO;

import java.util.List;

/**
 * 用户转换器
 *
 * @author: yagena
 * @date: 2021-03-05
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    /**
     * param -> entity
     *
     * @param userParamDTO param
     * @return entity
     */
    User convert2user(UserParamDTO userParamDTO);

    /**
     * entity -> vo
     *
     * @param user entity
     * @return vo
     */
    UserVO convert2userVO(User user);

    /**
     * List<entity> -> List<vo>
     *
     * @param users List<entity>
     * @return List<vo>
     */
    List<UserVO> convert2userVOList(List<User> users);

}
