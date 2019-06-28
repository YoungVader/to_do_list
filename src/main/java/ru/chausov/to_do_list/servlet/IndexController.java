package ru.chausov.to_do_list.servlet;

import lombok.RequiredArgsConstructor;
//import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.Visit;
import ru.chausov.to_do_list.data_base.repository.VisitsRepository;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


@Controller
@RequiredArgsConstructor
public class IndexController {

    private final VisitsRepository visitsRepository;

//    Logger logger = Logger.getLogger(IndexController.class);
    Logger logger = Logger.getLogger(IndexController.class.getName());
    @GetMapping("")
    public ModelAndView index() {
        Map<String, String> model = new HashMap<>();

        Visit visit = Visit.builder()
                .description(String.format("Visited at %s", LocalDateTime.now()))
                .build();
        visitsRepository.save(visit);

        logger.info("INDEX");
        return new ModelAndView("index", model);
    }
}
