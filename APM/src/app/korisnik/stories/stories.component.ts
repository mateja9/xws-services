import { Component, OnInit } from "@angular/core";
import { KorisnikService } from "app/services/korisnik.services";

@Component({
  selector: "app-stories",
  templateUrl: "./stories.component.html",
  styleUrls: ["./stories.component.css"],
})
export class StoriesComponent implements OnInit {

  selectedFile: File = null;
  fileName = "";
  fileExtension = "";
  public closeFriends;
  public onlyCloseFriends : string;
  public close = "no";

  constructor(private userService: KorisnikService) {}

  ngOnInit(): void {
    this.userService.getStories().subscribe({
      next: (stories) => {
        console.log("Dobavio sam storije");
        stories.forEach((element) => {
          console.log("ELEMENT " + element);
        });
      },
    });
  }

  createStory() {
    console.log("IDEMO UPLOAD!");
    const fd = new FormData();

    if(this.selectedFile == null) {
      return;
    }

    fd.append('file', this.selectedFile,  this.selectedFile.name);
    fd.append('onlyCloseFriends', this.close);

    this.userService.createStory(fd).subscribe(
      (data) => {
        console.log("STORY IS POSTED.");
      },
      error => {
        console.log("CREATE STORY ERROR: " + error.errorMessage);
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
