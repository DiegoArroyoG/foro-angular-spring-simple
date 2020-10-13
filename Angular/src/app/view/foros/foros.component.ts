import { Component, Input, OnInit } from '@angular/core';
import {GestorForosService} from '../../services/gestor-foros.service';
import {Foro} from '../../model/foro';

@Component({
  selector: 'app-foros',
  templateUrl: './foros.component.html',
  styleUrls: ['./foros.component.css']
})
export class ForosComponent implements OnInit {
  forosList: Foro[] = [];
  foro: Foro = new Foro;
  constructor(public forosService: GestorForosService) {
  }
  ngOnInit(): void {
    this.getForos();
  }
  // tslint:disable-next-line:typedef
  getForos(){
    this.forosService.getForos().subscribe(
      (forosGet: Foro[]) => {
        this.forosList = forosGet;
      }, error => {
        console.log(error);
    });
  }
  crearForo(){
    this.forosService.agregarForo(this.foro).subscribe(data =>{
      window.location.reload();
    }, error => window.location.reload());
  }
}
