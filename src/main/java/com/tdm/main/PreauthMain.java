package com.preauth.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.preauth.configuration.BeanRegistry;  

@SpringBootApplication
@Configuration
@ComponentScan({
	"com.preauth",
	"com.preauth.shared",
	"com.preauth.shared.model",
	"com.preauth.repository", 
	"com.preauth.configuration"})

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class PreauthMain extends SpringBootServletInitializer {
	
	private static ApplicationContext applicationContext;
	private static Class<PreauthMain> applicationClass = PreauthMain.class;
	
	@Autowired
	private static BeanRegistry beanRegistry;
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    
    
	public static void main(String[] args) {
//		try {
//			Class.forName("net.sourceforge.jtds.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			System.out.println(e.getMessage());
//		}
//		
		
		
		setApplicationContext(SpringApplication.run(PreauthMain.class, args));
		
		
		
		/*try {	
		beanRegistry.LoadScenarioList();
		}catch(Exception e) {
			System.out.println("PreauthMain.main()" + e);
			}
		beanRegistry.Register();*/
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		PreauthMain.applicationContext = applicationContext;
	}	
	
	
}
