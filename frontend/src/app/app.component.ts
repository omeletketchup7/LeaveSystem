import { HttpClient } from '@angular/common/http';
import { Component, OnInit, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  id = "uid828845";
  thisYear: number | undefined;
  today: string | Date | undefined;

  selectedTabIndex = 0;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.thisYear = new Date().getFullYear();
    this.today = new Date();
    this.today = this.today.toISOString().split('T')[0];
    
  }



}