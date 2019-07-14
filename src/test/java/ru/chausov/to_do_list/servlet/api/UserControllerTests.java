package ru.chausov.to_do_list.servlet.api;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.chausov.to_do_list.data_base.entity.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests  {

    @Autowired
    private UserController userController;

//    @Test
//    public void addUserTest() throws Exception {
//        User userToAdd = new User();
//
//        User addedUser = userController.addUser(userToAdd);
//
//        Assert.assertEquals(userToAdd, addedUser);
//    }

//    @Test
//    public void updateUserTest() throws Exception {
//        User userToUpdate = new User();
//
//        userController.updateUser(userToUpdate.getId(), userToUpdate);
//
//        User updatedUser = userController.updateUser(userToUpdate.getId(),
//                User.builder().name("TestName").build());
//
//        Assert.assertNotNull(updatedUser);
//        Assert.assertNotEquals(updatedUser.getName(), userToUpdate.getName());
//    }
}
