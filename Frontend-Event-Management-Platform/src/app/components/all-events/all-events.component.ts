import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface Event {
  id: number;
  title: string;
  description: string;
  startDate: string;
  endDate: string;
  eventType: string;
  isPublic: boolean;
  maxAttendees: number;
}

@Component({
  selector: 'app-all-events',
  standalone: true,
  imports: [CommonModule, HttpClientModule, NgFor, NgIf, FormsModule],
  templateUrl: './all-events.component.html',
  styleUrls: ['./all-events.component.css']
})
export class AllEventsComponent implements OnInit {
  events: Event[] = [];
  selectedEvent: Event | null = null;

  attendeeName = '';
  attendeeEmail = '';
  showModal = false;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<Event[]>('http://localhost:8080/api/events')
      .subscribe(data => this.events = data);
  }

  openRegisterModal(event: Event) {
    this.selectedEvent = event;
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
    this.attendeeName = '';
    this.attendeeEmail = '';
  }

registerForEvent() {
  if (!this.selectedEvent) return;

  const registration = {
    attendeeName: this.attendeeName,
    attendeeEmail: this.attendeeEmail,
    event: {
      id: this.selectedEvent.id
    }
  };

  this.http.post('http://localhost:8080/api/registrations', registration)
    .subscribe({
      next: () => {
        alert('Successfully registered!');
        this.closeModal();
      },
      error: (err) => {
        console.error('Registration failed:', err);
        alert('Something went wrong during registration.');
      }
    });
}
registeredMembers: { [eventId: number]: string[] } = {};
showingMembersFor: number | null = null;

fetchRegisteredMembers(eventId: number) {
  this.http.get<any[]>(`http://localhost:8080/api/registrations/event/${eventId}`)
    .subscribe({
      next: (registrations) => {
        this.registeredMembers[eventId] = registrations.map(r => r.attendeeName);
        this.showingMembersFor = eventId;
      },
      error: (err) => {
        console.error('Error fetching registrations:', err);
        alert('Failed to load registered members.');
      }
    });
}


}
