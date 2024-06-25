package com.projectmanager.projectmanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projectmanager.projectmanager.models.Project;
import com.projectmanager.projectmanager.models.User;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findAll();
    List<Project> findByTeamMembersNotContains(User user);
    List<Project> findByTeamMembersContains(User user);
}
