import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user = '';
  password = '';
  hidePass = true;
  result: any;
  session: boolean;
  message: any;

  constructor(public authService: AuthService) {}

  ngOnInit() {
    if(sessionStorage.getItem('user') === null){
      this.session = false;
    }
    else{
      this.session = true;
      this.user = sessionStorage.getItem('user');
    }
  }

  doLogin() {
    console.log(this.user + ' - ' + this.password);
    this.authService.login(this.user, this.password).subscribe(data => {
        this.message = 'Login Ok';
        sessionStorage.setItem('user', this.user);
        this.session = true;
        window.location.reload();
      }, error => {
        console.error(error);
        this.message = JSON.stringify(error);
      });
  }

  logout() {
    this.authService.logout().subscribe(data => {
        this.message = 'Logout Ok';
        sessionStorage.clear();
        this.session = false;
        this.user = '';
        window.location.reload();
      }, error => {
        console.error(error);
        this.message = JSON.stringify(error);
      });
  }
}
