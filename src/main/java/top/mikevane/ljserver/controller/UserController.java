package top.mikevane.ljserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mikevane.ljserver.pojo.User;
import top.mikevane.ljserver.pojo.UserInfo;
import top.mikevane.ljserver.service.UserInfoService;
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

    private static final String TAG = "UserController: ";

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    private static ThreadPoolUtil threadPoolUtil = ThreadPoolUtil.getInstance();

    /**
     * 根据 id 或 email 查询用户是否存在
     * @param user
     * @return
     */
    @PostMapping("/select")
    public Result selectByUser(@RequestBody User user){
        log.info(TAG + "select");
        if (!StringUtils.getInstance().isNullOrEmpty(user.getPassword())){
            return Result.error("非法输入");
        }
        User[] selectByUser = userService.selectByUser(user);
        if(selectByUser == null){
            return Result.error("用户不存在或者邮箱出错");
        }
        return Result.success(selectByUser.length);
    }

    /**
     * 发送验证码
     * @param user 传入用于信息
     * @param request 用于添加 sessionId
     * @return
     */
    @PostMapping("/sendCode")
    public Result sendCode(@RequestBody User user, HttpServletRequest request){
        log.info(TAG + "sendCode");
        HttpSession session = request.getSession();
        try{
            EmailUtil.sendCode(user.getPhone(),session);
            return Result.success(session.getId(),"邮件发送成功");
        } catch (Exception e){
            return Result.error("邮件发送失败");
        }
    }

    /**
     * 注册
     * @param user 传入 user 对象
     * @param httpSession session
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user, HttpSession httpSession){
        log.info(TAG + "register");
        log.info("sessionId: " + httpSession.getId());
        String frontPhone = user.getPhone();
        String frontVerityCode = user.getVerityCode();
        String sessionPhone = (String) httpSession.getAttribute("phone");
        String sessionVerityCode = (String)httpSession.getAttribute("verityCode");
        // 防空指针，前端传入手机号
        if (StringUtils.getInstance().isNullOrEmpty(frontPhone)){
            return Result.error("手机号为空");
        }
        // 防空指针，session 中没有相应的手机号
        if (StringUtils.getInstance().isNullOrEmpty(sessionPhone)){
            return Result.error("请先获取验证码");
        }
        // 前端传入手机号与 session 中手机号冲突
        if (!frontPhone.equals(sessionPhone)){
            return Result.error("手机号有误");
        }
        // 前端验证码为空
        if (StringUtils.getInstance().isNullOrEmpty(frontVerityCode)){
            return Result.error("验证码为空");
        }
        // session 中验证码为空
        if (StringUtils.getInstance().isNullOrEmpty(sessionVerityCode)){
            return Result.error("请重新获取验证码");
        }
        // 前端传入验证码与 session 中验证码冲突
        if (!frontVerityCode.equals(sessionVerityCode)){
            return Result.error("验证码有误");
        }
        // 插入 userinfo 信息到数据库
        int userInfoKey = userInfoService.addUserInfo(user);
        // 将插入得到的 userinfoId 设置到 user 中
        user.setUserInfoId(userInfoKey);
        // 注册
        int userCount = userService.register(user);
        // 如果注册失败
        if (userCount <= 0){
            return Result.error("未知错误");
        }
        return Result.success(userCount);
    }

    /**
     * 用户登录
     * @param user 传入用户的 phone 与 password
     * @param session 存 sessionId
     * @return 如果查到数据则返回 sessionId，反之则返回错误
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user,HttpSession session){
        String sessionId = session.getId();
        log.info(TAG + "login");
        log.info(TAG + sessionId);
        if (StringUtils.getInstance().isNullOrEmpty(user.getPhone())){
            return Result.error("手机号不能为空");
        }
        if (StringUtils.getInstance().isNullOrEmpty(user.getPassword())){
            return Result.error("密码不能为空");
        }
        int userCount = userService.selectByUser(user).length;
        if(userCount > 0){
            session.setAttribute("phone",user.getPhone());
            return Result.success(sessionId);
        }
        return Result.error("手机号或密码错误");
    }

    /**
     * 根据传入 phone，查询用户信息
     * @param user 用户 phone
     * @param session session用于辨别客户端
     * @return
     */
    @PostMapping("/selectUserInfo")
    public Result selectUserInfo(@RequestBody User user, HttpSession session){
        log.info(TAG + "selectUserInfo");
        UserInfo userInfo = new UserInfo();
        // session中没有之前存储的信息
        if (StringUtils.getInstance().isNullOrEmpty(session.getAttribute("phone"))){
            return Result.error("请登录");
        }
        // 传入参数出错
        if (StringUtils.getInstance().isNullOrEmpty(user.getPhone())){
            return Result.error("请重新输入手机号");
        }
        // session中存储的手机号与前端传入手机号有冲突
        if (!user.getPhone().equals(session.getAttribute("phone"))){
            return Result.error("未知错误");
        }
        userInfo.setPhone(user.getPhone());
        UserInfo[] userInfos = userInfoService.selectUserInfo(userInfo);
        // 查询到的用户信息大于 1
        if (userInfos.length > 1){
            return Result.error("未知错误");
        }
        return Result.success(userInfos[0],"查询成功");
    }

    /**
     * 更新用户信息
     * @param userInfo userinfo
     * @param session session
     * @return
     */
    @PostMapping("/updateUserInfo")
    public Result updateUserInfo(@RequestBody UserInfo userInfo, HttpSession session){
        log.info(TAG + "updateUserInfo");
        // session中没有之前存储的信息
        if (StringUtils.getInstance().isNullOrEmpty(session.getAttribute("phone"))){
            return Result.error("请重新登录");
        }
        // session中存储的手机号与前端传入手机号有冲突
        if (!userInfo.getPhone().equals(session.getAttribute("phone"))){
            return Result.error("未知错误");
        }
        int id = userInfoService.updateUserInfo(userInfo);
        if (id > 0){
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }
}
