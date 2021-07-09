import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Korisnik } from 'app/model/Korisnik';
import { KorisnikService } from 'app/services/korisnik.services';
import { LoginService } from 'app/services/login.services';
import { Story } from "app/model/story";
import { StoryGroup } from "app/model/story";
import { Post } from "app/model/post";
import { FollowRequest } from 'app/model/FollowRequest';
import { PostComment } from 'app/model/PostComment';


@Component({
  selector: 'pm-profil-korisnika',
  templateUrl: './profil-korisnika.component.html',
  styleUrls: ['./profil-korisnika.component.css']
})
export class ProfilKorisnikaComponent implements OnInit {
  korisnik :Korisnik;
  updatedUser:Korisnik;
  posts : PostComment[] = [];
  request:Request;
  publicStoryGroups: StoryGroup[] = [];
  highlightStoryGroups: StoryGroup[] = [];

  followRequests : FollowRequest[] = [];
  privacy: string = 'sda';
  comment = "";
  allComments: Comment[]=[];
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

      

        this.userService.getFollowersRequests(korisnik.id).subscribe((res: String[]) => {
          console.log(korisnik);
          this.followRequests = res;
        })

        this.getUserPrivacy();

      }
      
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

  }
  vratiListuKom(postId: number){
    console.log("Vrati listu komentara  "+ postId);
    //this.postId nije Id posta koji mi treba, pokazuje undefined, 
    //napravio sam dugme pa ce za svaki post da posalje njegov id i prikaze komentare
    
    // this.userService.getComments(postId).subscribe(x => this.allComments = x);
    // console.log(this.allComments);
  }
  
  addComment(id) {
    let fd = {
      postId: id,
      autorId: +localStorage.getItem('currentUserId'),
      content: this.comment,
      username: localStorage.getItem('currentUsername'),
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
  getUserPrivacy() {
    this.userService.getUserPrivacy(+localStorage.getItem('currentUserId')).subscribe(res => {
      this.privacy = res;
    })
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


  acceptFollow(username : String) {
   let  id = +localStorage.getItem('currentUserId');
    this.userService.acceptFollow(username, id).subscribe(res => {
      this.ngOnInit();
    })
  }


  rejectFollow(username : String) {
    let  id = +localStorage.getItem('currentUserId');
     this.userService.rejectFollow(username, id).subscribe(res => {
       this.ngOnInit();
     })
   }

   changePrivacy() {
    let  userId = +localStorage.getItem('currentUserId');
     this.userService.changePrivacy(userId).subscribe(res => {
       this.privacy = res;
     })
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
