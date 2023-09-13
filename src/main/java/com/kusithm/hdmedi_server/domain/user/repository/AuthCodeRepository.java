package com.kusithm.hdmedi_server.domain.user.repository;

import com.kusithm.hdmedi_server.domain.user.domain.AuthCode;
import org.springframework.data.repository.CrudRepository;

public interface AuthCodeRepository extends CrudRepository<AuthCode, Long> {
    boolean existsByAuthCode(String authCode);
}
