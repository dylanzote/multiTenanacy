package com.zote.multitenancy.model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantContext {

    private static final ThreadLocal<String> TENANT_CONTEXT = new ThreadLocal<>();

    public static void setTenantId(String tenantId) {
        log.info("setting tenantID with id {}", tenantId);
        TENANT_CONTEXT.set(tenantId);
    }

    public static String getTenantId() {
        return TENANT_CONTEXT.get();
    }

    public static void clear() {
        TENANT_CONTEXT.remove();
    }
}
