package com.company.levelupservice.service;

import com.company.levelupservice.dao.LevelUpDao;
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
        return levelUpDao.getLevelUpByLvlUpId(id);
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
        return levelUpDao.getLevelUpByCustomerId(customerId);
    }

    public void addPointsByCustomerId(int points, int customerId) {
        levelUpDao.addPointsByCustomerId(points, customerId);
    }

}
