package ru.Chausov.toDoList.toDoList.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.Chausov.toDoList.toDoList.dataBase.entities.Visit;
import ru.Chausov.toDoList.toDoList.dataBase.repositories.VisitsRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    final VisitsRepository visitsRepository;

    public IndexController(VisitsRepository visitsRepository) {
        this.visitsRepository = visitsRepository;
    }

    @GetMapping("/")
    public ModelAndView index() {
        Map<String, String> model = new HashMap<>();
        model.put("name", "Aleksandr");

        Visit visit = new Visit();
        visit.setDescription(String.format("Visited at %s", LocalDateTime.now()));
        visitsRepository.save(visit);

        return new ModelAndView("index", model);
    }
}
