package yc.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import yc.project.model.entity.User;
import yc.project.model.vo.UserVO;

import java.util.List;

/**
 * user service
 *
 * @author: yagena
 * @date: 2021-03-05
 */
public interface UserService extends IService<User> {

    /**
     * 分页查询
     *
     * @param page      分页参数
     * @param keyword   关键字
     * @return  List<UserVO>
     */
    Page<UserVO> pageUser(Page page, String keyword);
}
