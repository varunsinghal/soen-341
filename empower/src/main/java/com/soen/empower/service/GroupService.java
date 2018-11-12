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

    public void addAdmin(long groupId, long userId) {
        Group group = groupRepository.findById(groupId);
        User admin = userRepository.findById(userId);
        group.getAdmins().add(admin);
        groupRepository.save(group);
    }

    public void removeAdmin(long groupId, long userId){
        Group group = groupRepository.findById(groupId);
        User admin = userRepository.findById(userId);
        group.getAdmins().remove(admin);
        groupRepository.save(group);
    }
}
