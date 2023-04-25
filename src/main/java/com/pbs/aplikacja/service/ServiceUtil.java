package com.pbs.aplikacja.service;

import com.pbs.aplikacja.model.Projekt;
import com.pbs.aplikacja.model.Student;
import com.pbs.aplikacja.model.Zadanie;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class ServiceUtil {

    public static RestResponsePage<Projekt> getPageProjekt(URI uri, RestTemplate restTemplate,
                                                  ParameterizedTypeReference<RestResponsePage<Projekt>> responseType) {
        ResponseEntity<RestResponsePage<Projekt>> result = restTemplate.exchange(uri, HttpMethod.GET, null,
                responseType);
        return result.getBody();
    }

    public static RestResponsePage<Student> getPageStudent(URI uri, RestTemplate restTemplate,
                                              ParameterizedTypeReference<RestResponsePage<Student>> responseType) {
        ResponseEntity<RestResponsePage<Student>> result = restTemplate.exchange(uri, HttpMethod.GET, null,
                responseType);
        return result.getBody();
    }

    public static RestResponsePage<Zadanie> getPageZadanie(URI uri, RestTemplate restTemplate,
                                                     ParameterizedTypeReference<RestResponsePage<Zadanie>> responseType) {
        ResponseEntity<RestResponsePage<Zadanie>> result = restTemplate.exchange(uri, HttpMethod.GET, null,
                responseType);
        return result.getBody();
    }

    public static URI getURI(String serverUrl, String resourcePath, Pageable pageable) {
        return getUriComponent(serverUrl, resourcePath)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("sort", ServiceUtil.getSortParams(pageable.getSort())).build().toUri();
    }
    public static UriComponentsBuilder getUriComponent(String serverUrl, String resourcePath, Pageable
            pageable) {
        return getUriComponent(serverUrl, resourcePath)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("sort", ServiceUtil.getSortParams(pageable.getSort()));
    }
    public static UriComponentsBuilder getUriComponent(String serverUrl, String resourcePath) {
        return UriComponentsBuilder.fromUriString(serverUrl).path(resourcePath);
    }
    public static String getSortParams(Sort sort) {
        StringBuilder builder = new StringBuilder();
        if (sort != null) {
            String sep = "";
            for (Sort.Order order : sort) {
                builder.append(sep).append(order.getProperty()).append(",").append(order.getDirection());
                sep = "&sort=";
            }
        }
        return builder.toString();
    }
}
