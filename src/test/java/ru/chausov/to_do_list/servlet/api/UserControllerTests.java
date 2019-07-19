package ru.chausov.to_do_list.servlet.api;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.chausov.to_do_list.data_base.repository.UserRepository;
import ru.chausov.to_do_list.servlet.UserController;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests  {

    @Autowired
    private UserController userController;
    @Autowired
    private UserRepository userRepository;

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
//        userToUpdate.setRoles(Collections.singleton(Role.USER));
//        userToUpdate.setUsername("testUser");
//
//        Map<String, Object> map = new HashMap<>();
//
//        userRepository.save(userToUpdate);
//
//        User updatedUser = (User) userController.updateUser(
//                User.builder().name("TestName").build(), map).getModel().get("user");
//
//        Assert.assertNotNull(updatedUser);
//        Assert.assertNotEquals(updatedUser.getName(), userToUpdate.getName());
//    }
}
