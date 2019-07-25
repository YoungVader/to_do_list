package ru.chausov.to_do_list.data_base.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.chausov.to_do_list.data_base.entity.Task;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskRepositoryTests {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository usersRepository;

    @Test
    public void saveTest() {
        Task taskToSave = Task.builder().build();

        Task savedTask = taskRepository.save(taskToSave);

        Assert.assertEquals(savedTask, taskToSave);
    }

    @Test
    public void findTest() {
        Task taskToFind = Task.builder().build();

        taskRepository.save(taskToFind);

        Task foundTask = taskRepository.findById(taskToFind.getId()).get();

        Assert.assertEquals(taskToFind, foundTask);
    }


    @Test
    public void deleteTest() {
        Task task = Task.builder().build();

        taskRepository.save(task);

        long countBefore = taskRepository.count();

        taskRepository.deleteById(task.getId());

        long countAfter = taskRepository.count();

        Assert.assertEquals(countBefore - 1, countAfter);
    }
}
