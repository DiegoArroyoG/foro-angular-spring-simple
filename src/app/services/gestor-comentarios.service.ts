import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Comentario } from '../model/comentario';

@Injectable({
  providedIn: 'root'
})
export class GestorComentariosService {
  constructor(private http: HttpClient) {
  }
  private handleError(error: HttpErrorResponse): Observable<any> {
    console.log(error);
    return throwError('An error has occurred');
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
  puntuar(tipo: boolean, nombre_foro: any, id_tema: any, id_comentario: string){
    let url;
    url = 'http://localhost:8080/' + nombre_foro + '/' + id_tema + '/' + id_comentario;
    if (tipo === true){
        url = url + '/positivo';
    }
    else{
      url = url + '/negativo';
    }
    console.log('Url->' + url);
    return this.put(url, {});
  }
  comentar(nombre_foro: string, id_tema: string, comentario: Comentario){
    const url = `http://localhost:8080/` + nombre_foro + '/' + id_tema;
    return this.post(url, comentario);
  }
  responder(nombre_foro: any, id_tema: any, id_comentario: string, comentario: Comentario) {
    const url = `http://localhost:8080/` + nombre_foro + '/' + id_tema + '/' + id_comentario;
    return this.post(url, comentario);
  }
  aprobar(nombre: string, id_tema: string, id: string) {
    const url = `http://localhost:8080/` + nombre + '/' + id_tema + '/' + id + "/aprobar";
    return this.put(url,{});
  }

  desaprobar(nombre: string, id_tema: string, id: string) {
    const url = `http://localhost:8080/` + nombre + '/' + id_tema + '/' + id + "/desarpobar";
    return this.put(url,{});
  }
}
