package ru.chausov.to_do_list.data_base.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskTests {
    @Autowired
    private Task task;

    @Test
    public void contextLoads(){
        Assert.assertNotNull(task);
        Assert.assertNotNull(task.getId());
        Assert.assertNotNull(task.getUserId());
    }
}
