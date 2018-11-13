package com.soen.empower.repository;

import com.soen.empower.entity.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The Interface GroupRepository.
 */
public interface GroupRepository extends CrudRepository<Group, Long> {

	
    /**
     * Find by name containing ignore case.
     *
     * @param name the name
     * @return the list
     */
    List<Group> findByNameContainingIgnoreCase(String name);

    /**
     * Find by members id.
     *
     * @param userId the user id
     * @return the list
     */
    List<Group> findByMembersId(long userId);

    /**
     * Find by id.
     *
     * @param groupId the group id
     * @return the group
     */
    Group findById(long groupId);

    /**
     * Find by owner id and id.
     *
     * @param userId the user id
     * @param groupId the group id
     * @return the group
     */
    Group findByOwnerIdAndId(long userId, long groupId);

	/**
	 * Find by admins id and id.
	 *
	 * @param userId the user id
	 * @param groupId the group id
	 * @return the group
	 */
	Group findByAdminsIdAndId(long userId, long groupId);

	/**
	 * Find by id and members id.
	 *
	 * @param groupId the group id
	 * @param userId the user id
	 * @return the group
	 */
	Group findByIdAndMembersId(long groupId, long userId);

	/**
	 * Find by id and requests id.
	 *
	 * @param groupId the group id
	 * @param userId the user id
	 * @return the group
	 */
	Group findByIdAndRequestsId(long groupId, long userId);
}
