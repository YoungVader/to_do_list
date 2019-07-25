package ru.chausov.to_do_list.data_base.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.chausov.to_do_list.data_base.entity.Visit;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitRepositoryTests {

    @Autowired
    private VisitRepository visitRepository;

    @Test
    public void saveTest() {
        Visit visitToSave = Visit.builder().build();

        Visit savedVisit = visitRepository.save(visitToSave);

        Assert.assertEquals(savedVisit, visitToSave);
    }

    @Test
    public void findTest() {
        Visit visitToFind = Visit.builder().build();

        visitRepository.save(visitToFind);

        Visit foundVisit = visitRepository.findById(visitToFind.getId()).get();

        Assert.assertEquals(visitToFind, foundVisit);
    }


    @Test
    public void deleteTest() {
        Visit visit = Visit.builder().build();

        visitRepository.save(visit);

        long countBefore = visitRepository.count();

        visitRepository.deleteById(visit.getId());

        long countAfter = visitRepository.count();

        Assert.assertEquals(countBefore - 1, countAfter);
    }

}
