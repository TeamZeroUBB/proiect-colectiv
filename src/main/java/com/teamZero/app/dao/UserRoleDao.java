package com.teamZero.app.dao;

import com.teamZero.app.domain.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserRoleDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    UserRoleDao(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //GET -----------------------------------------------------------------------------------------------------

    public List<UserRole> getRolesForUser(Long userId){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);

        List<Integer> roleList = jdbcTemplate.queryForList("SELECT role FROM app_user_has_role WHERE app_user_pk = :userId", parameters, Integer.class);

        return roleList.stream()
                .map(x -> UserRole.values()[x])
                .collect(Collectors.toList());

    }

    //ADD -----------------------------------------------------------------------------------------------

    public void addUserRole(Long userId, UserRole role){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);
        parameters.put("role", role.getCode());

        jdbcTemplate.update("INSERT INTO app_user_has_role VALUES (:userId, :role)", parameters);
    }


    //REMOVE ----------------------------------------------------------------------------------------------------

    public void removeUserRole(Long userId, UserRole role){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);
        parameters.put("role", role.getCode());

        jdbcTemplate.update("DELETE FROM app_user_has_role WHERE app_user_pk = :userId AND role = :role", parameters);

    }

    public void removeAllRolesFromUser(Long userId){

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("userId", userId);

        jdbcTemplate.update("DELETE FROM app_user_has_role WHERE app_user_pk = :userId", parameters);
    }

}
