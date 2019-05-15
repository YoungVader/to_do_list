package ru.chausov.to_do_list.servlets.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chausov.to_do_list.data_base.entities.Visit;
import ru.chausov.to_do_list.data_base.repositories.VisitsRepository;

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
