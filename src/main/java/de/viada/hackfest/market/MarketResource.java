package de.viada.hackfest.market;

import de.viada.hackfest.market.model.CreateMarketRequest;
import de.viada.hackfest.market.repository.MarketEntity;
import de.viada.hackfest.market.repository.MarketRepository;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/markets")
public class MarketResource {

    @Inject
    MarketRepository marketRepository;

    @GET
    public List<MarketEntity> getAllMarkets() {
        return marketRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{marketId}")
    public MarketEntity getMarket(@PathParam("marketId") UUID marketId) {
        return marketRepository.findById(marketId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void createMarket(CreateMarketRequest createMarketRequest) {
        marketRepository.persist(new MarketEntity(createMarketRequest.id(),
                Set.of(), createMarketRequest.name()));
    }

}

