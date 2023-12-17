package com.zote.multitenancy.model;

import java.math.BigDecimal;

public record ProductRequest(String name, String description, BigDecimal price, String skuCode, int quantity) {
}
