import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfilKorisnikaComponent } from './profil-korisnika.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { ProfilNeRegKorComponent } from 'app/korisnik/profilNeRegKor/profilNeRegKor.component';
import { ExploreNistagramComponent } from '../explore/exploreNistagram.component';
import { KorisnikService } from 'app/services/korisnik.services';
import { FollowComponent } from '../follow/follow.component';


@NgModule({
  declarations: [ProfilKorisnikaComponent,
    ExploreNistagramComponent,
    FollowComponent
   ],

  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,

    RouterModule.forChild([
      { path: 'explore', component: ExploreNistagramComponent },
      { path: 'explore/:id', component: ExploreNistagramComponent },
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
