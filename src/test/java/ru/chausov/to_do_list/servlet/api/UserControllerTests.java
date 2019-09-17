package ru.chausov.to_do_list.servlet.api;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.repository.UserRepository;
import ru.chausov.to_do_list.data_base.type.Role;
import ru.chausov.to_do_list.servlet.UserController;

import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {

    @Autowired
    private UserController userController;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void addUserTest() throws Exception {
        User userToAdd = User.builder().username("username").password("123").build();

        Map<String, Object> model = new HashMap<>();

        ModelAndView modelAndView = userController.addUser(userToAdd, model);

        User addedUser = (User) modelAndView.getModel().get("user");

        Assert.assertEquals(userToAdd, addedUser);
    }

    @WithMockUser(username = "updUser", password = "123")
    @Test
    public void updateUserTest() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = User.builder().username("updUser").password("123").build();

        Set roles = new HashSet();

        roles.add(Role.ADMIN);

        user.setRoles(roles);

        userRepository.save(user);

        User userToUpdate = User.builder().username("testUser").password("testPassword")
                .build();

        userRepository.save(userToUpdate);

        Map<String, Object> model = new HashMap<>();

        ModelAndView modelAndView = userController.updateUser(auth, userToUpdate.getId(),
                User.builder().name("TestName").password("123").build(),
                null,
                null,
                model);

        User updatedUser = (User) modelAndView.getModel().get("user");

        Assert.assertNotNull(updatedUser);
        Assert.assertNotEquals(updatedUser.getName(), userToUpdate.getName());
    }

    @WithMockUser(username = "administrator", password = "123")
    @Test
    public void deleteUserTest() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User admin = User.builder().username("administrator").password("123").build();

        Set roles = new HashSet();

        roles.add(Role.ADMIN);

        admin.setRoles(roles);

        userRepository.save(admin);

        User user = User.builder().username("user").password("123").build();

        userRepository.save(user);

        long countBefore = ((Collection<User>) userRepository.findAll()).size();

        userController.deleteUser(auth, user.getId());

        long countAfter = ((Collection<User>) userRepository.findAll()).size();

        Assert.assertEquals(countAfter, countBefore - 1);
    }
}
