package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages = "com.example.demo.external.dao", sqlSessionFactoryRef = "externalSqlSessionFactory")
@EnableTransactionManagement
@PropertySource("classpath:external-db.properties")
public class ExternelDBConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${external.datasource.url}")
    private String url;

    @Value("${external.datasource.username}")
    private String username;

    @Value("${external.datasource.password}")
    private String password;

    @Value("${external.datasource.driver-class-name}")
    private String driverClassName;

    @Bean(name = "externalDataSource")
    DataSource externalDataSource() {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }

    @Bean(name = "externalSqlSessionFactory")
    SqlSessionFactory externalSqlSessionFactory(@Qualifier("externalDataSource") DataSource externalDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(externalDataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mappers/external/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

}
