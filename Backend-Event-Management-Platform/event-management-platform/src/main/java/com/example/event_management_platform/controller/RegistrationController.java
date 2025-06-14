package com.example.event_management_platform.controller;


import com.example.event_management_platform.model.Registration;
import com.example.event_management_platform.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/registrations")
@CrossOrigin(origins = "http://localhost:4200") // Angular frontend
@RequiredArgsConstructor
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/event/{eventId}")
    public ResponseEntity<Registration> register(@PathVariable Long eventId, @RequestBody Registration registration) {
        return new ResponseEntity<>(registrationService.register(eventId, registration), HttpStatus.CREATED);
    }

    // ✅ This one is for frontend POSTing { event: { id: X }, attendeeName, attendeeEmail }
    @PostMapping
    public ResponseEntity<Registration> register(@RequestBody Registration registration) {
        Long eventId = registration.getEvent().getId();
        if (eventId == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(registrationService.register(eventId, registration), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }

    @GetMapping("/event/{eventId}")
    public List<Registration> getRegistrationsByEvent(@PathVariable Long eventId) {
        return registrationService.getRegistrationsByEvent(eventId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
        return ResponseEntity.noContent().build();
    }
}
