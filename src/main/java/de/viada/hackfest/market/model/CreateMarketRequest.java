package de.viada.hackfest.market.model;

import java.util.UUID;

public record CreateMarketRequest(
        UUID id,
        String name
) { }
