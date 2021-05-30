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
    //  this.pacijetUrl='http//localhost:8080/api/pacijenti';
  }

  public save(user: Korisnik) {
    return this.http.post<Korisnik>("/user/addClient", user);
  }

  public getKorisnika(): Observable<Korisnik> {
    return this.http.get<Korisnik>("/user/login/")//.pipe(
    //  map((data: Korisnik) =>
    //  data.map(
    //  (item: Korisnik) =>
    //  new Korisnik(item.ime, item.prezime,item.email,item.password,item.grad,
    //  item.drzava,item.adresa,item.telefon,item.broj_osiguranika,item.uloga)))
    //)
    //)
  }

  public ulogujSe(loginZahtev: Login) {
    return this.http.post<Response>("/user/login", loginZahtev);
  }
  public IzlogujSe(request: Request) {
    return this.http.put("/user/logOut", request);
  }

}