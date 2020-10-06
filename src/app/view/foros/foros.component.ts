import { Component, Input, OnInit } from '@angular/core';
import {CargarForosService} from '../../services/cargar-foros.service';
import {Foro} from '../../model/foro';

@Component({
  selector: 'app-foros',
  templateUrl: './foros.component.html',
  styleUrls: ['./foros.component.css']
})
export class ForosComponent implements OnInit {
  forosList: Foro[] = [];
  constructor(public infoForos: CargarForosService) {
  }
  ngOnInit(): void {
    this.getForos();
  }
  // tslint:disable-next-line:typedef
  getForos(){
    this.infoForos.getForos().subscribe(
      (forosGet: Foro[]) => {
        this.forosList = forosGet;
      }, error => {
        console.log(error);
    });
  }
}
