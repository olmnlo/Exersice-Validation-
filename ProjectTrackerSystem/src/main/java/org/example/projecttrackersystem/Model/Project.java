package org.example.projecttrackersystem.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Project {

    @NotEmpty
    @Size(min = 2, message = "id must have at least 2 chars")
    private String id;

    @NotEmpty
    @Size(min = 8, message = "title must be at least 8 chars")
    private String title;

    @NotEmpty
    @Size(min = 15, message = "description must be at least 15 chars")
    private String description;

    @NotEmpty
    @Pattern(regexp = "(Not Started|in Progress|Completed)", message = "you must enter one valid status")
    private String status;

    @NotEmpty
    @Size(min = 6, message = "company name must be at least 6 chars")
    private String companyName;

}