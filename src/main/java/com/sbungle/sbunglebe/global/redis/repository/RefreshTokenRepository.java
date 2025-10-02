package com.sbungle.sbunglebe.global.redis.repository;

import com.sbungle.sbunglebe.global.redis.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
