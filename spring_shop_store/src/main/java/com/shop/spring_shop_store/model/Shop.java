package com.shop.spring_shop_store.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Shop {
    @JsonProperty(value = "idShop")
    private int idShop;
    @JsonProperty(value = "address")
    private String address;
    @JsonProperty(value = "title")
    private String title;

    @Id
    @Column(name = "ID_Shop")
    public int getIdShop() {
        return idShop;
    }

    public void setIdShop(int idShop) {
        this.idShop = idShop;
    }

    @Basic
    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        if (idShop != shop.idShop) return false;
        if (address != null ? !address.equals(shop.address) : shop.address != null) return false;
        if (title != null ? !title.equals(shop.title) : shop.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idShop;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "idShop=" + idShop +
                ", address='" + address + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
