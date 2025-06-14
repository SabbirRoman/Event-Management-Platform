import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AllEventsComponent } from './components/all-events/all-events.component';
import { CreateEventComponent } from './components/create-event/create-event.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

export const routes: Routes = [
    { path: 'home', component: HomeComponent },
  { path: 'events', component: AllEventsComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'create-event', component: CreateEventComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];
