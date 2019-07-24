package ru.chausov.to_do_list.servlet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.repository.UserRepository;

import java.security.Principal;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@RequestMapping("/add")
public class AddController {

    private final UserRepository userRepository;

    @GetMapping("/task")
    public ModelAndView addTask(Principal authUser, Map<String, Object> model){
        User user = userRepository.findByUsername(authUser.getName());

        model.put("user", user);

        return new ModelAndView("add_task", model);
    }
}
