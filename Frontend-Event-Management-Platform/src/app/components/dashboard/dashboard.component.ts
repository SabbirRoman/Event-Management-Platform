// dashboard.component.ts
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NgChartsModule } from 'ng2-charts';
import { ChartConfiguration, ChartType } from 'chart.js';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, NgChartsModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  monthlyData: ChartConfiguration<'bar'>['data'] = {
    labels: [],
    datasets: [{ data: [], label: 'Events by Month' }]
  };

  typeData: ChartConfiguration<'pie'>['data'] = {
    labels: [],
    datasets: [{ data: [], label: 'Event Types' }]
  };

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchMonthlyData();
    this.fetchTypeData();
  }

  
  fetchMonthlyData() {
    this.http.get<[string, number][]>('http://localhost:8080/api/events/chart/month')
      .subscribe((response) => {
        const labels = response.map(item => item[0]);
        const values = response.map(item => item[1]);
        this.monthlyData = {
          labels,
          datasets: [{ data: values, label: 'Events by Month' }]
        };
      });
  }

  fetchTypeData() {
    this.http.get<[string, number][]>('http://localhost:8080/api/events/chart/type')
      .subscribe((response) => {
        const labels = response.map(item => item[0]);
        const values = response.map(item => item[1]);
        this.typeData = {
          labels,
          datasets: [{ data: values, label: 'Event Types' }]
        };
      });
  }
}
