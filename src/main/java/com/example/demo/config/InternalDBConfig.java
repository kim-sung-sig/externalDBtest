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
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages = "com.example.demo.dao", sqlSessionFactoryRef = "internalSqlSessionFactory")
@EnableTransactionManagement
public class InternalDBConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Primary
    @Bean(name = "internalDataSource")
    DataSource internalDataSource() {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }

    @Primary
    @Bean(name = "internalSqlSessionFactory")
    SqlSessionFactory internalSqlSessionFactory(@Qualifier("internalDataSource") DataSource internalDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(internalDataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mappers/**/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

}
