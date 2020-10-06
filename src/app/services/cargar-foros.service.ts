import { Injectable } from '@angular/core';
import {newArray} from '@angular/compiler/src/util';
import {HttpClient, HttpClientModule, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CargarForosService {
  constructor(private http: HttpClient) {
  }
  private handleError(error: HttpErrorResponse): Observable<any> {
    console.log(error);
    return throwError('An error has occurred');
  }
  private get<T>(url): Observable<T> {
    console.log('get:', url);
    return this.http
      .get<T>(url, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          Accept: 'application/json',
        }),
      })
      .pipe(
        // retry(5),
        catchError(this.handleError)
      );
  }
  getForos(): any{
    const url = '../../assets/test_archives/foros.json';
    this.get(url).subscribe(value => console.log());
    return this.get(url);
  }
}
