import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent {
  flightDetails: any = { 
    source: '',
    destination: '',
    departureTime:'',
    arrivalTime:'',
    noOfDaysInWeek: []
  };

  daysOfWeek = ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'];

  selectedDays: { [key: string]: boolean } = {};

  constructor(private http: HttpClient, private router: Router) { }

  onSubmit() {
    // Collect selected days from selectedDays object
    this.flightDetails.noOfDaysInWeek = Object.keys(this.selectedDays)
      .filter(day => this.selectedDays[day]);

    // Now this.flightDetails.noOfDaysInWeek contains the selected days
    console.log('Selected days:', this.flightDetails.noOfDaysInWeek);

    // Send flightDetails to the backend using the service
    const backendUrl = 'http://localhost:8081/flights/add'; // Replace with your backend URL

    this.http.post(backendUrl, this.flightDetails)
      .subscribe(
        (response) => {
          console.log('Flight details added successfully:', response);
          alert('Flight added successfully!!');
          this.router.navigate(['/flights']);
          
        },
        (error) => {
          console.error('Error adding flight details:', error);
          alert('Error in adding Flight');
        }
      );
  }

  private resetForm() {
    // Reset the form fields
    this.flightDetails = {
      flightNumber: '',
      source: '',
      destination: '',
      departureTime:'',
      arrivalTime:'',
      noOfDaysInWeek: []
    };
    
    // Reset selectedDays object (uncheck all checkboxes)
    for (const day of this.daysOfWeek) {
      this.selectedDays[day] = false;
    }
  }
}
