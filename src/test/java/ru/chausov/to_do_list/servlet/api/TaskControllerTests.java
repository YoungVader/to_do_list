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
import ru.chausov.to_do_list.data_base.entity.Task;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.repository.TaskRepository;
import ru.chausov.to_do_list.data_base.repository.UserRepository;
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
    @Autowired
    private UserRepository userRepository;


    @WithMockUser(username = "addUser", password = "123")
    @Test
    public void addTaskTest() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = User.builder().username("addUser").password("123").build();

        userRepository.save(user);

        Task taskToAdd = Task.builder().user(user).build();

        taskToAdd.setDescription("TestDescription");

        Map<String, Object> model = new HashMap<>();

        ModelAndView modelAndView = taskController.addTask(auth, taskToAdd, model);

        Task addedTask = (Task) modelAndView.getModel().get("task");

        Assert.assertEquals(taskToAdd, addedTask);
        Assert.assertEquals(taskToAdd.getDescription(), addedTask.getDescription());
    }

    @WithMockUser(username = "updateUser", password = "123")
    @Test
    public void updateTaskTest() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = User.builder().username("updateUser").password("123").build();

        userRepository.save(user);

        Task taskToUpdate = Task.builder().user(user).build();

        taskRepository.save(taskToUpdate);

        Map<String, Object> model = new HashMap<>();

        ModelAndView modelAndView = taskController.updateTask(auth, taskToUpdate.getId(),
                Task.builder().name("TestName").build(), model);

        Task updatedTask = (Task) modelAndView.getModel().get("task");

        Assert.assertNotNull(updatedTask);
        Assert.assertNotEquals(updatedTask.getName(), taskToUpdate.getName());
    }

    @WithMockUser(username = "doneUser", password = "123")
    @Test
    public void setTaskDoneTest() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = User.builder().username("doneUser").password("123").build();

        userRepository.save(user);

        Task taskToSetDone = Task.builder().user(user).build();

        taskRepository.save(taskToSetDone);

        Assert.assertNotEquals(true, taskToSetDone.isDone());


        Map<String, Object> model = new HashMap<>();

        ModelAndView modelAndView = taskController.setTaskDone(auth, taskToSetDone.getId(),
                model);

        Task setDoneTask = (Task) modelAndView.getModel().get("task");

        Assert.assertNotNull(setDoneTask);
        Assert.assertTrue(setDoneTask.isDone());

        modelAndView = taskController.setTaskDone(auth, taskToSetDone.getId(),
                model);
        setDoneTask = (Task) modelAndView.getModel().get("task");

        Assert.assertNotNull(setDoneTask);
        Assert.assertTrue(!setDoneTask.isDone());
    }
}
