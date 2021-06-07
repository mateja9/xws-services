import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'app/services/login.services';

import { Korisnik } from '../model/Korisnik';
import { KorisnikService } from '../services/korisnik.services';

@Component({

  templateUrl: './profilNeRegKor.html',
  
})
export class ProfilNeRegKorComponent implements OnInit {
  public pageTitle = 'NotR'; 
  korisnik :Korisnik;
    id: number;
    request:Request;
    errorMessage = '';

  constructor(private httpClient: HttpClient,private route: ActivatedRoute, private router: Router, private userService: KorisnikService) { 
    this.korisnik = new Korisnik();
 
  }
 
  
  ngOnInit(): void {
    
    const param = this.route.snapshot.paramMap.get('id');
    if (param) {
        this.id = +param;
        this.getUsers(this.id);

    }

  }
  getUsers(id: number) {
    this.userService.vratiKor(id).subscribe(
        korisnik => this.korisnik= korisnik,
      error => this.errorMessage = <any>error
    );
}
  
  refresh(){
    window.location.reload();
  }
 

}