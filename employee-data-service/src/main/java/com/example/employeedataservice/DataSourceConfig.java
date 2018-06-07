package com.example.employeedataservice;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;


@Configuration
@Primary
public class DataSourceConfig {
	@Autowired
	private Environment env;
	
	@Autowired
	private ResourceLoader resourceLoader;
		
    @Bean(name = "dataSource")
    public DataSource getDataSource(){
        DataSource dataSource = createDataSource();
        DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
        return dataSource;
    }

    private DatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        String datasourceSchema = env.getProperty("spring.datasource.schema");
        String datasourceData = env.getProperty("spring.datasource.data");
        databasePopulator.addScript(resourceLoader.getResource(datasourceSchema));
        databasePopulator.addScript(resourceLoader.getResource(datasourceData));
        return databasePopulator;
    }

    private DriverManagerDataSource createDataSource() {
    	DriverManagerDataSource simpleDriverDataSource = new DriverManagerDataSource();
    	String datasourceUrl = env.getProperty("spring.datasource.url");
        String datasourceUsername = env.getProperty("spring.datasource.username");
        String datasourcePassword = env.getProperty("spring.datasource.password");
        String datasourceDriverClass = env.getProperty("spring.datasource.driver-class-name");
    	simpleDriverDataSource.setDriverClassName(datasourceDriverClass);
        simpleDriverDataSource.setUrl(datasourceUrl);
        simpleDriverDataSource.setUsername(datasourceUsername);
        simpleDriverDataSource.setPassword(datasourcePassword);
        return simpleDriverDataSource;      
    }
}
