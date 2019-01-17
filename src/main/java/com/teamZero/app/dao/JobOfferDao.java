package com.teamZero.app.dao;

import com.teamZero.app.domain.job.JobOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JobOfferDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    JobOfferDao(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    //POSTED OR SAVED JOB OFFERS ------------------------------------------------------------------------------------------------------------

    public List<JobOffer> getJobOffersPostedByCompany(Long companyId){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("companyId", companyId);

        return jdbcTemplate.query("SELECT * FROM job_offer WHERE company_pk = :companyId", parameters, new JobOfferRowMapper());
    }

    public List<JobOffer> getJobOffersPostedByUser(Long userId){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);

        return jdbcTemplate.query("SELECT * FROM job_offer WHERE app_user_pk = :userId", parameters, new JobOfferRowMapper());
    }


    public List<JobOffer> getPagedJobOffers(int start, int limit){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("start", start);
        parameters.put("limit", limit);

        return jdbcTemplate.query("SELECT * FROM job_offer ORDER BY created_timestamp DESC OFFSET :start ROWS FETCH FIRST :limit ROWS ONLY",
                parameters, new JobOfferRowMapper());

    }

    //CRUD -------------------------------------------------------------------------------------------------------------------------------------------

    public List<JobOffer> getAll() {
        return jdbcTemplate.query("SELECT * FROM job_offer", new JobOfferRowMapper());
    }

    public JobOffer getOneById(Long jobOfferId){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("jobOfferId", jobOfferId);

        return jdbcTemplate.queryForObject("SELECT * FROM job_offer WHERE job_offer_pk = :jobOfferId", parameters, new JobOfferRowMapper());
    }


    public JobOffer add(JobOffer jobOffer){


        MapSqlParameterSource parameters = new MapSqlParameterSource();

        KeyHolder keyHolder = new GeneratedKeyHolder();


        parameters.addValue("title", jobOffer.getTitle());
        parameters.addValue("description", jobOffer.getDescription());
        parameters.addValue("userId", jobOffer.getUserId());
        parameters.addValue("companyId", jobOffer.getCompanyId());
        parameters.addValue("email", jobOffer.getEmail());
        parameters.addValue("phoneNumber", jobOffer.getPhoneNumber());
        parameters.addValue("address", jobOffer.getAddress());
        parameters.addValue("city", jobOffer.getCity());
        parameters.addValue("type", jobOffer.getType());
        parameters.addValue("numberOfApplications", jobOffer.getNumberOfApplications());

        parameters.addValue("createdTimestamp", new Timestamp(System.nanoTime()));

        jdbcTemplate.update("INSERT INTO job_offer VALUES(" +
                ":title, :description, :userId, :companyId, " +
                ":email, :phoneNumber, :address, " +
                ":city, :type, :numberOfApplications, :createdTimestamp" +
                ")", parameters, keyHolder);

        jobOffer.setJobOfferId((Long) keyHolder.getKey());

        return jobOffer;
    }

    public void update(JobOffer jobOffer){

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("jobOfferId", jobOffer.getJobOfferId());
        parameters.put("title", jobOffer.getTitle());
        parameters.put("description", jobOffer.getDescription());
        parameters.put("userId", jobOffer.getUserId());
        parameters.put("companyId", jobOffer.getCompanyId());
        parameters.put("email", jobOffer.getEmail());
        parameters.put("phoneNumber", jobOffer.getPhoneNumber());
        parameters.put("address", jobOffer.getAddress());
        parameters.put("city", jobOffer.getCity());
        parameters.put("type", jobOffer.getType());
        parameters.put("numberOfApplications", jobOffer.getNumberOfApplications());

        jdbcTemplate.update("UPDATE job_offer SET " +
                "title = :title, description = :description, company_pk = :companyId, app_user_pk = :userId" +
                "email = :email, phone_number = :phoneNumber, address = :address," +
                "city = :city, type = :type, no_of_applications = :numberOfApplications WHERE job_offer_pk = :jobOfferId", parameters);
    }

    public void delete(Long jobOfferId){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("jobOfferId", jobOfferId);

        jdbcTemplate.update("DELETE FROM job_offer WHERE job_offer_pk = :jobOfferId", parameters);

    }


    public List<JobOffer> filterJobOffers(String startDate, String endDate, String jobType) {

        boolean whereIsSet = false;

        String codition = "";

        if (!startDate.equals("NaN")) {
            whereIsSet = true;
            codition += "\n WHERE created_timestamp >= :startDate";
        }

        if (!endDate.equals("NaN")) {

            if (whereIsSet) {
                codition += "\n AND created_timestamp <= :endDate";
            } else {
                codition += "\n WHERE created_timestamp <= :endDate";
                whereIsSet = true;
            }
        }

        if (!jobType.equals("NaN")){
            if (whereIsSet) {
                codition += "\n AND jobType = :jobType";
            } else {
                codition += "\n WHERE jobType = :jobType";
                whereIsSet = true;
            }
        }

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("starDate", startDate);
        parameters.put("endDate" , endDate);
        parameters.put("jobType" , jobType);

        String query = "SELECT * FROM job_offer" + codition;

        return jdbcTemplate.query(query, parameters, new JobOfferRowMapper());

    }


    //FAVORITE JOB OFFERS----------------------------------------------------------------------------------------------------------

    public List<JobOffer> getFavoriteJobOffersForUser(Long userId){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);

        return jdbcTemplate.query(
                    "SELECT * FROM job_offer a " +
                        "INNER JOIN app_user_has_favorite_job_offer b " +
                        "ON a.job_offer_pk = b.job_offer_pk " +
                        "WHERE b.app_user_pk = :userId",
                parameters, new JobOfferRowMapper());

    }


    //REMOVE WHEN USER OR COMPANY IS DELETED

    public void deleteByUserId(Long userId){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);

        jdbcTemplate.update("DELETE FROM job_offer WHERE app_user_pk = :userId", parameters);

    }

    public void deleteByCompanyId(Long companyId){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("companyId", companyId);

        jdbcTemplate.update("DELETE FROM job_offer WHERE company_pk = :companyId", parameters);

    }

    //MAPPER------------------------------------------------------------------------------------------------------------------

    class JobOfferRowMapper implements RowMapper<JobOffer> {

        @Override
        public JobOffer mapRow(ResultSet rs, int rowNum) throws SQLException {
            JobOffer jobOffer = new JobOffer();

            jobOffer.setJobOfferId(rs.getLong("job_offer_pk"));
            jobOffer.setTitle(rs.getString("title"));
            jobOffer.setDescription(rs.getString("description"));
            jobOffer.setUserId(rs.getLong("app_user_pk"));
            jobOffer.setCompanyId(rs.getLong("company_pk"));
            jobOffer.setEmail(rs.getString("email"));
            jobOffer.setPhoneNumber(rs.getString("phone_number"));
            jobOffer.setAddress(rs.getString("address"));
            jobOffer.setCity(rs.getString("city"));
            jobOffer.setType(rs.getString("type"));
            jobOffer.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));

            return jobOffer;
        }
    }


    public List<String> getAllJobTypes(){

        List<String> jobTypeList = new ArrayList<>();

        jobTypeList.add("Software development");
        jobTypeList.add("Health");
        jobTypeList.add("Sales");
        jobTypeList.add("Entertainment");
        jobTypeList.add("HR");
        jobTypeList.add("Marketing");

        //TODO add more jobs here

        return jobTypeList;
    }





}
