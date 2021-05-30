import { Component, OnInit } from '@angular/core';
import { Korisnik } from '../login/Korisnik';
import { Router } from '@angular/router';
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
      next: admin => {
        this.user = admin;
        console.log('Korisnik', this.user);
        if (this.user == null) {
          this._router.navigate(["/welcome"]);
        }
      
        if (this.user != null && this.user.rola == "USER") {
        
          this._router.navigate(["/korisnik"]);
        }
       
      }
    });
    
  }

}
