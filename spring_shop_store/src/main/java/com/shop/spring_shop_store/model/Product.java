package com.shop.spring_shop_store.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    private int id;
    private String title;
    private Date incomingDate;
    private String serialNumber;
    private int shopId;
    private int count;
    private String condition;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "Incoming_date")
    public Date getIncomingDate() {
        return incomingDate;
    }

    public void setIncomingDate(Date incomingDate) {
        this.incomingDate = incomingDate;
    }

    @Basic
    @Column(name = "Serial_number")
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Basic
    @Column(name = "Shop_Id")
    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    @Basic
    @Column(name = "Count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Basic
    @Column(name = "Condition")
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (shopId != product.shopId) return false;
        if (count != product.count) return false;
        if (title != null ? !title.equals(product.title) : product.title != null) return false;
        if (incomingDate != null ? !incomingDate.equals(product.incomingDate) : product.incomingDate != null)
            return false;
        if (serialNumber != null ? !serialNumber.equals(product.serialNumber) : product.serialNumber != null)
            return false;
        if (condition != null ? !condition.equals(product.condition) : product.condition != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (incomingDate != null ? incomingDate.hashCode() : 0);
        result = 31 * result + (serialNumber != null ? serialNumber.hashCode() : 0);
        result = 31 * result + shopId;
        result = 31 * result + count;
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", incomingDate=" + incomingDate +
                ", serialNumber='" + serialNumber + '\'' +
                ", shopId=" + shopId +
                ", count=" + count +
                ", condition='" + condition + '\'' +
                '}';
    }
}
