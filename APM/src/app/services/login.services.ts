import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Login } from '../model/Login';
import { Korisnik } from '../model/Korisnik';
import { map } from "rxjs/operators";
import { ResetP } from "app/model/ResetP";


@Injectable()
export class LoginService {
  private pacijetUrl: string;
  adapter: any;


  constructor(private http: HttpClient) {
  
  }

  public save(user: Korisnik) {
    return this.http.post<Korisnik>("/user/addUser", user);
  }

  public getKorisnika(): Observable<Korisnik> {
    return this.http.get<Korisnik>("/user/login/")//.pipe(
 
  }

  public ulogujSe(loginZahtev: Login) {
    return this.http.post<Response>("/user/login", loginZahtev);
  }

  public IzlogujSe(request: Request) {
    localStorage.removeItem("currentUserId");
    return this.http.post("/user/logOut", request);
  }
  public resetPassword(resetp: ResetP) {
    return this.http.post("/user/resetPassword", resetp);
  }
}