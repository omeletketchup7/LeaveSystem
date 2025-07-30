import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css','../../app.component.css']
})
export class RequestComponent implements OnInit {

  thisYear: number | undefined;
  today: string | Date | undefined;

  @Input() id: string = '';

  requestFormGroup = new FormGroup({
    leaveType: new FormControl(null),
    dateStart: new FormControl(),
    dateEnd: new FormControl(),
    reason: new FormControl(),
  });

  ngOnInit(): void {
      this.thisYear = new Date().getFullYear();
    this.today = new Date();
    this.today = this.today.toISOString().split('T')[0];
  }

    constructor(private http: HttpClient) { }

  public cancelLeaveRequest() {
    if (window.confirm("คุณต้องการยกเลิกหรือไม่")) {
      this.requestFormGroup.reset();
    }
  }

  public sendLeaveRequest() {
    if (window.confirm("คุณต้องการส่งคำขอลางานหรือไม่")) {
      const requestData: any = {
        userId: this.id,
        leaveTypeId: this.requestFormGroup.get('leaveType')?.value,
        startDate: this.requestFormGroup.get('dateStart')?.value,
        endDate: this.requestFormGroup.get('dateEnd')?.value,
        reason: this.requestFormGroup.get('reason')?.value
      };
      this.http.post<any>('http://localhost:8080/api/leave-requests', requestData).toPromise().then((res) => {
        this.requestFormGroup.reset();
        // this.callGetRequest();
        alert("ส่งคำขอเรียบร้อย")
      })
    }
  }

  

}
