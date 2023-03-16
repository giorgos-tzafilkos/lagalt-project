package com.lagalt.function.controllers;

import com.lagalt.function.models.Skill;
import com.lagalt.function.services.skillServices.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/skills")
public class SkillsController {

    // Variables
    private final SkillService skillService;

    // Constructor
    public SkillsController(SkillService skillService) {
        this.skillService = skillService;
    }

    // Skill's requests
    @GetMapping
    public ResponseEntity findAllSkills(){
        return ResponseEntity.ok(skillService.findAllSkills());
    }
    @GetMapping({"{skill_id}"})
    public ResponseEntity findSkillById(@PathVariable int skill_id){
        return ResponseEntity.ok(skillService.findSkillById(skill_id));
    }
    @PostMapping
    public ResponseEntity addSkill(@RequestBody Skill skill) throws URISyntaxException {
        skillService.add(skill); // Add Skill
        URI uri = new URI("skills/"+ skill.getSkill_id());
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("{skill_id}")
    public ResponseEntity updateSkill(@RequestBody Skill skill){
        this.skillService.update(skill);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value="/{skill_id}")
    public void deleteSkill(@PathVariable("skill_id") int skill_id){
        skillService.deleteById(skill_id);
    }
}
