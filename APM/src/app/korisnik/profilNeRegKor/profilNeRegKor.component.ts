import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Korisnik } from 'app/model/Korisnik';
import { KorisnikService } from 'app/services/korisnik.services';
import { LoginService } from 'app/services/login.services';
import { Story } from "app/model/story";
import { StoryGroup } from "app/model/story";
import { PostComment } from 'app/model/PostComment';


@Component({

  templateUrl: './profilNeRegKor.html',
  
})
export class ProfilNeRegKorComponent implements OnInit {
  public pageTitle = 'NotR'; 
  korisnik :Korisnik;
    id: number;
    request:Request;
    errorMessage = '';
    publicStoryGroups: StoryGroup[] = [];
    highlightStoryGroups: StoryGroup[] = [];
    posts : PostComment[] = [];

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

          this.userService.getPublicStories(korisnik.id).subscribe({
            next: (stories) => {
              stories.forEach((element) => {
                this.publicStoryGroups = this.createStoryGroups(stories);
              });
            },
          });
          this.userService.getPosts().subscribe({
            next: (posts) => {

              console.log("Dobavio sam postove");
              this.posts = posts;
              posts.forEach((p) => {
                p.post.isVideo = p.post.pathOfContent.endsWith("mp4");

                console.log(p);
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
