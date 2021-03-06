package ru.chausov.to_do_list.servlet;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.Task;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.repository.TaskRepository;
import ru.chausov.to_do_list.data_base.repository.UserRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Controller
@Data
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Transactional
    @GetMapping("/table")
    public ModelAndView getTasks(Principal authUser, Map<String, Object> model) {
        User user = userRepository.findByUsername(authUser.getName());

        if(user.getTasks().isEmpty())
            model.put("message", "You haven't added any tasks yet!");
        else
            model.put("tasks", user.getTasks());

        return new ModelAndView("table_tasks", model);
    }

    @Transactional
    @PostMapping("/add")
    public ModelAndView addTask(Principal authUser, Task task, Map<String, Object> model) {
        task.setReceivedDate(LocalDate.now());

        User user = userRepository.findByUsername(authUser.getName());

        task.setUser(user);

        model.put("task", taskRepository.save(task));

        return new ModelAndView("redirect:/index", model);
    }

    @Transactional
    @PostMapping("/delete")
    public ModelAndView deleteTask(Principal authUser, Long id) {
        User user = userRepository.findByUsername(authUser.getName());

        List<Task> tasks = user.getTasks();

        boolean found = false;

        for(Task taskToCheck : tasks) {
            if(taskToCheck.getId().equals(id))
                found = true;
        }

        if(found)
            taskRepository.deleteById(id);

        return new ModelAndView("redirect:/tasks/table");
    }

    @Transactional
    @PostMapping("/update")
    public ModelAndView updateTask(Principal authUser, Long id, Task task, Map<String, Object> model) {
        User user = userRepository.findByUsername(authUser.getName());

        List<Task> tasks = user.getTasks();

        boolean found = false;

        for(Task taskToCheck : tasks) {
            if(taskToCheck.getId().equals(id))
                found = true;
        }

        if(found) {
            Task taskToUpdate = taskRepository.findById(id).get();

            taskToUpdate.setName(task.getName());
            taskToUpdate.setDescription(task.getDescription());
            taskToUpdate.setReceivedDate(task.getReceivedDate());
            taskToUpdate.setToBeDone(task.getToBeDone());

            model.put("task", taskRepository.save(taskToUpdate));
        }

        return new ModelAndView("redirect:/tasks/table", model);
    }

    @Transactional
    @PostMapping("/done")
    public ModelAndView setTaskDone(Principal authUser, Long id, Map<String, Object> model) {
        User user = userRepository.findByUsername(authUser.getName());

        List<Task> tasks = user.getTasks();

        boolean found = false;

        for(Task taskToCheck : tasks) {
            if(taskToCheck.getId().equals(id))
                found = true;
        }

        if(found) {
            Task task = taskRepository.findById(id).get();

            if (!task.isDone())
                task.setDone(true);
            else
                task.setDone(false);

            model.put("task", taskRepository.save(task));
        }

        return new ModelAndView("redirect:/tasks/table", model);
    }
}
