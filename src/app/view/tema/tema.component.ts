import { Component, OnInit } from '@angular/core';
import {Tema} from '../../model/tema';
import {ActivatedRoute, Router} from '@angular/router';
import {GestorTemasService} from '../../services/gestor-temas.service';
import { Comentario } from 'src/app/model/comentario';
import { GestorComentariosService } from 'src/app/services/gestor-comentarios.service';

@Component({
  selector: 'app-tema',
  templateUrl: './tema.component.html',
  styleUrls: ['./tema.component.css']
})
export class TemaComponent implements OnInit {
  tema: Tema;
  comentario: Comentario = new Comentario();
  comentarioRespuesta: Comentario = new Comentario();
  list = [];
  respuestas: string[] = [];
  constructor(private route: ActivatedRoute,
              private router: Router,
              private temasService: GestorTemasService,
              private comenatriosService: GestorComentariosService) { }

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
  comentar(): void {
    this.route.params.subscribe(params => {
      const nombre = params.nombre;
      const id = params.id_tema;
      this.comenatriosService.comentar(nombre, id, this.comentario).subscribe();
    });
  }
  responder(id_comentario: string): void {
    this.comentarioRespuesta.contenido = this.respuestas[id_comentario];
    this.route.params.subscribe(params => {
      const nombre = params.nombre;
      const id = params.id_tema;
      this.comenatriosService.responder(nombre, id, id_comentario, this.comentarioRespuesta).subscribe();
    });
  }
  puntuarPositivo(id_comentario: string): void{
    this.route.params.subscribe(params => {
      const nombre = params.nombre;
      const id = params.id_tema;
      this.comenatriosService.puntuar(true, nombre, id, id_comentario).subscribe(
        params2 => {
          window.location.reload();
        }
      );
    });
  }
  puntuarNegativo(id_comentario: string): void{
    this.route.params.subscribe(params => {
      const nombre = params.nombre;
      const id = params.id_tema;
      this.comenatriosService.puntuar(false, nombre, id, id_comentario).subscribe(
        params2 => {
          window.location.reload();
        }
      );
    });
  }
  votoPositivo(): void{
    this.route.params.subscribe(params => {
      const nombre = params.nombre;
      const id = params.id_tema;
      this.temasService.votoPositivo(nombre, id).subscribe(params2 => {
          window.location.reload();
        }
      );
    });
  }
  votoNegativo(): void{
    this.route.params.subscribe(params => {
      const nombre = params.nombre;
      const id = params.id_tema;
      this.temasService.votoNegativo(nombre, id).subscribe(params2 => {
        window.location.reload();
      });
    });
  }
  verificar(id: string): void{
    this.route.params.subscribe(params => {
      const nombre = params.nombre;
      const id_tema = params.id_tema;
      this.comenatriosService.aprobar(nombre, id_tema, id).subscribe();
    });
  }
  denegar(id: string): void{
    this.route.params.subscribe(params => {
      const nombre = params.nombre;
      const id_tema = params.id_tema;
      this.comenatriosService.desaprobar(nombre, id_tema, id).subscribe();
    });
  }
}
