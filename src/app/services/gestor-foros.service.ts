import { Injectable } from '@angular/core';
import {newArray} from '@angular/compiler/src/util';
import {HttpClient, HttpClientModule, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';
import {Tema} from '../model/tema';

@Injectable({
  providedIn: 'root'
})
export class GestorForosService {
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
        withCredentials: true,
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
  private post<T>(url, data: T): Observable<T> {
    console.log('post:', url);
    return this.http
      .post<T>(url, data, {
        withCredentials: true,
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
        }),
      })
      .pipe(catchError(this.handleError));
  }
  getForos(){
    const url = `http://localhost:8080/`;
    return this.get(url);
  }
  agregarTema(tema: Tema, nombre: string){
    const url = `http://localhost:8080/` + nombre;
    return this.post(url, tema);
  }
}
