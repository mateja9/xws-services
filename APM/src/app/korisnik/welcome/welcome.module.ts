import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';


import { WelcomeComponent } from './welcome.component';
import { RouterModule } from '@angular/router';
import { LoginService } from 'app/services/login.services';
import { ProfilKorisnikaComponent } from '../profil-korisnika/profil-korisnika.component';
import { ProfilNeRegKorComponent } from '../profilNeRegKor/profilNeRegKor.component';
import { KorisnikService } from 'app/services/korisnik.services';
import { RegistrationNoticeComponent } from '../registration-notice/registration-notice.component';


@NgModule({
  declarations: [
    WelcomeComponent, 
    ProfilKorisnikaComponent,
    ProfilNeRegKorComponent,
  
   
   ],

   imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,

    RouterModule.forChild([
      


    ]),
    FormsModule
  ],
  providers: [
    LoginService,
    KorisnikService,
  
 
  ]
})
export class WelcomeModule { }
