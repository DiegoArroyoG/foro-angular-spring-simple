import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: string = '';
  password: string = '';

  result: any;

  message: any;

  constructor(private authService: AuthService) {}

  ngOnInit() {}

  doLogin() {
    console.log(this.user + ' - ' + this.password);
    this.authService.login(this.user, this.password).subscribe(data => {
        this.message = 'Login Ok';
      }, error => {
        console.error(error);
        this.message = JSON.stringify(error);
      });
  }

  logout() {
    this.authService.logout().subscribe(data => {
        this.message = 'Logout Ok';
      }, error => {
        console.error(error);
        this.message = JSON.stringify(error);
      });
  }
}
