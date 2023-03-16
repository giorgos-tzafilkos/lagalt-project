package com.lagalt.function.services.userServices;

import com.lagalt.function.models.DTOs.LagaltUserDTOs.LagaltUserWithMoreInfoDTO;
import com.lagalt.function.models.DTOs.ProjectsDTOs.ProjectDTO;
import com.lagalt.function.models.DTOs.SkillDTOs.SkillDTO;
import com.lagalt.function.models.LagaltUser;
import com.lagalt.function.models.Project;
import com.lagalt.function.models.Skill;
import com.lagalt.function.services.CRUDService;
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
