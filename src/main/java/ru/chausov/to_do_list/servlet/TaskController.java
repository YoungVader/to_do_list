package ru.chausov.to_do_list.servlet;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.Task;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.repository.TaskRepository;
import ru.chausov.to_do_list.data_base.repository.UserRepository;

import javax.transaction.Transactional;
import java.security.Principal;
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
        User user = userRepository.findByUsername(authUser.getName());

        task.setUser(user);

        model.put("task", taskRepository.save(task));

        return new ModelAndView("redirect:/index", model);
    }

    @Transactional
    @PostMapping("/delete")
    public ModelAndView deleteTask(String id) {
        taskRepository.deleteById(Long.parseLong(id));

        return new ModelAndView("redirect:/tasks/table");
    }

    @Transactional
    @PostMapping("/update")
    public ModelAndView updateTask(Task task, Map<String, Object> model) {
        Task taskToUpdate = taskRepository.findById(task.getId()).get();

        taskToUpdate.setName(task.getName());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setReceivedDate(task.getReceivedDate());
        taskToUpdate.setToBeDone(task.getToBeDone());

        model.put("task", taskRepository.save(taskToUpdate));

        return new ModelAndView("redirect:/tasks/table", model);
    }

    @Transactional
    @PostMapping("/done")
    public ModelAndView setTaskDone(String id, Map<String, Object> model) {
        Task task = taskRepository.findById(Long.parseLong(id)).get();

        if(!task.isDone())
            task.setDone(true);
        else
            task.setDone(false);

        model.put("task", taskRepository.save(task));

        return new ModelAndView("redirect:/tasks/table");
    }
}
