package com.example.event_management_platform.service;

import com.example.event_management_platform.model.Event;
import com.example.event_management_platform.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        Event event = getEventById(id);
        if (event == null) return null;
        updatedEvent.setId(id);
        return eventRepository.save(updatedEvent);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Object[]> countEventsByMonth() {
        return eventRepository.countEventsByMonth();
    }

    public List<Object[]> countEventsByType() {
        return eventRepository.countEventsByType();
    }
}
