// CORE DEPS
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';


// MATERIAL DESIGN MODULES
import 'hammerjs';
import {MaterialModule} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {APP_ROUTES} from './app.routes';

import {AppComponent} from './app.component';
import {UserComponent} from './user/user.component';
import {UserService} from "./shared/user.service";

@NgModule({
  declarations: [
    AppComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule.forRoot(),
    HttpModule,
    RouterModule,
    APP_ROUTES
  ],
  providers: [
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
