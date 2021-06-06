import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KorisnikService } from 'app/korisnik/korisnici.services';
import { SearchUser } from 'app/korisnik/SearchUser';
import { Korisnik } from '../login/Korisnik';

import { LoginService } from '../login/login.services';

@Component({
  templateUrl: './welcome.component.html'
})
export class WelcomeComponent implements OnInit{
  public pageTitle = 'Welcome';
  allPharmacies : Korisnik[] = [];
  searchParameters  : SearchUser;
  request: Request;
  user: Korisnik;

  constructor(private _router: Router, private loginService: LoginService, private userService: KorisnikService) {
    this.user = new Korisnik();
    this.searchParameters = new SearchUser();
  }

  ngOnInit(): void {
    this.userService.getKorisnikeSve().subscribe({
      next: pharmacies => {
          this.allPharmacies = pharmacies;
      }

  });
    this.loginService.getKorisnika().subscribe({
      next: x => {
        this.user = x;
        console.log('Korisnik', this.user);
        if (this.user == null) {
          this._router.navigate(["/welcome"]);
        }
      
        if (this.user != null && this.user.rola == "CLIENT") {
        
          this._router.navigate(["/korisnik"]);
        }
       
      }
    });
    
  }
  refresh(){
    window.location.reload();
  }

  searchUser(){
    let sp = new SearchUser();
    
    if(this.searchParameters.name == undefined){
        sp.name = "all";
    } else {
        sp.name = this.searchParameters.name;
    }

    if(this.searchParameters.lastname == undefined){
        sp.lastname = "all";
    } else {
        sp.lastname = this.searchParameters.lastname;
    }

    if(this.searchParameters.username == undefined){
        sp.username = "all";
    } else {
        sp.username = this.searchParameters.username;
    }

  
    console.log(this.searchParameters);
    console.log(sp);

    this.userService.searchUser(sp).subscribe({
        next: pharmacies => {
            this.allPharmacies = pharmacies;
        }

    });
    console.log(this.allPharmacies);
}

}
