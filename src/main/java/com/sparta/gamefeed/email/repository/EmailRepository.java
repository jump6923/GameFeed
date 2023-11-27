package com.sparta.gamefeed.email.repository;

import com.sparta.gamefeed.email.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Long> {

    Optional<Email> findByCode(String code);
}
