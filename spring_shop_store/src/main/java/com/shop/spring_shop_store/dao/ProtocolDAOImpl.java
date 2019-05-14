package com.shop.spring_shop_store.dao;

import com.shop.spring_shop_store.dao.repository.CertificateProtocolDAO;
import com.shop.spring_shop_store.model.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProtocolDAOImpl {
    @Autowired
    private CertificateProtocolDAO certificateProtocolDAO;

    public Object getCertificateProtocol(String certificateProtocol) {
        Certificate certificate = (Certificate) certificateProtocolDAO.findByCertificateProtocol(certificateProtocol);
        return certificate;
    }
}
