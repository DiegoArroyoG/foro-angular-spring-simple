import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tema } from 'src/app/model/tema';
import { GestorForosService } from 'src/app/services/gestor-foros.service';

@Component({
  selector: 'app-crear-temas',
  templateUrl: './crear-temas.component.html',
  styleUrls: ['./crear-temas.component.css']
})
export class CrearTemasComponent implements OnInit {
  tema: Tema = new Tema();

  constructor(
    private route: ActivatedRoute,
    public router: Router,
    private temasService: GestorForosService
    ) { }

  ngOnInit(): void {
    
  }
  agregarTema(): void {
    this.route.params.subscribe(params => {
      const nombre = params.nombre;
      this.temasService.agregarTema(this.tema, nombre).subscribe();
    });
  }
}
