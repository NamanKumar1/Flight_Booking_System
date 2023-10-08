import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
  passengers: { name: string, email: string }[] = [];
  numberOfPassengers: number=0;

  flightNumber:string="";
  date:string="";

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    // Get the flight ID from the route parameter
    this.route.params.subscribe(params => {
      this.flightNumber = params['id'];
      this.date=params['date'];
    });
  }

  onSubmit() {
    const apiUrl = `http://localhost:8083/flights/book/${this.flightNumber}/${this.date}`;
    this.http.post(apiUrl, this.passengers, { responseType: 'text' })
      .subscribe(
        (response) => {
          console.log('Booking response:', response);
          if (response === 'Booking successful!') {
            // Booking was successful
            alert('Booking successful!');
            this.router.navigate(['/home']);
          } else {
            // Handle other types of responses or errors
            console.error('Unexpected response:', response);
            alert('An unexpected error occurred while booking.');
          }
        },
        (error) => {
          if (error instanceof HttpErrorResponse) {
            console.error('HTTP error occurred:', error);
            // Handle the HTTP error, e.g., show an error message to the user
            alert('HTTP error occurred while booking.');
          } else {
            console.error('An unexpected error occurred:', error);
            // Handle other types of errors, if needed
            alert('An unexpected error occurred while booking.');
          }
        }
      );
  }


  generatePassengerFields() {
    this.passengers = [];
    for (let i = 0; i < this.numberOfPassengers; i++) {
      this.passengers.push({ name: '', email: '' });
    }
  }


}
