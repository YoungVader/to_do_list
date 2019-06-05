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
    public void contextLoads() {
        Assert.assertNotNull(userController);
    }

    @Test
    public void addUserTest() {
        User userToAdd = new User();

        User addedUser = userController.addUser(0L, userToAdd);

        Assert.assertEquals(userToAdd, addedUser);
    }

    @Test
    public void updateUserTest() {
        User userToUpdate = new User();

        userController.addUser(0L, userToUpdate);

        User updatedUser = userController.updateUser(userToUpdate.getId(),
                User.builder().name("TestName").build());

        Assert.assertNotNull(updatedUser);
        Assert.assertNotEquals(updatedUser.getName(), userToUpdate.getName());
    }
}
