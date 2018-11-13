package com.soen.empower.service;

import com.soen.empower.entity.Group;
import com.soen.empower.entity.User;
import com.soen.empower.repository.GroupRepository;
import com.soen.empower.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;

    GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }


    public List<Group> fetchAll() {
        return (List<Group>) groupRepository.findAll();
    }

    public void add(Group group) {
        groupRepository.save(group);
    }

    public List<Group> findByPartialName(String name) {
        return groupRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Group> fetchJoinedGroups(long userId) {
        return groupRepository.findByMembersId(userId);
    }

    public Group findById(long groupId) {
        return groupRepository.findById(groupId);
    }

    public boolean isOwner(long userId, long groupId) {
        Group group = groupRepository.findByOwnerIdAndId(userId, groupId);
        return group != null;
    }

    public boolean isAdmin(long userId, long groupId) {
        Group group = groupRepository.findByAdminsIdAndId(userId, groupId);
        return group != null;
    }

    public int isMember(long userId, long groupId) {
        Group group = groupRepository.findByIdAndMembersId(groupId, userId);
        if (group != null) return 1;
        group = groupRepository.findByIdAndRequestsId(groupId, userId);
        if (group != null) return 0;
        return -1;
    }

    public void addAdmin(long groupId, long userId) {
        Group group = groupRepository.findById(groupId);
        User admin = userRepository.findById(userId);
        group.getAdmins().add(admin);
        groupRepository.save(group);
    }

    public void removeAdmin(long groupId, long userId) {
        Group group = groupRepository.findById(groupId);
        User admin = userRepository.findById(userId);
        group.getAdmins().remove(admin);
        groupRepository.save(group);
    }

    public void addRequest(long userId, long groupId) {
        Group group = groupRepository.findById(groupId);
        group.getRequests().add(userRepository.findById(userId));
        groupRepository.save(group);
    }

    public void leave(long userId, long groupId) {
        Group group = groupRepository.findById(groupId);
        User user = userRepository.findById(userId);
        group.getAdmins().remove(user);
        group.getMembers().remove(user);
        //TODO: Owner leaves the group ??
        groupRepository.save(group);
    }

    public List<User> fetchJoinRequests(long groupId) {
        return groupRepository.findById(groupId).getRequests();
    }
}
