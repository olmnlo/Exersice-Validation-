package org.example.eventsystem.Controller;

import jakarta.validation.Valid;
import org.example.eventsystem.Api.ApiResponse;
import org.example.eventsystem.Model.Event;
import org.example.eventsystem.Model.EventCapacity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event-system")
public class EventSystemController {
    ArrayList<Event> events = new ArrayList<>();



    @PostMapping("/create")
    public ResponseEntity createEvent(@Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors.getFieldError().getDefaultMessage());
        }else {
            events.add(event);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Event created successfully"));
        }
    }


    @GetMapping("/show")
    public ResponseEntity getEvents() {
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }


    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index, @Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors.getFieldError().getDefaultMessage());
        } else if (index >= events.size() || index < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Index out of bounds"));
        }else {
            events.set(index, event);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Event updated successfully"));
        }
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index) {
        if (index >= events.size() || index < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Index out of bounds"));
        }else {
            events.remove(index);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Event deleted successfully"));
        }
    }

    @PutMapping("/update/capacity/{index}")
    public ResponseEntity updateCapacity(@PathVariable int index,@Valid @RequestBody EventCapacity eventCapacity, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        } else if (index >= events.size() || index < 0) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Index out of bounds"));
        } else  {
            events.get(index).setCapacity(eventCapacity.getCapacity());
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Capacity updated successfully"));
        }
    }


    @GetMapping("/find/{id}")
    public ResponseEntity findEventById(@PathVariable String id){
        for (Event event : events){
            if (event.getId().equals(id)){
                return ResponseEntity.status(HttpStatus.OK).body(event);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Event not found"));

    }

}
