import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-temas',
  templateUrl: './temas.component.html',
  styleUrls: ['./temas.component.css']
})
export class TemasComponent implements OnInit {
  tema: Tema[] = [];
  constructor(
    private route: ActivatedRoute,
    private temasService: GestorTemasService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      let nombre = params['nombre'];
        this.temasService.getTemas(nombre).subscribe
          ((tema: Tema[]) => {
            this.tema = tema;
      }, error =>{
        console.log(error);
      });

  }

}
