package com.kusithm.hdmedi_server.domain.user.repository;

import com.kusithm.hdmedi_server.domain.user.domain.Platform;
import com.kusithm.hdmedi_server.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByPlatformAndPlatformId(Platform platform, String platformId);
    boolean existsUserByPlatformAndPlatformId(Platform platform, String platformId);
}
