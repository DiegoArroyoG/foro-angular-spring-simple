import { Injectable } from '@angular/core';
import {Observable, throwError} from 'rxjs';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class GestorTemasService {
  constructor(private http: HttpClient) { }
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
  getTemas(nombre: string){
    const url = `http://localhost:8080/` + nombre;
    return this.get(url);
  }

  getTema(nombre: string, id: string) {
    const url = `http://localhost:8080/` + nombre + '/' + id;
    return this.get(url);
  }
}
