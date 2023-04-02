package de.viada.hackfest.market;

import de.viada.hackfest.market.model.ProductEventMessage;
import de.viada.hackfest.market.model.ProductEventResponse;
import io.smallrye.mutiny.Multi;
import java.util.UUID;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;

@RequestScoped
@Path("/products")
@ActivateRequestContext
public class ProductEventsResource {


    @Channel("processed-products")
    Multi<ProductEventMessage> productEvents;

    @GET
    @Path("/{marketId}")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<ProductEventResponse> stream(UUID marketId) {
        return productEvents
                .filter(e -> e.getMarketId().equals(marketId))
                .map(e -> new ProductEventResponse(e.getProductId(), e.getProductName(), e.getProductCategory(), e.getSensorFillData()))
                .log();
    }
}
