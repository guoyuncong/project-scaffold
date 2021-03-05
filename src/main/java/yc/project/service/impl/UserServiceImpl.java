package yc.project.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import yc.project.convert.UserConvert;
import yc.project.mapper.UserMapper;
import yc.project.model.entity.User;
import yc.project.model.vo.UserVO;
import yc.project.service.UserService;

import java.util.List;

/**
 * user service impl
 *
 * @author: yagena
 * @date: 2021-03-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    @SuppressWarnings("unchecked")
    public Page<UserVO> pageUser(Page page, String keyword) {
        Page userPage = this.baseMapper.selectPage(page, null);
        List<UserVO> userVOS = UserConvert.INSTANCE.convert2userVOList(userPage.getRecords());
        userPage.setRecords(userVOS);
        return userPage;
    }
}
