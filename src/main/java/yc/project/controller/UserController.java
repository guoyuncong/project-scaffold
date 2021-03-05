package yc.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yc.project.basic.group.AddGroup;
import yc.project.basic.group.DeleteGroup;
import yc.project.basic.group.UpdateGroup;
import yc.project.model.dto.UserParamDTO;
import yc.project.model.vo.Result;
import yc.project.model.vo.UserVO;
import yc.project.service.UserService;

import javax.validation.constraints.NotBlank;

/**
 * user controller
 *
 * @author: yagena
 * @date: 2021-03-05
 */
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    /**
     * 新增用户
     *
     * @param userParamDTO param
     * @return Result
     */
    @PostMapping(value = "save")
    public Result saveUser(@RequestBody @Validated(AddGroup.class) UserParamDTO userParamDTO) {
        String userId = userService.saveUser(userParamDTO);
        return Result.ofSuccess(userId);
    }


    /**
     * 更新用户
     *
     * @param userParamDTO param
     * @return Result
     */
    @PostMapping(value = "update")
    public Result updateUser(@RequestBody @Validated(UpdateGroup.class) UserParamDTO userParamDTO) {
        return Result.ofSuccess();
    }

    /**
     * 删除用户
     *
     * @param userParamDTO param
     * @return Result
     */
    @PostMapping(value = "delete")
    public Result deleteUser(@RequestBody @Validated(DeleteGroup.class) UserParamDTO userParamDTO) {
        return Result.ofSuccess();
    }


    /**
     * 分页查询用户
     *
     * @param keyword 关键字
     * @return Result
     */
    @GetMapping(value = "page")
    public Result pageUser(Page page,
                           @NotBlank(message = "关键字不能为空") String keyword) {
        Page<UserVO> userVOPage = userService.pageUser(page, keyword);
        return Result.ofSuccess(userVOPage);
    }
}
