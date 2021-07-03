import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Korisnik } from 'app/model/Korisnik';
import { KorisnikService } from 'app/services/korisnik.services';
import { LoginService } from 'app/services/login.services';
import { Story } from "app/model/story";
import { HttpClient } from '@angular/common/http';
import { Post } from "app/model/post";
import { StoryGroup } from "app/model/story";

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
      publicStoryGroups: StoryGroup[] = [];
      highlightStoryGroups: StoryGroup[] = [];
      posts : Post[] = [];
      user: Korisnik;

    userFollow: any;
    followBtnValue: string = 'FOL';
    followersNumber: string;
    followingsNumber: string;

    closeFriendsBtnValue: string = 'ATF';
  
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

    showCloseFriends(): boolean {
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

    checkIsFollow() {
      let userFrom = localStorage.getItem('currentUserId');

      let dto = new Object();
      dto['userFrom'] = userFrom;
      dto['userTo'] = this.korisnik.id;
      this.userService.checkIsFollow(dto).subscribe(res => {
        console.log(res);
        this.userFollow = res;
        if(res != null && res.status === 'accepted') {
          this.followBtnValue = 'UNF';
        } else {
          this.followBtnValue = 'FOL';
        }
      })
    }

    getFollowersAndFollowing() {
      let id = this.korisnik.id;
      this.userService.getFollowersAndFollowing(id).subscribe(res => {
        console.log(res);
        this.followersNumber = res[0];
        this.followingsNumber = res[1];
        
      })
    }

    getUsers(id: number) {
      this.userService.vratiKor(id).subscribe(
          korisnik => {
            this.korisnik = korisnik
            console.log("IMAM USERA!");
            this.checkIsFollow();
            this.getFollowersAndFollowing();
            this.checkIsCloseFriend();
  
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

    onClickFollowProfile() {
      if(this.followBtnValue === 'UNF') {
        this.userService.unfollow(this.userFollow.id).subscribe(res => {
          console.log(res);
          this.followBtnValue = 'FOL';
          this.userFollow = res;
          this.getFollowersAndFollowing();
        })
      } else {
        let userFrom = localStorage.getItem('currentUserId');

        let dto = new Object();
        dto['userFrom'] = userFrom;
        dto['userTo'] = this.korisnik.id;
        this.userService.followProfile(dto).subscribe(res => {
          console.log(res);
          this.userFollow = res;
          if(res != null && res.status === 'accepted') {
            this.followBtnValue = 'UNF';
          } else {
            this.followBtnValue = 'FOL';
          }
          this.getFollowersAndFollowing();
        })
      }
    }

    onClickAddToCloseFriends() {
      let userFrom = localStorage.getItem('currentUserId');

        let dto = new Object();
        dto['user'] = userFrom;
        dto['closeFriend'] = this.korisnik.id.toString();
      this.userService.onClickAddToCloseFriends(dto).subscribe(res => {
        console.log(res);
        if(res != null && res.closeFriend === this.korisnik.id) {
          this.closeFriendsBtnValue = 'DTF';
        } else {
          this.closeFriendsBtnValue = 'ATF';
        }
      })
    }

    checkIsCloseFriend() {
      let userFrom = localStorage.getItem('currentUserId');

        let dto = new Object();
        dto['user'] = userFrom;
        dto['closeFriend'] = this.korisnik.id.toString();
      this.userService.checkIsCloseFriend(dto).subscribe(res => {
        console.log(res);
        if(res != null && res.closeFriend === this.korisnik.id) {
          this.closeFriendsBtnValue = 'DTF';
        } else {
          this.closeFriendsBtnValue = 'ATF';
        }
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
