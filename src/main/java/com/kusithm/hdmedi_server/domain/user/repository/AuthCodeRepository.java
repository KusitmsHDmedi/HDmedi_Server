package com.kusithm.hdmedi_server.domain.user.repository;

import com.kusithm.hdmedi_server.domain.user.domain.AuthCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthCodeRepository extends CrudRepository<AuthCode, Long> {
    Optional<AuthCode> findByAuthCode(String AuthCode);

    boolean existsByAuthCode(String authCode);
}
