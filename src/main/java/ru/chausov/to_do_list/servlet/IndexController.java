package ru.chausov.to_do_list.servlet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.Visit;
import ru.chausov.to_do_list.data_base.repository.VisitRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class IndexController {

    private final VisitRepository visitRepository;

    @GetMapping("")
    public String greeting() {
        Visit visit = Visit.builder()
                .description(String.format("Visited at %s", LocalDateTime.now()))
                .build();
        visitRepository.save(visit);

        return "greeting";
    }

    @GetMapping("/index")
    public ModelAndView index(Principal authUser, Map<String, Object> model) {
        model.put("username", authUser.getName());

        return new ModelAndView("index", model);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
