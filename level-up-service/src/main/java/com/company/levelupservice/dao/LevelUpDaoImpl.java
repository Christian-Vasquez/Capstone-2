package com.company.levelupservice.dao;

import com.company.levelupservice.model.LevelUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LevelUpDaoImpl implements LevelUpDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_LEVEL_UP_SQL =
            "insert into level_up (customer_id, points, member_date) values (?, ?, ?)";

    private static final String SELECT_LEVEL_UP_SQL =
            "select * from level_up where level_up_id = ?";

    private static final String GET_ALL_LEVEL_UP_SQL =
            "select * from level_up";

    private static final String UPDATE_LEVEL_UP_SQL =
            "update level_up set customer_id = ?, points = ?, member_date = ? where level_up_id = ?";

    private static final String DELETE_LEVEL_UP_SQL =
            "delete from level_up where level_up_id = ?";

    private static final String LAST_INSERT_ID =
            "select last_insert_id()";

    private static final String GET_LEVEL_UP_BY_CUSTOMER =
            "select * from level_up where customer_id = ?";

    private static final String UPDATE_POINTS_SQL =
            "update level_up set points = ? where customer_id = ?";

    private static final String GET_POINTS_SQL =
            "select points from level_up where customer_id = ?";


    @Autowired
    public LevelUpDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public LevelUp createLevelUp(LevelUp lvlUp) {
        jdbcTemplate.update(
                INSERT_LEVEL_UP_SQL,
                lvlUp.getCustomerId(),
                lvlUp.getPoints(),
                lvlUp.getMemberDate()
        );

        int id = jdbcTemplate.queryForObject(LAST_INSERT_ID, Integer.class);

        lvlUp.setLevelUpId(id);

        return lvlUp;
    }


    @Override
    public LevelUp getLevelUpByLvlUpId(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_LEVEL_UP_SQL, this::mapRowToLevelUp, id);
        } catch (EmptyResultDataAccessException e) {
//            throw new NotFoundException("no levelup with that id");
            return null;
        }
    }

    @Override
    public List<LevelUp> getAllLevelUps() {
        return jdbcTemplate.query(GET_ALL_LEVEL_UP_SQL, this::mapRowToLevelUp);
    }

    @Override
    public void updateLevelUp(LevelUp levelUp) {
        jdbcTemplate.update(
                UPDATE_LEVEL_UP_SQL,
                levelUp.getCustomerId(),
                levelUp.getPoints(),
                levelUp.getMemberDate(),
                levelUp.getLevelUpId()
        );
    }

    @Override
    public void deleteLevelUp(int id) {
        jdbcTemplate.update(DELETE_LEVEL_UP_SQL, id);
    }

    @Override
    public LevelUp getLevelUpByCustomerId(int customerId) {
        try {
            return jdbcTemplate.queryForObject(GET_LEVEL_UP_BY_CUSTOMER, this::mapRowToLevelUp, customerId);
        } catch (EmptyResultDataAccessException e) {
//            throw new NotFoundException("no levelup with that id");
            return null;
        }
    }

//    @Override
//    public int getPointsByCustomerId(int customerId) {
//        try {
//            return jdbcTemplate.queryForObject(GET_POINTS_SQL, customerId);
//        } catch (EmptyResultDataAccessException e) {
//            throw new NotFoundException("no levelup with that id");
//        }
//    }

    @Override
    public void addPointsByCustomerId(int points, int customerId) {
        int customerPoints = getLevelUpByCustomerId(customerId).getPoints() + points;
        jdbcTemplate.update(UPDATE_POINTS_SQL, customerPoints, customerId);
    }

    private LevelUp mapRowToLevelUp(ResultSet rs, int rowNum) throws SQLException {
        LevelUp levelUp = new LevelUp();
        levelUp.setLevelUpId(rs.getInt("level_up_id"));
        levelUp.setCustomerId(rs.getInt("customer_id"));
        levelUp.setPoints(rs.getInt("points"));
        levelUp.setMemberDate(rs.getDate("member_date").toLocalDate());

        return levelUp;
    }

}
