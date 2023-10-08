import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-get-flight',
  templateUrl: './get-flight.component.html',
  styleUrls: ['./get-flight.component.css']
})
export class GetFlightComponent {
  searchArray: any[]=[];



  constructor(private http: HttpClient, private router: Router) { 
    this.getFlights();
  }

  getFlights() {
    this.http.get(`http://localhost:8081/flights/All`)
      .subscribe((data: any) => {
        this.searchArray = data;
       
      });
  }

  editFlight(id: number) {
    // Navigate to the EditFlightComponent with the flight ID as a parameter
    this.router.navigate(['/update-details', id]);
  }

  
}
