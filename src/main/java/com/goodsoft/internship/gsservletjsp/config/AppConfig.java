package com.goodsoft.internship.gsservletjsp.config;

import com.goodsoft.internship.gsservletjsp.converter.UserDtoToUserConverter;
import com.goodsoft.internship.gsservletjsp.converter.UserToUserDtoConverter;
import com.goodsoft.internship.gsservletjsp.dao.UserDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@EnableWebMvc
@Configuration
@ComponentScan("com.goodsoft.internship.gsservletjsp")
public class AppConfig implements WebMvcConfigurer  {

    @Bean
    public ConversionService conversionService() {
        var conversionService = new ApplicationConversionService();
        conversionService.addConverter(new UserDtoToUserConverter());
        conversionService.addConverter(new UserToUserDtoConverter());
        return conversionService;
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/postgres");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("postgres");
        return dataSourceBuilder.build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.addMapper(UserDao.class);
        factoryBean.setDataSource(dataSource());
        factoryBean.setConfiguration(configuration);
        return factoryBean.getObject();
    }

    @Bean
    public UserDao userMapper() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sqlSessionTemplate.getMapper(UserDao.class);
    }
}
