package com.projectmanager.projectmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanager.projectmanager.models.Project;
import com.projectmanager.projectmanager.models.User;
import com.projectmanager.projectmanager.repositories.ProjectRepository;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepo;

    public List<Project> allProjects() {
        return projectRepo.findAll();
    }

    public List<Project> unassignedProjects(User user) {
        return projectRepo.findByTeamMembersNotContains(user);
    }

    public List<Project> assignedProjects(User user) {
        return projectRepo.findByTeamMembersContains(user);
    }

    public Project createProject(Project project) {
        return projectRepo.save(project);
    }

    public Project findProject(Long id) {
        Optional<Project> optionalProject = projectRepo.findById(id);
        return optionalProject.isPresent() ? optionalProject.get() : null;
    }

    public Project updateProject(Project project) {
        return projectRepo.save(project);
    }

    public void deleteProject(Project project) {
        projectRepo.delete(project);
    }

    public void joinTeam(Project project, User user) {
        project.getTeamMembers().add(user);
        projectRepo.save(project);
    }

    public void leaveTeam(Project project, User user) {
        project.getTeamMembers().remove(user);
        projectRepo.save(project);
    }
}