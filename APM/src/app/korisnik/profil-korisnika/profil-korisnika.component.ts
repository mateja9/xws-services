import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Korisnik } from 'app/model/Korisnik';
import { KorisnikService } from 'app/services/korisnik.services';
import { LoginService } from 'app/services/login.services';
import { Story } from "app/model/story";
import { StoryGroup } from "app/model/story";
import { Post } from "app/model/post";


@Component({
  selector: 'pm-profil-korisnika',
  templateUrl: './profil-korisnika.component.html',
  styleUrls: ['./profil-korisnika.component.css']
})
export class ProfilKorisnikaComponent implements OnInit {
  korisnik :Korisnik;
  updatedUser:Korisnik;
  request:Request;
  publicStoryGroups: StoryGroup[] = [];
  highlightStoryGroups: StoryGroup[] = [];
  posts : Post[] = [];

  constructor(private _router: Router,private loginService:LoginService,private userService: KorisnikService,) { 
    this.korisnik = new Korisnik();
    this.updatedUser = new Korisnik();
  }

  
  ngOnInit(): void {
    this.loginService.getKorisnika().subscribe({
      next: korisnik => {
        this.korisnik = korisnik;
        console.log('Korisnik', this.korisnik);
        if (this.korisnik == null) {
          this._router.navigate(["/welcome"]);
        }
        if (this.korisnik != null && this.korisnik.rola != "CLIENT") {
          this.loginService.IzlogujSe(this.request).subscribe(result => this.kraj());
        }

        this.userService.getPublicStories(korisnik.id).subscribe({
          next: (stories) => {
            stories.forEach((element) => {
              this.publicStoryGroups = this.createStoryGroups(stories);
            });
          },
        });

        this.userService.getHighlightStories(korisnik.id).subscribe({
          next: (stories) => {
            stories.forEach((element) => {
              this.highlightStoryGroups = this.createStoryGroups(stories);
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


      }
    });

  }
  edit() {

    console.log(this.korisnik);
    this.userService.update(this.korisnik).subscribe();
 //   this.refresh();
  }
  refresh(){
    window.location.reload();
  }
  kraj() {
  //  this.korisnik.rola="CLIENT";
    this._router.navigate(["/welcome"]);
  }

  odjaviSe() {
    this.loginService.IzlogujSe(this.request).subscribe(result => this.kraj());
  }

  createStoryGroups(stories:Story[]): StoryGroup[] {
    var storyGroups = [];

    stories.forEach((s) => {
      s.isVideo = s.pathOfContent.endsWith("mp4");
    });

    for (let i = 0; i < stories.length; i += 3) {
      const group = new StoryGroup();
      group.story1 = stories[i];
      if(i+1 < stories.length) {
        group.story2 = stories[i+1];
      }
      
      if(i+2 < stories.length) {
        group.story3 = stories[i+2];
      }
      
      storyGroups.push(group);
    }
    return storyGroups;
  }
}
