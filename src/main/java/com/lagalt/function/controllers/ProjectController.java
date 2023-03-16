package com.lagalt.function.controllers;

import com.lagalt.function.models.Project;
import com.lagalt.function.services.projectServices.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    // Variables
    private final ProjectService projectService;

    // Constructor
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Project's requests
    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(projectService.findAll());
    }
    @GetMapping({"{id}"})
    public ResponseEntity findByID(@PathVariable int id){
        return ResponseEntity.ok(projectService.findById(id));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Project project) throws URISyntaxException {
        projectService.add(project); //Add Project
        URI uri = new URI("projects/"+ project.getProject_id());
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Project project){
        this.projectService.update(project);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value="/{project_id}")
    public void deleteProject(@PathVariable("project_id") int project_id){
        projectService.deleteById(project_id);
    }
}
