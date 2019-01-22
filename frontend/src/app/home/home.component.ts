import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  breakpoint: number;

  constructor() {
  }

  ngOnInit() {
    this.breakpoint = (window.innerWidth <= 1024) ? 1 : 3;

  }

  onResize(event) {
    this.breakpoint = (event.target.innerWidth <= 1024) ? 1 : 3;
  }
}
