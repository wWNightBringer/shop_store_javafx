package com.bespalov.shop.model;

import com.bespalov.shop.annotation.ValidatorAnnotation;
import com.bespalov.shop.repository.ElementRepository;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Shop implements ElementRepository {
    private String title;
    @ValidatorAnnotation(formInputData = "[a-zA-Z]{1,30}", errorMessage = "Invalid city")
    public String city;
    private String address;
    private int idShop;

    public Shop(String title, String city, String address) {
        this.title = title;
        this.city = city;
        this.address = address;
    }

    public Shop() {
    }

    @Basic
    @Column(name = "Title", nullable = false, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "City", nullable = false, length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "Address", nullable = false, length = 45)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "title='" + title + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Id
    @Column(name = "ID_Shop", nullable = false)
    public int getIdShop() {
        return idShop;
    }

    public void setIdShop(int idShop) {
        this.idShop = idShop;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Shop shop = (Shop) object;

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
