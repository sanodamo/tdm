package com.preauth.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="spring.datasource.preauth.aim-member")
public class AimDatabase extends DatabaseProperties  {	
}
