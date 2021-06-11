import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { profilKor } from './profilNeRegKor';

import { LoginService } from 'app/services/login.services';
import { ProfilNeRegKorComponent } from './profilNeRegKor.component';
import { KorisnikService } from 'app/services/korisnik.services';


@NgModule({
  declarations: [ProfilNeRegKorComponent,
   // WelcomeComponent,
  //  ProfilKorisnikaComponent
   ],

  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,

    RouterModule.forChild([
   //   { path: 'korisnik', component: ProfilKorisnikaComponent },
      { path: 'korisnik/profilKor', component: ProfilNeRegKorComponent },
      { path: 'korisnik/profilKor/:id', component: ProfilNeRegKorComponent },
     
     


    ]),
    FormsModule
  ],
  providers: [
    KorisnikService,
    LoginService
 
  ]
})
export class ProfilNeRegKorModule { }
