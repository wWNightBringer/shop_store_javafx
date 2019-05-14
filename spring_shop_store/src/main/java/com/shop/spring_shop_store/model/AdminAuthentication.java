package com.shop.spring_shop_store.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "admin_authentication", schema = "server")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminAuthentication {
    private int id;
    private String username;
    private String password;
    private Timestamp dateIncoming;
    private int certificateId;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "date_incoming")
    public Timestamp getDateIncoming() {
        return dateIncoming;
    }

    public void setDateIncoming(Timestamp dateIncoming) {
        this.dateIncoming = dateIncoming;
    }

    @Basic
    @Column(name = "Certificate_id")
    public int getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminAuthentication that = (AdminAuthentication) o;

        if (id != that.id) return false;
        if (certificateId != that.certificateId) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (dateIncoming != null ? !dateIncoming.equals(that.dateIncoming) : that.dateIncoming != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (dateIncoming != null ? dateIncoming.hashCode() : 0);
        result = 31 * result + certificateId;
        return result;
    }

    @Override
    public String toString() {
        return "AdminAuthentication{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateIncoming=" + dateIncoming +
                ", certificateId=" + certificateId +
                '}';
    }
}
