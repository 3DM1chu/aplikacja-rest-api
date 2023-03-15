package com.pbs.aplikacja.repository;

import com.pbs.aplikacja.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByIndexNumber(String indexNumber);
    Page<Student> findByIndexNumberStartsWith(String indexNumber, Pageable pageable);
    Page<Student> findByLastNameStartsWithIgnoreCase(String lastName, Pageable pageable);

}
