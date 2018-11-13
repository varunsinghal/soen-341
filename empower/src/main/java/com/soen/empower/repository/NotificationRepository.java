package com.soen.empower.repository;

import com.soen.empower.entity.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The Interface NotificationRepository.
 */
public interface NotificationRepository extends CrudRepository<Notification, Long> {
    
    /**
     * Find by user id order by id desc.
     *
     * @param id the id
     * @return the list
     */
    List<Notification> findByUserIdOrderByIdDesc(Long id);
    
    /**
     * Find by id.
     *
     * @param id the id
     * @return the notification
     */
    Notification findById(long id);
}
