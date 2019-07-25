package ru.chausov.to_do_list.servlet.api;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.Task;
import ru.chausov.to_do_list.data_base.repository.TaskRepository;
import ru.chausov.to_do_list.servlet.TaskController;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTests {
    @Autowired
    private TaskController taskController;
    @Autowired
    private TaskRepository taskRepository;

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

    @Test
    public void updateTaskTest() {
        Task taskToUpdate = Task.builder().build();

        taskRepository.save(taskToUpdate);

        Map<String, Object> model = new HashMap<>();

        ModelAndView modelAndView = taskController.updateTask(taskToUpdate.getId().toString(),
                Task.builder().name("TestName").build(), model);

        Task updatedTask = (Task) modelAndView.getModel().get("task");

        Assert.assertNotNull(updatedTask);
        Assert.assertNotEquals(updatedTask.getName(), taskToUpdate.getName());
    }

    @Test
    public void setTaskDoneTest() {
        Task taskToSetDone = new Task();

        taskRepository.save(taskToSetDone);

        Assert.assertNotEquals(true, taskToSetDone.isDone());


        Map<String, Object> model = new HashMap<>();

        ModelAndView modelAndView = taskController.setTaskDone(taskToSetDone.getId().toString(),
                model);

        Task setDoneTask = (Task) modelAndView.getModel().get("task");

        Assert.assertNotNull(setDoneTask);
        Assert.assertTrue(setDoneTask.isDone());

        modelAndView = taskController.setTaskDone(taskToSetDone.getId().toString(),
                model);
        setDoneTask = (Task) modelAndView.getModel().get("task");

        Assert.assertNotNull(setDoneTask);
        Assert.assertTrue(!setDoneTask.isDone());
    }
}
