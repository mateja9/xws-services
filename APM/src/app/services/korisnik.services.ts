import { Injectable } from "@angular/core";

import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Korisnik } from "app/model/Korisnik";
import { Story } from "app/model/story";
import { SearchUser } from "../model/SearchUser";
import { Post } from "app/model/post";
import { PostComment } from "app/model/PostComment";
import { SearchPost } from "app/model/SearchPost";


@Injectable()
export class KorisnikService{
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

  public searchPost(sp: SearchPost): Observable<Post[]> {
    return this._http.post<Post[]>("/user/searchPost", sp);
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

  public getHighlightStories(userId: number): Observable<Story[]> {
    return this._http.get<Story[]>("/user/" + userId +"/highlightStories");
  }

  public createStory(data : FormData) {
    return this._http.post("/media/stories", data, {responseType: 'text'});
  }



  public getPosts(): Observable<PostComment[]> {
    return this._http.get<PostComment[]>("/media/posts");
  }

  public getFavouritePosts(): Observable<PostComment[]> {
    return this._http.get<PostComment[]>("/media/posts/favourites/");
  }

  public addToFavourite(userId:number, postId: number) {
    return this._http.get("/media/post/addToFavourite/" + userId + "/" + postId, {responseType: 'text'});
  }

  public getPublicPosts(userId: number): Observable<PostComment[]> {
    return this._http.get<PostComment[]>("/user/" + userId +"/publicPosts");
  }

  public createPost(data : FormData) {
    return this._http.post("/media/createPost", data, {responseType: 'text'});
  }

  public addDislike (postId:number, dislikes:number){
    return this._http.get("/media/posts/dislike/" + postId + "/" + dislikes);
  }


  public addLike (postId:number, likes:number){
    console.log("U servisu, postID: "+postId+", broj lajkova: "+likes); //ispisuje
      return this._http.get("http://localhost:8080/media/posts/like/" + postId + "/" + likes);
  }

  public getComments(postId: number) : Observable<Comment[]> {
    return this._http.get<Comment[]>("/media/post/comment/" + postId);
  }

  public createComment(data) {
    return this._http.post("/media/post/comment/createComment", data);
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

  public getFollowersRequests(userId : number){
  return this._http.get<String[]>("http://localhost:8500/userFollow/getFollowersRequests/" + userId);
  }

  public acceptFollow(username : String, id : number) {
    return this._http.get<String>("http://localhost:8500/userFollow/accept/" + username + "/" + id, { responseType: 'text' as 'json' });
  }

  public rejectFollow(username : String, id : number) {
    return this._http.get<String>("http://localhost:8500/userFollow/reject/" + username + "/" + id, { responseType: 'text' as 'json' });
  }

  public getPostsForFeed(): Observable<PostComment[]> {
    return this._http.get<PostComment[]>("/media/getPostsForFeed");
  }

  public getStoriesForFeed(): Observable<Story[]> {
    return this._http.get<Story[]>("/media/getStoriesForFeed");
  }

  public changePrivacy(userId: number): Observable<any> {
    return this._http.get<any>("/user/changePrivacy/" + userId, { responseType: 'text' as 'json' });
  }

  public getUserPrivacy(userId: number) {
    return this._http.get<string>("/user/getUserPrivacy/" + userId, { responseType: 'text' as 'json' });
  }


}
