package com.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ActorDAO {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public ActorDTO getActor(Integer id) {
	      String SQL = "select * from Actor where actor_id = ?";
	      ActorDTO actor = jdbcTemplate.queryForObject(SQL, new Object[]{id}, new ActorMapper());
	      return actor;
	   }
	
	public int updateActor(ActorDTO dto){
		String updateSQL = "Update Actor set first_name = \" " +  dto.getFirstName() + " \" where actor_id = " + dto.getActorId();
		int result = jdbcTemplate.update(updateSQL);
		return result;
	}
	
	
	public int insertActor(ActorDTO actor){
		String insertSQL = " INSERT INTO actor (actor_id,first_name, last_name, last_update) " + 
				"	VALUES (:actorid, :firstName, :lastName, CURRENT_TIMESTAMP)";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("actorid", actor.getActorId());
		paramMap.put("firstName", actor.getFirstName());
		paramMap.put("lastName", actor.getLastName());
		
		int rowsAffected = namedJdbcTemplate.update(insertSQL, paramMap);
		
		return rowsAffected;
		
		
		
	}

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
		return namedJdbcTemplate;
	}

	public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
	}
	
}
