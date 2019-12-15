package com.company.levelupservice.service;

import com.company.levelupservice.dao.LevelUpDao;
import com.company.levelupservice.exception.NotFoundException;
import com.company.levelupservice.model.LevelUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {

    public LevelUpDao levelUpDao;

    @Autowired
    public ServiceLayer(LevelUpDao levelUpDao) {
        this.levelUpDao = levelUpDao;
    }

    public LevelUp saveLevelUp(LevelUp levelUp) {
        return levelUpDao.createLevelUp(levelUp);
    }

    public LevelUp findLevelUpByLvlId(int id) {
        LevelUp levelUp = levelUpDao.getLevelUpByLvlUpId(id);
        if (levelUp == null) {
            throw new NotFoundException("no levelup with that id");
        }
        return levelUp;
    }

    public void updateLevelUp(LevelUp levelUp) {
        levelUpDao.updateLevelUp(levelUp);
    }

    public void deleteLevelUp(int id) {
        levelUpDao.deleteLevelUp(id);
    }

    public List<LevelUp> getAllLevelUps() {
        return levelUpDao.getAllLevelUps();
    }

    public LevelUp getLevelUpByCustomerId(int customerId) {
        LevelUp levelUp = levelUpDao.getLevelUpByCustomerId(customerId);
        if (levelUp == null) {
            throw new NotFoundException("no levelup with that id");
        }
        return levelUp;
    }

    public void addPointsByCustomerId(int points, int customerId) {
        levelUpDao.addPointsByCustomerId(points, customerId);
    }

}
