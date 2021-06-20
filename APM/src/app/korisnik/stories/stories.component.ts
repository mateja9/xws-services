import { Component, OnInit } from "@angular/core";
import { KorisnikService } from "app/services/korisnik.services";
import { Story } from "app/model/story";

@Component({
  selector: "app-stories",
  templateUrl: "./stories.component.html",
  styleUrls: ["./stories.component.css"],
})
export class StoriesComponent implements OnInit {

  stories : Story[] = [];

  selectedFile: File = null;
  fileName = "";
  fileExtension = "";
  public onlyCloseFriends = "no";
  public highlighted = "no";

  constructor(private userService: KorisnikService) {}

  ngOnInit(): void {
    this.userService.getStories().subscribe({
      next: (stories) => {
        console.log("Dobavio sam storije");
        this.stories = stories;
        stories.forEach((s) => {
          console.log("STORY " + s);
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
    fd.append('onlyCloseFriends', this.onlyCloseFriends);
    fd.append('highlighted', this.highlighted);

    this.userService.createStory(fd).subscribe(
      (data) => {
        console.log("STORY IS POSTED.");

        this.userService.getStories().subscribe({
          next: (stories) => {
            console.log("Dobavio sam storije");
            stories.forEach((element) => {
              this.stories = stories;
              console.log("ELEMENT " + element);
            });
          },
        });

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
