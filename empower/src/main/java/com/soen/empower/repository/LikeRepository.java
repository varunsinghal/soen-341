package com.soen.empower.repository;

import com.soen.empower.entity.Like;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, String> {
    Like findByUserIdAndCardId(Long id, Long id1);
}
