package com.pbs.aplikacja.service;

import com.pbs.aplikacja.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Value("${rest.server.url}")
    private String serverUrl;

    private RestTemplate restTemplate;

    @Autowired
    public StudentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final static String RESOURCE_PATH = "/api/projekty";
    @Override
    public Optional<Student> getStudent(Integer studentId) {
        URI url = ServiceUtil.getUriComponent(serverUrl, getResourcePath(studentId))
                .build()
                .toUri();
        logger.info("REQUEST -> GET {}", url);
        return Optional.ofNullable(restTemplate.getForObject(url, Student.class));
    }

    @Override
    public Student setStudent(Student student) {
        if (student.getStudentId() != null) {
            String url = getUriStringComponent(student.getStudentId());
            logger.info("REQUEST -> PUT {}", url);
            restTemplate.put(url, student);
            return student;
        } else {
            HttpEntity<Student> request = new HttpEntity<>(student);
            String url = getUriStringComponent();
            logger.info("REQUEST -> POST {}", url);
            URI location = restTemplate.postForLocation(url, request);
            logger.info("REQUEST (location) -> GET {}", location);
            return restTemplate.getForObject(location, Student.class);
        }
    }

    @Override
    public void deleteStudent(Integer studentId) {
        URI url = ServiceUtil.getUriComponent(serverUrl, getResourcePath(studentId))
                .build()
                .toUri();
        logger.info("REQUEST -> DELETE {}", url);
        restTemplate.delete(url);
    }

    // TODO Zmienić metodę getPage

    @Override
    public Page<Student> getStudenci(Pageable pageable) {
        URI url = ServiceUtil.getURI(serverUrl, getResourcePath(), pageable);
        logger.info("REQUEST -> GET {}", url);
        return getPage(url, restTemplate);
    }

    @Override
    public Page<Student> searchByNazwa(String nazwa, Pageable pageable) {
        URI url = ServiceUtil.getUriComponent(serverUrl, getResourcePath(), pageable)
                .queryParam("nazwisko", nazwa)
                .build().toUri();
        logger.info("REQUEST -> GET {}", url);
        return getPage(url, restTemplate);
    }

    private Page<Student> getPage(URI uri, RestTemplate restTemplate) {
        return ServiceUtil.getPageStudent(uri, restTemplate,
                new ParameterizedTypeReference<RestResponsePage<Student>>() {});
    }
    private String getResourcePath() {
        return RESOURCE_PATH;
    }

    private String getResourcePath(Integer id) {
        return RESOURCE_PATH + "/" + id;
    }

    private String getUriStringComponent() {
        return serverUrl + getResourcePath();
    }

    private String getUriStringComponent(Integer id) {
        return serverUrl + getResourcePath(id);
    }
}
