import { Injectable } from "@angular/core";

import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Korisnik } from "app/model/Korisnik";
import { Story } from "app/model/story";
import { SearchUser } from "../model/SearchUser";
import { Post } from "app/model/post";

@Injectable()
export class KorisnikService {
  adapter: any;
  constructor(private _http: HttpClient) {}
  

  public getKorisnika(): Observable<Korisnik> {
    return this._http.get<Korisnik>("/user/login");
  }

  public getKorisnikeSve(): Observable<Korisnik[]> {
    return this._http.get<Korisnik[]>("/user/getAllUsers");
  }
  public update(updatedUser: Korisnik) {
    return this._http.put("/user/edit", updatedUser);
  }

  public searchUser(sp: SearchUser): Observable<Korisnik[]> {
    return this._http.post<Korisnik[]>("/user/searchUser", sp);
  }
  public vratiKor(id: number): Observable<Korisnik> {
    return this._http.get<Korisnik>("/user/" + id);
  }



  public getStories(): Observable<Story[]> {
    return this._http.get<Story[]>("/media/stories");
  }

  public getPublicStories(userId: number): Observable<Story[]> {
    return this._http.get<Story[]>("/user/" + userId +"/publicStories");
  }

  public createStory(data : FormData) {
    return this._http.post("/media/stories", data, {responseType: 'text'});
  }



  public getPosts(): Observable<Post[]> {
    return this._http.get<Post[]>("/media/posts");
  }
  public getPublicPosts(userId: number): Observable<Post[]> {
    return this._http.get<Post[]>("/user/" + userId +"/publicPosts");
  }

  public createPost(data : FormData) {
    return this._http.post("/media/createPost", data, {responseType: 'text'});
  }

  public getComments(postId: number) : Observable<Comment[]> {
    return this._http.get<Comment[]>("/media/comment/" + postId);
  }

  public createComment(data) {
    return this._http.post("/media/comment/createComment", data);
  }

  public followProfile(dto) {
    return this._http.post<any>("http://localhost:8500/userFollow/createUserFollow", dto);
  }

  public checkIsFollow(dto) {
    return this._http.post<any>("http://localhost:8500/userFollow/checkIsFollow", dto);
  }

  public unfollow(id) {
    return this._http.get<any>("http://localhost:8500/userFollow/unfollow/" + id);
  }

  public getFollowersAndFollowing(id) {
    return this._http.get<any>("http://localhost:8500/userFollow/getFollowersAndFollowing/" + id);
  }

  public onClickAddToCloseFriends(dto) {
    return this._http.post<any>("http://localhost:8500/closeFriend/add", dto);
  }

  public checkIsCloseFriend(dto) {
    return this._http.post<any>("http://localhost:8500/closeFriend/checkIsCloseFriend", dto);
  }

}
