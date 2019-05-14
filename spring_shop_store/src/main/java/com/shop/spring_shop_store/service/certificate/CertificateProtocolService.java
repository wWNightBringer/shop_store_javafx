package com.shop.spring_shop_store.service.certificate;

import com.shop.spring_shop_store.dao.ProtocolDAOImpl;
import com.shop.spring_shop_store.model.Certificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateProtocolService implements CertificateProtocolRepository {
    private Logger logger = LoggerFactory.getLogger(CertificateProtocolService.class);
    private ProtocolDAOImpl certificateProtocolDAO;

    @Autowired
    public CertificateProtocolService(ProtocolDAOImpl certificateProtocolDAO) {
        this.certificateProtocolDAO = certificateProtocolDAO;
    }

    @Override
    public Object getCertificateProtocol(Certificate certificate) throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("getCertificateProtocol", Certificate.class).getName()));
        logger.info(String.format("Request %s", certificate));
        Object object = certificateProtocolDAO.getCertificateProtocol(certificate.getCertificateProtocol());
        logger.info(String.format("Response %s", object));
        return object;
    }
}
