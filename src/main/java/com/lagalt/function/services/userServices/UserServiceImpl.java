package com.lagalt.function.services.userServices;

import com.lagalt.function.models.LagaltUser;
import com.lagalt.function.models.Project;
import com.lagalt.function.models.Skill;
import com.lagalt.function.repositories.*;
import com.lagalt.function.services.Transformer;
import com.lagalt.function.models.*;
import com.lagalt.function.models.DTOs.LagaltUserDTOs.LagaltUserWithMoreInfoDTO;
import com.lagalt.function.models.DTOs.ProjectsDTOs.ProjectDTO;
import com.lagalt.function.models.DTOs.SkillDTOs.SkillDTO;
import com.lagalt.function.repositories.*;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    // Variables
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final SkillRepository skillRepository;
    private final RequestRepository requestRepository;
    private final MessageRepository messageRepository;

    // Constructor
    public UserServiceImpl(UserRepository userRepository, ProjectRepository projectRepository, SkillRepository skillRepository,
                           RequestRepository requestRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.messageRepository = messageRepository;
        this.skillRepository = skillRepository;
        this.requestRepository = requestRepository;
    }

    // User's crud methods
    // Do not use this (instead use DTO)
    @Override
    public LagaltUser findById(Integer user_id) {
        return this.userRepository.findById(user_id).orElse(null);
    }
    public LagaltUserWithMoreInfoDTO findUserById(Integer user_id){
        return Transformer.lagaltUserToLagaltUserWithMoreInfoDTO(findById(user_id));
    }
    // Do not use this (instead use DTO)
    @Override
    public Collection<LagaltUser> findAll() {
        return this.userRepository.findAll();
    }
    public Collection<LagaltUserWithMoreInfoDTO> findAllUsers(){
        return Transformer.lagaltUserSetToLagaltUserWithMoreInfoDTOSet(findAll().stream().collect(Collectors.toSet()));
    }
    @Override
    public LagaltUser add(LagaltUser user) {
        return this.userRepository.save(user);
    }
    @Override
    public LagaltUser update(LagaltUser user) {
        return this.userRepository.save(user);
    }
    @Override
    public void deleteById(Integer user_id) {
        if(this.userRepository.existsById(user_id)){
            LagaltUser user = this.userRepository.findById(user_id).get();
            // Break skill link to user that we want to delete
            this.userRepository.findById(user_id).get().setSkills(null);
            // Set users' project the ones without user that we want to delete
            for(Project project : this.projectRepository.findAll())
                if(project.getUsers().contains((user))){
                   Set<LagaltUser> users = project.getUsers();
                   users.remove(user);
                   project.setUsers(users);
                }
            // Delete user's requests that we want to delete
            this.requestRepository.findAll().stream()
                                            .filter(request -> request.getUser() == user)
                                            .forEach(request -> this.requestRepository.delete(request));
            // Delete user's messages that we want to delete
            this.messageRepository.findAll().stream()
                                            .filter(message -> message.getUser() == user)
                                            .forEach(message -> this.messageRepository.delete(message));
            this.userRepository.delete(user);
        }
    }
    @Override
    public boolean exists(Integer user_id) {
        return this.userRepository.existsById(user_id);
    }

    // User's skills crud methods
    @Override
    public SkillDTO findSkillByIdOfUser(Integer user_id, Integer skill_id) {
        SkillDTO skillDTO = null;
        for(Skill s : this.userRepository.findById(user_id).get().getSkills())
            if(s.getSkill_id() == skill_id)
                skillDTO = Transformer.skillToSkillDTO(s);
        return skillDTO;
    }
    @Override
    public Collection<SkillDTO> findSkillsOfUser(Integer user_id) {
        return Transformer.skillSetToSkillDTOSet(this.userRepository.findById(user_id).get().getSkills());
    }
    @Override
    public SkillDTO addSkillOnUser(Integer user_id, Skill skill) {
        LagaltUser user = this.userRepository.findById(user_id).get();
        Set<Skill> skills = user.getSkills();
        skills.add(skill);
        user.setSkills(skills);
        return Transformer.skillToSkillDTO(skill);
    }
    @Override
    public void deleteSkillByIdFromUser(Integer user_id, Integer skill_id) {
        LagaltUser user = this.userRepository.findById(user_id).get();
        Set<Skill> skills = user.getSkills();
        skills.remove(this.skillRepository.findById(skill_id));
        user.setSkills(skills);
    }

    // User's projects crud methods
    @Override
    public ProjectDTO findProjectByIdOfUser(Integer user_id, Integer project_id) {
        ProjectDTO projectDTO = null;
        for(Project p : this.userRepository.findById(user_id).get().getProjects())
            if(p.getProject_id() == project_id)
                projectDTO = Transformer.projectToProjectDTO(p);
        return projectDTO;
    }
    @Override
    public Collection<ProjectDTO> findProjectsOfUser(Integer user_id) {
        return Transformer.projectSetToProjectDTOSet(this.userRepository.findById(user_id).get().getProjects());
    }
    @Override
    public ProjectDTO addUserOnProject(Integer user_id, Project project) {
        LagaltUser user = this.userRepository.findById(user_id).get();
        Set<Project> projects = user.getProjects();
        projects.add(project);
        user.setProjects(projects);
        return Transformer.projectToProjectDTO(project);
    }
    @Override
    public void deleteUserFromProjectById(Integer user_id, Integer project_id) {
        LagaltUser user = this.userRepository.findById(user_id).get();
        Set<Project> projects = user.getProjects();
        projects.remove(this.projectRepository.findById(project_id).get());
        user.setProjects(projects);
    }
}