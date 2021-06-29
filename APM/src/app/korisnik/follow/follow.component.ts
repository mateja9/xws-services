import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Korisnik } from 'app/model/Korisnik';
import { KorisnikService } from 'app/services/korisnik.services';
import { LoginService } from 'app/services/login.services';
import { Story } from "app/model/story";
import { HttpClient } from '@angular/common/http';
import { Post } from "app/model/post";


@Component({
 
  templateUrl: './follow.component.html',
  styleUrls: ['./follow.component.css']
})
export class FollowComponent implements OnInit {
    public pageTitle = 'NotR'; 
    korisnik :Korisnik;
      id: number;
      request:Request;
      errorMessage = '';
      stories : Story[] = [];
      posts : Post[] = [];
      user: Korisnik;
  
    constructor(private httpClient: HttpClient,private route: ActivatedRoute,
      private loginService: LoginService,
       private router: Router, private userService: KorisnikService) { 
      this.korisnik = new Korisnik();

  
    }

    
    showFollow(): boolean {

      if(!this.user || !this.korisnik) {
        return false;
      }


      return this.user.id != this.korisnik.id;
    }
    
    ngOnInit(): void {
      
      const param = this.route.snapshot.paramMap.get('id');
      if (param) {
          this.id = +param;
          this.getUsers(this.id);
  
      }
  
      this.loginService.getKorisnika().subscribe({
        next: x => {
          this.user = x;
        }
      });
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

          this.userService.getPublicPosts(korisnik.id).subscribe({
            next: (posts) => {
              console.log("Dobavio sam postove");
              posts.forEach((element) => {
                this.posts = posts;
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
