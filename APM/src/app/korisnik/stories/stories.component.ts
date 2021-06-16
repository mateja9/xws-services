import { Component, OnInit } from "@angular/core";
import { KorisnikService } from "app/services/korisnik.services";

@Component({
  selector: "app-stories",
  templateUrl: "./stories.component.html",
  styleUrls: ["./stories.component.css"],
})
export class StoriesComponent implements OnInit {
  constructor(private userService: KorisnikService) {}

  ngOnInit(): void {
    this.userService.getStories().subscribe({
      next: (stories) => {
        console.log("Dobavio sam storije");
      },
    });
  }
}
