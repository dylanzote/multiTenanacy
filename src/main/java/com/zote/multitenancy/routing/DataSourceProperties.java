package com.zote.multitenancy.routing;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "tenants")
public class DataSourceProperties {

    private Map<Object, Object> dataSources = new LinkedHashMap<>();

    public Map<Object, Object> getDataSources() {
        return dataSources;
    }

    public void setDataSources(Map<String, Map<String, String>> dataSources) {
        log.info("adding data source to map with data {}", dataSources);
        dataSources.forEach((key, value) -> this.dataSources.put(key, convert(value)));
    }

    public DataSource convert(Map<String, String> source) {
        log.info("creating dataSource from {}", source);
        return DataSourceBuilder.create()
                .url(source.get("jdbcUrl"))
                .driverClassName(source.get("driverClassName"))
                .username(source.get("username"))
                .password(source.get("password"))
                .build();
    }
}
