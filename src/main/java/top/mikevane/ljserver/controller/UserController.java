package top.mikevane.ljserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mikevane.ljserver.pojo.User;
import top.mikevane.ljserver.service.UserService;
import top.mikevane.ljserver.util.EmailUtil;
import top.mikevane.ljserver.util.Result;
import top.mikevane.ljserver.util.StringUtils;
import top.mikevane.ljserver.util.ThreadPoolUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    private static ThreadPoolUtil threadPoolUtil = ThreadPoolUtil.getInstance();

    /**
     * 根据 id 或 email 查询用户是否存在
     * @param user
     * @return
     */
    @PostMapping("/select")
    public Result selectByUser(@RequestBody User user){
        User[] selectByUser = userService.selectByUser(user);
        if(selectByUser == null){
            return Result.error("用户不存在或者邮箱出错");
        }
        return Result.success(selectByUser.length);
    }

    /**
     * 发送验证码
     * @param user
     * @param request 用于添加 sessionId
     * @return
     */
    @PostMapping("/sendCode")
    public Result sendCode(@RequestBody User user, HttpServletRequest request){
        HttpSession session = request.getSession();
        EmailUtil.sendCode(user.getPhone(),session);
        return Result.success(session.getId(),"邮件发送成功");
    }

    /**
     * 注册
     * @param user 传入 user 对象
     * @param httpSession
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody(required = false) User user,
                           @RequestBody(required = false) String verityCode,
                           HttpSession httpSession){
        log.info("sessionId: " + httpSession.getId());
        String frontPhone = user.getPhone();
        String sessionPhone = (String) httpSession.getAttribute("phone");
        String sessionVerityCode = (String)httpSession.getAttribute("verityCode");
        // 防空指针，前端传入手机号
        if (StringUtils.getInstance().isNullOrEmpty(frontPhone)){
            return Result.error("手机号不能为空");
        }
        // 防空指针，session 中没有相应的手机号
        if (StringUtils.getInstance().isNullOrEmpty(sessionPhone)){
            return Result.error("重新输入手机号");
        }
        // 前端传入手机号与 session 中手机号冲突
        if (!frontPhone.equals(sessionPhone)){
            return Result.error("手机号有误");
        }
        // 前端传入验证码与 session 中验证码冲突
        if (!verityCode.equals(sessionVerityCode)){
            System.out.println("验证码有误");
        }
        int account = userService.register(user);
        if (account <= 0){
            return Result.error("未知错误");
        }
        return Result.success(account);
    }
}
