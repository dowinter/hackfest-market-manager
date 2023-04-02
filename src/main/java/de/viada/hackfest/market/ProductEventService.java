package de.viada.hackfest.market;

import de.viada.hackfest.market.model.ProductEventMessage;
import de.viada.hackfest.market.repository.MarketEntity;
import de.viada.hackfest.market.repository.MarketRepository;
import io.smallrye.common.annotation.Blocking;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

@ApplicationScoped
public class ProductEventService {

    @Channel("processed-products")
    @OnOverflow(OnOverflow.Strategy.DROP)
    Emitter<ProductEventMessage> processedProductsEmitter;

    @Inject
    MarketRepository marketRepository;

    @Incoming("product-events")
    @Transactional
    @Blocking
    public void processProductEvent(ProductEventMessage p) {

        ProductEventMessage productEntity = ProductEventMessage.findById(p.getProductId());

        productEntity = productEntity == null ? p : productEntity;

        if (!productEntity.isPersistent()) {
            productEntity.persist();
        }

        MarketEntity entity = marketRepository.findByIdOptional(p.getMarketId())
                .orElse(new MarketEntity(p.getMarketId(), new HashSet<>(), "Unknown market " + p.getMarketId()));

        if (!marketRepository.isPersistent(entity)) {
            marketRepository.persist(entity);
        }

        Set<ProductEventMessage> lastMessages = entity.getLastMessages();
        lastMessages.remove(productEntity);
        lastMessages.add(productEntity);

        processedProductsEmitter.send(p);
    }
}
