package com.company.levelupservice.service;


import com.company.levelupservice.dao.LevelUpDao;
import com.company.levelupservice.dao.LevelUpDaoImpl;
import com.company.levelupservice.exception.NotFoundException;
import com.company.levelupservice.model.LevelUp;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LevelUpServiceTests {

    public LevelUpDao levelUpDao;

    public ServiceLayer service;

    @Before
    public void setUp() throws Exception {
        setUpLevelUpDaoMock();

        service = new ServiceLayer(levelUpDao);
    }

    @Test
    public void shouldSaveAndFindLevelUp() {

        LevelUp levelUp = new LevelUp();
        levelUp = new LevelUp();
        levelUp.setCustomerId(12);
        levelUp.setPoints(200);
        levelUp.setMemberDate(LocalDate.of(2019, 12, 10));

        levelUp = service.saveLevelUp(levelUp);

        assertEquals(levelUp, service.findLevelUpByLvlId(levelUp.getLevelUpId()));

    }

    @Test
    public void shouldFindAndDeleteLevelUpCustomerId() {

        LevelUp levelUp1 = new LevelUp();
        levelUp1 = new LevelUp();
        levelUp1.setCustomerId(12);
        levelUp1.setPoints(200);
        levelUp1.setMemberDate(LocalDate.of(2019, 12, 10));

        levelUp1 = service.saveLevelUp(levelUp1);

        assertEquals(levelUp1, service.getLevelUpByCustomerId(12));

    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundExceptionWhenNoCustomerNotFound() throws Exception {
        service.getLevelUpByCustomerId(10);

    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundExceptionWhenNoLevelUpNotFound() throws Exception {
        service.findLevelUpByLvlId(19);

    }

    @Test
    public void shouldReturnAllLevelUps() {
        LevelUp levelUp1 = new LevelUp();
        levelUp1 = new LevelUp();
        levelUp1.setCustomerId(12);
        levelUp1.setPoints(200);
        levelUp1.setMemberDate(LocalDate.of(2019, 12, 10));

        levelUp1 = service.saveLevelUp(levelUp1);

        List<LevelUp> levelUps = new ArrayList<>(Collections.singletonList(levelUp1));

        assertEquals(levelUps, service.getAllLevelUps());

    }


    public void setUpLevelUpDaoMock() {
        levelUpDao = mock(LevelUpDaoImpl.class);

        LevelUp levelUp = new LevelUp();
        levelUp = new LevelUp();
        levelUp.setCustomerId(12);
        levelUp.setPoints(200);
        levelUp.setMemberDate(LocalDate.of(2019, 12, 10));

        LevelUp levelUp1 = new LevelUp();
        levelUp1 = new LevelUp();
        levelUp1.setLevelUpId(1);
        levelUp1.setCustomerId(12);
        levelUp1.setPoints(200);
        levelUp1.setMemberDate(LocalDate.of(2019, 12, 10));

        LevelUp updatedLevelUp = new LevelUp();
        updatedLevelUp = new LevelUp();
        updatedLevelUp.setCustomerId(1);
        updatedLevelUp.setPoints(250);
        updatedLevelUp.setMemberDate(LocalDate.of(2019, 12, 10));

        List<LevelUp> levelUps = new ArrayList<>(Collections.singletonList(levelUp1));


        when(levelUpDao.createLevelUp(levelUp)).thenReturn(levelUp1);
        when(levelUpDao.getLevelUpByLvlUpId(1)).thenReturn(levelUp1);
        when(levelUpDao.getAllLevelUps()).thenReturn(levelUps);
        when(levelUpDao.getLevelUpByCustomerId(12)).thenReturn(levelUp1);
        when(levelUpDao.getLevelUpByLvlUpId(19)).thenThrow(new NotFoundException("Level Up customer account not found"));
        when(levelUpDao.getLevelUpByCustomerId(10)).thenThrow(new NotFoundException("Level Up customer account not found"));
//        when(levelUpDao.addPointsByCustomerId(50, 12))


    }

}
