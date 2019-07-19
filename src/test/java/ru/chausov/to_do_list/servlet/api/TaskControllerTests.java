package ru.chausov.to_do_list.servlet.api;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.chausov.to_do_list.data_base.entity.Task;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTests {
    @Autowired
    TaskController taskController;

//    @Test
//    public void addTaskTest() {
//        Task taskToAdd = new Task();
//
//        taskToAdd.setDescription("TestDescription");
//        Task addedTask = taskController.addTask(taskToAdd);
//
//        Assert.assertEquals(taskToAdd, addedTask);
//        Assert.assertEquals(taskToAdd.getDescription(), addedTask.getDescription());
//    }

//    @Test
//    public void updateTaskTest() {
//        Task taskToUpdate = new Task();
//
//        taskController.addTask(taskToUpdate);
//
//        Task updatedTask = taskController.updateTask(taskToUpdate.getId(),
//                                            Task.builder().name("TestName").build());
//
//        Assert.assertNotNull(updatedTask);
//        Assert.assertNotEquals(updatedTask.getName(), taskToUpdate.getName());
//    }
//
//    @Test
//    public void setTaskDoneTest() {
//        Task taskToSetDone = new Task();
//
//        taskController.addTask(taskToSetDone);
//
//        Assert.assertNotEquals(true, taskToSetDone.isDone());
//
//        Task setDoneTask = taskController.setTaskDone(taskToSetDone.getId(),
//                taskToSetDone);
//
//        Assert.assertNotNull(setDoneTask);
//        Assert.assertTrue(setDoneTask.isDone());
//    }
}
