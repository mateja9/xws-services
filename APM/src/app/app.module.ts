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
import { ProfilNeRegKorModule } from './korisnik/profilNeRegKor.module';
import { WelcomeModule } from './korisnik/welcome.module';
import { ProfilNeRegKorComponent } from './korisnik/profilNeRegKor.component';
import { ProfilKorisnikaComponent } from './korisnik/profil-korisnika.component';
import { ExploreNistagramComponent } from './korisnik/exploreNistagram.component';
import { Korisnik } from './model/Korisnik';
import { KorisnikService } from './services/korisnik.services';



@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    LoginComponent,
    SignupComponent,
    ProfilNeRegKorComponent,
    ProfilKorisnikaComponent,
 ExploreNistagramComponent,
    
   
    
  
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      { path: 'welcome', component: WelcomeComponent },
      { path: 'login', component: LoginComponent },
      { path: 'signup', component: SignupComponent },
      { path: 'explore', component: ExploreNistagramComponent },
     
      { path: 'welcome/:id', component: ProfilNeRegKorComponent },
      { path: 'korisnik', component: ProfilKorisnikaComponent },
      { path: '', redirectTo: 'welcome', pathMatch: 'full' },
      { path: '**', redirectTo: 'welcome', pathMatch: 'full'},
      
      // { path: 'homePage', component: HomeComponent }

    ]),
    FormsModule
    
  ],
  providers:[LoginService, KorisnikService],
  bootstrap: [AppComponent]
})
export class AppModule { }
