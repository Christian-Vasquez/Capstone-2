package com.company.levelupservice.dao;

import com.company.levelupservice.model.LevelUp;

import java.util.List;

public interface LevelUpDao {

    public LevelUp createLevelUp(LevelUp lvlUp);

    public LevelUp getLevelUpByLvlUpId(int id);

    public List<LevelUp> getAllLevelUps();

    public void updateLevelUp(LevelUp levelUp);

    public void deleteLevelUp(int id);

    public LevelUp getLevelUpByCustomerId(int customerId);

    public void addPointsByCustomerId(int points, int customerId);



}
