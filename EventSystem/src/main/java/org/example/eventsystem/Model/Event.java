package org.example.eventsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Event {
    @NotEmpty
    @Size(min = 2, message = "id must have two chars at least")
    private String id;

    @NotEmpty
    @Size(min = 15, message = "description must have 15 chars at least")
    private String description;

    @NotNull
    @Positive
    @Min(value = 25, message = "capacity at least have to be 25")
    private int capacity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FutureOrPresent(message = "Start time must be in the present or future")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FutureOrPresent(message = "end time must be in the present or future")
    private LocalDateTime endTime;
}
