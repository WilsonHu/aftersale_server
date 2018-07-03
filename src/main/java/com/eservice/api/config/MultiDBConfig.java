package com.eservice.api.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MultiDBConfig {
    @Bean(name = "mysqlDb")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("mysqlDb") DataSource dsMySQL) {
        return new JdbcTemplate(dsMySQL);
    }


    //第二个数据库
    @Bean(name = "datasource_sinsim_process_db")
    @ConfigurationProperties(prefix = "spring.datasource_sinsim_db")
    public DataSource SecondDatasource() {
        return  DataSourceBuilder.create().build();
    }

    @Bean(name = "DataSourceSinsimProcessDbTemplate")
    public JdbcTemplate SecondDatasourceJdbcTemplate(@Qualifier("datasource_sinsim_process_db")
                                                     DataSource dsPostgres) {
        return new JdbcTemplate(dsPostgres);
    }
}
