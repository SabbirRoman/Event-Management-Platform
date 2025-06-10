package com.example.event_management_platform.service;

import com.example.event_management_platform.model.Event;
import com.example.event_management_platform.model.Registration;
import com.example.event_management_platform.repository.EventRepository;
import com.example.event_management_platform.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public Registration getRegistrationById(Long id) {
        return registrationRepository.findById(id).orElse(null);
    }

    public Registration register(Long eventId, Registration reg) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event == null) return null;
        reg.setEvent(event);
        reg.setRegistrationDate(LocalDateTime.now());
        return registrationRepository.save(reg);
    }

    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }

    public List<Registration> getRegistrationsByEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        return registrationRepository.findByEvent(event);
    }
}

