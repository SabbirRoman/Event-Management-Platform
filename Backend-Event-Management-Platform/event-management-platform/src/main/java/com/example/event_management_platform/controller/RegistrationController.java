package com.example.event_management_platform.controller;


import com.example.event_management_platform.model.Registration;
import com.example.event_management_platform.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/registrations")
@CrossOrigin
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService service;

    @GetMapping
    public List<Registration> getAll() {
        return service.getAllRegistrations();
    }

    @GetMapping("/{id}")
    public Registration get(@PathVariable Long id) {
        return service.getRegistrationById(id);
    }

    @PostMapping("/event/{eventId}")
    public Registration register(@PathVariable Long eventId, @RequestBody Registration r) {
        return service.register(eventId, r);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteRegistration(id);
    }

    @GetMapping("/event/{eventId}")
    public List<Registration> getByEvent(@PathVariable Long eventId) {
        return service.getRegistrationsByEvent(eventId);
    }
}

