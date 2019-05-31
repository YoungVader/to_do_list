package ru.chausov.to_do_list.servlets.api;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.chausov.to_do_list.data_base.entities.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests  {

    @Autowired
    private UserController userController;

    @Test
    public void contextLoads() throws Exception {
        Assert.assertNotNull(userController);
        Assert.assertNotNull(userController.getUsersRepository());
    }

    @Test
    public void addUserTest() {
        User user = User.builder().name("TestUserName").lastName("TestUserLastName").build();

        long countBefore = userController.getUsersRepository().count();

        userController.addUser(0L, user);

        long countAfter = userController.getUsersRepository().count();

        Assert.assertEquals(countBefore + 1, countAfter);
    }
}
