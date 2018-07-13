package io.pivotal;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {
    @Bean
    public DataSource dataSource() throws IOException {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.url(EnvParser.getInstance().getUrl());
            dataSourceBuilder.username(EnvParser.getInstance().getUsername());
            dataSourceBuilder.password(EnvParser.getInstance().getPasssword());
            return dataSourceBuilder.build();   
    }
}
