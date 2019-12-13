package com.company.levelupservice.dao;

import com.company.levelupservice.model.LevelUp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LevelUpDaoImplTests {

    @Autowired
    public LevelUpDao lvlUpDao;

    private LevelUp levelUp;

    @Before
    public void setUp() {
        clearDataBase();
        setUpTestObjects();
    }

    public void clearDataBase() {
        List<LevelUp> levelUps = lvlUpDao.getAllLevelUps();
        for (LevelUp levelUp : levelUps) {
            lvlUpDao.deleteLevelUp(levelUp.getLevelUpId());
        }
    }

    public void setUpTestObjects() {
        levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setPoints(200);
        levelUp.setMemberDate(LocalDate.of(2019, 12, 10));
    }

    @Test
    public void shouldAddGetAndDeleteLevelUp() {
        levelUp = lvlUpDao.createLevelUp(levelUp);

        LevelUp levelUpFromDb = lvlUpDao.getLevelUpByLvlUpId(levelUp.getLevelUpId());

        assertEquals(levelUp, levelUpFromDb);

        lvlUpDao.deleteLevelUp(levelUp.getLevelUpId());

        assertNull(lvlUpDao.getLevelUpByLvlUpId(levelUp.getLevelUpId()));
    }


    @Test
    public void shouldUpdateLevelUp() {

        levelUp = lvlUpDao.createLevelUp(levelUp);

        levelUp.setPoints(310);

        lvlUpDao.updateLevelUp(levelUp);

        assertEquals(levelUp, lvlUpDao.getLevelUpByLvlUpId(levelUp.getLevelUpId()));

    }

    @Test
    public void shouldReturnAllLevelUps() {
        levelUp = lvlUpDao.createLevelUp(levelUp);

        List<LevelUp> levelUps = new ArrayList<>(Collections.singletonList(levelUp));

        assertEquals(levelUps, lvlUpDao.getAllLevelUps());
    }

    @Test
    public void shouldReturnLevelUpByCustomerId() {
        levelUp = lvlUpDao.createLevelUp(levelUp);

        assertEquals(levelUp, lvlUpDao.getLevelUpByCustomerId(1));
    }

    @Test
    public void shouldAddPointsToLevelUpsBasedOnCustomerId() {
        levelUp = lvlUpDao.createLevelUp(levelUp);

        lvlUpDao.addPointsByCustomerId(100, levelUp.getCustomerId());

        assertEquals(300, lvlUpDao.getLevelUpByLvlUpId(levelUp.getLevelUpId()).getPoints());

    }

}
