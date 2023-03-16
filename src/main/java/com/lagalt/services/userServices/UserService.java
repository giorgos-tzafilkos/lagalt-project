package com.lagalt.services.userServices;

import com.lagalt.models.DTOs.LagaltUserDTOs.LagaltUserWithMoreInfoDTO;
import com.lagalt.models.DTOs.ProjectsDTOs.ProjectDTO;
import com.lagalt.models.DTOs.SkillDTOs.SkillDTO;
import com.lagalt.models.LagaltUser;
import com.lagalt.models.Project;
import com.lagalt.models.Skill;
import com.lagalt.services.CRUDService;
import java.util.Collection;

public interface UserService extends CRUDService<LagaltUser, Integer> {

    // User's extra requests
    LagaltUserWithMoreInfoDTO findUserById(Integer id);
    Collection<LagaltUserWithMoreInfoDTO> findAllUsers();

    // User's skills requests
    SkillDTO findSkillByIdOfUser(Integer user_id, Integer skill_id);
    Collection<SkillDTO> findSkillsOfUser(Integer user_id);
    SkillDTO addSkillOnUser(Integer user_id, Skill skill);
    void deleteSkillByIdFromUser(Integer user_id, Integer skill_id);

    // User's projects requests
    ProjectDTO findProjectByIdOfUser(Integer user_id, Integer project_id);
    Collection<ProjectDTO> findProjectsOfUser(Integer user_id);
    ProjectDTO addUserOnProject(Integer user_id, Project project);
    void deleteUserFromProjectById(Integer user_id, Integer project_id);
}
