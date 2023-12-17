package com.zote.multitenancy.routing.config;

import com.zote.multitenancy.routing.DataSourceProperties;
import com.zote.multitenancy.routing.TenantRoutingDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    private final DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource dataSource() {
        log.info("setting default and target dataSource");
        var dataSource = new TenantRoutingDataSource();
        dataSource.setTargetDataSources(dataSourceProperties.getDataSources());
        dataSource.setDefaultTargetDataSource(dataSourceProperties.getDataSources().get("client1"));
        return dataSource;
    }
}
