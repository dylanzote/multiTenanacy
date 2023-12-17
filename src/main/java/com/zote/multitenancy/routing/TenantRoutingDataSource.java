package com.zote.multitenancy.routing;

import com.zote.multitenancy.model.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class TenantRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("querying tenant context holder to get current tenantId");
        log.info("current tenanId is {}", TenantContext.getTenantId());
        return TenantContext.getTenantId();
    }
}
