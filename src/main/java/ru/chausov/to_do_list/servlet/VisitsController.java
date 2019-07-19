package ru.chausov.to_do_list.servlet;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chausov.to_do_list.data_base.entity.Visit;
import ru.chausov.to_do_list.data_base.repository.VisitRepository;


import javax.transaction.Transactional;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class VisitsController {
    private final VisitRepository visitRepository;

    @Transactional
    @GetMapping("/visits")
    public Iterable<Visit> getVisits() {
        return visitRepository.findAll();
    }
}
