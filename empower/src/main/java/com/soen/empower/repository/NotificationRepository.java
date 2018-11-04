package com.soen.empower.repository;

import com.soen.empower.entity.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
    List<Notification> findByUserIdOrderByIdDesc(Long id);
}
