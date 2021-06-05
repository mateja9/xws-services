import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfilKorisnikaComponent } from './profil-korisnika.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { KorisnikService } from './korisnici.services';


@NgModule({
  declarations: [ProfilKorisnikaComponent,
   ],

  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,

    RouterModule.forChild([
      { path: 'korisnik', component: ProfilKorisnikaComponent },
     
     


    ]),
    FormsModule
  ],
  providers: [
    KorisnikService
 
  ]
})
export class ProfilKorisnikaModule { }
