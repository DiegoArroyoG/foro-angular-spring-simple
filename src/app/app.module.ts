import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { MenuComponent } from './view/menu/menu.component';
import { LoginComponent } from './view/login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule} from './material/material.module';
import { ForosComponent } from './view/foros/foros.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { TemasComponent } from './view/temas/temas.component';
import { CrearTemasComponent } from './view/crear-temas/crear-temas.component';
import { TemaComponent } from './view/tema/tema.component';
import { PresentationViewComponent } from './view/presentation-view/presentation-view.component';
import { ModerarComponent } from './view/moderar/moderar.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    LoginComponent,
    ForosComponent,
    TemasComponent,
    CrearTemasComponent,
    TemaComponent,
    PresentationViewComponent,
    ModerarComponent,
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
