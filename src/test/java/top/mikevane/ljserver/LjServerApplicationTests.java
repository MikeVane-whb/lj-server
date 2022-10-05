package top.mikevane.ljserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mikevane.ljserver.pojo.User;
import top.mikevane.ljserver.service.UserService;

@SpringBootTest
class LjServerApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void selectUserTest(){
        User user = new User();
        user.setId(1);
        userService.selectByUser(user);
    }

}
