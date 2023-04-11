package com.pbs.aplikacja.service;

import com.pbs.aplikacja.model.Zadanie;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class ZadanieServiceImpl implements ZadanieService {

    private static final Logger logger = LoggerFactory.getLogger(ZadanieServiceImpl.class);

    @Value("${rest.server.url}")
    private String serverUrl;

    private static final String RESOURCE_PATH = "/api/zadanie";

    private RestTemplate restTemplate;

    @Autowired
    public ZadanieServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String getResourcePath(Integer id) {
        return RESOURCE_PATH + "/" + id;
    }

    private String getResourcePath() {
        return RESOURCE_PATH;
    }

    private String getUriStringComponent(Integer id) {
        return serverUrl + getResourcePath(id);
    }

    private String getUriStringComponent() {
        return serverUrl + getResourcePath();
    }

    // TODO Zmienić metodę getPage
    private Page<Zadanie> getPage(URI uri, RestTemplate restTemplate) {
        return ServiceUtil.getPage(uri, restTemplate, new ParameterizedTypeReference<RestResponsePage<Zadanie>>() {});
    }

    @Override
    public Optional<Zadanie> getZadanie(Integer zadanieId) {
        URI uri = ServiceUtil.getUriComponent(serverUrl, getResourcePath(zadanieId))
                .build()
                .toUri();
        logger.info("REQUEST -> GET {}", uri);
        return Optional.ofNullable(restTemplate.getForObject(uri, Zadanie.class));
    }

    @Override
    public Zadanie setZadanie(Zadanie zadanie) {
        if (zadanie.getZadanieId() != null) {
            String url = getUriStringComponent(zadanie.getZadanieId());
            logger.info("REQUEST -> PUT {}", url);
            restTemplate.put(url, zadanie);
            return zadanie;
        } else {
            HttpEntity<Zadanie> request = new HttpEntity<>(zadanie);
            String url = getUriStringComponent();
            logger.info("REQUEST -> POST {}", url);
            URI location  =restTemplate.postForLocation(url, request);
            logger.info("REQUEST (location) -> GET {}", location);
            return restTemplate.getForObject(location, Zadanie.class);
        }
    }

    @Override
    public void deleteZadanie(Integer zadanieId) {
        URI url = ServiceUtil.getUriComponent(serverUrl, getResourcePath(zadanieId))
                .build()
                .toUri();
        logger.info("REQUEST -> DELETE {}", url);
        restTemplate.delete(url);

    }

    @Override
    public Page<Zadanie> getZadania(Pageable pageable) {
        URI url = ServiceUtil.getURI(serverUrl, getResourcePath(), pageable);
        logger.info("REQUEST -> GET {}", url);
        return getPage(url, restTemplate);
    }

    @Override
    public Page<Zadanie> searchByNazwa(String nazwa, Pageable pageable) {
        URI url = ServiceUtil.getUriComponent(serverUrl, getResourcePath(), pageable)
                .queryParam("nazwisko", nazwa)
                .build()
                .toUri();
        logger.info("REQUEST -> GET {}", url);
        return getPage(url, restTemplate);
    }

    @Override
    public List<Zadanie> getZadania() {
        return null;
    }
}
