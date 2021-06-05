import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Korisnik } from '../login/Korisnik';

import { LoginService } from '../login/login.services';

@Component({
  templateUrl: './welcome.component.html'
})
export class WelcomeComponent implements OnInit{
  public pageTitle = 'Welcome';

  request: Request;
  user: Korisnik;

  constructor(private _router: Router, private loginService: LoginService) {
    this.user = new Korisnik();
  }

  ngOnInit(): void {
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

}
