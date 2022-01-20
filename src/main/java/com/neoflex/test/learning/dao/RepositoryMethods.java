package com.neoflex.test.learning.dao;

import com.neoflex.test.learning.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositoryMethods extends JpaRepository <Student, Long> {

}
