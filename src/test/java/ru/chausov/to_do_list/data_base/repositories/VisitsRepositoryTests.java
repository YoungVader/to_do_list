package ru.chausov.to_do_list.data_base.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.chausov.to_do_list.data_base.entities.Visit;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitsRepositoryTests {

    @Autowired
    private VisitsRepository visitsRepository;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(visitsRepository);
    }

    @Test
    public void saveTest() {
        Visit visitToSave = new Visit();

        Visit savedVisit = visitsRepository.save(visitToSave);

        Assert.assertEquals(savedVisit, visitToSave);
    }

    @Test
    public void findTest() {
        Visit visitToFind = new Visit();

        visitsRepository.save(visitToFind);

        Visit foundVisit = visitsRepository.findById(visitToFind.getId()).get();

        Assert.assertEquals(visitToFind, foundVisit);
    }


    @Test
    public void deleteTest() {
        Visit visit = new Visit();

        visitsRepository.save(visit);

        long countBefore = visitsRepository.count();

        visitsRepository.delete(visit);

        long countAfter = visitsRepository.count();

        Assert.assertEquals(countBefore - 1, countAfter);
    }

}
