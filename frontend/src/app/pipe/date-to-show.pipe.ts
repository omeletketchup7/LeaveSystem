import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateToShow'
})
export class DateToShowPipe implements PipeTransform {

  transform(value: string | Date): string {
    if (!value) return '';

    const date = new Date(value);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Month index starts at 0
    const year = date.getFullYear();

    return `${day}/${month}/${year}`;
  }

}
