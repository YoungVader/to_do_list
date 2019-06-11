package ru.chausov.to_do_list.data_base.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.chausov.to_do_list.data_base.entities.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersRepositoryTests {
    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void saveTest() {
        User userToSave = new User();

        User savedUser =  usersRepository.save(userToSave);

        Assert.assertEquals(savedUser, userToSave);
    }

    @Test
    public void findTest() {
        User userToFind = new User();

        usersRepository.save(userToFind);

        User foundUser = usersRepository.findById(userToFind.getId()).get();

        Assert.assertEquals(userToFind, foundUser);
    }


    @Test
    public void deleteTest() {
        User user = new User();

        usersRepository.save(user);

        long countBefore = usersRepository.count();

        usersRepository.deleteById(user.getId());

        long countAfter = usersRepository.count();

        Assert.assertEquals(countBefore - 1, countAfter);
    }
}
