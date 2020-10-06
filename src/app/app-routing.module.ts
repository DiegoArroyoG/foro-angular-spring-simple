import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './view/login/login.component';
import {ForosComponent} from './view/foros/foros.component';
import { TemasComponent } from './view/temas/temas.component';
import {TemaComponent} from './view/tema/tema.component';
import {CrearTemasComponent} from './view/crear-temas/crear-temas.component';

const routes: Routes = [
  {
    path: '',
    component: ForosComponent,
    children: []
  },
  {
    path: ':nombre',
    component: TemasComponent,
    children: []
  },
  {
    path: ':nombre/:id_tema',
    component: TemaComponent,
    children: []
  },
  {
    path: ':nombre/crearTema',
    component: CrearTemasComponent,
    children: []
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
