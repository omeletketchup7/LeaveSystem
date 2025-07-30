import { Component } from '@angular/core';
import { ChartConfiguration, ChartOptions } from 'chart.js';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css','../../app.component.css']
})
export class HistoryComponent {

  public barChartType: 'bar' = 'bar';

  public barChartData: ChartConfiguration<'bar'>['data'] = {
    labels: ['ม.ค.', 'ก.พ.', 'มี.ค.', 'เม.ย.', 'พ.ค.'],
    datasets: [
      { data: [10, 20, 30, 40, 50], label: 'ยอดขาย' }
    ]
  };

  public barChartOptions: ChartOptions<'bar'> = {
    responsive: true,
  };

}
