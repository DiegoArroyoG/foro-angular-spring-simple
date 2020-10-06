import { Component, OnInit } from '@angular/core';
import {Tema} from '../../model/tema';
import {ActivatedRoute, Router} from '@angular/router';
import {GestorTemasService} from '../../services/gestor-temas.service';

@Component({
  selector: 'app-tema',
  templateUrl: './tema.component.html',
  styleUrls: ['./tema.component.css']
})
export class TemaComponent implements OnInit {
  tema: Tema;
  list = [];
  constructor(private route: ActivatedRoute,
              private router: Router,
              private temasService: GestorTemasService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const nombre = params.nombre;
      const id = params.id_tema;
      this.temasService.getTema(nombre, id).subscribe
      ((tema: Tema) => {
        this.tema = tema;
        this.list = this.tema.comentario;
      }, error => {
        console.log(error);
      });
    });
  }

}
