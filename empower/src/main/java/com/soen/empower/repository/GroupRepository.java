package com.soen.empower.repository;

import com.soen.empower.entity.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/*
 * The interface GroupRepository
 */
public interface GroupRepository extends CrudRepository<Group, Long> {
	/*
	 * Find list of group by name
	 * 
	 * @param String argument to search by name
	 * @return list of groups
	 */
	
    List<Group> findByNameContainingIgnoreCase(String name);

    List<Group> findByMembersId(long userId);

    Group findById(long groupId);

    Group findByOwnerIdAndId(long userId, long groupId);

	Group findByAdminsIdAndId(long userId, long groupId);
}
