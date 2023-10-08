import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-details',
  templateUrl: './update-details.component.html',
  styleUrls: ['./update-details.component.css']
})
export class UpdateDetailsComponent implements OnInit {
  flightDetails: any = {};
  flightNumber: string = "";
  source: string = "";
  destination: string = "";
  departureTime: string = "";
  arrivalTime: string = "";
  FlightID = "";

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    // Get the flight ID from the route parameter
    this.route.params.subscribe(params => {
      this.FlightID = params['id'];
      // Fetch flight details by ID and populate the form
      this.http.get(`http://localhost:8081/flights/get/${this.FlightID}`).subscribe(data => {
        this.flightDetails = data;
        this.flightNumber = this.flightDetails.flightNumber;
        this.source = this.flightDetails.source;
        this.destination = this.flightDetails.destination;
        this.departureTime = this.flightDetails.departureTime;
        this.arrivalTime = this.flightDetails.arrivalTime;
      });
    });
  }

    UpdateRecords() {
      this.http.put(`http://localhost:8081/flights/update/${this.FlightID}`, this.flightDetails, { responseType: 'text' }).subscribe(
        (resultData: any) => {
          console.log(resultData);
          alert('Flight updated successfully');
          this.router.navigate(['/flights']);
        },
        error => {
          console.error('Error updating flight:', error);
          alert('Error updating flight. Please try again later.');
        }
      );
    }
    
  }