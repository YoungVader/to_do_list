package ru.Chausov.toDoList.toDoList.servlets.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.Chausov.toDoList.toDoList.dataBase.entities.Visit;
import ru.Chausov.toDoList.toDoList.dataBase.repositories.VisitsRepository;

@RestController
@RequestMapping("/api")
public class ApiController {

    final VisitsRepository visitsRepository;

    public ApiController(VisitsRepository visitsRepository) {
        this.visitsRepository = visitsRepository;
    }

    @GetMapping("/visits")
    public Iterable<Visit> getVisits() {
        return visitsRepository.findAll();
    }
}
