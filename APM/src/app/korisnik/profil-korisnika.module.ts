import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfilKorisnikaComponent } from './profil-korisnika.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { KorisnikService } from '../services/korisnik.services';
import { ProfilNeRegKorComponent } from 'app/korisnik/profilNeRegKor.component';
import { profilKor } from './profilNeRegKor';
import { ExploreNistagramComponent } from './exploreNistagram.component';


@NgModule({
  declarations: [ProfilKorisnikaComponent,
    ExploreNistagramComponent
   ],

  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,

    RouterModule.forChild([
      { path: 'explore', component: ExploreNistagramComponent },
      { path: 'korisnik', component: ProfilKorisnikaComponent },
      { path: 'profilNeRegKor/:id', component: ProfilNeRegKorComponent },
      { path: 'profilNeRegKor', component: ProfilNeRegKorComponent },
      


    ]),
    FormsModule
  ],
  providers: [
    KorisnikService
 
  ]
})
export class ProfilKorisnikaModule { }
