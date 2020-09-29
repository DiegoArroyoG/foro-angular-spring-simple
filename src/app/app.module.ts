import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ErdlmNavBarComponent } from './presentation/erdlm-nav-bar/erdlm-nav-bar.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './presentation/login/login.component';
import { CommentBoxComponent } from './presentation/comment-box/comment-box.component';
import { HomeComponent } from './presentation/home/home.component';
import { ForoComponent } from './presentation/foro/foro.component';
import { TemaComponent } from './presentation/tema/tema.component';
@NgModule({
  declarations: [
    AppComponent,
    ErdlmNavBarComponent,
    LoginComponent,
    CommentBoxComponent,
    HomeComponent,
    ForoComponent,
    TemaComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
