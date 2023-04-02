package de.viada.hackfest.market.repository;

import de.viada.hackfest.market.model.ProductEventMessage;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MarketEntity {

    @Id
    UUID marketId;

    String marketName;

    @OneToMany(mappedBy = "marketId")
    Set<ProductEventMessage> lastMessages;

    public MarketEntity() {
    }

    public MarketEntity(UUID marketId, Set<ProductEventMessage> lastMessages, String marketName) {
        this.marketId = marketId;
        this.lastMessages = lastMessages;
        this.marketName = marketName;
    }

    public UUID getMarketId() {
        return marketId;
    }

    public void setMarketId(UUID marketId) {
        this.marketId = marketId;
    }

    public Set<ProductEventMessage> getLastMessages() {
        return lastMessages;
    }

    public void setLastMessages(Set<ProductEventMessage> lastMessages) {
        this.lastMessages = lastMessages;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarketEntity that = (MarketEntity) o;
        return Objects.equals(marketId, that.marketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marketId);
    }
}
