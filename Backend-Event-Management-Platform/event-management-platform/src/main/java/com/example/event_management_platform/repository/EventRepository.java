package com.example.event_management_platform.repository;




import com.example.event_management_platform.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT MONTH(e.startDate) as month, COUNT(e) FROM Event e GROUP BY MONTH(e.startDate)")
    List<Object[]> countEventsByMonth();

    @Query("SELECT e.eventType, COUNT(e) FROM Event e GROUP BY e.eventType")
    List<Object[]> countEventsByType();
}
