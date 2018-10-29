package com.soen.empower.service;

import com.soen.empower.entity.Group;
import com.soen.empower.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }


    public List<Group> fetchAll() {
        return (List<Group>) groupRepository.findAll();
    }

    public void add(Group group) {
        groupRepository.save(group);
    }
}
