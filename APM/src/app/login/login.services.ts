import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Login } from './login';
import { Korisnik } from './Korisnik';
import { map } from "rxjs/operators";


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
    return this.http.put("/user/logOut", request);
  }

}