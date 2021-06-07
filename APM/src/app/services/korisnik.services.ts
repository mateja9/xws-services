import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Korisnik } from 'app/model/Korisnik';
import { SearchUser } from '../model/SearchUser';


@Injectable()
export class KorisnikService {
    adapter: any;
    constructor(private _http: HttpClient) { }

 public getKorisnika(): Observable<Korisnik> {
        return this._http.get<Korisnik>("/user/login");
    }

   public getKorisnikeSve(): Observable<Korisnik[]> {
        return this._http.get<Korisnik[]>("/user/getAllUsers")
    }
  public  update(updatedUser: Korisnik) {
        return this._http.put("/user/edit", updatedUser);
      }
    
  public searchUser(sp: SearchUser): Observable<Korisnik[]> {
    return this._http.post<Korisnik[]>("/user/searchUser", sp);
  }
  public vratiKor(id:number):Observable<Korisnik>{
    return this._http.get<Korisnik>("/user/"+id);
}

    }

