package com.shop.spring_shop_store.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Shop {
    private int idShop;
    private String title;
    private String city;
    private String address;

    @Id
    @Column(name = "ID_Shop")
    public int getIdShop() {
        return idShop;
    }

    public void setIdShop(int idShop) {
        this.idShop = idShop;
    }

    @Basic
    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "City")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        if (idShop != shop.idShop) return false;
        if (title != null ? !title.equals(shop.title) : shop.title != null) return false;
        if (city != null ? !city.equals(shop.city) : shop.city != null) return false;
        if (address != null ? !address.equals(shop.address) : shop.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idShop;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
