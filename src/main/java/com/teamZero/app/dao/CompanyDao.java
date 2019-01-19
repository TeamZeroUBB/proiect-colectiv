package com.teamZero.app.dao;

import com.teamZero.app.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CompanyDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CompanyDao(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //GET -----------------------------------------------------------------------------------------------------

    public List<Company> getAll() {
        return jdbcTemplate.query("SELECT * FROM company", new CompanyDao.CompanyRowMapper());
    }

    public Company getOneByCompanyId(Long companyId){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("companyId", companyId);

        return jdbcTemplate.queryForObject("SELECT * FROM company WHERE Company_pk = :companyId", parameters, new CompanyDao.CompanyRowMapper());
    }
    
    public Company getOneByUserId(Long userId){
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);

        return jdbcTemplate.queryForObject("SELECT * FROM company WHERE app_user_pk = :userId", parameters, new CompanyDao.CompanyRowMapper());

    }

    //ADD --------------------------------------------------------------------------------------------------

    public void add(Company company){

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("name", company.getName());
        parameters.put("description", company.getDescription());
        parameters.put("email", company.getEmail());
        parameters.put("phoneNumber", company.getPhoneNumber());
        parameters.put("userId", company.getUserId());

        jdbcTemplate.update("INSERT INTO company VALUES (:name, :description, :email, :phoneNumber, :phoneNumber, :userId)", parameters);

    }

    //UPDATE --------------------------------------------------------------------------------------------

    public void update(Company company){

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("companyId", company.getCompanyId());
        parameters.put("name", company.getName());
        parameters.put("description", company.getDescription());
        parameters.put("email", company.getEmail());
        parameters.put("phoneNumber", company.getPhoneNumber());
        parameters.put("userId", company.getUserId());

        jdbcTemplate.update("UPDATE company SET name = :name, description = :description, email = :email, phone_number = :phoneNumber, app_user_pk = :userId WHERE Company_pk = :companyId", parameters);
    }

    //REMOVE ----------------------------------------------------------------------------------------------------


    public void delete(Long companyId){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("companyId", companyId);

        jdbcTemplate.update("DELETE FROM company WHERE Company_pk = :companyId", parameters);

    }

    public void deleteByUserId(Long userId){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);

        jdbcTemplate.update("DELETE FROM company WHERE app_user_pk = :userId", parameters);

    }


    //MAPPER--------------------------------------------------------------------------------------------

    class CompanyRowMapper implements RowMapper<Company> {

        @Override
        public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
            Company company = new Company();

            company.setCompanyId(rs.getLong("Company_pk"));
            company.setDescription(rs.getString("description"));
            company.setName(rs.getString("name"));
            company.setEmail(rs.getString("email"));
            company.setPhoneNumber(rs.getString("phone_number"));
            company.setUserId(rs.getLong("app_user_pk"));
            
            return company;
        }
    }
    
    
}
