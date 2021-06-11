import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Korisnik } from 'app/model/Korisnik';
import { LoginService } from 'app/services/login.services';

@Component({
    selector: 'registration-notice',
    templateUrl: './registration-notice.component.html'
  })

  
export class RegistrationNoticeComponent{
    user: Korisnik;
  
    constructor(private router: Router, private loginService: LoginService) {
    }
  
    getUser() {
  
      this.loginService.getKorisnika().subscribe({
        next: user => {
          this.user = user;
  
          console.log(this.user);
  
   
            this.router.navigate(["/profil-korisnika.component.html"]);
  
         
  
  
        }
  
      });
  
  
  
  
    }
  
    }