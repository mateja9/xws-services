import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'app/login/login.services';

import { Korisnik } from '../login/Korisnik';
import { KorisnikService } from './korisnici.services';

@Component({
  selector: 'pm-profil-korisnika',
  templateUrl: './profil-korisnika.component.html',
  styleUrls: ['./profil-korisnika.component.css']
})
export class ProfilKorisnikaComponent implements OnInit {

  constructor(private _router: Router,private loginService:LoginService,private userService: KorisnikService,) { 
    this.korisnik = new Korisnik();
    this.updatedUser = new Korisnik();
  }
  korisnik :Korisnik;
  updatedUser:Korisnik;
  request:Request;
  
  ngOnInit(): void {
    this.loginService.getKorisnika().subscribe({
      next: korisnik => {
        this.korisnik = korisnik;
        console.log('Korisnik', this.korisnik);
        if (this.korisnik == null) {
          this._router.navigate(["/welcome"]);
        }
        if (this.korisnik != null && this.korisnik.rola != "CLIENT") {
          this.loginService.IzlogujSe(this.request).subscribe(result => this.kraj());
        }
      }
    });

  }
  edit() {
    console.log("ueeeeeeeeeeeeeeee");
    console.log(this.korisnik);
    this.userService.update(this.korisnik).subscribe();
    console.log("ueeeeeeeeeeeeeeee");
    this.refresh();
  }
  refresh(){
    window.location.reload();
  }
  kraj() {
    this._router.navigate(["/welcome"]);
  }

  odjaviSe() {
    this.loginService.IzlogujSe(this.request).subscribe(result => this.kraj());
  }
}
