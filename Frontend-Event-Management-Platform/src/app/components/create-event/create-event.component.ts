import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-event',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.css']
})
export class CreateEventComponent {
  event = {
    title: '',
    description: '',
    startDate: '',
    endDate: '',
    eventType: 'Conference',
    isPublic: false,
    maxAttendees: 10
  };

  constructor(private http: HttpClient, private router: Router) {}
   
  createEvent() {
   

    console.log('Submitting event:', this.event);
    this.http.post('http://localhost:8080/api/events', this.event).subscribe({
      next: () => {
        alert('Event created successfully!');
        this.router.navigate(['/events']);
      },
      error: (err) => {
        console.error('Error:', err);
        alert('Something went wrong.');
      }
    });
  }
}
