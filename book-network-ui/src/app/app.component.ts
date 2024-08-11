import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'book-network-ui';

  constructor() {
    console.log('AppComponent constructor');
  }

  ngOnInit(): void {
    console.log('AppComponent ngOnInit');
  }
}
