import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent{
  searchArray: any[]=[];

  source: string="";
  destination: string="";
  date: string="";

  private baseUrl = 'http://localhost:8082';

  constructor(private http: HttpClient, private router: Router) { }

  searchFlights() {
    this.http.get(`${this.baseUrl}/flights/search/${this.source}/${this.destination}/${this.date}`)
      .subscribe((data: any) => {
        this.searchArray = data;
      });
  }

  BookFlight(flightNumber: String) {
    // Navigate to the EditFlightComponent with the flight ID as a parameter
   console.log(flightNumber)
   console.log(this.date)

   this.router.navigate(['/book',flightNumber,this.date]);
  }
  
}
