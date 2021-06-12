import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { Korisnik } from "app/model/Korisnik";
import { Login } from "app/model/Login";
import { ResetP } from "app/model/ResetP";
import { KorisnikService } from "app/services/korisnik.services";
import { LoginService } from "app/services/login.services";

@Component({
    //selector: 'login',
    templateUrl: './forgotpassword.component.html'
  })
  
  
  export class ForgotPasswordComponent implements OnInit{
    user:Korisnik;
    respnse:Response;
    request:Login;
    resetp : ResetP;
    constructor(private router:Router,private loginService:LoginService){
  
        this.resetp= new ResetP();
        this.request=new Login();
        this.user=new Korisnik();
      
      
    }
    ngOnInit(): void {
      
    }

    reset(){
        console.log(this.resetp);
  
        this.loginService.resetPassword(this.resetp).subscribe();
    }
}