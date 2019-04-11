package com.bespalov.shop.model;

import com.bespalov.shop.repository.ShopStoreRepository;
import com.bespalov.shop.validates.repository.ValidatorAnnotation;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Shop implements ShopStoreRepository {
    @JsonProperty(value = "shopId")
    private IntegerProperty shopId;
    @JsonProperty(value = "address")
    private StringProperty address;
    @JsonProperty(value = "titleShop")
    private StringProperty titleShop;

    public Shop(Integer shopId, String address, String titleShop) {
        this.shopId = new SimpleIntegerProperty(shopId);
        this.address = new SimpleStringProperty(address);
        this.titleShop = new SimpleStringProperty(titleShop);
    }

    public Shop() {
    }

    public int getShopId() {
        return shopId.get();
    }

    public IntegerProperty shopIdProperty() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId.set(shopId);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getTitleShop() {
        return titleShop.get();
    }

    public StringProperty titleShopProperty() {
        return titleShop;
    }

    public void setTitleShop(String titleShop) {
        this.titleShop.set(titleShop);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", address='" + address + '\'' +
                ", titleShop='" + titleShop + '\'' +
                '}';
    }
}
