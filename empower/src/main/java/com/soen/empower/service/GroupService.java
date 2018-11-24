package com.soen.empower.service;

import com.soen.empower.entity.Group;
import com.soen.empower.entity.User;
import com.soen.empower.repository.GroupRepository;
import com.soen.empower.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Group service.
 */
@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Added placeholder for dependency injection to support test cases.
     *
     * @param groupRepository repository connection
     */
    GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }


    /**
     * Fetch all groups.
     *
     * @return list of group
     */
    public List<Group> fetchAll() {
        return (List<Group>) groupRepository.findAll();
    }

    /**
     * Create the group from group entity.
     *
     * @param group group with created id.
     */
    public void add(Group group) {
        groupRepository.save(group);
    }

    /**
     * Search for group by name
     *
     * @param name incomplete name of group
     * @return list of group which satisfy the condition
     */
    public List<Group> findByPartialName(String name) {
        return groupRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Groups which have been joined by the given user id.
     *
     * @param userId user id
     * @return list of group
     */
    public List<Group> fetchJoinedGroups(long userId) {
        return groupRepository.findByMembersId(userId);
    }

    /**
     * locate group by id
     *
     * @param groupId id
     * @return group as entity object
     */
    public Group findById(long groupId) {
        return groupRepository.findById(groupId);
    }

    /**
     * for a given user id, it returns true or false depending upon the user is owner or not.
     *
     * @param userId  user id
     * @param groupId group id
     * @return boolean
     */
    public boolean isOwner(long userId, long groupId) {
        Group group = groupRepository.findByOwnerIdAndId(userId, groupId);
        return group != null;
    }

    /**
     * Group having mutliple admins, this validates if the given user id is admin for the
     * given group.
     *
     * @param userId  user id
     * @param groupId group id
     * @return boolean
     */
    public boolean isAdmin(long userId, long groupId) {
        Group group = groupRepository.findByAdminsIdAndId(userId, groupId);
        return group != null;
    }

    /**
     * Checks whether the user id is member of not. Return status bit:
     * 1  - member
     * 0  - requested
     * -1 - not even requested
     *
     * @param userId  user id
     * @param groupId group id
     * @return integer
     */
    public int isMember(long userId, long groupId) {
        Group group = groupRepository.findByIdAndMembersId(groupId, userId);
        if (group != null) return 1;
        group = groupRepository.findByIdAndRequestsId(groupId, userId);
        if (group != null) return 0;
        return -1;
    }

    /**
     * To add as admin, fetch the user object and then add it as admin to the group.
     *
     * @param groupId group id
     * @param userId  user id
     */
    public void addAdmin(long groupId, long userId) {
        Group group = groupRepository.findById(groupId);
        User admin = userRepository.findById(userId);
        group.getAdmins().add(admin);
        groupRepository.save(group);
    }

    /**
     * Remove from list of admin.
     *
     * @param groupId group id
     * @param userId  user id
     */
    public void removeAdmin(long groupId, long userId) {
        Group group = groupRepository.findById(groupId);
        User admin = userRepository.findById(userId);
        group.getAdmins().remove(admin);
        groupRepository.save(group);
    }

    /**
     * Register a request to join the group
     *
     * @param userId  user id
     * @param groupId group id
     */
    public Group addRequest(long userId, long groupId) {
        Group group = groupRepository.findById(groupId);
        group.getRequests().add(userRepository.findById(userId));
        return groupRepository.save(group);
    }

    /**
     * Leave the group will remove you from the list of admins.
     *
     * @param userId  user id
     * @param groupId group id
     */
    public void leave(long userId, long groupId) {
        Group group = groupRepository.findById(groupId);
        User user = userRepository.findById(userId);
        group.getAdmins().remove(user);
        group.getMembers().remove(user);
        //TODO: Owner leaves the group ??
        groupRepository.save(group);
    }

    /**
     * fetch all the users who have requested to join the group.
     *
     * @param groupId group id
     * @return list of users
     */
    public List<User> fetchJoinRequests(long groupId) {
        return groupRepository.findById(groupId).getRequests();
    }

    /**
     * Action taken by admin to accept the group join request.
     *
     * @param userId  user id
     * @param groupId group id
     */
    public Group acceptRequest(long userId, long groupId) {
        Group group = groupRepository.findById(groupId);
        User user = userRepository.findById(userId);
        group.getRequests().remove(user);
        group.getMembers().add(user);
        return groupRepository.save(group);
    }

    /**
     * Action taken by the admin to decline the group join request.
     *
     * @param userId  user id
     * @param groupId group id
     */
    public void declineRequest(long userId, long groupId) {
        Group group = groupRepository.findById(groupId);
        User user = userRepository.findById(userId);
        group.getRequests().remove(user);
        groupRepository.save(group);
    }
}
