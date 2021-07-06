import { Component, OnInit } from "@angular/core";
import { KorisnikService } from "app/services/korisnik.services";
import { Post } from "app/model/post";

@Component({
  selector: "app-posts",
  templateUrl: "./posts.component.html",
  styleUrls: ["./posts.component.css"],
})
export class PostsComponent implements OnInit {

  posts : Post[] = [];
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
  numberOfLikes:number;
  numberOfDislikes:number;

  public onlyCloseFriends = "no";
  public highlighted = "no";

  constructor(private userService: KorisnikService) {}

  ngOnInit(): void {
    
    this.userService.getPosts().subscribe({
      next: (posts) => {
        //this.vratiListuKom();
        //this.addLike(this.id, this.numberOfLikes);
         //this.addDislike(this.id, this.numberOfLikes);
        console.log("Dobavio sam postove");
        this.posts = posts;
        posts.forEach((p) => {
          p.isVideo = p.pathOfContent.endsWith("mp4");
        // this.addLike(this.id, this.numberOfLikes);
        // this.addDislike(this.id, this.numberOfLikes);
          console.log(p);
        });
      },
      
    });
  }

  vratiListuKom(postId: number){
    console.log("Vrati listu komentara  "+ postId);
    //this.postId nije Id posta koji mi treba, pokazuje undefined, 
    //napravio sam dugme pa ce za svaki post da posalje njegov id i prikaze komentare
    
    this.userService.getComments(postId).subscribe(x => this.allComments = x);
    console.log(this.allComments);
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
    })
  }

  addLike(id, numberOfLikes){
    console.log("Like u post.ts, Id:" + id + ", brojLajkova:" + numberOfLikes);
    this.userService.addLike(id, numberOfLikes);
    console.log("Post.ts, prosao poziv servisa.");
  }

  addDislike(id, numberOfDislikes){
    this.userService.addDislike(id, numberOfDislikes);
  }


  createPost() {
    console.log("UPLOAD!");
    const fd = new FormData();

    if(this.selectedFile == null) {
      return;
    }

    fd.append('file', this.selectedFile,  this.selectedFile.name);
    fd.append('location', this.location);
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
