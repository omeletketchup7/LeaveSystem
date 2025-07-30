import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID, Inject } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css', '../../app.component.css']
})
export class DashboardComponent implements OnInit {

  @Input() id: string = '';

  calendarOptions: CalendarOptions | undefined;
  

  requestPending: any = [];
  myRequest: any = [];
  remainDay: number = 0;
  dayLeaveUsed: number = 0;
  
  constructor(@Inject(PLATFORM_ID) private platformId: any, private http: HttpClient) { }
  
  ngOnInit(): void {
    if (this.id != '') {
      this.callGetRequest();
      this.callGetRemain();
      this.callGetDayLeaveUsed();
      this.callGetToShowToTable();
    }
  }


  public callGetRequest() {
    this.http.get<any>(`http://localhost:8080/api/leave-requests/waitng-request-by-userid?userID=${this.id}`).toPromise().then((res) => {
      this.requestPending = res
    });
  }

  public callGetRemain() {
    this.http.get<number>(`http://localhost:8080/api/leave-requests/get-remainDay?id=${this.id}`)
      .toPromise()
      .then((res) => {
        this.remainDay = res ?? 0;
      });
  }

  public callGetDayLeaveUsed() {
    this.http.get<number>(`http://localhost:8080/api/leave-requests/get-leaveDayUsed?id=${this.id}`)
      .toPromise()
      .then((res) => {
        this.dayLeaveUsed = res ?? 0;
      });
  }

  public callGetToShowToTable() {
    this.http.get<any>(`http://localhost:8080/api/leave-requests/amount-day-dto?id=${this.id}`)
      .toPromise()
      .then((res) => {
        this.myRequest = res;
      });
  }

}
