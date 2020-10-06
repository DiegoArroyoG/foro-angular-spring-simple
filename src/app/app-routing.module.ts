import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './view/login/login.component';
import {ForosComponent} from './view/foros/foros.component';

const routes: Routes = [
  {
    path: '',
    component: ForosComponent,
    children: []
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
