package com.example.event_management_platform.service;

import com.example.event_management_platform.model.Event;
import com.example.event_management_platform.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        return eventRepository.findById(id).map(event -> {
            event.setTitle(updatedEvent.getTitle());
            event.setDescription(updatedEvent.getDescription());
            event.setStartDate(updatedEvent.getStartDate());
            event.setEndDate(updatedEvent.getEndDate());
            event.setEventType(updatedEvent.getEventType());
            event.setPublic(updatedEvent.isPublic());
            event.setMaxAttendees(updatedEvent.getMaxAttendees());
            return eventRepository.save(event);
        }).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Object[]> getEventCountByMonth() {
        return eventRepository.countEventsByMonth(); // [['Jan', 5], ['Feb', 8]]
    }


    public List<Object[]> getEventCountByType() {
        return eventRepository.countEventsByType(); // [['Conference', 6], ['Workshop', 3]]
    }

}