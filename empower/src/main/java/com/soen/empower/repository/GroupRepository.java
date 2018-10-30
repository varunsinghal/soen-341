package com.soen.empower.repository;

import com.soen.empower.entity.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group, Long> {
    List<Group> findByNameContainingIgnoreCase(String name);
}
