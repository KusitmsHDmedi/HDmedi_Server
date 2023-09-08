package com.kusithm.hdmedi_server.domain.user.repository;

import com.kusithm.hdmedi_server.domain.user.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
}
