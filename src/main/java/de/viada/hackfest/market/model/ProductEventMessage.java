package de.viada.hackfest.market.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductEventMessage extends PanacheEntityBase {
    @Id
    String productId;
    UUID marketId;
    String productName;
    ProductCategory productCategory;
    double sensorFillData;


    public ProductEventMessage() {
    }

    public ProductEventMessage(String productId, UUID marketId, String productName, ProductCategory productCategory, double sensorFillData) {
        this.productId = productId;
        this.marketId = marketId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.sensorFillData = sensorFillData;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public UUID getMarketId() {
        return marketId;
    }

    public void setMarketId(UUID marketId) {
        this.marketId = marketId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public double getSensorFillData() {
        return sensorFillData;
    }

    public void setSensorFillData(double sensorFillData) {
        this.sensorFillData = sensorFillData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEventMessage that = (ProductEventMessage) o;
        return Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
