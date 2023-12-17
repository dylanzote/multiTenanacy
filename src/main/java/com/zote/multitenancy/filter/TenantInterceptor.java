package com.zote.multitenancy.filter;

import com.zote.multitenancy.model.TenantContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import jakarta.servlet.*;


import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class TenantInterceptor implements Filter {

    private static final String TENANT_HEADER = "X-TenantID";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String tenantId = req.getHeader(TENANT_HEADER);

        if (Objects.nonNull(tenantId) && !tenantId.isEmpty()) {
            TenantContext.setTenantId(tenantId);
            log.info("Tenant header found {}", tenantId);
        } else {
            log.error("Tenant header not found.");
            throw new RuntimeException("Tenant header not found.");
        }

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            TenantContext.clear();
        }
    }
}
