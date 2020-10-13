import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  user = '';
  session: boolean;
  constructor(public auth: AuthService) {
  }

  ngOnInit(): void {
    if(sessionStorage.getItem('user') === null){
      this.session = false;
    }
    else{
      this.session = true;
      this.user = sessionStorage.getItem('user');
    }
  }
  logout() {
    this.auth.logout().subscribe(data => {
      sessionStorage.clear();
      this.session = false;
      this.user = '';
      window.location.reload();
    }, error => {
      console.error(error);
    });
  }

}
