import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfilKorisnikaComponent } from './profil-korisnika.component';
import { FormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { KorisnikService } from '../services/korisnik.services';
import { ProfilNeRegKorComponent } from 'app/korisnik/profilNeRegKor.component';
import { profilKor } from './profilNeRegKor';
import { WelcomeComponent } from './welcome.component';
import { RouterModule } from '@angular/router';
import { LoginService } from 'app/services/login.services';


@NgModule({
  declarations: [
    WelcomeComponent, 
   // ProfilKorisnikaComponent,
   //ProfilNeRegKorComponent,
   ],

   imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,

    RouterModule.forChild([
      { path: 'korisnik', component: ProfilKorisnikaComponent },
      { path: 'welcome/profilNeRegKor/:id', component: ProfilNeRegKorComponent },
     
    //  { path: 'profilNeRegKor', component: ProfilNeRegKorComponent },


    ]),
    FormsModule
  ],
  providers: [
    KorisnikService,
    LoginService
 
  ]
})
export class WelcomeModule { }
