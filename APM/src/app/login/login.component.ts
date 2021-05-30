
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Login } from './login';
import { LoginService } from './login.services';
import { Korisnik } from './Korisnik';
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

    }

    ngOnInit() {
        this.vratiKorisnika();

    }


    login() {
        this.odgovor = false;
        this.loginService.ulogujSe(this.loginZahtev).subscribe(result => this.vratiKorisnika(),
            err => this.odgovor = true
        );

    }

    vratiKorisnika() {

        this.loginService.getKorisnika().subscribe({
            next: korisnik => {
                this.korisnik = korisnik;

                console.log(this.korisnik.rola);
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