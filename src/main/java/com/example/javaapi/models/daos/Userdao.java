package com.example.javaapi.models.daos;

import com.example.javaapi.models.Userbean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Userdao extends JpaRepository<Userbean, Long> {
    List<Userbean> findAllByLogin(String login);
}
