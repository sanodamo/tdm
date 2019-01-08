package com.preauth.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseRepository {

	@Autowired  
	@Qualifier("preAuthJdbc")
    protected JdbcTemplate jdbc;    
	
	@Autowired  
	@Qualifier("preAuthAnthemMemberJdbc")
    protected JdbcTemplate AnthemJdbc; 
	
	@Autowired  
	@Qualifier("preAuthAimMemberJdbc")
    protected JdbcTemplate AimJdbc; 
}
