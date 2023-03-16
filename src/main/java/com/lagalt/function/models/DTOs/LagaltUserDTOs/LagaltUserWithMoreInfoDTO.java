package com.lagalt.function.models.DTOs.LagaltUserDTOs;

import lombok.Getter;
import lombok.Setter;
import com.lagalt.function.models.DTOs.ProjectsDTOs.ProjectDTO;
import com.lagalt.function.models.DTOs.SkillDTOs.SkillDTO;
import com.lagalt.function.models.DTOs.MessageDTOs.MessageDTO;
import com.lagalt.function.models.DTOs.RequestsDTOs.RequestDTO;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class LagaltUserWithMoreInfoDTO {
    // Constructor
    public LagaltUserWithMoreInfoDTO(int user_id, String user_name, Set<ProjectDTO> projects,
                                     Set<SkillDTO> skills, Set<RequestDTO> requests, List<MessageDTO> messages){
        this.user_id = user_id;
        this.user_name = user_name;
        this.projects = projects;
        this.skills = skills;
        this.requests =requests;
        this.messages = messages;
    }
    private int user_id;
    private String user_name;
    private Set<ProjectDTO> projects;
    private Set<SkillDTO> skills;
    private Set<RequestDTO> requests;
    private List<MessageDTO> messages;
}
