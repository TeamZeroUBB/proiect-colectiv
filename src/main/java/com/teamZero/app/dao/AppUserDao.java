package com.teamZero.app.dao;

import com.teamZero.app.domain.user.AppUser;
import com.teamZero.app.domain.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AppUserDao {


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    AppUserDao(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //GET -----------------------------------------------------------------------------------------------------


    public List<AppUser> getAll() {
        return jdbcTemplate.query("SELECT * FROM App_User", new AppUserRowMapper());
    }


    public AppUser getOneById(Long userId){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);

        return jdbcTemplate.queryForObject("SELECT * FROM app_user WHERE app_user_pk = :userId" , parameters, new AppUserRowMapper());
    }

    public AppUser getOneByEmail(String email){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", email);

        return jdbcTemplate.queryForObject("SELECT * FROM app_user WHERE email = :email" , parameters, new AppUserRowMapper());
    }

    public AppUser getOneByUsername(String username){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", username);

        return jdbcTemplate.queryForObject("SELECT * FROM app_user WHERE username = :username" , parameters, new AppUserRowMapper());
    }

    public AppUser add(AppUser user){

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        KeyHolder keyHolder = new GeneratedKeyHolder();

        parameters.addValue("username", user.getUsername());
        parameters.addValue("password", user.getPassword());
        parameters.addValue("email", user.getEmail());
        parameters.addValue("firstName", user.getFirstName());
        parameters.addValue("lastName", user.getLastName());
        parameters.addValue("city", user.getCity());
        parameters.addValue("companyId", user.getCompanyId());
        parameters.addValue("phoneNumber", user.getPhoneNumber());

        parameters.addValue("cv", user.getCv());

        jdbcTemplate.update("INSERT INTO app_user VALUES (" +
                ":username, " +
                ":email, :firstName, :lastName, " +
                ":city, " +
                ":phoneNumber, :companyId, :password, cv)",
                parameters, keyHolder);

        user.setId((Long) keyHolder.getKey());

        return user;

    }


    //WARNING - this method does not update the CV. use addCvToUser(userId, cv) instead
    public void update(AppUser user){


        Map<String, Object> parameters = new HashMap<>();

        parameters.put("userId", user.getId());
        parameters.put("username", user.getUsername());
        parameters.put("password", user.getPassword());
        parameters.put("email", user.getEmail());
        parameters.put("firstName", user.getFirstName());
        parameters.put("lastName", user.getLastName());
        parameters.put("city", user.getCity());
        parameters.put("companyId", user.getCompanyId());
        parameters.put("phoneNumber", user.getPhoneNumber());

        jdbcTemplate.update("UPDATE app_user SET " +
                        "username = :username, password = :password, " +
                        "email  = :email,  first_name = :firstName, last_name = :lastName, " +
                        "city   = :city,   first_name = :address, " +
                        "phone_number = :phoneNumber, company_pk = :companyId " +
                        "WHERE app_user_pk = :userId",
                parameters);

    }

    public void addCvToUser(Long userId, byte[] cv){

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("userId", userId);
        parameters.put("cv", cv);

        jdbcTemplate.update(
                "UPDATE app_user SET " +
                        " cv = :cv " +
                        "WHERE app_user_pk = :userId",
                parameters);
    }

    public void delete(Long userId){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);

        jdbcTemplate.update("DELETE FROM app_user WHERE app_user_pk = :userId", parameters);

    }


    //MAPPER--------------------------------------------------------------------------------------------

    class AppUserRowMapper implements RowMapper<AppUser> {

        @Override
        public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {

            AppUser user = new AppUser();

            user.setId(rs.getLong("app_user_pk"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setCity(rs.getString("city"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setCompanyId(rs.getLong("company_pk"));


            return user;

        }
    }


    public Optional<Long> login(String username, String password){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", username);
        parameters.put("password", password);

        try {

            AppUser appUser = jdbcTemplate.queryForObject("SELECT * FROM app_user WHERE username = :username AND password = :password", parameters, new AppUserRowMapper());

            assert appUser != null;
            return Optional.of(appUser.getId());

        }catch (Exception e){
            return Optional.empty();
        }

    }

}
