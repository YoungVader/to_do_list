package ru.chausov.to_do_list.servlet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.Visit;
import ru.chausov.to_do_list.data_base.repository.VisitsRepository;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class IndexController {

    private final VisitsRepository visitsRepository;

    @GetMapping("")
    public ModelAndView index() {
        Map<String, String> model = new HashMap<>();

        Visit visit = Visit.builder()
                .description(String.format("Visited at %s", LocalDateTime.now()))
                .build();
        visitsRepository.save(visit);

        return new ModelAndView("index", model);
    }
}
