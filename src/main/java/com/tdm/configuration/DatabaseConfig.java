package com.preauth.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig  {
	
	@Autowired
	TransactionDatabase transactionDb;
	
	@Autowired
	AimDatabase aimDb;
	
	@Autowired
	AnthemDatabase anthemDb;
	
	
	@Primary
	@Bean(name = "preAuthDataSource")	
    public DataSource getPreAuthDataSource() {				       
        return getDataSource(transactionDb);
    }

	@Bean(name = "preAuthJdbc")	
	@Autowired
    public JdbcTemplate getPreAuthJdbcTemplate(@Qualifier("preAuthDataSource") DataSource dsPreAuth) {        
		return new JdbcTemplate(dsPreAuth);
    }
		
	@Bean(name = "preAuthAnthemMemberDataSource")	
    public DataSource getPreAuthAnthemMemberDataSource() {
		 return getDataSource(anthemDb);
    }

	@Bean(name = "preAuthAnthemMemberJdbc")
	@Autowired
	 public JdbcTemplate getPreAuthAnthemMemberJdbcTemplate(@Qualifier("preAuthAnthemMemberDataSource") DataSource dsPreAuth) {
        return new JdbcTemplate(dsPreAuth);
    }


	@Bean(name = "preAuthAimMemberDataSource")
    public DataSource getPreAuthAimMemberDataSource() {
		 return getDataSource(aimDb);
    }

	@Bean(name = "preAuthAimMemberJdbc")	
    @Autowired    
    public JdbcTemplate getPreAuthAimMemberJdbcTemplate(@Qualifier("preAuthAimMemberDataSource") DataSource dsPreAuth) {
        return new JdbcTemplate(dsPreAuth);
    }

	
	private DataSource getDataSource(DatabaseProperties prop) {
		HikariConfig c = new HikariConfig();
        c.setDriverClassName(prop.getDriverClassName());
        c.setConnectionTestQuery("SELECT GETDATE()");
        c.setMaximumPoolSize(32);
        c.setJdbcUrl(prop.getUrl());
        c.getDataSourceProperties().put("user", prop.getUsername());
        c.getDataSourceProperties().put("password", prop.getPassword());
        c.getDataSourceProperties().put("cacheMetaData", true);

        return new HikariDataSource(c);
	}
	
}