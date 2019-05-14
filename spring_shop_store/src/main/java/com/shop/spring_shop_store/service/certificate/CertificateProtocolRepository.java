package com.shop.spring_shop_store.service.certificate;


import com.shop.spring_shop_store.model.Certificate;

public interface CertificateProtocolRepository {

    Object getCertificateProtocol(Certificate certificate) throws NoSuchMethodException;

}
