package ru.chausov.to_do_list.servlet.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chausov.to_do_list.data_base.entity.Visit;
import ru.chausov.to_do_list.data_base.repository.VisitsRepository;


import javax.transaction.Transactional;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VisitsController {
    private final VisitsRepository visitsRepository;

    @Transactional
    @GetMapping("/visits")
    public Iterable<Visit> getVisits() {
        return visitsRepository.findAll();
    }
}