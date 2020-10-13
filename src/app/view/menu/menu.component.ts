import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  userStatus: boolean;
  username: string;
  constructor(public auth: AuthService) {
    this.userStatus = this.auth.userStatus;
    this.username = this.auth.userName;
  }

  ngOnInit(): void {
  }

}
