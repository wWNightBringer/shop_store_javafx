package com.bespalov.shop.model;

import com.bespalov.shop.repository.ShopStoreRepository;
import com.bespalov.shop.util.LocalDateAdapter;
import com.bespalov.shop.validates.repository.ValidatorAnnotation;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlType(name = "oneProduct")
public class Product implements ShopStoreRepository {
    @JsonProperty(value = "title")
    private StringProperty title;
    @JsonProperty(value = "incomingDate")
    private ObjectProperty<LocalDate> incomingDate;
    @JsonProperty(value = "id")
    private int idProduct;
    @JsonProperty(value = "shopId")
    private int shopId;
    @JsonProperty(value = "serialNumber")
    private StringProperty serialNumber;
    @JsonProperty(value = "count")
    private StringProperty count;
    @JsonProperty(value = "condition")
    private StringProperty condition;

    public Product() {
        this(0, "", LocalDate.now(), null, 0, null);
    }

    public Product(int idProduct, String title, LocalDate incomingDate, String serialNumber, int count, String condition) {
        this.idProduct = idProduct;
        this.title = new SimpleStringProperty(title);
        this.incomingDate = new SimpleObjectProperty<>(incomingDate);
        this.serialNumber = new SimpleStringProperty(serialNumber);
        this.count = new SimpleStringProperty(String.valueOf(count));
        this.condition = new SimpleStringProperty(condition);
    }

    @XmlElement(name = "title")
    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    @XmlElement(name = "incomingDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getIncomingDate() {
        return incomingDate.get();
    }

    public ObjectProperty<LocalDate> incomingDateProperty() {
        return incomingDate;
    }


    public void setIncomingDate(LocalDate incomingDate) {
        this.incomingDate.set(incomingDate);
    }

    @XmlElement(name = "serialNumber")
    public String getSerialNumber() {
        return serialNumber.get();
    }

    public StringProperty serialNumberProperty() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber.set(serialNumber);
    }

    @XmlElement(name = "count")
    public String getCount() {
        return count.get();
    }

    public StringProperty countProperty() {
        return count;
    }


    public void setCount(String count) {
        if (Integer.parseInt(count) > 0 && Integer.parseInt(count) < 10000) {
            this.count.set(count);
        } else
            this.count = null;

    }

    @XmlElement(name = "condition")
    public String getCondition() {
        return condition.get();
    }

    public StringProperty conditionProperty() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition.set(condition);
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", incomingDate=" + incomingDate +
                ", serialNumber='" + serialNumber + '\'' +
                ", count=" + count +
                ", Condition='" + condition + '\'' +
                '}';
    }

}
