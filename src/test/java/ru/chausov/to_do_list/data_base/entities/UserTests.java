package ru.chausov.to_do_list.data_base.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
    @Autowired
    private User user;

    @Test
    public void contextLoads(){
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
    }
}
