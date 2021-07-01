import { Component, OnInit } from "@angular/core";
import { KorisnikService } from "app/services/korisnik.services";
import { Story } from "app/model/story";
import { StoryGroup } from "app/model/story";

@Component({
  selector: "app-stories",
  templateUrl: "./stories.component.html",
  styleUrls: ["./stories.component.css"],
})
export class StoriesComponent implements OnInit {

  stories : Story[] = [];
  storyGroups: StoryGroup[] = [];

  selectedFile: File = null;
  fileName = "";
  fileExtension = "";
  public onlyCloseFriends = "no";
  public highlighted = "no";

  constructor(private userService: KorisnikService) {}

  ngOnInit(): void {
    this.userService.getStories().subscribe({
      next: (stories) => {
        this.stories = stories;
        this.stories.forEach((s) => {
          s.isVideo = s.pathOfContent.endsWith("mp4");
        });

        this.createStoryGroups();
      },
    });
  }

  createStory() {
    console.log("CREATE STORY INVOKED!");
    const fd = new FormData();

    if(this.selectedFile == null) {
      return;
    }

    fd.append('file', this.selectedFile,  this.selectedFile.name);
    fd.append('onlyCloseFriends', this.onlyCloseFriends);
    fd.append('highlighted', this.highlighted); 

    this.userService.createStory(fd).subscribe(
      (data) => {
        console.log("STORY CREATED.");
        this.selectedFile = null;

        this.userService.getStories().subscribe({
          next: (stories) => {
            stories.forEach((element) => {
              this.stories = stories;
              this.createStoryGroups();
            });
          },
        });

      },
      error => {
        console.log("CREATE STORY ERROR: " + error.errorMessage);
      }
      );
  }

  createStoryGroups() {
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

  onFileSelected(event) {
    console.log("ON FILE SELECT");
    this.selectedFile = <File>event.target.files[0];
    this.fileName = "Data selected";
    this.fileExtension = this.selectedFile.name.split('?')[0].split('.').pop();
    console.log(this.selectedFile.name);
    console.log("ON FILE SELECT EXIT");
  }
}
