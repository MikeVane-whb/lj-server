package top.mikevane.ljserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mikevane.ljserver.pojo.User;
import top.mikevane.ljserver.service.UserService;
import top.mikevane.ljserver.util.Result;

/**
 * 用户相关的controller
 * 用于注册与登录
 * @author: whb
 * @date: 2022-09-16-09-54
 * @version: 1.0
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 根据 id 或 email 查询用户是否存在
     * @param id
     * @return
     */
    @PostMapping("/select")
    public Result selectByUser(@RequestParam(value = "id",required = false) Integer id,
                               @RequestParam(value = "email",required = false) String email){
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        User selectByUser = userService.selectByUser(user);
        if(selectByUser == null){
            return Result.error("用户不存在或者邮箱出错");
        }
        return Result.success(selectByUser);
    }

//    @PostMapping
//    public User login(User user){
//        User selectByUser = userService.selectByUser(user);
//        if(selectByUser != null){
//            user
//        }
//    }
}
