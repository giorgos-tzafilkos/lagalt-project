package com.lagalt.function.services;

import com.lagalt.function.models.*;

import com.lagalt.function.models.DTOs.LagaltUserDTOs.LagaltUserDTO;
import com.lagalt.function.models.DTOs.LagaltUserDTOs.LagaltUserWithMoreInfoDTO;
import com.lagalt.function.models.DTOs.ProjectsDTOs.ProjectDTO;
import com.lagalt.function.models.DTOs.SkillDTOs.SkillDTO;
import com.lagalt.function.models.DTOs.TopicDTOs.TopicDTO;
import com.lagalt.function.models.DTOs.MessageDTOs.MessageDTO;
import com.lagalt.function.models.DTOs.RequestsDTOs.RequestDTO;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Transformer {
    public static Set<LagaltUserWithMoreInfoDTO> lagaltUserSetToLagaltUserWithMoreInfoDTOSet(Set<LagaltUser> users){
        return users.stream().map(user -> lagaltUserToLagaltUserWithMoreInfoDTO(user)).collect(Collectors.toSet());
    }
    public static LagaltUserWithMoreInfoDTO lagaltUserToLagaltUserWithMoreInfoDTO(LagaltUser user){
        Set<ProjectDTO> projectDTOSet = projectSetToProjectDTOSet(user.getProjects());
        Set<SkillDTO> skillDTOSet = skillSetToSkillDTOSet(user.getSkills());
        Set<RequestDTO> requestDTOSet = requestSetToRequestDTOSet(user.getRequests());
        List<MessageDTO> messageDTOList = messageListToMessageDTOList(user.getMessages());
        return  new LagaltUserWithMoreInfoDTO(user.getUser_id(),user.getUser_name(), projectDTOSet,
                skillDTOSet, requestDTOSet, messageDTOList);
    }
    public static Set<LagaltUserDTO> lagaltUserSetToLagaltUserDTOSet(Set<LagaltUser> users){
        return users.stream().map(user -> lagaltUserToLagaltUserDTO(user)).collect(Collectors.toSet());
    }
    public static LagaltUserDTO lagaltUserToLagaltUserDTO(LagaltUser user){
        return new LagaltUserDTO(user.getUser_id(),user.getUser_name());
    }
    public static Set<SkillDTO> skillSetToSkillDTOSet(Set<Skill> skills){
        return  skills.stream().map(skill -> skillToSkillDTO(skill)).collect(Collectors.toSet());
    }
    public static SkillDTO skillToSkillDTO(Skill skill){
        return new SkillDTO(skill.getSkill_id(), skill.getSkill_name());
    }
    public static TopicDTO topicToTopicDTO(Topic topic){
        return new TopicDTO(topic.getTopic_id(), topic.getTopic_name());
    }
    public static Set<RequestDTO> requestSetToRequestDTOSet(Set<Request> requests){
        return requests.stream().map(request -> requestToRequestDTO(request)).collect(Collectors.toSet());
    }
    public static RequestDTO requestToRequestDTO(Request request){
        return new RequestDTO(request.getRequest_id(), request.getRequest_text(),
                request.getUser().getUser_id(), request.getUser().getUser_name());
    }
    public static List<MessageDTO> messageListToMessageDTOList(List<Message> messages){
        return messages.stream().map(message -> messageToMessageDTO(message)).collect(Collectors.toList());
    }
    public static MessageDTO messageToMessageDTO(Message message){
        return new MessageDTO(message.getMessage_id(), message.getMessage_text(),
                message.getMessage_timestamp(), message.getUser().getUser_name());
    }
    public static Set<ProjectDTO> projectSetToProjectDTOSet(Set<Project> projects){
        return projects.stream().map(project -> projectToProjectDTO(project)).collect(Collectors.toSet());
    }
    public static ProjectDTO projectToProjectDTO(Project project){
        Set<LagaltUserDTO> lagaltUserDTOSet = lagaltUserSetToLagaltUserDTOSet(project.getUsers());
        TopicDTO topicDTO = topicToTopicDTO(project.getTopic());
        Set<SkillDTO> skillDTOSet = skillSetToSkillDTOSet(project.getSkills());
        Set<RequestDTO> requestDTOSet = requestSetToRequestDTOSet(project.getRequests());
        List<MessageDTO> messageDTOList = messageListToMessageDTOList(project.getMessages());
        return new ProjectDTO(project.getProject_id(), project.getProject_title(),
                project.getProject_purpose(), project.getProject_stage(),
                project.getProject_repo_url(), project.getProject_owner(),
                lagaltUserDTOSet, topicDTO, skillDTOSet,requestDTOSet, messageDTOList);
    }
}
