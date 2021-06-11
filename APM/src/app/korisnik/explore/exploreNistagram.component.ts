import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Korisnik } from 'app/model/Korisnik';
import { SearchUser } from 'app/model/SearchUser';
import { KorisnikService } from 'app/services/korisnik.services';
import { LoginService } from 'app/services/login.services';



@Component({
 // selector: 'exploreNistagram',
  templateUrl: './exploreNistagram.component.html',
  //styleUrls: ['./exploreNistagram.component.css']
})
export class ExploreNistagramComponent implements OnInit{
    public pageTitle = 'Explore';
    allPharmacies : Korisnik[] = [];
    searchParameters  : SearchUser;
    request: Request;
    user: Korisnik;
  
    constructor(private route: ActivatedRoute, private _router: Router, private loginService: LoginService, private userService: KorisnikService) {
      this.user = new Korisnik();
      this.searchParameters = new SearchUser();
    }
  
    ngOnInit(): void {
      this.userService.getKorisnikeSve().subscribe({
        next: pharmacies => { this.allPharmacies = pharmacies;
            this.userService.vratiKor(this.user.id).subscribe({
             next : us => { this.user = us;
            
        
   
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
  });
  }
  
  })
  
  
  
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
  
      if(this.searchParameters.email == undefined){
          sp.email = "all";
      } else {
          sp.email = this.searchParameters.email;
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

 
  kraj() {
    this._router.navigate(["/welcome"]);
  }

  odjaviSe() {
    this.loginService.IzlogujSe(this.request).subscribe(result => this.kraj());
  }
}
