package com.lagalt.services.skillServices;

import com.lagalt.models.DTOs.SkillDTOs.SkillDTO;
import com.lagalt.models.Skill;
import com.lagalt.services.CRUDService;
import java.util.Collection;

public interface SkillService extends CRUDService<Skill, Integer> {

    // User's extra requests
    SkillDTO findSkillById(Integer skill_id);
    Collection<SkillDTO> findAllSkills();
}
