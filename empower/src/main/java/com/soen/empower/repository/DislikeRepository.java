package com.soen.empower.repository;

import com.soen.empower.entity.Dislike;
import org.springframework.data.repository.CrudRepository;

public interface DislikeRepository extends CrudRepository<Dislike, Long> {
    Dislike findByUserIdAndCardId(Long id, Long id1);
}
