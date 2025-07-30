import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-approving',
  templateUrl: './approving.component.html',
  styleUrls: ['./approving.component.css','../../app.component.css']
})
export class ApprovingComponent implements OnInit {
  
  requestPending: any = [];

    @Input() id: string = '';

      ngOnInit(): void {
    this.callGetRequest();
  }

    constructor(private http: HttpClient) { }

    public callGetRequest() {
    this.http.get<any>('http://localhost:8080/api/leave-requests/waitng-request').toPromise().then((res) => {
      this.requestPending = res
    });
  }
  
  
  public approved(id: string , comment: string) {
    if(comment == ''){
      comment = "ไม่มีความเห็น"
    }
    if (window.confirm("คุณต้องการอนุมัติการลานี้หรือไม่")) {
      this.http.post<any>(`http://localhost:8080/api/leave-requests/approve-waitng-request?id=${id}`,comment).toPromise().then((res) => {
        this.callGetRequest();
        alert("อนุมัติแล้วเรียบร้อย")
      });
    }
  }
  
  public rejected(id: string , comment: string) {
    if(comment == ''){
      comment = "ไม่มีความเห็น"
    }
    if (window.confirm("คุณต้องการปฏิเสธการลานี้หรือไม่")) {
      this.http.post<any>(`http://localhost:8080/api/leave-requests/reject-waitng-request?id=${id}`,comment).toPromise().then((res) => {
        this.callGetRequest();
        alert("ปฏิเสธการลาเรียบร้อย")
      });
    }
  }

}
