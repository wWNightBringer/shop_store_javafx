package com.bespalov.shop.model;

import com.sun.istack.internal.NotNull;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.Date;

public class Product {
    @NotNull
    private StringProperty title;
    @NotNull
    private ObjectProperty<LocalDate> incomingDate;
    @NotNull
    private StringProperty serialNumber;
    @NotNull
    private StringProperty count;
    @NotNull
    private StringProperty condition;

    public Product() {
        this("", LocalDate.now(), null, 0, null);
    }

    public Product(String title, LocalDate incomingDate, String serialNumber, int count, String condition) {
        this.title = new SimpleStringProperty(title);
        this.incomingDate = new SimpleObjectProperty<>(incomingDate);
        this.serialNumber = new SimpleStringProperty(serialNumber);
        this.count = new SimpleStringProperty(String.valueOf(count));
        this.condition = new SimpleStringProperty(condition);
    }


    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public LocalDate getIncomingDate() {
        return incomingDate.get();
    }

    public ObjectProperty<LocalDate> incomingDateProperty() {
        return incomingDate;
    }

    public void setIncomingDate(LocalDate incomingDate) {
        this.incomingDate.set(incomingDate);
    }

    public String getSerialNumber() {
        return serialNumber.get();
    }

    public StringProperty serialNumberProperty() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber.set(serialNumber);
    }

    public String getCount() {
        return count.get();
    }

    public StringProperty countProperty() {
        return count;
    }

    public void setCount(String count) {
        this.count.set(count);
    }

    public String getCondition() {
        return condition.get();
    }

    public StringProperty conditionProperty() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition.set(condition);
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
