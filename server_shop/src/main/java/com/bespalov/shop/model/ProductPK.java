package com.bespalov.shop.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ProductPK implements Serializable {
    private int idProduct;
    private int shopId;

    @Column(name = "ID_Product", nullable = false)
    @Id
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Column(name = "Shop_id", nullable = false)
    @Id
    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        ProductPK productPK = (ProductPK) object;

        if (idProduct != productPK.idProduct) return false;
        if (shopId != productPK.shopId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProduct;
        result = 31 * result + shopId;
        return result;
    }
}
