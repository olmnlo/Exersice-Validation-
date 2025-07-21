package org.example.projecttrackersystem.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.example.projecttrackersystem.ApiResponse.ApiResponse;
import org.example.projecttrackersystem.Model.Project;
import org.example.projecttrackersystem.Model.ProjectStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/tracker")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();


    @GetMapping("/show")
    public ResponseEntity getProjects() {
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    @PostMapping("/create")
    public ResponseEntity addProject(@Valid @RequestBody Project project, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }else {
            projects.add(project);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Project created successfully"));
        }
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @Valid @RequestBody Project project, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        } else if (index >= projects.size() || index < 0) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("index out of bounds"));
        } else {
            projects.set(index, project);
            return ResponseEntity.status(HttpStatus.OK).body(new  ApiResponse("Project updated successfully"));
        }
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index) {
        if (index >= projects.size() || index < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Index out of bounds");
        }else {
            projects.remove(index);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Project deleted successfully"));
        }
    }


    @PutMapping("/update-status/{index}")
    public ResponseEntity changeProjectStatus(@Valid @RequestBody ProjectStatus status, @PathVariable int index, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        } else if (index >= projects.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Index out of bounds");
        }else {
            projects.get(index).setStatus(status.getStatus());
            return ResponseEntity.status(HttpStatus.OK).body(new  ApiResponse("Project updated successfully"));
        }
    }

    @GetMapping("/find/{title}")
    public ResponseEntity getProjectByTitle(@PathVariable String title) {
        for (Project project : projects) {
            if (project.getTitle().equals(title)) {
                return ResponseEntity.status(HttpStatus.OK).body(project);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Project not found"));
    }

    @GetMapping("/find-company/{companyName}")
    public ResponseEntity getProjectByCompanyName(@PathVariable String companyName) {
        ArrayList<Project> companyProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equals(companyName)) {
                companyProjects.add(project);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(companyProjects);
    }


}
