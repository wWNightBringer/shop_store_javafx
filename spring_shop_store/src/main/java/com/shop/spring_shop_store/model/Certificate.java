package com.shop.spring_shop_store.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Certificate {
    private int id;
    private String certificateProtocol;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Certificate_protocol")
    public String getCertificateProtocol() {
        return certificateProtocol;
    }

    public void setCertificateProtocol(String certificateProtocol) {
        this.certificateProtocol = certificateProtocol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Certificate that = (Certificate) o;

        if (id != that.id) return false;
        if (certificateProtocol != null ? !certificateProtocol.equals(that.certificateProtocol) : that.certificateProtocol != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (certificateProtocol != null ? certificateProtocol.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", certificateProtocol='" + certificateProtocol + '\'' +
                '}';
    }
}
