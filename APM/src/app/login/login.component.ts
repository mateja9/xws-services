
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Login } from '../model/Login';
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
      //  this.korisnik.rola="CLIENT";
        

    }

    ngOnInit() {
      ///////  this.korisnik.rola="CLIENT";
        this.vratiKorisnika();
     

    }

    refresh(){

        console.log("Idi na stranicu za obnovu sifre");
        this.router.navigate(["/forgotpassword"]);
    }
  
    login() {
        console.log(this.loginZahtev);
        //this.korisnik.rola="CLIENT";
        this.odgovor = false;
    //this.korisnik.rola = "CLIENT";
        this.loginService.ulogujSe(this.loginZahtev).subscribe(result => this.vratiKorisnika(),
            err => this.alertError()
        );

    }
    alertError() {
        alert("Wrong password and/or e-mail. Please, try again.");
    }
    vratiKorisnika() {
      //this.korisnik.rola = "CLIENT";
         this.loginService.getKorisnika().subscribe({
            next: korisnik => {
                this.korisnik = korisnik;
             // this.korisnik.rola = "CLIENT";
                
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