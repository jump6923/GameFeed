package com.sparta.gamefeed.repository;

import com.sparta.gamefeed.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Long> {

    Optional<Email> findByCode(String code);
}
