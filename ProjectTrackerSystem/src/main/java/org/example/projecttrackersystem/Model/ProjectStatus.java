package org.example.projecttrackersystem.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class ProjectStatus {
    @NotEmpty
    @Pattern(regexp = "(Not Started|in Progress|Completed)", message = "you must enter one valid status")
    private String status;
}
