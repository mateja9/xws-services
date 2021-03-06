import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Korisnik } from '../model/Korisnik';
import { LoginService } from '../services/login.services';

@Component({
	selector: 'pm-signup',
	templateUrl: './signup.component.html',
	styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

	user: Korisnik;

	constructor(private router: Router, private loginServices: LoginService) {
		this.user = new Korisnik();
		//this.user.rola = "CLIENT";
	}

	ngOnInit(): void {
	}

	onSubmit() {

		//this.user.rola = "CLIENT";
		this.router.navigate(["/login"]);
	//	this.user.rola = "CLIENT";
		this.loginServices.save(this.user).subscribe(result => this.finish());
	
	}

	finish() {
		this.user.rola = "CLIENT";
		//alert("sacuvan korisnik " + this.user.name + "" + this.user.lastname)
		this.user.name = "";
		this.user.lastname = "";
        this.user.phoneNumer="";
		this.user.email = "";
        this.user.username="";
		this.user.password = "";
		
        this.user.gender="";
		this.user.website="";
		this.user.bio="";
		this.user.dateofb="";
		this.user.isPrivate=true;
		
	}
  
}
