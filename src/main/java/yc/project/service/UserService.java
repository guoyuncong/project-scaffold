package yc.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import yc.project.model.dto.UserParamDTO;
import yc.project.model.entity.User;
import yc.project.model.vo.UserVO;

/**
 * user service
 *
 * @author: yagena
 * @date: 2021-03-05
 */
public interface UserService extends IService<User> {

    /**
     * 新增用户
     *
     * @param userParamDTO param
     * @return 主键ID
     */
    String saveUser(UserParamDTO userParamDTO);

    /**
     * 分页查询
     *
     * @param page      分页参数
     * @param keyword   关键字
     * @return  List<UserVO>
     */
    Page<UserVO> pageUser(Page page, String keyword);

}
