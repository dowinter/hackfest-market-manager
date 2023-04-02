package de.viada.hackfest.market.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MarketRepository implements PanacheRepositoryBase<MarketEntity, UUID> {
}
