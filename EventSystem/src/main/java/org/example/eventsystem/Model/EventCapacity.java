package org.example.eventsystem.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class EventCapacity {

    @NotNull
    @Positive
    @Min(value = 25, message = "capacity at least have to be 25")
    private int capacity;
}
