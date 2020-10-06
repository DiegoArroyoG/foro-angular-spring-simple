import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {GestorTemasService} from '../../services/gestor-temas.service';
import {Tema} from '../../model/tema';

@Component({
  selector: 'app-temas',
  templateUrl: './temas.component.html',
  styleUrls: ['./temas.component.css']
})
export class TemasComponent implements OnInit {
  tema: Tema[] = [];
  constructor(
    private route: ActivatedRoute,
    public router: Router,
    private temasService: GestorTemasService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const nombre = params.nombre;
      this.temasService.getTemas(nombre).subscribe
      ((tema: Tema[]) => {
        this.tema = tema;
      }, error => {
        console.log(error);
      });
    });
  }
}
