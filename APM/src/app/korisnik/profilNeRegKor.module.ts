import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfilKorisnikaComponent } from './profil-korisnika.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { KorisnikService } from '../services/korisnik.services';
import { ProfilNeRegKorComponent } from 'app/korisnik/profilNeRegKor.component';
import { profilKor } from './profilNeRegKor';
import { WelcomeComponent } from './welcome.component';
import { LoginService } from 'app/services/login.services';


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
