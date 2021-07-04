import { Component, OnInit } from "@angular/core";
import { KorisnikService } from "app/services/korisnik.services";
import { Post } from "app/model/post";

@Component({
  selector: "app-favourite",
  templateUrl: "./favourite.component.html",
  styleUrls: ["./favourite.component.css"],
})
export class FavouriteComponent implements OnInit {

  posts : Post[] = [];

  selectedFile: File = null;
  fileName = "";
  fileExtension = "";
  location = "";
  description = "";
  tag = "";
  comment = "";
  numberOfLikes = 0;
  numberOfDislikes = 0;

  public onlyCloseFriends = "no";
  public highlighted = "no";

  constructor(private userService: KorisnikService) {}

  ngOnInit(): void {
    this.userService.getFavouritePosts().subscribe({
      next: (posts) => {
        console.log("Dobavio sam postove");
        this.posts = posts;
        posts.forEach((p) => {
          p.isVideo = p.pathOfContent.endsWith("mp4");
          console.log(p);
        });
      },
    });
  }

  addComment(id, userId) {
    let fd = {
      postId: id,
      autorId: userId,
      content: this.comment
    };
    this.userService.createComment(fd).subscribe((data) => {
      console.log(data);
    })
  }

  addLike(id, numberOfLikes){
    this.userService.addLike(id, numberOfLikes+1)
  }

  addDislike(id, numberOfDislikes){
    this.userService.addDislike(id, numberOfDislikes+1)
  }


  createPost() {
    console.log("UPLOAD!");
    const fd = new FormData();

    if(this.selectedFile == null) {
      return;
    }

    fd.append('file', this.selectedFile,  this.selectedFile.name);
    fd.append('location', this.location)
    fd.append('description', this.description);
    fd.append('tag', this.tag);

    this.userService.createPost(fd).subscribe(
      (data) => {
        console.log("Post is posted.");

        this.userService.getPosts().subscribe({
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
        console.log("CREATE POST ERROR: " + error.errorMessage);
      }
      );
  }

  onFileSelected(event) {
    this.selectedFile = <File>event.target.files[0];
    this.fileName = "Data selected";
    this.fileExtension = this.selectedFile.name.split('?')[0].split('.').pop();
    console.log(this.selectedFile.name);
  }
}
