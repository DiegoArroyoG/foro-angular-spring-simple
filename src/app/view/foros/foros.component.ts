import { Component, OnInit } from '@angular/core';
import {CargarForosService} from '../../services/cargar-foros.service';

@Component({
  selector: 'app-foros',
  templateUrl: './foros.component.html',
  styleUrls: ['./foros.component.css']
})
export class ForosComponent implements OnInit {
  foros = Array<object>();
  constructor(public infoForos: CargarForosService) {
  }
  ngOnInit(): void {
    this.getForos();
  }
  // tslint:disable-next-line:typedef
  getForos(){
    this.infoForos.getForos().subscribe(
      (forosGet) => {
        this.foros = [];
        forosGet.forEach((x) => {
          this.foros.push(x);
        }, err => { });
      }
    );
  }
}
