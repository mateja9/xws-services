
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Login } from '../model/login';
import { LoginService } from '../services/login.services';
import { Korisnik } from '../model/Korisnik';
import { Observable } from 'rxjs';



@Component({

    //selector: 'login-component',
    templateUrl: './login.html',
    //styleUrls: []

})


export class LoginComponent implements OnInit {
    korisnik: Korisnik;
    respnse: Response;
    odgovor: boolean = false;

    loginZahtev: Login;

    constructor(private router: Router, private loginService: LoginService) {
        this.loginZahtev = new Login();
        
        this.korisnik = new Korisnik();
       // this.korisnik.rola="CLIENT";
        

    }

    ngOnInit() {
        this.vratiKorisnika();
      //  this.korisnik.rola="CLIENT";

    }


    login() {
        
        this.odgovor = false;
      //  this.korisnik.rola = "CLIENT";
        this.loginService.ulogujSe(this.loginZahtev).subscribe(result => this.vratiKorisnika(),
            err => this.odgovor = true
        );

    }

    vratiKorisnika() {
      //  this.korisnik.rola = "CLIENT";
         this.loginService.getKorisnika().subscribe({
            next: korisnik => {
                this.korisnik = korisnik;
                this.korisnik.rola = "CLIENT";
                
                console.log('Prosao u login subscribe!')

               
                 if (this.korisnik.rola == "CLIENT") {
                     
                    this.router.navigate(["/korisnik"]);
                }
               
                else {
                    this.router.navigate(["/signup"]);
                }


            }

        });

    }



}