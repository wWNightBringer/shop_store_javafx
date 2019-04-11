package com.bespalov.shop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

public class Connect {
    private String nameOfMethod;
    private Object[] arg;
    private URL url;
    private Logger logger = LoggerFactory.getLogger(Connect.class);
    private StringReader stringReader;
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(Paths.get("shop_1/src/main/resources/config/authentication.properties").toFile());
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Connect(String nameOfMethod) throws IOException {
        this.nameOfMethod = nameOfMethod;
    }

    public void outputStream(Object object) throws IOException {
        url = new URL(String.format("http://%s:%s/%s?login=%s&password=%s", getArg()));
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url.toString());
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(object);
        httpPost.setEntity(new StringEntity(json));
        HttpResponse httpResponse = client.execute(httpPost);
        logger.info(String.valueOf(httpResponse.getStatusLine().getStatusCode()));

    }

    public StringReader inputStream(Object object) throws IOException {
        url = new URL(String.format("http://%s:%s/%s?login=%s&password=%s", getArg()));
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url.toString());
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(object);
        httpPost.setEntity(new StringEntity(json));
        HttpResponse httpResponse = client.execute(httpPost);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        String message;
        while ((message = bufferedReader.readLine()) != null) {
            stringReader = new StringReader(message);
            logger.info("Request message: " + message);
        }
        bufferedReader.close();
        return stringReader;
    }

    private Object[] getArg() throws UnknownHostException {
        return arg = new Object[]{
                Inet4Address.getLocalHost().getHostAddress(), properties.getProperty("connect.port"), nameOfMethod, properties.getProperty("connect.login")
                , properties.getProperty("connect.password")
        };
    }

}
