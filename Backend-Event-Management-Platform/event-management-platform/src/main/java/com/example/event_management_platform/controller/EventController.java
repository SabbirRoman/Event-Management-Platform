package com.example.event_management_platform.controller;


import com.example.event_management_platform.model.Event;
import com.example.event_management_platform.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin
@RequiredArgsConstructor
public class EventController {
    private final EventService service;

    @GetMapping
    public List<Event> getAll() {
        return service.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event get(@PathVariable Long id) {
        return service.getEventById(id);
    }

    @PostMapping
    public Event create(@RequestBody Event e) {
        return service.createEvent(e);
    }

    @PutMapping("/{id}")
    public Event update(@PathVariable Long id, @RequestBody Event e) {
        return service.updateEvent(id, e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEvent(id);
    }

    @GetMapping("/monthly-counts")
    public List<Object[]> eventsByMonth() {
        return service.countEventsByMonth();
    }

    @GetMapping("/type-counts")
    public List<Object[]> eventsByType() {
        return service.countEventsByType();
    }
}

