import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfilKorisnikaComponent } from './profil-korisnika.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { KorisnikService } from '../services/korisnici.services';
import { ProfilNeRKor } from 'app/korisnik/profilKor.component';
import { profilKor } from './profilKor';


@NgModule({
  declarations: [ProfilKorisnikaComponent,
   ],

  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,

    RouterModule.forChild([
      { path: 'korisnik', component: ProfilKorisnikaComponent },
      { path: 'korisnik/profilKor/:id', component: ProfilNeRKor },
     
      { path: 'korisnik/profilKor', component: profilKor },


    ]),
    FormsModule
  ],
  providers: [
    KorisnikService
 
  ]
})
export class ProfilKorisnikaModule { }
