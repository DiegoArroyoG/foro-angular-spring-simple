import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public userStatus: boolean;
  public userName: string;
  constructor(private http: HttpClient) {
    this.userStatus = false;
    this.userName = '';
  }
  login(username: string, password: string) {
    const formHeaders = new HttpHeaders();
    formHeaders.append('Content-Type', 'application/x-www-form-urlencoded');
    const formParams = new HttpParams()
      .set('username', username)
      .set('password', password);
    this.userStatus = true;
    this.userName = username;
    return this.http.post('http://localhost:8080/login', null, {
      headers: formHeaders,
      params: formParams,
      withCredentials: true
    });
  }
  logout() {
    return this.http.post('http://localhost:8080/logout', '', {
      withCredentials: true
    });
    this.userStatus = false;
    this.userName = '';
  }
}
