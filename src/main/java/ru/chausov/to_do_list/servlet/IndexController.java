package ru.chausov.to_do_list.servlet;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.chausov.to_do_list.data_base.entity.Visit;
import ru.chausov.to_do_list.data_base.repository.VisitRepository;


import java.time.LocalDateTime;


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
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
