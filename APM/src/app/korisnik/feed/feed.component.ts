import { Component, OnInit } from "@angular/core";
import { KorisnikService } from "app/services/korisnik.services";
import { PostComment } from "app/model/PostComment";
import { Story } from "app/model/story";
import { StoryGroup } from "app/model/story";
import { Korisnik } from 'app/model/Korisnik';
import { LoginService } from 'app/services/login.services';
import { Router } from '@angular/router';
import { FollowRequest } from 'app/model/FollowRequest';

@Component({
    selector: "app-posts",
    templateUrl: "./feed.component.html",
    styleUrls: ["./feed.component.css"],
  })
  export class FeedComponent implements OnInit {
  
    posts : PostComment[] = [];
    id: number;
    postId:number;
    allComments: Comment[]=[];
    selectedFile: File = null;
    fileName = "";
    fileExtension = "";
    location = "";
    description = "";
    tag = "";
    comment = "";
    username = "";
    request:Request;
    numberOfLikes:number;
    numberOfDislikes:number;
    stories : Story[] = [];
    storyGroups: StoryGroup[] = [];
    publicStoryGroups: StoryGroup[] = [];
    highlightStoryGroups: StoryGroup[] = [];
    korisnik :Korisnik;
    followRequests : FollowRequest[] = [];

  
    public onlyCloseFriends = "no";
    public highlighted = "no";
  
    constructor(private userService: KorisnikService, private loginService:LoginService, private _router: Router) {}
  
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

      
          /*
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

        this.userService.getStoriesForFeed().subscribe({
            next: (stories) => {
              this.stories = stories;
              this.stories.forEach((s) => {
                s.isVideo = s.pathOfContent.endsWith("mp4");
              });
      
              this.createStoryGroups1();
            },
          });

          */
      
      this.userService.getPostsForFeed().subscribe({
        next: (posts) => {
  
          console.log("Dobavio sam postove");
          this.posts = posts;
          posts.forEach((p) => {
            p.post.isVideo = p.post.pathOfContent.endsWith("mp4");
  
            console.log(p);
          });
        },
        
      });

        }});

    }

    kraj() {
      //  this.korisnik.rola="CLIENT";
        this._router.navigate(["/welcome"]);
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

      createStoryGroups1() {
        this.storyGroups = [];
        console.log("KREIRAM GRUPE")
    
        this.stories.forEach((s) => {
          s.isVideo = s.pathOfContent.endsWith("mp4");
        });
    
        for (let i = 0; i < this.stories.length; i += 3) {
          const group = new StoryGroup();
          group.story1 = this.stories[i];
          if(i+1 < this.stories.length) {
            group.story2 = this.stories[i+1];
          }
          
          if(i+2 < this.stories.length) {
            group.story3 = this.stories[i+2];
          }
          
          this.storyGroups.push(group);
        }
      }


      
    addComment(id, userId, username) {
      let fd = {
        postId: id,
        autorId: userId,
        content: this.comment,
        username: username
      };
      this.userService.createComment(fd).subscribe((data) => {
        console.log(data);
        this.ngOnInit();
        this.comment = '';
      })
    }
  
    addToFavourite(postId) {
    
      this.userService.addToFavourite(+localStorage.getItem("currentUserId"), postId).subscribe((data) => 
      console.log(data));
  
  
    }
  
    addLike(id, numberOfLikes){
      console.log("Like u post.ts, Id:" + id + ", brojLajkova:" + numberOfLikes);
      this.userService.addLike(id, numberOfLikes).subscribe(res => {
        console.log(res);
        this.ngOnInit();
      });
      console.log("Post.ts, prosao poziv servisa.");
    }
  
    addDislike(id, numberOfDislikes){
      this.userService.addDislike(id, numberOfDislikes).subscribe(res => {
        console.log(res);
        this.ngOnInit();
      });
    }
    
    onFileSelected(event) {
      this.selectedFile = <File>event.target.files[0];
      this.fileName = "Data selected";
      this.fileExtension = this.selectedFile.name.split('?')[0].split('.').pop();
      console.log(this.selectedFile.name);
    }
  }
  