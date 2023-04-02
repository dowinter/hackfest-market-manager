package de.viada.hackfest.market.model;

public record ProductEventResponse(
        String productId,
        String productName,
        ProductCategory productCategory,
        double sensorFillData
) {}
