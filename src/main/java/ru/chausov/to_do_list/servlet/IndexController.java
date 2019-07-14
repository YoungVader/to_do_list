package ru.chausov.to_do_list.servlet;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.entity.Visit;
import ru.chausov.to_do_list.data_base.repository.UserRepository;
import ru.chausov.to_do_list.data_base.repository.VisitRepository;

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
    public ModelAndView editProfile(Map<String, Object> model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUsername(auth.getName());

        model.put("name", user.getName());
        model.put("last_name", user.getLastName());

        return new ModelAndView("edit_profile", model);
    }
}
