package com.lagalt.function.services.skillServices;

import com.lagalt.function.models.DTOs.SkillDTOs.SkillDTO;
import com.lagalt.function.models.Skill;
import com.lagalt.function.services.CRUDService;
import java.util.Collection;

public interface SkillService extends CRUDService<Skill, Integer> {

    // User's extra requests
    SkillDTO findSkillById(Integer skill_id);
    Collection<SkillDTO> findAllSkills();
}
