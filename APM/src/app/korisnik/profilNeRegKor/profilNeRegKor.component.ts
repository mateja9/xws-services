import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Korisnik } from 'app/model/Korisnik';
import { KorisnikService } from 'app/services/korisnik.services';
import { LoginService } from 'app/services/login.services';
import { Story } from "app/model/story";


@Component({

  templateUrl: './profilNeRegKor.html',
  
})
export class ProfilNeRegKorComponent implements OnInit {
  public pageTitle = 'NotR'; 
  korisnik :Korisnik;
    id: number;
    request:Request;
    errorMessage = '';
    stories : Story[] = [];

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
        korisnik => {
          this.korisnik = korisnik
          console.log("IMAM USERA!");

          this.userService.getPublicStories(korisnik.id).subscribe({
          next: (stories) => {
            console.log("Dobavio sam storije");
            stories.forEach((element) => {
              this.stories = stories;
              console.log("ELEMENT " + element);
            });
          },
        });

        },
      error => {
        this.errorMessage = <any>error
        console.log(this.errorMessage);
      }
    );
}
  
  refresh(){
    window.location.reload();
  }
 

}