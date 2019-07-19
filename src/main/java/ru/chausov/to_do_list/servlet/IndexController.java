package ru.chausov.to_do_list.servlet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.entity.Visit;
import ru.chausov.to_do_list.data_base.repository.UserRepository;
import ru.chausov.to_do_list.data_base.repository.VisitRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class IndexController {

    private final VisitRepository visitRepository;
    private final UserRepository userRepository;

    @GetMapping("")
    public String greeting() {
        Visit visit = Visit.builder()
                .description(String.format("Visited at %s", LocalDateTime.now()))
                .build();
        visitRepository.save(visit);

        return "greeting";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/edit/profile")
    public ModelAndView editProfile(Principal authUser, Map<String, Object> model){
        User user = userRepository.findByUsername(authUser.getName());

        model.put("user", user);

        if(user.getGender().equals("male"))
            model.put("gender", false);
        else if(user.getGender().equals("female"))
            model.put("gender", true);

        return new ModelAndView("edit_profile", model);
    }

    @GetMapping("/add/task")
    public ModelAndView addTask(Principal authUser, Map<String, Object> model){
        User user = userRepository.findByUsername(authUser.getName());

        model.put("user", user);

        return new ModelAndView("add_task", model);
    }
}
