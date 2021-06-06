import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import {
  AgmCoreModule
} from '@agm/core';

import { WelcomeComponent } from './korisnik/welcome.component';
import { LoginComponent } from './login/login.component';
import { LoginService } from './services/login.services';
import { BrowserModule } from '@angular/platform-browser';
import { SignupComponent } from './login/signup.component';
import { ProfilKorisnikaModule } from './korisnik/profil-korisnika.module';



@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    LoginComponent,
    SignupComponent,
    
  
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      { path: 'welcome', component: WelcomeComponent },
      { path: 'login', component: LoginComponent },
      { path: 'signup', component: SignupComponent },

      { path: '', redirectTo: 'welcome', pathMatch: 'full' },
      { path: '**', redirectTo: 'welcome', pathMatch: 'full'},
      
      // { path: 'homePage', component: HomeComponent }

    ]),
    FormsModule,
    ProfilKorisnikaModule
    
  ],
  providers:[LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
