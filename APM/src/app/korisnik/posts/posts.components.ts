import { Component, OnInit } from "@angular/core";
import { KorisnikService } from "app/services/korisnik.services";
import { PostComment } from "app/model/PostComment";

@Component({
  selector: "app-posts",
  templateUrl: "./posts.component.html",
  styleUrls: ["./posts.component.css"],
})
export class PostsComponent implements OnInit {

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
  numberOfLikes:number;
  numberOfDislikes:number;

  public onlyCloseFriends = "no";
  public highlighted = "no";

  constructor(private userService: KorisnikService) {}

  ngOnInit(): void {
    
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
        this.location = '';
        this.description = '';
        this.tag = '';
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
