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
  private put<T>(url, data: T): Observable<T> {
    console.log('post:', url);
    return this.http
      .put<T>(url, data, {
        withCredentials: true,
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
        }),
      })
      .pipe(catchError(this.handleError));
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
  getTemas(nombre: string){
    const url = `http://localhost:8080/` + nombre;
    return this.get(url);
  }

  getTema(nombre: string, id: string) {
    const url = `http://localhost:8080/` + nombre + '/' + id;
    return this.get(url);
  }

  aprobar(nombre: string, id: string) {
    const url = `http://localhost:8080/` + nombre + '/' + id + "/aprobar";
    return this.put(url,{});
  }

  desaprobar(nombre: string, id: string) {
    const url = `http://localhost:8080/` + nombre + '/' + id + "/desarpobar";
    return this.put(url,{});
  }

  votoPositivo(nombre: string, id: string) {
    const url = `http://localhost:8080/` + nombre + '/' + id + "/positivo";
    return this.put(url,{});
  }

  votoNegativo(nombre: string, id: string) {
    const url = `http://localhost:8080/` + nombre + '/' + id + "/negativo";
    return this.put(url,{});
  }
}
