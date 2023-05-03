package com.thiagowlian.MSPRODUTO.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringJdbcConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:produtosdb");
        dataSource.setUsername("sa");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("simpleJdbcInsertProduto")
    public SimpleJdbcInsert simpleJdbcInsertProduto(JdbcTemplate jdbcTemplate){
        return new SimpleJdbcInsert(jdbcTemplate).withTableName("PRODUTO");
    }
}
