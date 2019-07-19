package ru.chausov.to_do_list.data_base.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.type.Role;

import java.util.Collections;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveTest() {
        User userToSave = new User();

        User savedUser =  userRepository.save(userToSave);

        Assert.assertEquals(savedUser, userToSave);
    }

    @Test
    public void findTest() {
        User userToFind = new User();

        userToFind.setRoles(Collections.singleton(Role.USER));

        userRepository.save(userToFind);

        User foundUser = userRepository.findById(userToFind.getId()).get();

        Assert.assertEquals(userToFind, foundUser);
    }


    @Test
    public void deleteTest() {
        User user = new User();

        userRepository.save(user);

        long countBefore = userRepository.count();

        userRepository.deleteById(user.getId());

        long countAfter = userRepository.count();

        Assert.assertEquals(countBefore - 1, countAfter);
    }
}
