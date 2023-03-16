package com.lagalt.controllers;

import com.lagalt.models.LagaltUser;
import com.lagalt.models.Project;
import com.lagalt.models.Skill;
import com.lagalt.services.userServices.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    // Variables
    private final UserService userService;

    // Constructor
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // User's requests
    @GetMapping
    public ResponseEntity findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }
    @GetMapping({"{user_id}"})
    public ResponseEntity findUserById(@PathVariable int user_id){
        return ResponseEntity.ok(userService.findUserById(user_id));
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody LagaltUser user) throws URISyntaxException {
        userService.add(user); //Add User
        URI uri = new URI("users/"+ user.getUser_id());
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("{user_id}")
    public ResponseEntity updateUser(@RequestBody LagaltUser user){
        this.userService.update(user);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value="/{user_id}")
    public void deleteUser(@PathVariable("user_id") int user_id){
        userService.deleteById(user_id);
    }

    // User's skills requests
    @GetMapping("{user_id}/skills")
    public ResponseEntity findSkillsOfUser(@PathVariable int user_id){
        return ResponseEntity.ok(userService.findSkillsOfUser(user_id));
    }
    @GetMapping({"{user_id}/skills/{skill_id}"})
    public ResponseEntity findSkillByIdOfUser(@PathVariable int user_id, @PathVariable int skill_id){
        return ResponseEntity.ok(userService.findSkillByIdOfUser(user_id, skill_id));
    }

    @PostMapping("{user_id}/skills")
    public ResponseEntity addSkillOnUser(@PathVariable int user_id, @RequestBody Skill skill) throws URISyntaxException {
        userService.addSkillOnUser(user_id, skill); //Add User
        URI uri = new URI("users/{user_id}/skills/"+ skill.getSkill_id());
        return ResponseEntity.created(uri).build();
    }
    @DeleteMapping(value="{user_id}/skills/{skill_id}")
    public void deleteSkillByIdFromUser(@PathVariable int user_id, @PathVariable int skill_id){
        userService.deleteSkillByIdFromUser(user_id, skill_id);
    }

    // User's projects requests
    @GetMapping("{user_id}/projects")
    public ResponseEntity findProjectsOfUser(@PathVariable int user_id){
        return ResponseEntity.ok(userService.findProjectsOfUser(user_id));
    }
    @GetMapping({"{user_id}/projects/{project_id}"})
    public ResponseEntity findProjectByIdOfUser(@PathVariable int user_id, @PathVariable int project_id){
        return ResponseEntity.ok(userService.findProjectByIdOfUser(user_id, project_id));
    }

    @PostMapping("{user_id}/projects")
    public ResponseEntity addUserOnProject(@PathVariable int user_id, @RequestBody Project project) throws URISyntaxException {
        userService.addUserOnProject(user_id, project); //Add User
        URI uri = new URI("users/{user_id}/projects/"+ project.getProject_id());
        return ResponseEntity.created(uri).build();
    }
    @DeleteMapping(value="{user_id}/projects/{project_id}")
    public void deleteUserFromProjectById(@PathVariable int user_id, @PathVariable int project_id){
        userService.deleteUserFromProjectById(user_id, project_id);
    }

}
